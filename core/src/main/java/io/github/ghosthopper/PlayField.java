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
   * @return {@code true} if anyone of the {@link PlayGround#getHumanPlayers() human players} is currently on this
   *         {@link PlayField}, {@code false} otherwise.
   */
  public boolean hasPlayer() {

    for (PlayFigureHuman player : this.playGround.getHumanPlayers()) {
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
    field.top = new PlayBorder(null, PlayBorderTypeWall.get(), field, Orientation.VERTICAL);
    field.left = new PlayBorder(null, PlayBorderTypeWall.get(), field, Orientation.HORIZONTAL);
    return field;
  }

  /**
   * @param borderType the {@link #getRight() right} {@link PlayBorderType}.
   * @return the created {@link #getRight() right} {@link PlayField}.
   */
  public PlayField createRightField(PlayBorderType borderType) {

    if (this.right != null) {
      throw new IllegalStateException("Right border already created!");
    }
    PlayField rightField = new PlayField(this.playGround);
    this.right = new PlayBorder(this, borderType, rightField, Orientation.HORIZONTAL);
    rightField.left = this.right;
    if ((this.top != null) && (this.top.getFieldLeftOrTop() == null)) {
      rightField.top = new PlayBorder(null, PlayBorderTypeWall.get(), rightField, Orientation.VERTICAL);
    }
    if ((this.bottom != null) && (this.bottom.getFieldRightOrBottom() == null)) {
      rightField.bottom = new PlayBorder(rightField, PlayBorderTypeWall.get(), null, Orientation.VERTICAL);
    }
    return rightField;
  }

  /**
   * Ends the {@link PlayGround} to the {@link #getRight() right} of this {@link PlayField} with an outer
   * {@link PlayBorderTypeWall wall}. No {@link PlayFigure} can ever be {@link #getRight() right} to this
   * {@link PlayField}.
   */
  public void createRightEnd() {

    if (this.right != null) {
      throw new IllegalStateException("Right border already created!");
    }
    this.right = new PlayBorder(this, PlayBorderTypeWall.get(), null, Orientation.HORIZONTAL);
  }

  /**
   * @param borderType the {@link #getBottom() bottom} {@link PlayBorderType}.
   * @return the created {@link #getBottom() bottom} {@link PlayField}.
   */
  public PlayField createBottomField(PlayBorderType borderType) {

    if (this.bottom != null) {
      throw new IllegalStateException("Bottom border already created!");
    }
    PlayField bottomField = new PlayField(this.playGround);
    this.bottom = new PlayBorder(this, borderType, bottomField, Orientation.VERTICAL);
    bottomField.top = this.bottom;
    if ((this.left != null) && (this.left.getFieldLeftOrTop() == null)) {
      bottomField.left = new PlayBorder(null, PlayBorderTypeWall.get(), bottomField, Orientation.HORIZONTAL);
    }
    if ((this.right != null) && (this.right.getFieldRightOrBottom() == null)) {
      bottomField.right = new PlayBorder(bottomField, PlayBorderTypeWall.get(), null, Orientation.HORIZONTAL);
    }
    return bottomField;
  }

  /**
   * Ends the {@link PlayGround} to the {@link #getBottom() bottom} of this {@link PlayField} with an outer
   * {@link PlayBorderTypeWall wall}. No {@link PlayFigure} can ever be {@link #getBottom() below} to this
   * {@link PlayField}.
   */
  public void createBottomEnd() {

    if (this.bottom != null) {
      throw new IllegalStateException("Bottom border already created!");
    }
    this.bottom = new PlayBorder(this, PlayBorderTypeWall.get(), null, Orientation.VERTICAL);
  }
}
