package net.sf.mmm.game.engine.figure;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import net.sf.mmm.game.engine.Game;
import net.sf.mmm.game.engine.asset.GameAssetBase;
import net.sf.mmm.game.engine.border.GameBorder;
import net.sf.mmm.game.engine.direction.GameAttributeDirection;
import net.sf.mmm.game.engine.direction.GameDirection;
import net.sf.mmm.game.engine.field.GameField;
import net.sf.mmm.game.engine.item.GamePickItem;
import net.sf.mmm.game.engine.item.GamePushItem;
import net.sf.mmm.game.engine.player.GamePlayer;
import net.sf.mmm.game.engine.position.GamePosition;
import net.sf.mmm.game.engine.properties.GamePropertyValueDouble;
import net.sf.mmm.game.engine.properties.GamePropertyValueInt;

/**
 * Any figure of an {@link GamePlayer#getFigures() owning} {@link GamePlayer}. Each {@link GameFigure} has a {@link #getType()
 * type} that represents its characteristics and influences its visualization in the UI of the game. For the current
 * moment in time of the {@link Game} each {@link GameFigure} is {@link #getLocation() located on} a specific
 * {@link GameField}.
 */
public class GameFigure extends GameAssetBase<GameField> implements GameAttributeDirection {

  private final GamePlayer player;

  private final GameFigureType type;

  private GameDirection direction;

  private GameFigureGroup group;

  /**
   * The constructor.
   *
   * @param player - see {@link #getPlayer()}.
   * @param type - see {@link #getType()}.
   */
  public GameFigure(GamePlayer player, GameFigureType type) {
    super();
    this.player = player;
    this.type = type;
    this.direction = GameDirection.NORTH;
    if (player != null) {
      setColor(player.getColor());
    }
  }

  @Override
  public GameFigureType getType() {

    return this.type;
  }

  @Override
  protected GameFigureMoveEvent createMoveEvent(GameField oldLocation, GamePosition oldPosition, GameField newLocation, GamePosition newPosition) {

    return new GameFigureMoveEvent(oldLocation, oldPosition, this, newLocation, newPosition);
  }

  /**
   * @return the {@link GamePlayer} owning this {@link GameFigure}.
   */
  public GamePlayer getPlayer() {

    return this.player;
  }

  /**
   * @return the {@link GameFigureGroup} owning this {@link GameFigure} or {@code null} if this {@link GameFigure} is
   *         not grouped.
   */
  public GameFigureGroup getGroup() {

    return this.group;
  }

  void setGroup(GameFigureGroup group) {

    if (this.group == group) {
      return;
    }
    GameFigureGroup old = this.group;
    this.group = group;
    GameFigureGroup eventGroup;
    boolean added;
    if (group != null) {
      eventGroup = group;
      added = true;
    } else {
      eventGroup = old;
      added = false;
    }
    getGame().sendEvent(new GameFigureGroupEvent(this, eventGroup, added));
  }

  /**
   * @return {@code true} if this {@link GameFigure} has no {@link #getGroup() group} (solo) or is the leader (first
   *         figure) of its {@link #getGroup() group}.
   */
  public boolean isGroupLead() {

    if (this.group == null) {
      return true;
    }
    List<GameFigure> figures = this.group.getFigures();
    if (figures.isEmpty()) {
      return true; // Actually illegal state, but lets be tolerant...
    }
    if (figures.get(0) == this) {
      return true;
    }
    return false;
  }

  @Override
  public Game getGame() {

    return this.player.getGame();
  }

  /**
   * @return {@code true} if this is the {@link Game#getCurrentFigure() current figure}, {@code false} otherwise.
   */
  public boolean isCurrentFigure() {

    return getGame().getCurrentFigure() == this;
  }

  /**
   * @return {@code true} if this is an active {@link GameFigure} (the {@link Game#getCurrentFigure() current
   *         figure} or in its {@link #getGroup() group}), {@code false} otherwise.
   */
  public boolean isActiveFigure() {

    GameFigure currentFigure = getGame().getCurrentFigure();
    if (currentFigure == this) {
      return true;
    }
    if ((currentFigure.group != null) && currentFigure.group.getFigures().contains(this)) {
      return true;
    }
    return false;
  }

  /**
   * @return the current {@link GameDirection} this {@link GameFigure} is pointing to. May be {@code null} if
   *         unspecified.
   */
  @Override
  public GameDirection getDirection() {

    return this.direction;
  }

  /**
   * @param direction the new value of {@link #getDirection()}.
   */
  public void setDirection(GameDirection direction) {

    Objects.requireNonNull(direction, "direction");
    GameDirection old = this.direction;
    this.direction = direction;
    getGame().sendEvent(new GameFigureDirectionEvent(old, this, this.direction));
  }

  /**
   * @return the new {@link #getLocation()} after this move or {@code null} if the move was not possible and therefore
   *         failed.
   */
  public GameField move() {

    return move(this.direction);
  }

  /**
   * @param clockwise {@code true} if figure should rotate clockwise, {@code false} otherwise (opposite direction).
   * @return the new {@link GameDirection}.
   */
  public GameDirection rotate(boolean clockwise) {

    GameDirection result = rotateSingle(clockwise);
    if (this.group != null) {
      for (GameFigure figure : this.group.getFigures()) {
        if (figure != this) {
          figure.rotateSingle(clockwise);
        }
      }
    }
    return result;
  }

  private GameDirection rotateSingle(boolean clockwise) {

    if (this.direction == null) {
      this.direction = GameDirection.NORTH;
    }
    setDirection(this.direction.rotate(clockwise));
    setPosition(getPosition().rotate(clockwise));
    return this.direction;
  }

  /**
   * @param dir the {@link GameDirection} to move to.
   * @return the new {@link #getLocation()} after this move or {@code null} if the move was not possible and therefore
   *         failed.
   */
  public GameField move(GameDirection dir) {

    GameField location = getLocation();
    if (location == null) {
      return null;
    }
    if ((this.group != null) && (this.group.getFigures().size() > 1)) {
      return moveGrouped(dir);
    }
    GameBorder border = location.getBorder(dir);
    if ((border == null) || !border.canPass(this)) {
      return null;
    }
    GameField targetField = border.getField(dir);
    if (targetField == null) {
      return null;
    }
    if (!targetField.canAddFigure(this)) {
      return null;
    }
    if (!push(targetField, dir, null)) {
      return null;
    }
    if (!border.pass(this)) {
      return null;
    }
    setLocation(targetField);
    if (getGame().isAutoPickItem()) {
      GamePickItem item;
      do {
        item = pickItem();
      } while (item != null);
    }
    return targetField;
  }

  private GameField moveGrouped(GameDirection dir) {

    GameField location = getLocation();
    GameBorder border = location.getBorder(dir);
    if (border == null) {
      return null;
    }
    Set<GameFigure> figuresGroup = new HashSet<>(this.group.getFigures());
    Set<GameFigure> figuresUngroup = new HashSet<>();
    assert (figuresGroup.contains(this));

    if (!moveGroupedPassBorder(border, figuresGroup, figuresUngroup, false)) {
      return null;
    }
    GameField targetField = border.getField(dir);
    if (targetField == null) {
      return null;
    }
    if (!moveGroupedCanMoveToField(targetField, figuresGroup, figuresUngroup)) {
      return null;
    }
    if (!push(targetField, dir, figuresGroup)) {
      return null;
    }
    if (!moveGroupedPassBorder(border, figuresGroup, figuresUngroup, true)) {
      return null;
    }
    for (GameFigure figure : figuresUngroup) {
      this.group.removeFigure(figure);
    }
    for (GameFigure figure : figuresGroup) {
      figure.setLocation(targetField);
    }
    return targetField;
  }

  private boolean moveGroupedPassBorder(GameBorder border, Set<GameFigure> figuresGroup, Set<GameFigure> figuresUngroup, boolean pass) {

    Iterator<GameFigure> iterator = figuresGroup.iterator();
    while (iterator.hasNext()) {
      GameFigure figure = iterator.next();
      boolean success;
      if (pass) {
        success = border.pass(figure);
      } else {
        success = border.canPass(figure);
      }
      if (!success) {
        figuresUngroup.add(figure);
        iterator.remove();
      }
    }
    return !figuresGroup.isEmpty();
  }

  private boolean moveGroupedCanMoveToField(GameField targetField, Set<GameFigure> figuresGroup, Set<GameFigure> figuresUngroup) {

    int maxFigures = GamePropertyValueInt.MAX_FIGURES.get(targetField);
    int figureCount = targetField.getFigures().size();
    Iterator<GameFigure> iterator = figuresGroup.iterator();
    while (iterator.hasNext()) {
      GameFigure figure = iterator.next();
      if (targetField.canAddFigure(figure) && (figureCount < maxFigures)) {
        figureCount++;
      } else {
        figuresUngroup.add(figure);
        iterator.remove();
      }
    }
    return !figuresGroup.isEmpty();
  }

  private boolean push(GameField sourceField, GameDirection dir, Set<GameFigure> figuresGroup) {

    GamePushItem item = sourceField.getPushItem();
    if (item == null) {
      return true;
    }
    return push(item, sourceField, dir, figuresGroup, 0);
  }

  private boolean push(GamePushItem item, GameField sourceField, GameDirection dir, Set<GameFigure> figuresGroup, double weight) {

    GameBorder border = sourceField.getBorder(dir);
    if (!border.canPass(item)) {
      return false;
    }
    GameField targetField = border.getField(dir);
    if ((targetField == null) || !targetField.canAddAsset(item) || targetField.hasHumanFigure()) {
      return false;
    }
    double itemWeight = item.getWeight();
    double totalWeight = weight + itemWeight;
    GamePushItem nextItem = targetField.getPushItem();
    boolean success;
    if (nextItem == null) {
      double maxWeight;
      if (figuresGroup == null) {
        maxWeight = GamePropertyValueDouble.MAX_WEIGHT.get(this);
      } else {
        maxWeight = 0;
        for (GameFigure figure : figuresGroup) {
          double mw = GamePropertyValueDouble.MAX_WEIGHT.get(figure);
          if (maxWeight == 0) {
            maxWeight = mw;
          } else if (maxWeight != Integer.MAX_VALUE) {
            maxWeight += mw;
          }
        }
      }
      success = (totalWeight < maxWeight);
    } else {
      success = push(nextItem, targetField, dir, figuresGroup, totalWeight);
    }
    if (success) {
      targetField.setPushItem(item);
      sourceField.setPushItem(null);
    }
    return success;
  }

  /**
   * @return the result of {@link #pickItem(GamePickItem)} invoked for the top-most item of the {@link #getLocation()
   *         current field}.
   */
  public GamePickItem pickItem() {

    GameField location = getLocation();
    if (location == null) {
      return null;
    }
    if (location.getItems().isEmpty()) {
      return null;
    }
    int itemIndex = location.getItems().size() - 1;
    GamePickItem item = location.getItems().get(itemIndex);
    return pickItem(item, itemIndex);
  }

  /**
   * @param item the {@link GamePickItem} to pick up.
   * @return the {@link GamePickItem} that has been picked up from the {@link #getLocation() field} this
   *         {@link GameFigure} is on, {@code null} otherwise (no {@link #getLocation() field}, no
   *         {@link GameField#getItems() item}, {@link GamePropertyValueInt#MAX_ITEMS full}, or
   *         {@link GamePropertyValueDouble#MAX_WEIGHT overloaded}).
   */
  public GamePickItem pickItem(GamePickItem item) {

    GameField location = getLocation();
    if (location == null) {
      return null;
    }
    int itemIndex = location.getItems().indexOf(item);
    return pickItem(item, itemIndex);
  }

  private GamePickItem pickItem(GamePickItem item, int itemIndex) {

    if ((itemIndex >= 0) && item.getType().isPickable(this)) {
      if (item.setLocation(this)) {
        return item;
      }
    }
    return null;
  }

  /**
   * @return the {@link GamePickItem} that has been dropped from the {@link #getItems() inventory} to the
   *         {@link #getLocation() field} this {@link GameFigure} is on, {@code null} otherwise (no
   *         {@link #getLocation() field}, no {@link #getItems() item in inventory}, etc.).
   */
  public GamePickItem dropItem() {

    GameField location = getLocation();
    if (location == null) {
      return null;
    }
    List<GamePickItem> items = getItems();
    if (items.isEmpty()) {
      return null;
    }
    int itemIndex = items.size() - 1;
    GamePickItem item = items.get(itemIndex);
    return dropItem(item, itemIndex);
  }

  /**
   * @param item the {@link GamePickItem} to pick up.
   * @return the {@link GamePickItem} that has been dropped from the {@link #getItems() inventory} to the
   *         {@link #getLocation() field} this {@link GameFigure} is on, {@code null} otherwise (no
   *         {@link #getLocation() field}, item not in {@link #getItems() inventory}, etc.).
   */
  public GamePickItem dropItem(GamePickItem item) {

    GameField location = getLocation();
    if (location == null) {
      return null;
    }
    int itemIndex = getItems().indexOf(item);
    return dropItem(item, itemIndex);
  }

  private GamePickItem dropItem(GamePickItem item, int itemIndex) {

    GameField location = getLocation();
    if ((itemIndex >= 0) && item.getType().isDroppable(location)) {
      if (location.addItem(item)) {
        return item;
      }
    }
    return null;
  }

}
