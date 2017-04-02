package io.github.ghosthopper;

/**
 * A {@link PlayBorderType} that is an open gate so every {@link PlayFigureHuman human player}
 * {@link #canPass(PlayFigureHuman, PlayBorder) can always pass through}.
 */
public class PlayBorderTypMagicDoor extends PlayBorderType {

  private boolean open;

  private PlayBorderTypMagicDoor() {
    super();
  }

  @Override
  public boolean canPass(PlayFigureHuman player, PlayBorder border) {

    if (this.open) {
      return true;
    }
    if (border.getFieldLeftOrTop().hasPlayer()) {
      if (border.getFieldRightOrBottom().hasPlayer()) {
        this.open = true;
        return true;
      }
    }
    return false;
  }

  /**
   * @return an instance of this border type.
   */
  public static PlayBorderTypMagicDoor get() {

    return new PlayBorderTypMagicDoor();
  }

}
