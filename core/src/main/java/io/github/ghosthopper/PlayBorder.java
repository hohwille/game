package io.github.ghosthopper;

/**
 * A {@link PlayBorder} connects two {@link PlayField} horizontally or vertically. The {@link PlayBorder} has a
 * {@link #getType() type} that decides if a player {@link #canPass(PlayFigureHuman) can pass} the border.
 */
public class PlayBorder {

  private final PlayField fieldLeftOrTop;

  private final PlayField fieldRightOrBottom;

  private final PlayBorderType type;

  private final Orientation orientation;

  /**
   * The constructor.
   *
   * @param fieldLeftOrTop - see {@link #getFieldLeftOrTop()}.
   * @param type - see {@link #getType()}.
   * @param fieldRightOrBottom - see {@link #getFieldRightOrBottom()}.
   * @param orientation - see {@link #getOrientation()}.
   */
  public PlayBorder(PlayField fieldLeftOrTop, PlayBorderType type, PlayField fieldRightOrBottom, Orientation orientation) {
    super();
    this.fieldLeftOrTop = fieldLeftOrTop;
    this.fieldRightOrBottom = fieldRightOrBottom;
    this.type = type;
    this.orientation = orientation;
  }

  /**
   * @return the {@link PlayField} on the left of this {@link PlayBorder} if {@link #getOrientation() orientation} is
   *         {@link Orientation#HORIZONTAL} or at the top if {@link Orientation#VERTICAL}.
   */
  public PlayField getFieldLeftOrTop() {

    return this.fieldLeftOrTop;
  }

  /**
   * @return the {@link PlayBorderType} of this border.
   */
  public PlayBorderType getType() {

    return this.type;
  }

  /**
   * @return the {@link PlayField} on the right of this {@link PlayBorder} if {@link #getOrientation() orientation} is
   *         {@link Orientation#HORIZONTAL} or at the bottom if {@link Orientation#VERTICAL}.
   */
  public PlayField getFieldRightOrBottom() {

    return this.fieldRightOrBottom;
  }

  /**
   * @return the {@link Orientation}.
   */
  public Orientation getOrientation() {

    return this.orientation;
  }

  /**
   * @param player the {@link PlayFigureHuman human player}.
   * @return {@code true} if the given {@link PlayFigureHuman} can pass this border, {@code false} otherwise.
   */
  public boolean canPass(PlayFigureHuman player) {

    return this.type.canPass(player, this);
  }
}
