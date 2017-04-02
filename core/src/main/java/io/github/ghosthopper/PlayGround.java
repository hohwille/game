package io.github.ghosthopper;

import java.util.ArrayList;
import java.util.List;

/**
 * The {@link PlayGround} is the main object of this game. It contains the {@link #getFieldTopLeft() top-left field}
 * that is connected with all other {@link PlayField}s. Also it contains the {@link #getPlayers() human players}.
 */
public class PlayGround {

  private final List<PlayFigureHuman> players;

  private final PlayField fieldTopLeft;

  private int currentHumanFigure;

  /**
   * The constructor.
   */
  public PlayGround() {
    super();
    this.players = new ArrayList<>();
    this.fieldTopLeft = PlayField.createTopLeftField(this);
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
