package io.github.ghosthopper.border;

import io.github.ghosthopper.PlayDirection;
import io.github.ghosthopper.PlayLevel;
import io.github.ghosthopper.PlayObjectType;
import io.github.ghosthopper.figure.PlayFigure;

/**
 * The abstract class for the {@link PlayBorder#getType() type} of a {@link PlayBorder}.
 * {@link PlayBorder#canPass(PlayFigure)} delegates to {@link #canPass(PlayFigure, PlayBorder)} what makes the actual
 * decision according to this {@link PlayBorderType} and its potential state.
 */
public abstract class PlayBorderType extends PlayObjectType {

  /**
   * The constructor.
   *
   * @param id - see {@link #getId()}.
   */
  public PlayBorderType(String id) {
    super(id);
  }

  /**
   * @see PlayBorder#canPass(PlayFigure)
   *
   * @param figure the {@link PlayFigure}.
   * @param border the {@link PlayBorder}.
   * @return {@code true} if the given {@link PlayFigure} can pass this border, {@code false} otherwise.
   */
  public abstract boolean canPass(PlayFigure figure, PlayBorder border);

  /**
   * @param direction the {@link PlayDirection}. See {@link PlayBorder#getDirection()}.
   * @return the character used to represent this {@link PlayBorderType} in ASCII-Art of the {@link PlayLevel}.
   */
  protected abstract char getAsciiArt(PlayDirection direction);
}
