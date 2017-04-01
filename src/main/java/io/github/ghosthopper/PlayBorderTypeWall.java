package io.github.ghosthopper;

public class PlayBorderTypeWall extends PlayBorderType {

  public static final PlayBorderTypeWall INSTANCE = new PlayBorderTypeWall();

  @Override
  public boolean canPass(PlayFigureHuman player, PlayBorder border) {
    return false;
  }

}
