package io.github.ghosthopper;

public class PlayField {

  private final PlayGround playGround;

  private PlayBorder top;
  private PlayBorder right;
  private PlayBorder left;
  private PlayBorder bottom;

  public PlayField(PlayGround playGround) {
    super();
    this.playGround = playGround;
    // this.top = new PlayBorder(this, type, fieldRightOrBottom);
  }

  public PlayGround getPlayGround() {
    return this.playGround;
  }

  public boolean hasPlayer() {

    for (PlayFigureHuman player : this.playGround.getPlayers()) {
      if (player.getField() == this) {
        return true;
      }
    }
    return false;
  }

}
