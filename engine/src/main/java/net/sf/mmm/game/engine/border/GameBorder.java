package net.sf.mmm.game.engine.border;

import java.util.List;

import net.sf.mmm.game.engine.Game;
import net.sf.mmm.game.engine.asset.GameAsset;
import net.sf.mmm.game.engine.direction.GameAttributeDirection;
import net.sf.mmm.game.engine.direction.GameDirection;
import net.sf.mmm.game.engine.field.GameField;
import net.sf.mmm.game.engine.object.GameTypedObjectBase;

/**
 * A {@link GameBorder} connects two {@link GameField}s (typically bidirectional). A {@link #getSourceField() source
 * field} is leading in the {@link #getDirection() direction} towards the {@link #getTargetField() target field}. The
 * {@link GameBorder} has a {@link #getType() type} that decides if a figure {@link #isPassable(GameAsset) can}
 * {@link #pass(GameAsset) pass} the border.
 */
public class GameBorder extends GameTypedObjectBase<GameBorderType> implements GameAttributeDirection, GameAttributePassable {

  private final GameField sourceField;

  private final GameDirection direction;

  private final GameField targetField;

  /**
   * The constructor.
   *
   * @param sourceField - see {@link #getSourceField()}.
   * @param direction - see {@link #getDirection()}.
   * @param targetField - see {@link #getTargetField()}.
   * @param type - see {@link #getType()}.
   */
  public GameBorder(GameField sourceField, GameDirection direction, GameField targetField, GameBorderType type) {

    super(type);
    assert direction.isNatural();
    this.sourceField = sourceField;
    this.targetField = targetField;
    this.direction = direction;
  }

  @Override
  public Game getGame() {

    if (this.sourceField != null) {
      return this.sourceField.getGame();
    }
    if (this.targetField != null) {
      return this.targetField.getGame();
    }
    return super.getGame();
  }

  /**
   * @return the source {@link GameField} when moving from here in the {@link #getDirection() direction} towards the
   *         {@link #getTargetField() target field}.
   */
  public GameField getSourceField() {

    return this.sourceField;
  }

  /**
   * @return the target {@link GameField} when moving from here in the {@link GameDirection#getInverse() inverse}
   *         {@link #getDirection() direction} towards the {@link #getSourceField() source field}.
   */
  public GameField getTargetField() {

    return this.targetField;
  }

  /**
   * @param playDirection the {@link GameDirection} leading to the requested {@link GameField}.
   * @return the {@link #getTargetField() target field} in case the given {@link GameDirection} is the
   *         {@link #getDirection() direction} of this border, and the {@link #getSourceField() source field} in case of
   *         the {@link GameDirection#getInverse() inverse direction}.
   */
  public GameField getField(GameDirection playDirection) {

    if (this.direction == playDirection) {
      return this.targetField;
    } else if (this.direction.getInverse() == playDirection) {
      return this.sourceField;
    } else if ((playDirection != null) && playDirection.isCombined()) {
      List<GameDirection> combinations = playDirection.getCombinations();
      GameDirection lastDirection = combinations.get(combinations.size() - 1);
      return getField(lastDirection);
    } else {
      return null;
    }
  }

  @Override
  public void setType(GameBorderType type) {

    super.setType(type);
  }

  /**
   * @return the {@link GameDirection}. Should be {@link GameDirection#isNatural() natural} and not
   *         {@link GameDirection#isCombined() combined}.
   */
  @Override
  public GameDirection getDirection() {

    return this.direction;
  }

  @Override
  public boolean isPassable(GameAsset<?> asset, boolean move) {

    return getType().isPassable(asset, move, this);
  }

  /**
   * @param field1 the {@link #getSourceField() source field} in case of {@link GameDirection#isNatural() natural}
   *        {@link GameDirection} and the {@link #getTargetField() target field} otherwise.
   * @param direction the {@link #getDirection() direction} if {@link GameDirection#isNatural() natural} or
   *        {@link GameDirection#getInverse() inverse} otherwise.
   * @param field2 the {@link #getSourceField() target field} in case of {@link GameDirection#isNatural() natural}
   *        {@link GameDirection} and the {@link #getTargetField() source field} otherwise.
   * @param type the {@link #getType() type}.
   * @return the new {@link GameBorder}.
   */
  public static GameBorder of(GameField field1, GameDirection direction, GameField field2, GameBorderType type) {

    if (direction.isNatural()) {
      return new GameBorder(field1, direction, field2, type);
    } else {
      return new GameBorder(field2, direction.getInverse(), field1, type);
    }
  }
}
