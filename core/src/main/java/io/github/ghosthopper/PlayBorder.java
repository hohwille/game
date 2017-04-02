package io.github.ghosthopper;

public class PlayBorder {
  private final PlayField fieldLeftOrTop;
  private final PlayField fieldRightOrBottom;
  private final PlayBorderType type;

  public PlayBorder(PlayField fieldLeftOrTop, PlayBorderType type, PlayField fieldRightOrBottom) {
    super();
    this.fieldLeftOrTop = fieldLeftOrTop;
    this.fieldRightOrBottom = fieldRightOrBottom;
    this.type = type;
  }

  public PlayField getFieldLeftOrTop() {
    return this.fieldLeftOrTop;
  }

  public PlayBorderType getType() {
    return this.type;
  }

  public PlayField getFieldRightOrBottom() {
    return this.fieldRightOrBottom;
  }

  public boolean canPass(PlayFigureHuman player) {

    return this.type.canPass(player, this);
  }
}
