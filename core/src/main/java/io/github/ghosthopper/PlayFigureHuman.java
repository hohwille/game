package io.github.ghosthopper;

/**
 * A human {@link PlayFigure} that is moved and controlled by a human player.
 */
public class PlayFigureHuman extends PlayFigure {

  private final PlayFigureTypeHuman type;

  /**
   * The constructor.
   *
   * @param type the {@link #getType() type}.
   */
  public PlayFigureHuman(PlayFigureTypeHuman type) {
    super();
    this.type = type;
  }

  @Override
  public boolean isHuman() {

    return true;
  }

  @Override
  public PlayFigureType getType() {

    return this.type;
  }

}
