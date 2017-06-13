package net.sf.mmm.game.engine.field;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.mmm.game.engine.Game;
import net.sf.mmm.game.engine.asset.GameAsset;
import net.sf.mmm.game.engine.border.GameBorder;
import net.sf.mmm.game.engine.border.GameBorderType;
import net.sf.mmm.game.engine.border.GameBorderTypeWall;
import net.sf.mmm.game.engine.direction.GameDirection;
import net.sf.mmm.game.engine.figure.GameAttributeFiguresAdvanced;
import net.sf.mmm.game.engine.figure.GameFigure;
import net.sf.mmm.game.engine.item.GameAttributePushItem;
import net.sf.mmm.game.engine.item.GamePickItem;
import net.sf.mmm.game.engine.item.GamePushItem;
import net.sf.mmm.game.engine.level.GameLevel;
import net.sf.mmm.game.engine.object.GameTypedObjectWithItems;
import net.sf.mmm.game.engine.player.GamePlayer;
import net.sf.mmm.game.engine.properties.GamePropertyValueInt;

/**
 * A single field on the {@link #getLevel() level}. Such {@link GameLevel} is a two-dimensional area divided into
 * {@link GameField}s. Each {@link GameField} has {@link GameBorder}s that can be navigated via
 * {@link #getBorder(GameDirection)} and also {@link #getField(GameDirection)}.
 */
public class GameField extends GameTypedObjectWithItems implements GameAttributePushItem, GameAttributeFiguresAdvanced {

  private final GameLevel level;

  private final Map<GameDirection, GameBorder> direction2borderMap;

  private final List<GameFigure> figures;

  private final List<GameFigure> figuresView;

  private GameFieldType type;

  private GamePushItem pushItem;

  /**
   * The constructor.
   *
   * @param level the owning {@link GameLevel}.
   */
  public GameField(GameLevel level) {
    this(level, GameFieldType.NORMAL);
  }

  /**
   * The constructor.
   *
   * @param level the owning {@link GameLevel}.
   * @param type the {@link #getType() type}.
   */
  public GameField(GameLevel level, GameFieldType type) {
    super();
    this.level = level;
    this.type = type;
    this.direction2borderMap = new HashMap<>();
    this.figures = new ArrayList<>();
    this.figuresView = Collections.unmodifiableList(this.figures);
  }

  @Override
  public Game getGame() {

    return this.level.getGame();
  }

  /**
   * @return the {@link GameLevel} owning this {@link GameField}.
   */
  public GameLevel getLevel() {

    return this.level;
  }

  @Override
  public GameFieldType getType() {

    if (this.type == null) {
      return GameFieldType.NORMAL;
    }
    return this.type;
  }

  /**
   * @param type the new value of {@link #getType()}.
   */
  public void setType(GameFieldType type) {

    this.type = type;
  }

  /**
   * @return the {@link GamePushItem} that is on this {@link GameField} or {@code null} if there is no such item here.
   */
  @Override
  public GamePushItem getPushItem() {

    return this.pushItem;
  }

  /**
   * @param pushItem the new value of {@link #getPushItem()}.
   * @return {@code true} if the {@link GamePushItem} has been set successfully, {@code false} otherwise.
   */
  @Override
  public boolean setPushItem(GamePushItem pushItem) {

    if (this.pushItem == pushItem) {
      return true;
    }
    boolean success;
    if (this.pushItem == null) {
      success = this.type.addAsset(pushItem);
    } else if (pushItem == null) {
      success = this.type.removeAsset(this.pushItem);
    } else {
      success = false; // PlayField can only hold a single push item
    }
    if (success) {
      this.pushItem = pushItem;
      if (pushItem != null) {
        pushItem.setLocation(this, pushItem.getPosition(), false);
      }
    }
    return success;
  }

  /**
   * @return the {@link GameFigure}s that are {@link GameFigure#getLocation() currently located} on this
   *         {@link GameField}.
   * @see GameFigure#getLocation()
   */
  @Override
  public List<GameFigure> getFigures() {

    return this.figuresView;
  }

  /**
   * @return {@code true} if any {@link GameFigure} of a {@link GamePlayer#isHuman() human} {@link GamePlayer} is
   *         currently on this {@link GameField}, {@code false} otherwise.
   */
  public boolean hasHumanFigure() {

    return this.figures.stream().anyMatch(x -> x.getPlayer().isHuman());
  }

  @Override
  public boolean canAddFigure(GameFigure figure) {

    int maxFigures = GamePropertyValueInt.MAX_FIGURES.get(this);
    int figureCount = this.figures.size();
    if (figureCount >= maxFigures) {
      return false;
    }
    return this.type.canAddAsset(figure);
  }

  @Override
  public boolean addFigure(GameFigure figure) {

    GameField oldLocation = figure.getLocation();
    if ((oldLocation == this) || (this.figures.contains(figure))) {
      return true;
    }
    if (!canAddFigure(figure)) {
      return false;
    }
    if (oldLocation != null) {
      boolean success = oldLocation.removeFigure(figure, false);
      if (!success) {
        return false;
      }
    }
    this.figures.add(figure);
    figure.setLocation(this, figure.getPosition(), false);
    return true;
  }

  @Override
  public boolean removeFigure(GameFigure figure, boolean updateLocation) {

    boolean success = this.figures.remove(figure);
    if (success) {
      if (updateLocation) {
        figure.setLocation(null);
      }
    }
    return success;
  }

  /**
   * @param direction the {@link GameDirection} to move.
   * @return the {@link GameField} reached by moving in the given {@link GameDirection}.
   */
  public GameField getField(GameDirection direction) {

    if (direction.isCombined()) {
      GameField field = this;
      GameBorder border = null;
      for (GameDirection atomicDirection : direction.getCombinations()) {
        assert (!atomicDirection.isCombined());
        border = field.getBorder(atomicDirection);
        field = border.getField(atomicDirection);
      }
      return field;
    } else {
      return getBorder(direction).getField(direction);
    }
  }

  /**
   * @param direction the {@link GameDirection} to move.
   * @param count the number of times to move in the given {@link GameDirection}.
   * @param stopAtEnd {@code true} to stop at the {@link GameField} that has a {@link #createWall(GameDirection)
   *        terminating wall} in the given {@link GameDirection}, {@code false} to return {@code null} in such case.
   * @return the resulting {@link GameField}. May be {@code null}.
   */
  public GameField getField(GameDirection direction, int count, boolean stopAtEnd) {

    GameField field = this;
    for (int i = 0; i < count; i++) {
      GameField nextField = field.getField(direction);
      if (nextField == null) {
        if (stopAtEnd) {
          return field;
        } else {
          return null;
        }
      }
      field = nextField;
    }
    return field;
  }

  /**
   * @param direction the {@link GameDirection}. Must not be {@code null} or {@link GameDirection#isCombined()
   *        combined}.
   * @return the {@link GameBorder} for the given {@link GameDirection}.
   */
  public GameBorder getBorder(GameDirection direction) {

    GameBorder playBorder = this.direction2borderMap.get(direction);
    if ((playBorder == null) && (direction != null) && direction.isCombined()) {
      GameField field = this;
      for (GameDirection dir : direction.getCombinations()) {
        if (field == null) {
          return null;
        }
        playBorder = field.direction2borderMap.get(dir);
        if (playBorder == null) {
          return null;
        }
        field = playBorder.getField(dir);
      }
    }
    return playBorder;
  }

  /**
   * {@link #createWall(GameDirection) Creates walls} in all non {@link GameDirection#isNatural() natural} and non
   * {@link GameDirection#isCombined() combined} {@link GameDirection}s. Useful to initialize the
   * {@link GameLevel#getStartField() start field}.
   */
  public void initEdge() {

    List<GameDirection> directions = this.level.getGame().getDirections();
    for (GameDirection direction : directions) {
      if (!direction.isCombined() && !direction.isNatural()) {
        createWall(direction);
      }
    }
  }

  /**
   * @param borderType the {@link GameBorderType}.
   * @param direction the {@link GameDirection}.
   * @return the {@link GameField} next to this one in the given {@link GameDirection} that has been bi-directional
   *         connected with this field by a {@link GameBorder} of the given {@link GameBorderType}. Will be all created
   *         and connected if not exists. Otherwise only the existing {@link GameField} is returned.
   */
  public GameField createBorder(GameBorderType borderType, GameDirection direction) {

    GameBorder border = this.direction2borderMap.get(direction);
    if (border != null) {
      GameBorderType existingBorderType = border.getType();
      if ((existingBorderType == null) && (borderType != null)) {
        border.setType(borderType);
      } else if (existingBorderType != borderType) {
        throw new IllegalStateException(
            "Border with type " + existingBorderType + " already exists in direction " + direction + " - cannot create border of type " + borderType);
      }
      return border.getField(direction);
    }
    GameField targetField = new GameField(this.level);
    createBorder(borderType, direction, targetField);
    return targetField;
  }

  /**
   * @param borderType the {@link GameBorderType}.
   * @param direction the {@link GameDirection}.
   * @param targetField the {@link GameBorder#getTargetField() target field}.
   */
  public void createBorder(GameBorderType borderType, GameDirection direction, GameField targetField) {

    assert (targetField.level == this.level);
    GameBorder border = GameBorder.of(this, direction, targetField, borderType);
    setBorder(border, direction);
    targetField.setBorder(border, direction.getInverse());
  }

  /**
   * @param direction the {@link GameDirection} where to create a {@link GameBorderTypeWall wall}
   *        {@link #getBorder(GameDirection) border}.
   */
  public void createWall(GameDirection direction) {

    assert !direction.isCombined();
    GameBorder border = this.direction2borderMap.get(direction);
    if (border == null) {
      border = GameBorder.of(this, direction, null, GameBorderTypeWall.get());
      setBorder(border, direction);
    } else {
      if (!(border.getType() instanceof GameBorderTypeWall)) {
        throw new IllegalStateException(
            "Border in direction " + direction + " already existed as " + border + " and cannot be replaced with " + GameBorderTypeWall.get());
      }
    }
  }

  private void setBorder(GameBorder border, GameDirection direction) {

    GameBorder duplicate = this.direction2borderMap.put(direction, border);
    if (duplicate != null) {
      throw new IllegalStateException("Border in direction " + direction + " already existed as " + duplicate + " and has been replaced with " + border);
    }
  }

  @Override
  public boolean canAddAsset(GameAsset<?> asset) {

    if (asset instanceof GamePickItem) {
      return canAddItem((GamePickItem) asset);
    } else if (asset instanceof GameFigure) {
      return canAddFigure((GameFigure) asset);
    } else {
      return this.type.addAsset(asset);
    }
  }

  @Override
  public boolean addAsset(GameAsset<?> asset) {

    if (asset instanceof GamePushItem) {
      return setPushItem((GamePushItem) asset);
    } else if (asset instanceof GamePickItem) {
      return addItem((GamePickItem) asset);
    } else if (asset instanceof GameFigure) {
      return addFigure((GameFigure) asset);
    } else {
      return this.type.addAsset(asset);
    }
  }

  @Override
  public boolean removeAsset(GameAsset<?> asset, boolean updateLocation) {

    if (asset instanceof GamePushItem) {
      return setPushItem(null);
    } else if (asset instanceof GamePickItem) {
      return removeItem((GamePickItem) asset, updateLocation);
    } else {
      return this.type.removeAsset(asset, updateLocation);
    }
  }

}
