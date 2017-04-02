package io.github.ghosthopper;

/**
 * A {@link PlayBorderType} that is an open gate so every {@link PlayFigureHuman human player}
 * {@link #canPass(PlayFigureHuman, PlayBorder) can always pass through}.
 */
public class PlayBorderTypeOpenGate extends PlayBorderType {

  private static final PlayBorderTypeOpenGate INSTANCE = new PlayBorderTypeOpenGate();

  @Override
  public boolean canPass(PlayFigureHuman player, PlayBorder border) {

    return true;
  }

  /**
   * @return an instance of this border type.
   */
  public static PlayBorderTypeOpenGate get() {

    return INSTANCE;
  }
}
