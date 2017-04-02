package io.github.ghosthopper;

public class PlayFigureBot extends PlayFigure {

  private final PlayFigureTypeBot type;

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
