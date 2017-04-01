package io.github.ghosthopper;

public abstract class PlayFigure {

  private PlayField field;

  public PlayFigure() {
    super();
  }

  public abstract boolean isHuman();

  public abstract PlayFigureType getType();

  public PlayField getField() {
    return this.field;
  }

  public void setField(PlayField field) {
    this.field = field;
  }

}
