/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.player;

import io.github.ghosthopper.event.PlayEvent;
import io.github.ghosthopper.game.PlayGame;

/**
 * A {@link PlayEvent} that notifies about a change in turn from a {@link #getOldPlayer() old player} to a
 * {@link #getNewPlayer() new player} in a {@link PlayGame#isTurnGame() turn game}.
 */
public class PlayerTurnEvent implements PlayEvent {

  private final Player oldPlayer;

  private final Player newPlayer;

  /**
   * The constructor.
   *
   * @param oldPlayer the {@link #getOldPlayer() old player}.
   * @param newPlayer the {@link #getNewPlayer() new player}.
   */
  public PlayerTurnEvent(Player oldPlayer, Player newPlayer) {
    super();
    this.oldPlayer = oldPlayer;
    this.newPlayer = newPlayer;
  }

  /**
   * @return the {@link Player} that was previously {@link PlayGame#getCurrentPlayer() active} before this event. May be
   *         {@code null}.
   */
  public Player getOldPlayer() {

    return this.oldPlayer;
  }

  /**
   * @return the {@link Player} that became active {@link PlayGame#getCurrentPlayer() active} after this event. May be
   *         {@code null}.
   */
  public Player getNewPlayer() {

    return this.newPlayer;
  }

}
