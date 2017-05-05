package io.github.ghosthopper.border;

import io.github.ghosthopper.asset.GameAsset;
import io.github.ghosthopper.field.GameField;
import io.github.ghosthopper.figure.GameFigure;

/**
 * A {@link GameBorderType} that is a magic door that {@link #canPass(GameAsset, GameBorder, boolean) can only be
 * passed} after it has been opened. Therefore a {@link GameFigure} has to
 * {@link #canPass(GameAsset, GameBorder, boolean) pass} whilst another {@link GameFigure} is on the other side.
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
  public boolean canPass(GameAsset<?> asset, GameBorder border, boolean move) {

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
