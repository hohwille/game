package io.github.ghosthopper;

/**
 * A {@link PlayBorderType} that is a hole where only {@link PlayFigureHuman human players} of a specific
 * {@link PlayFigureHuman#getType() type} {@link #canPass(PlayFigureHuman, PlayBorder) can pass through}.
 */
public class PlayBorderTypeHole extends PlayBorderType {

  private final PlayFigureTypeHuman playerType;

  private PlayBorderTypeHole(PlayFigureTypeHuman playerType) {
    super();
    this.playerType = playerType;
  }

  @Override
  public boolean canPass(PlayFigureHuman player, PlayBorder border) {

    return player.getType().equals(this.playerType);
  }

  /**
   * @param playerType the {@link PlayFigureTypeHuman} that is allowed to {@link #canPass(PlayFigureHuman, PlayBorder)
   *        pass through}.
   * @return an instance of this border type.
   */
  public static final PlayBorderTypeHole get(PlayFigureTypeHuman playerType) {

    return new PlayBorderTypeHole(playerType);
  }

}
