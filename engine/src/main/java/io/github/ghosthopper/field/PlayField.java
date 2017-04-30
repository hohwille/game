package io.github.ghosthopper.field;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.github.ghosthopper.asset.PlayAsset;
import io.github.ghosthopper.asset.PlayAttributeAsset;
import io.github.ghosthopper.border.PlayBorder;
import io.github.ghosthopper.border.PlayBorderType;
import io.github.ghosthopper.border.PlayBorderTypeWall;
import io.github.ghosthopper.figure.PlayAttributeFiguresAdvanced;
import io.github.ghosthopper.figure.PlayFigure;
import io.github.ghosthopper.game.PlayGame;
import io.github.ghosthopper.item.PlayAttributePushItem;
import io.github.ghosthopper.item.PlayPickItem;
import io.github.ghosthopper.item.PlayPushItem;
import io.github.ghosthopper.level.PlayLevel;
import io.github.ghosthopper.move.PlayDirection;
import io.github.ghosthopper.object.PlayTypedObjectWithItems;
import io.github.ghosthopper.player.Player;
import io.github.ghosthopper.properties.PlayPropertyValueInt;

/**
 * A single field on the {@link #getLevel() level}. Such {@link PlayLevel} is a two-dimensional area divided into
 * {@link PlayField}s. Each {@link PlayField} has {@link PlayBorder}s that can be navigated via
 * {@link #getBorder(PlayDirection)} and also {@link #getField(PlayDirection)}.
 */
public class PlayField extends PlayTypedObjectWithItems implements PlayAttributePushItem, PlayAttributeAsset, PlayAttributeFiguresAdvanced {

  private final PlayLevel level;

  private final Map<PlayDirection, PlayBorder> direction2borderMap;

  private final List<PlayFigure> figures;

  private final List<PlayFigure> figuresView;

  private PlayFieldType type;

  private PlayPushItem pushItem;

  /**
   * The constructor.
   *
   * @param level the owning {@link PlayLevel}.
   */
  public PlayField(PlayLevel level) {
    this(level, PlayFieldType.NORMAL);
  }

  /**
   * The constructor.
   *
   * @param level the owning {@link PlayLevel}.
   * @param type the {@link #getType() type}.
   */
  public PlayField(PlayLevel level, PlayFieldType type) {
    super();
    this.level = level;
    this.type = type;
    this.direction2borderMap = new HashMap<>();
    this.figures = new ArrayList<>();
    this.figuresView = Collections.unmodifiableList(this.figures);
  }

  @Override
  public PlayGame getGame() {

    return this.level.getGame();
  }

  /**
   * @return the {@link PlayLevel} owning this {@link PlayField}.
   */
  public PlayLevel getLevel() {

    return this.level;
  }

  @Override
  public PlayFieldType getType() {

    if (this.type == null) {
      return PlayFieldType.NORMAL;
    }
    return this.type;
  }

  /**
   * @param type the new value of {@link #getType()}.
   */
  public void setType(PlayFieldType type) {

    this.type = type;
  }

  /**
   * @return the {@link PlayPushItem} that is on this {@link PlayField} or {@code null} if there is no such item here.
   */
  @Override
  public PlayPushItem getPushItem() {

    return this.pushItem;
  }

  /**
   * @param pushItem the new value of {@link #getPushItem()}.
   * @return {@code true} if the {@link PlayPushItem} has been set successfully, {@code false} otherwise.
   */
  public boolean setPushItem(PlayPushItem pushItem) {

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
   * @return the {@link PlayFigure}s that are {@link PlayFigure#getLocation() currently located} on this
   *         {@link PlayField}.
   * @see PlayFigure#getLocation()
   */
  @Override
  public List<PlayFigure> getFigures() {

    return this.figuresView;
  }

  /**
   * @return {@code true} if any {@link PlayFigure} of a {@link Player#isHuman() human} {@link Player} is currently on
   *         this {@link PlayField}, {@code false} otherwise.
   */
  public boolean hasHumanFigure() {

    return this.figures.stream().anyMatch(x -> x.getPlayer().isHuman());
  }

  @Override
  public boolean canAddFigure(PlayFigure figure) {

    int maxFigures = PlayPropertyValueInt.MAX_FIGURES.get(this);
    int figureCount = this.figures.size();
    if (figureCount >= maxFigures) {
      return false;
    }
    return this.type.canAddAsset(figure);
  }

  @Override
  public boolean addFigure(PlayFigure figure) {

    PlayField oldLocation = figure.getLocation();
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
  public boolean removeFigure(PlayFigure figure, boolean updateLocation) {

    boolean success = this.figures.remove(figure);
    if (success) {
      if (updateLocation) {
        figure.setLocation(null);
      }
    }
    return success;
  }

  /**
   * @param direction the {@link PlayDirection} to move.
   * @return the {@link PlayField} reached by moving in the given {@link PlayDirection}.
   */
  public PlayField getField(PlayDirection direction) {

    if (direction.isCombined()) {
      PlayField field = this;
      PlayBorder border = null;
      for (PlayDirection atomicDirection : direction.getCombinations()) {
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
   * @param direction the {@link PlayDirection} to move.
   * @param count the number of times to move in the given {@link PlayDirection}.
   * @param stopAtEnd {@code true} to stop at the {@link PlayField} that has a {@link #createWall(PlayDirection)
   *        terminating wall} in the given {@link PlayDirection}, {@code false} to return {@code null} in such case.
   * @return the resulting {@link PlayField}. May be {@code null}.
   */
  public PlayField getField(PlayDirection direction, int count, boolean stopAtEnd) {

    PlayField field = this;
    for (int i = 0; i < count; i++) {
      PlayField nextField = field.getField(direction);
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
   * @param direction the {@link PlayDirection}. Must not be {@code null} or {@link PlayDirection#isCombined()
   *        combined}.
   * @return the {@link PlayBorder} for the given {@link PlayDirection}.
   */
  public PlayBorder getBorder(PlayDirection direction) {

    PlayBorder playBorder = this.direction2borderMap.get(direction);
    if ((playBorder == null) && (direction != null) && direction.isCombined()) {
      PlayField field = this;
      for (PlayDirection dir : direction.getCombinations()) {
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
   * {@link #createWall(PlayDirection) Creates walls} in all non {@link PlayDirection#isNatural() natural} and non
   * {@link PlayDirection#isCombined() combined} {@link PlayDirection}s. Useful to initialize the
   * {@link PlayLevel#getStartField() start field}.
   */
  public void initEdge() {

    List<PlayDirection> directions = this.level.getGame().getDirections();
    for (PlayDirection direction : directions) {
      if (!direction.isCombined() && !direction.isNatural()) {
        createWall(direction);
      }
    }
  }

  /**
   * @param borderType the {@link PlayBorderType}.
   * @param direction the {@link PlayDirection}.
   * @return the {@link PlayField} next to this one in the given {@link PlayDirection} that has been bi-directional
   *         connected with this field by a {@link PlayBorder} of the given {@link PlayBorderType}. Will be all created
   *         and connected if not exists. Otherwise only the existing {@link PlayField} is returned.
   */
  public PlayField createBorder(PlayBorderType borderType, PlayDirection direction) {

    PlayBorder border = this.direction2borderMap.get(direction);
    if (border != null) {
      PlayBorderType existingBorderType = border.getType();
      if ((existingBorderType == null) && (borderType != null)) {
        border.setType(borderType);
      } else if (existingBorderType != borderType) {
        throw new IllegalStateException(
            "Border with type " + existingBorderType + " already exists in direction " + direction + " - cannot create border of type " + borderType);
      }
      return border.getField(direction);
    }
    PlayField targetField = new PlayField(this.level);
    createBorder(borderType, direction, targetField);
    return targetField;
  }

  /**
   * @param borderType the {@link PlayBorderType}.
   * @param direction the {@link PlayDirection}.
   * @param targetField the {@link PlayBorder#getTargetField() target field}.
   */
  public void createBorder(PlayBorderType borderType, PlayDirection direction, PlayField targetField) {

    assert (targetField.level == this.level);
    PlayBorder border = PlayBorder.of(this, direction, targetField, borderType);
    setBorder(border, direction);
    targetField.setBorder(border, direction.getInverse());
  }

  /**
   * @param direction the {@link PlayDirection} where to create a {@link PlayBorderTypeWall wall}
   *        {@link #getBorder(PlayDirection) border}.
   */
  public void createWall(PlayDirection direction) {

    assert !direction.isCombined();
    PlayBorder border = this.direction2borderMap.get(direction);
    if (border == null) {
      border = PlayBorder.of(this, direction, null, PlayBorderTypeWall.get());
      setBorder(border, direction);
    } else {
      if (!(border.getType() instanceof PlayBorderTypeWall)) {
        throw new IllegalStateException(
            "Border in direction " + direction + " already existed as " + border + " and cannot be replaced with " + PlayBorderTypeWall.get());
      }
    }
  }

  private void setBorder(PlayBorder border, PlayDirection direction) {

    PlayBorder duplicate = this.direction2borderMap.put(direction, border);
    if (duplicate != null) {
      throw new IllegalStateException("Border in direction " + direction + " already existed as " + duplicate + " and has been replaced with " + border);
    }
  }

  @Override
  public boolean canAddAsset(PlayAsset<?> asset) {

    if (asset instanceof PlayPickItem) {
      return canAddItem((PlayPickItem) asset);
    } else if (asset instanceof PlayFigure) {
      return canAddFigure((PlayFigure) asset);
    } else {
      return this.type.addAsset(asset);
    }
  }

  @Override
  public boolean addAsset(PlayAsset<?> asset) {

    if (asset instanceof PlayPushItem) {
      return setPushItem((PlayPushItem) asset);
    } else if (asset instanceof PlayPickItem) {
      return addItem((PlayPickItem) asset);
    } else if (asset instanceof PlayFigure) {
      return addFigure((PlayFigure) asset);
    } else {
      return this.type.addAsset(asset);
    }
  }

  @Override
  public boolean removeAsset(PlayAsset<?> asset, boolean updateLocation) {

    if (asset instanceof PlayPushItem) {
      return setPushItem(null);
    } else if (asset instanceof PlayPickItem) {
      return removeItem((PlayPickItem) asset, updateLocation);
    } else {
      return this.type.removeAsset(asset, updateLocation);
    }
  }

}
