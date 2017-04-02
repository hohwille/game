package io.github.ghosthopper;

/**
 * A single field on the {@link PlayGround} that is a 2-dimensional area divided into {@link PlayField}s. Each
 * {@link PlayField} has four borders for each direction {@link #getLeft() left}, {@link #getTop() top},
 * {@link #getRight() right} and {@link #getBottom() bottom}.
 */
public class PlayField {

  private final PlayGround playGround;

  private PlayBorder top;

  private PlayBorder right;

  private PlayBorder left;

  private PlayBorder bottom;

  /**
   * The constructor.
   *
   * @param playGround the owning {@link PlayGround}.
   */
  public PlayField(PlayGround playGround) {
    super();
    this.playGround = playGround;
    // this.top = new PlayBorder(this, type, fieldRightOrBottom);
  }

  /**
   * @return the {@link PlayGround} owning this {@link PlayField}.
   */
  public PlayGround getPlayGround() {

    return this.playGround;
  }

  /**
   * @return {@code true} if anyone of the {@link PlayGround#getPlayers() human players} is currently on this
   *         {@link PlayField}, {@code false} otherwise.
   */
  public boolean hasPlayer() {

    for (PlayFigureHuman player : this.playGround.getPlayers()) {
      if (player.getField() == this) {
        return true;
      }
    }
    return false;
  }

  /**
   * @return the bottom
   */
  public PlayBorder getBottom() {

    return this.bottom;
  }

  /**
   * @return the left
   */
  public PlayBorder getLeft() {

    return this.left;
  }

  /**
   * @return the right
   */
  public PlayBorder getRight() {

    return this.right;
  }

  /**
   * @return the top
   */
  public PlayBorder getTop() {

    return this.top;
  }

  /**
   * @param playGround the {@link PlayGround}.
   * @return the {@link PlayGround#getFieldTopLeft() top left field}.
   */
  public static PlayField createTopLeftField(PlayGround playGround) {

    PlayField field = new PlayField(playGround);
    field.top = new PlayBorder(null, PlayBorderTypeWall.get(), field);
    field.left = new PlayBorder(null, PlayBorderTypeWall.get(), field);
    return field;
  }

}
