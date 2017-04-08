package io.github.ghosthopper.field;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;

import io.github.ghosthopper.PlayLevel;
import io.github.ghosthopper.border.PlayBorder;
import io.github.ghosthopper.border.PlayBorderType;
import io.github.ghosthopper.border.PlayBorderTypeWall;
import io.github.ghosthopper.color.PlayColor;
import io.github.ghosthopper.figure.PlayFigure;
import io.github.ghosthopper.game.PlayGame;
import io.github.ghosthopper.item.PlayPushItem;
import io.github.ghosthopper.move.PlayDirection;
import io.github.ghosthopper.object.PlayTypedObjectWithItems;
import io.github.ghosthopper.player.Player;

/**
 * A single field on the {@link #getLevel() level}. Such {@link PlayLevel} is a two-dimensional area divided into
 * {@link PlayField}s. Each {@link PlayField} has {@link PlayBorder}s that can be navigated via
 * {@link #getBorder(PlayDirection)} and also {@link #getField(PlayDirection)}.
 */
public class PlayField extends PlayTypedObjectWithItems {

  private final PlayLevel level;

  private final Map<PlayDirection, PlayBorder> direction2borderMap;

  private PlayFieldType type;

  private PlayColor color;

  private PlayPushItem pushItem;

  /**
   * The constructor.
   *
   * @param level the owning {@link PlayLevel}.
   */
  public PlayField(PlayLevel level) {
    super();
    this.level = level;
    this.direction2borderMap = new HashMap<>();
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

    assert (this.type == null);
    this.type = type;
  }

  /**
   * @return the {@link PlayPushItem} that is on this {@link PlayField} or {@code null} if there is no such item here.
   */
  public PlayPushItem getPushItem() {

    return this.pushItem;
  }

  /**
   * @param pushItem the new value of {@link #getPushItem()}.
   */
  public void setPushItem(PlayPushItem pushItem) {

    this.pushItem = pushItem;
  }

  /**
   * @param filter the {@link Predicate} used to filter the {@link Player}s.
   * @return the {@link Collection} containing the {@link PlayFigure}s of the {@link Player}s
   *         {@link Predicate#test(Object) accepted} by the given {@link Predicate} that are currently on this
   *         {@link PlayField}.
   */
  public Collection<PlayFigure> getCurrentFigures(Predicate<Player> filter) {

    Collection<PlayFigure> figures = new ArrayList<>();
    PlayGame game = this.level.getGame();
    for (Player player : game.getPlayers()) {
      if (filter.test(player)) {
        collectCurrentFigures(player, figures);
      }
    }
    return figures;
  }

  /**
   * @param player the {@link Player} whose {@link Player#getFigures() figures} shall be checked.
   * @param figures the {@link Collection} where to {@link Collection#add(Object) add} the {@link PlayFigure}s of the
   *        given {@link Player} if they are on this {@link PlayField}.
   */
  public void collectCurrentFigures(Player player, Collection<PlayFigure> figures) {

    for (PlayFigure figure : player.getFigures()) {
      if (figure.getField() == this) {
        figures.add(figure);
      }
    }
  }

  /**
   * @return the number of {@link PlayFigure}s currently on this {@link PlayField}.
   */
  public int getCurrentFigureCount() {

    return getCurrentFigures(x -> true).size();
  }

  /**
   * @return {@code true} if any {@link PlayFigure} of a {@link Player#isHuman() human} {@link Player} is currently on
   *         this {@link PlayField}, {@code false} otherwise.
   */
  public boolean hasHumanFigure() {

    return !getCurrentFigures(x -> x.isHuman()).isEmpty();
  }

  @Override
  public PlayColor getColor() {

    return this.color;
  }

  /**
   * @param color the new value of {@link #getColor()}.
   */
  @Override
  public void setColor(PlayColor color) {

    this.color = color;
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

    if (direction.isCombined()) {
      throw new IllegalStateException(direction.toString());
    }
    PlayBorder border = this.direction2borderMap.get(direction);
    if (border == null) {
      throw new IllegalStateException("No border defined for direction: " + direction.toString());
    }
    return border;
  }

  /**
   * {@link #createWall(PlayDirection) Creates walls} in all non {@link PlayDirection#isNatural() natural} and non
   * {@link PlayDirection#isCombined() combined} {@link PlayDirection}s. Useful to initialize the
   * {@link PlayLevel#getStartField() start field}.
   */
  public void initEdge() {

    Set<PlayDirection> directions = this.level.getGame().getDirections();
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
    PlayBorder border = PlayBorder.of(this, direction, null, PlayBorderTypeWall.get());
    setBorder(border, direction);
  }

  private void setBorder(PlayBorder border, PlayDirection direction) {

    PlayBorder duplicate = this.direction2borderMap.put(direction, border);
    if (duplicate != null) {
      throw new IllegalStateException("Border in direction " + direction + " already existed as " + duplicate + " and has been replaced with " + border);
    }
  }

}
