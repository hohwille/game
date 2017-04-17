package io.github.ghosthopper.border;

import io.github.ghosthopper.figure.PlayFigure;
import io.github.ghosthopper.object.PlayObjectType;

/**
 * The abstract class for the {@link PlayBorder#getType() type} of a {@link PlayBorder}.
 * {@link PlayBorder#canPass(PlayFigure)} and {@link PlayBorder#pass(PlayFigure)} delegate to
 * {@link #canPass(PlayFigure, PlayBorder, boolean)} what makes the actual decision according to this
 * {@link PlayBorderType} and its potential state.
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

  @Override
  public String getTypeName() {

    return "Border";
  }

  /**
   * @see PlayBorder#canPass(PlayFigure)
   *
   * @param figure the {@link PlayFigure}.
   * @param border the {@link PlayBorder}.
   * @param move {@code true} if the {@link PlayFigure} actually {@link PlayBorder#pass(PlayFigure) performs the move}
   *        if possible, {@code false} otherwise (only to {@link PlayBorder#canPass(PlayFigure) check a simulated
   *        move}).
   * @return {@code true} if the given {@link PlayFigure} can pass this border, {@code false} otherwise.
   */
  public abstract boolean canPass(PlayFigure figure, PlayBorder border, boolean move);

}
