package io.github.ghosthopper;

public class PlayBorderTypeHole extends PlayBorderType {

  private final PlayFigureType playerType;

  public PlayBorderTypeHole(PlayFigureType playerType) {
    super();
    this.playerType = playerType;
  }

  @Override
  public boolean canPass(PlayFigureHuman player, PlayBorder border) {

    return player.getType().equals(this.playerType);

  }

}
