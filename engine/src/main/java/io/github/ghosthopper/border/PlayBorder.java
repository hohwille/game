package io.github.ghosthopper.border;

import io.github.ghosthopper.field.PlayField;
import io.github.ghosthopper.figure.PlayFigure;
import io.github.ghosthopper.game.PlayGame;
import io.github.ghosthopper.move.PlayAttributeDirection;
import io.github.ghosthopper.move.PlayDirection;
import io.github.ghosthopper.object.PlayAsset;
import io.github.ghosthopper.object.PlayTypedObject;

/**
 * A {@link PlayBorder} connects two {@link PlayField}s. A {@link #getSourceField() source field} is leading in the
 * {@link #getDirection() direction} towards the {@link #getTargetField() target field}. The {@link PlayBorder} has a
 * {@link #getType() type} that decides if a figure {@link #canPass(PlayAsset) can pass} the border.
 */
public class PlayBorder extends PlayTypedObject implements PlayAttributeDirection {

  private final PlayField sourceField;

  private final PlayDirection direction;

  private final PlayField targetField;

  private PlayBorderType type;

  /**
   * The constructor.
   *
   * @param sourceField - see {@link #getSourceField()}.
   * @param direction - see {@link #getDirection()}.
   * @param targetField - see {@link #getTargetField()}.
   * @param type - see {@link #getType()}.
   */
  public PlayBorder(PlayField sourceField, PlayDirection direction, PlayField targetField, PlayBorderType type) {
    super();
    assert direction.isNatural();
    this.sourceField = sourceField;
    this.targetField = targetField;
    this.type = type;
    this.direction = direction;
  }

  @Override
  public PlayGame getGame() {

    if (this.sourceField != null) {
      return this.sourceField.getGame();
    }
    if (this.targetField != null) {
      return this.targetField.getGame();
    }
    return super.getGame();
  }

  /**
   * @return the source {@link PlayField} when moving from here in the {@link #getDirection() direction} towards the
   *         {@link #getTargetField() target field}.
   */
  public PlayField getSourceField() {

    return this.sourceField;
  }

  /**
   * @return the target {@link PlayField} when moving from here in the {@link PlayDirection#getInverse() inverse}
   *         {@link #getDirection() direction} towards the {@link #getSourceField() source field}.
   */
  public PlayField getTargetField() {

    return this.targetField;
  }

  /**
   * @param playDirection the {@link PlayDirection} leading to the requested {@link PlayField}.
   * @return the {@link #getTargetField() target field} in case the given {@link PlayDirection} is the
   *         {@link #getDirection() direction} of this border, and the {@link #getSourceField() source field} in case of
   *         the {@link PlayDirection#getInverse() inverse direction}.
   */
  public PlayField getField(PlayDirection playDirection) {

    if (this.direction == playDirection) {
      return this.targetField;
    } else {
      assert (this.direction.getInverse() == playDirection);
      return this.sourceField;
    }
  }

  /**
   * @return the {@link PlayBorderType} of this border.
   */
  @Override
  public PlayBorderType getType() {

    return this.type;
  }

  /**
   * @param type the new value of {@link #getType()}.
   */
  public void setType(PlayBorderType type) {

    this.type = type;
  }

  /**
   * @return the {@link PlayDirection}. Should be {@link PlayDirection#isNatural() natural} and not
   *         {@link PlayDirection#isCombined() combined}.
   */
  @Override
  public PlayDirection getDirection() {

    return this.direction;
  }

  /**
   * Unlike {@link #pass(PlayAsset)} this method only checks if the {@link PlayAsset} can potentially pass this
   * {@link PlayBorder} without changing its state.
   *
   * @param asset the {@link PlayAsset}.
   * @return {@code true} if the given {@link PlayAsset} can pass this border, {@code false} otherwise.
   */
  public boolean canPass(PlayAsset<?> asset) {

    assert (asset != null);
    return this.type.canPass(asset, this, false);
  }

  /**
   * Unlike {@link #canPass(PlayAsset)} this method actually lets the {@link PlayFigure} pass this {@link PlayBorder}
   * what may change its state (or more precisely the state of its {@link #getType() type}).
   *
   * @param asset the {@link PlayAsset} to {@link PlayFigure#move() move}.
   * @return {@code true} if the given {@link PlayFigure} passed this border, {@code false} otherwise (figure was
   *         blocked and move has to be cancelled).
   */
  public boolean pass(PlayAsset<?> asset) {

    assert (asset != null);
    return this.type.canPass(asset, this, true);
  }

  /**
   * @param field1 the {@link #getSourceField() source field} in case of {@link PlayDirection#isNatural() natural}
   *        {@link PlayDirection} and the {@link #getTargetField() target field} otherwise.
   * @param direction the {@link #getDirection() direction} if {@link PlayDirection#isNatural() natural} or
   *        {@link PlayDirection#getInverse() inverse} otherwise.
   * @param field2 the {@link #getSourceField() target field} in case of {@link PlayDirection#isNatural() natural}
   *        {@link PlayDirection} and the {@link #getTargetField() source field} otherwise.
   * @param type the {@link #getType() type}.
   * @return the new {@link PlayBorder}.
   */
  public static PlayBorder of(PlayField field1, PlayDirection direction, PlayField field2, PlayBorderType type) {

    if (direction.isNatural()) {
      return new PlayBorder(field1, direction, field2, type);
    } else {
      return new PlayBorder(field2, direction.getInverse(), field1, type);
    }
  }
}
