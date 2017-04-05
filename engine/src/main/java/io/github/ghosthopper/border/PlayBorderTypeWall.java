package io.github.ghosthopper.border;

import io.github.ghosthopper.PlayDirection;
import io.github.ghosthopper.figure.PlayFigure;

/**
 * A {@link PlayBorderType} that is an open gate so no {@link PlayFigure} {@link #canPass(PlayFigure, PlayBorder) can
 * ever pass through}.
 */
public class PlayBorderTypeWall extends PlayBorderType {

  private static final PlayBorderTypeWall INSTANCE = new PlayBorderTypeWall();

  private PlayBorderTypeWall() {
    super("Wall");
  }

  @Override
  public boolean canPass(PlayFigure player, PlayBorder border) {

    return false;
  }

  /**
   * @return an instance of this border type.
   */
  public static PlayBorderTypeWall get() {

    return INSTANCE;
  }

  @Override
  protected char getAsciiArt(PlayDirection direction) {

    if (direction == PlayDirection.RIGHT) {
      return '|';
    } else {
      return '-';
    }
  }

}
