package io.github.ghosthopper.border;

import io.github.ghosthopper.field.PlayField;
import io.github.ghosthopper.figure.PlayFigure;
import io.github.ghosthopper.object.PlayAsset;

/**
 * A {@link PlayBorderType} that is a magic door that {@link #canPass(PlayAsset, PlayBorder, boolean) can only be
 * passed} after it has been opened. Therefore a {@link PlayFigure} has to
 * {@link #canPass(PlayAsset, PlayBorder, boolean) pass} whilst another {@link PlayFigure} is on the other side.
 */
public class PlayBorderTypeMagicDoor extends PlayBorderType {

  private boolean open;

  private PlayBorderTypeMagicDoor() {
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
      return PlayBorderTypeOpen.ID;
    }
    return super.getId();
  }

  @Override
  public boolean canPass(PlayAsset<?> asset, PlayBorder border, boolean move) {

    if (this.open) {
      return true;
    }
    if (asset == null) {
      return false;
    }
    PlayField sourceField = border.getSourceField();
    if ((sourceField != null) && sourceField.hasHumanFigure()) {
      PlayField targetField = border.getTargetField();
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
  public static PlayBorderTypeMagicDoor get() {

    return new PlayBorderTypeMagicDoor();
  }

}
