package io.github.ghosthopper.border;

import io.github.ghosthopper.PlayDirection;
import io.github.ghosthopper.figure.PlayFigure;

/**
 * A {@link PlayBorderType} that is an open gate so every {@link PlayFigure} {@link #canPass(PlayFigure, PlayBorder) can
 * always pass through}.
 */
public class PlayBorderTypeOpen extends PlayBorderType {

  private static final PlayBorderTypeOpen INSTANCE = new PlayBorderTypeOpen();

  /**
   * The constructor.
   */
  public PlayBorderTypeOpen() {
    super("Open");
  }

  @Override
  public boolean canPass(PlayFigure figure, PlayBorder border) {

    return true;
  }

  /**
   * @return an instance of this border type.
   */
  public static PlayBorderTypeOpen get() {

    return INSTANCE;
  }

  @Override
  protected char getAsciiArt(PlayDirection direction) {

    return '.';
  }
}
