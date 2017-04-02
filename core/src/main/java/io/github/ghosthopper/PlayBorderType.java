package io.github.ghosthopper;

/**
 * The abstract class for the {@link PlayBorder#getType() type} of a {@link PlayBorder}.
 * {@link PlayBorder#canPass(PlayFigureHuman)} delegates to {@link #canPass(PlayFigureHuman, PlayBorder)} what makes the
 * actual decision according to this {@link PlayBorderType} and its potential state.
 */
public abstract class PlayBorderType {

  /**
   * @see PlayBorder#canPass(PlayFigureHuman)
   *
   * @param player the {@link PlayFigureHuman player}.
   * @param border the {@link PlayBorder}.
   * @return {@code true} if the given {@link PlayFigureHuman} can pass this border, {@code false} otherwise.
   */
  public abstract boolean canPass(PlayFigureHuman player, PlayBorder border);
}
