package io.github.ghosthopper.figure;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import io.github.ghosthopper.asset.AbstractPlayAsset;
import io.github.ghosthopper.asset.PlayAssetPositionEvent;
import io.github.ghosthopper.border.PlayBorder;
import io.github.ghosthopper.field.PlayField;
import io.github.ghosthopper.game.PlayGame;
import io.github.ghosthopper.item.PlayPickItem;
import io.github.ghosthopper.item.PlayPushItem;
import io.github.ghosthopper.move.PlayAttributeDirection;
import io.github.ghosthopper.move.PlayDirection;
import io.github.ghosthopper.player.Player;
import io.github.ghosthopper.position.PlayPosition;
import io.github.ghosthopper.properties.PlayPropertyValueDouble;
import io.github.ghosthopper.properties.PlayPropertyValueInt;

/**
 * Any figure of an {@link Player#getFigures() owning} {@link Player}. Each {@link PlayFigure} has a {@link #getType()
 * type} that represents its characteristics and influences its visualization in the UI of the game. For the current
 * moment in time of the {@link PlayGame} each {@link PlayFigure} is {@link #getLocation() located on} a specific
 * {@link PlayField}.
 */
public class PlayFigure extends AbstractPlayAsset<PlayField> implements PlayAttributeDirection {

  private final Player player;

  private final PlayFigureType type;

  private PlayField location;

  private PlayDirection direction;

  private PlayFigureGroup group;

  /**
   * The constructor.
   *
   * @param player - see {@link #getPlayer()}.
   * @param type - see {@link #getType()}.
   */
  public PlayFigure(Player player, PlayFigureType type) {
    super();
    this.player = player;
    this.type = type;
    if (player != null) {
      setColor(player.getColor());
    }
  }

  @Override
  public PlayFigureType getType() {

    return this.type;
  }

  @Override
  protected PlayAssetPositionEvent<?> createPositionEvent(PlayPosition oldPosition, PlayPosition newPosition) {

    return new PlayFigurePositionEvent(oldPosition, this, newPosition);
  }

  /**
   * @return the {@link Player} owning this {@link PlayFigure}.
   */
  public Player getPlayer() {

    return this.player;
  }

  /**
   * @return the {@link PlayFigureGroup} owning this {@link PlayFigure} or {@code null} if this {@link PlayFigure} is
   *         not grouped.
   */
  public PlayFigureGroup getGroup() {

    return this.group;
  }

  void setGroup(PlayFigureGroup group) {

    if (this.group == group) {
      return;
    }
    PlayFigureGroup old = this.group;
    this.group = group;
    PlayFigureGroup eventGroup;
    boolean added;
    if (group != null) {
      eventGroup = group;
      added = true;
    } else {
      eventGroup = old;
      added = false;
    }
    getGame().sendEvent(new PlayFigureGroupEvent(this, eventGroup, added));
  }

  /**
   * @return {@code true} if this {@link PlayFigure} has no {@link #getGroup() group} (solo) or is the leader (first
   *         figure) of its {@link #getGroup() group}.
   */
  public boolean isGroupLead() {

    if (this.group == null) {
      return true;
    }
    List<PlayFigure> figures = this.group.getFigures();
    if (figures.isEmpty()) {
      return true; // Actually illegal state, but lets be tolerant...
    }
    if (figures.get(0) == this) {
      return true;
    }
    return false;
  }

  @Override
  public PlayGame getGame() {

    return this.player.getGame();
  }

  @Override
  public PlayField getLocation() {

    return this.location;
  }

  @Override
  public boolean setLocation(PlayField location, boolean addOrRemove) {

    if (this.location == location) {
      return true;
    }
    boolean success = true;
    PlayField oldLocation = this.location;
    if ((location == null) && addOrRemove) {
      success = oldLocation.removeFigure(this, false);
    }
    if ((location != null) && addOrRemove) {
      success = location.addFigure(this);
    }
    this.location = location;
    if (success) {
      getGame().sendEvent(new PlayFigureMoveEvent(oldLocation, this, location));
    }
    return success;
  }

  /**
   * @return {@code true} if this is the {@link PlayGame#getCurrentFigure() current figure}, {@code false} otherwise.
   */
  public boolean isCurrentFigure() {

    return getGame().getCurrentFigure() == this;
  }

  /**
   * @return {@code true} if this is an active {@link PlayFigure} (the {@link PlayGame#getCurrentFigure() current
   *         figure} or in its {@link #getGroup() group}), {@code false} otherwise.
   */
  public boolean isActiveFigure() {

    PlayFigure currentFigure = getGame().getCurrentFigure();
    if (currentFigure == this) {
      return true;
    }
    if ((currentFigure.group != null) && currentFigure.group.getFigures().contains(this)) {
      return true;
    }
    return false;
  }

  /**
   * @return the current {@link PlayDirection} this {@link PlayFigure} is pointing to. May be {@code null} if
   *         unspecified.
   */
  @Override
  public PlayDirection getDirection() {

    return this.direction;
  }

  /**
   * @param direction the new value of {@link #getDirection()}.
   */
  public void setDirection(PlayDirection direction) {

    this.direction = direction;
  }

  /**
   * @return the new {@link #getLocation()} after this move or {@code null} if the move was not possible and therefore
   *         failed.
   */
  public PlayField move() {

    return move(this.direction);
  }

  /**
   * @param clockwise {@code true} if figure should turn clockwise, {@code false} otherwise (opposite direction).
   * @return the new {@link PlayDirection}. May be {@code null} if {@link #getDirection() current direction} was already
   *         {@code null}.
   */
  public PlayDirection turn(boolean clockwise) {

    if (this.direction == null) {
      return null;
    }
    this.direction = this.direction.turn(clockwise);
    return this.direction;
  }

  /**
   * @param dir the {@link PlayDirection} to move to.
   * @return the new {@link #getLocation()} after this move or {@code null} if the move was not possible and therefore
   *         failed.
   */
  public PlayField move(PlayDirection dir) {

    if (this.location == null) {
      return null;
    }
    if ((this.group != null) && (this.group.getFigures().size() > 1)) {
      return moveGrouped(dir);
    }
    PlayBorder border = this.location.getBorder(dir);
    if ((border == null) || !border.canPass(this)) {
      return null;
    }
    PlayField targetField = border.getField(dir);
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
    return targetField;
  }

  private PlayField moveGrouped(PlayDirection dir) {

    PlayBorder border = this.location.getBorder(dir);
    if (border == null) {
      return null;
    }
    Set<PlayFigure> figuresGroup = new HashSet<>(this.group.getFigures());
    Set<PlayFigure> figuresUngroup = new HashSet<>();
    assert (figuresGroup.contains(this));

    if (!moveGroupedPassBorder(border, figuresGroup, figuresUngroup, false)) {
      return null;
    }
    PlayField targetField = border.getField(dir);
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
    for (PlayFigure figure : figuresUngroup) {
      this.group.removeFigure(figure);
    }
    for (PlayFigure figure : figuresGroup) {
      figure.setLocation(targetField);
    }
    return targetField;
  }

  private boolean moveGroupedPassBorder(PlayBorder border, Set<PlayFigure> figuresGroup, Set<PlayFigure> figuresUngroup, boolean pass) {

    Iterator<PlayFigure> iterator = figuresGroup.iterator();
    while (iterator.hasNext()) {
      PlayFigure figure = iterator.next();
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

  private boolean moveGroupedCanMoveToField(PlayField targetField, Set<PlayFigure> figuresGroup, Set<PlayFigure> figuresUngroup) {

    int maxFigures = PlayPropertyValueInt.MAX_FIGURES.get(targetField);
    int figureCount = targetField.getFigures().size();
    Iterator<PlayFigure> iterator = figuresGroup.iterator();
    while (iterator.hasNext()) {
      PlayFigure figure = iterator.next();
      if (targetField.canAddFigure(figure) && (figureCount < maxFigures)) {
        figureCount++;
      } else {
        figuresUngroup.add(figure);
        iterator.remove();
      }
    }
    return !figuresGroup.isEmpty();
  }

  private boolean push(PlayField sourceField, PlayDirection dir, Set<PlayFigure> figuresGroup) {

    PlayPushItem item = sourceField.getPushItem();
    if (item == null) {
      return true;
    }
    return push(item, sourceField, dir, figuresGroup, 0);
  }

  private boolean push(PlayPushItem item, PlayField sourceField, PlayDirection dir, Set<PlayFigure> figuresGroup, double weight) {

    PlayBorder border = sourceField.getBorder(dir);
    if (!border.canPass(item)) {
      return false;
    }
    PlayField targetField = border.getField(dir);
    if ((targetField == null) || !targetField.canAddAsset(item) || targetField.hasHumanFigure()) {
      return false;
    }
    double itemWeight = item.getWeight();
    double totalWeight = weight + itemWeight;
    PlayPushItem nextItem = targetField.getPushItem();
    boolean success;
    if (nextItem == null) {
      double maxWeight;
      if (figuresGroup == null) {
        maxWeight = PlayPropertyValueDouble.MAX_WEIGHT.get(this);
      } else {
        maxWeight = 0;
        for (PlayFigure figure : figuresGroup) {
          double mw = PlayPropertyValueDouble.MAX_WEIGHT.get(figure);
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
   * @return the result of {@link #pickItem(PlayPickItem)} invoked for the top-most item of the {@link #getLocation()
   *         current field}.
   */
  public PlayPickItem pickItem() {

    if (this.location == null) {
      return null;
    }
    if (this.location.getItems().isEmpty()) {
      return null;
    }
    int itemIndex = this.location.getItems().size() - 1;
    PlayPickItem item = this.location.getItems().get(itemIndex);
    return pickItem(item, itemIndex);
  }

  /**
   * @param item the {@link PlayPickItem} to pick up.
   * @return the {@link PlayPickItem} that has been picked up from the {@link #getLocation() field} this
   *         {@link PlayFigure} is on, {@code null} otherwise (no {@link #getLocation() field}, no
   *         {@link PlayField#getItems() item}, {@link PlayPropertyValueInt#MAX_ITEMS full}, or
   *         {@link PlayPropertyValueDouble#MAX_WEIGHT overloaded}).
   */
  public PlayPickItem pickItem(PlayPickItem item) {

    if (this.location == null) {
      return null;
    }
    int itemIndex = this.location.getItems().indexOf(item);
    return pickItem(item, itemIndex);
  }

  private PlayPickItem pickItem(PlayPickItem item, int itemIndex) {

    if ((itemIndex >= 0) && item.getType().isPickable(this)) {
      if (item.setLocation(this)) {
        return item;
      }
    }
    return null;
  }

  /**
   * @return the {@link PlayPickItem} that has been dropped from the {@link #getItems() inventory} to the
   *         {@link #getLocation() field} this {@link PlayFigure} is on, {@code null} otherwise (no
   *         {@link #getLocation() field}, no {@link #getItems() item in inventory}, etc.).
   */
  public PlayPickItem dropItem() {

    if (this.location == null) {
      return null;
    }
    List<PlayPickItem> items = getItems();
    if (items.isEmpty()) {
      return null;
    }
    int itemIndex = items.size() - 1;
    PlayPickItem item = items.get(itemIndex);
    return dropItem(item, itemIndex);
  }

  /**
   * @param item the {@link PlayPickItem} to pick up.
   * @return the {@link PlayPickItem} that has been dropped from the {@link #getItems() inventory} to the
   *         {@link #getLocation() field} this {@link PlayFigure} is on, {@code null} otherwise (no
   *         {@link #getLocation() field}, item not in {@link #getItems() inventory}, etc.).
   */
  public PlayPickItem dropItem(PlayPickItem item) {

    if (this.location == null) {
      return null;
    }
    int itemIndex = getItems().indexOf(item);
    return dropItem(item, itemIndex);
  }

  private PlayPickItem dropItem(PlayPickItem item, int itemIndex) {

    if ((itemIndex >= 0) && item.getType().isDroppable(this.location)) {
      if (this.location.addItem(item)) {
        return item;
      }
    }
    return null;
  }

}
