package io.github.ghosthopper;

import java.util.ArrayList;
import java.util.List;

/**
 * The {@link PlayGround} is the main object of this game. It contains the {@link #getFieldTopLeft() top-left field}
 * that is connected with all other {@link PlayField}s. Also it contains the {@link #getHumanPlayers() human players}.
 */
public class PlayGround {

  private final PlayField fieldTopLeft;

  private final List<PlayFigureHuman> humanPlayers;

  private int currentHumanPlayer;

  private final List<PlayFigureBot> botPlayers;

  private int currentBotPlayer;

  /**
   * The constructor.
   */
  public PlayGround() {
    super();
    this.humanPlayers = new ArrayList<>();
    this.botPlayers = new ArrayList<>();
    this.fieldTopLeft = PlayField.createTopLeftField(this);
  }

  /**
   * @return the {@link List} of {@link PlayFigureHuman human players}.
   */
  public List<PlayFigureHuman> getHumanPlayers() {

    return this.humanPlayers;
  }

  /**
   * @return the current {@link PlayFigureHuman human player} whose turn is.
   */
  public PlayFigureHuman getCurrentPlayer() {

    return this.humanPlayers.get(this.currentHumanPlayer);
  }

  /**
   * @return the {@link List} of {@link PlayFigureBot bot players}.
   */
  public List<PlayFigureBot> getBotPlayers() {

    return this.botPlayers;
  }

  /**
   * Ends the current turn.
   *
   * @return the next {@link PlayFigureHuman human player} whose turn is just starting.
   */
  public PlayFigureHuman nextPlayer() {

    this.currentHumanPlayer++;
    if (this.currentHumanPlayer >= this.humanPlayers.size()) {
      this.currentHumanPlayer = 0;
    }
    return getCurrentPlayer();
  }

  /**
   * @return the top left {@link PlayField}. From this {@link PlayField} you can reach all other {@link PlayField}s and
   *         navigate the entire {@link PlayGround}.
   */
  public PlayField getFieldTopLeft() {

    return this.fieldTopLeft;
  }

}
