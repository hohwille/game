package io.github.ghosthopper;

import java.util.ArrayList;
import java.util.List;

public class PlayGround {

  private final List<PlayFigureHuman> players;

  private final PlayField fieldTopLeft;

  private int currentHumanFigure;

  public PlayGround() {
    super();
    this.players = new ArrayList<>();
    this.fieldTopLeft = new PlayField(this);
  }

  public List<PlayFigureHuman> getPlayers() {
    return this.players;
  }

  public PlayFigureHuman getCurrentPlayer() {

    return this.players.get(this.currentHumanFigure);
  }

  public PlayField getFieldTopLeft() {
    return this.fieldTopLeft;
  }

}
