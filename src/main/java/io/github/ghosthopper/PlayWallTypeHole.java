package io.github.ghosthopper;

public class PlayWallTypeHole extends PlayWallType {

  private final PlayPlayerType playerType;

  public PlayWallTypeHole(PlayPlayerType playerType) {
    super();
    this.playerType = playerType;
  }

  @Override
  public boolean canPass(PlayPlayer player) {

    return player.getType().equals(this.playerType);

  }

}
