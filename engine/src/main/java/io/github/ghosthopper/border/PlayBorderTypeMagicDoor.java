package io.github.ghosthopper.border;

import io.github.ghosthopper.PlayDirection;
import io.github.ghosthopper.field.PlayField;
import io.github.ghosthopper.figure.PlayFigure;

/**
 * A {@link PlayBorderType} that is a magic door so every {@link PlayFigure} {@link #canPass(PlayFigure, PlayBorder) can
 * always pass through}.
 */
public class PlayBorderTypeMagicDoor extends PlayBorderType {

  private boolean open;

  private PlayBorderTypeMagicDoor() {
    super("MagicDoor");
  }

  @Override
  public boolean canPass(PlayFigure figure, PlayBorder border) {

    if (this.open) {
      return true;
    }
    PlayField sourceField = border.getSourceField();
    if ((sourceField != null) && sourceField.hasHumanFigure()) {
      PlayField targetField = border.getTargetField();
      if ((targetField != null) && targetField.hasHumanFigure()) {
        this.open = true;
        return true;
      }
    }
    return false;
  }

  @Override
  protected char getAsciiArt(PlayDirection direction) {

    return 'D';
  }

  /**
   * @return an instance of this border type.
   */
  public static PlayBorderTypeMagicDoor get() {

    return new PlayBorderTypeMagicDoor();
  }

}
