/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.player;

import io.github.ghosthopper.event.GameEvent;
import io.github.ghosthopper.game.Game;

/**
 * A {@link GameEvent} that notifies about a change in turn from a {@link #getOldPlayer() old player} to a
 * {@link #getNewPlayer() new player} in a {@link Game#isTurnGame() turn game}.
 */
public class GamePlayerTurnEvent implements GameEvent {

  private final GamePlayer oldPlayer;

  private final GamePlayer newPlayer;

  /**
   * The constructor.
   *
   * @param oldPlayer the {@link #getOldPlayer() old player}.
   * @param newPlayer the {@link #getNewPlayer() new player}.
   */
  public GamePlayerTurnEvent(GamePlayer oldPlayer, GamePlayer newPlayer) {
    super();
    this.oldPlayer = oldPlayer;
    this.newPlayer = newPlayer;
  }

  /**
   * @return the {@link GamePlayer} that was previously {@link Game#getCurrentPlayer() active} before this event. May be
   *         {@code null}.
   */
  public GamePlayer getOldPlayer() {

    return this.oldPlayer;
  }

  /**
   * @return the {@link GamePlayer} that became active {@link Game#getCurrentPlayer() active} after this event. May be
   *         {@code null}.
   */
  public GamePlayer getNewPlayer() {

    return this.newPlayer;
  }

}
