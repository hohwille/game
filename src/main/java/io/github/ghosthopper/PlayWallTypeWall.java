package io.github.ghosthopper;

public class PlayWallTypeWall extends PlayWallType {

  @Override
  public boolean canPass(PlayPlayer player) {
    return false;
  }

}
