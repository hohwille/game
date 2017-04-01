package io.github.ghosthopper;

public class PlayWall {
  private final PlayField field;
  private final PlayWallType type;

  public PlayWall(PlayField field, PlayWallType type) {
    super();
    this.field = field;
    this.type = type;
  }

  public PlayField getField() {
    return this.field;
  }

  public PlayWallType getType() {
    return this.type;
  }

}
