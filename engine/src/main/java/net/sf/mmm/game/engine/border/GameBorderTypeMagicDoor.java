package net.sf.mmm.game.engine.border;

import net.sf.mmm.game.engine.asset.GameAsset;
import net.sf.mmm.game.engine.field.GameField;
import net.sf.mmm.game.engine.figure.GameFigure;

/**
 * A {@link GameBorderType} that is a magic door that {@link #isPassable(GameAsset, boolean, GameBorder) can only be
 * passed} after it has been opened. Therefore a {@link GameFigure} has to
 * {@link #isPassable(GameAsset, boolean, GameBorder) pass} whilst another {@link GameFigure} is on the other side.
 */
public class GameBorderTypeMagicDoor extends GameBorderType {

  private boolean open;

  private GameBorderTypeMagicDoor() {
    super("MagicDoor");
  }

  /**
   * @return {@code true} if open, {@code false} otherwise (closed).
   */
  public boolean isOpen() {

    return this.open;
  }

  @Override
  public String getId() {

    if (this.open) {
      return GameBorderTypeOpen.ID;
    }
    return super.getId();
  }

  @Override
  public boolean isPassable(GameAsset<?> asset, boolean move, GameBorder border) {

    if (this.open) {
      return true;
    }
    if (asset == null) {
      return false;
    }
    GameField sourceField = border.getSourceField();
    if ((sourceField != null) && sourceField.hasHumanFigure()) {
      GameField targetField = border.getTargetField();
      if ((targetField != null) && targetField.hasHumanFigure()) {
        if (move) {
          this.open = true;
          getGame().sendEvent(border);
        }
        return true;
      }
    }
    return false;
  }

  /**
   * @return an instance of this border type.
   */
  public static GameBorderTypeMagicDoor get() {

    return new GameBorderTypeMagicDoor();
  }

}
