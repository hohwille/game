package io.github.ghosthopper;

/**
 * A bot {@link PlayFigure} that is is automatically moved and controlled by the game software.
 */
public class PlayFigureBot extends PlayFigure {

  private final PlayFigureTypeBot type;

  /**
   * The constructor.
   *
   * @param type the {@link #getType() type}.
   */
  public PlayFigureBot(PlayFigureTypeBot type) {
    super();
    this.type = type;
  }

  @Override
  public boolean isHuman() {

    return false;
  }

  @Override
  public PlayFigureTypeBot getType() {

    return this.type;
  }

}
