package io.github.ghosthopper;

public class PlayFigureHuman extends PlayFigure {

  private final PlayFigureTypeHuman type;

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
