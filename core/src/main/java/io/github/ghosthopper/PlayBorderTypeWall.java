package io.github.ghosthopper;

/**
 * A {@link PlayBorderType} that is an open gate so no {@link PlayFigureHuman human player}
 * {@link #canPass(PlayFigureHuman, PlayBorder) can ever pass through}.
 */
public class PlayBorderTypeWall extends PlayBorderType {

  private static final PlayBorderTypeWall INSTANCE = new PlayBorderTypeWall();

  private PlayBorderTypeWall() {
    super();
  }

  @Override
  public boolean canPass(PlayFigureHuman player, PlayBorder border) {

    return false;
  }

  /**
   * @return an instance of this border type.
   */
  public static PlayBorderTypeWall get() {

    return INSTANCE;
  }

}
