package io.github.ghosthopper;

public class PlayBorderTypMagicDoor extends PlayBorderType {

  private boolean open;

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

}
