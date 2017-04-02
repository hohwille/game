package io.github.ghosthopper;

/**
 * Any figure of the {@link PlayGround}, either a {@link PlayFigureHuman human} or a {@link PlayFigureBot bot}.
 */
public abstract class PlayFigure {

  private PlayField field;

  /**
   * The constructor.
   */
  public PlayFigure() {
    super();
  }

  /**
   * @return {@code true} if this is a {@link PlayFigureHuman human player}, {@code false} otherwise (if a
   *         {@link PlayFigureBot bot}).
   */
  public abstract boolean isHuman();

  /**
   * @return the type of this {@link PlayField}.
   */
  public abstract PlayFigureType getType();

  /**
   * @return the {@link PlayField} this {@link PlayFigure} is currently standing on.
   */
  public PlayField getField() {

    return this.field;
  }

  /**
   * @param field the new value of {@link #getField()}.
   */
  public void setField(PlayField field) {

    this.field = field;
  }

}
