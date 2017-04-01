package io.github.ghosthopper;

public class PlayBorderTypeGate extends PlayBorderType {

  public static final PlayBorderTypeGate INSTANCE = new PlayBorderTypeGate();

  @Override
  public boolean canPass(PlayFigureHuman player, PlayBorder border) {
    return true;
  }
}
