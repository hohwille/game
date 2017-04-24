/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.figure;

import io.github.ghosthopper.event.PlayEvent;
import io.github.ghosthopper.game.PlayGame;

/**
 * A {@link PlayEvent} that notifies about a change in turn from a {@link #getOldFigure() old figure} to a
 * {@link #getNewFigure() new figure} in a {@link PlayGame#isTurnGame() turn game}.
 */
public class PlayFigureTurnEvent implements PlayEvent {

  private final PlayFigure oldFigure;

  private final PlayFigure newFigure;

  /**
   * The constructor.
   *
   * @param oldFigure the {@link #getOldFigure() old figure}.
   * @param newFigure the {@link #getNewFigure() new figure}.
   */
  public PlayFigureTurnEvent(PlayFigure oldFigure, PlayFigure newFigure) {
    super();
    this.oldFigure = oldFigure;
    this.newFigure = newFigure;
  }

  /**
   * @return the {@link PlayFigure} that was previously {@link PlayGame#getCurrentFigure() active} before this event.
   *         May be {@code null}.
   */
  public PlayFigure getOldFigure() {

    return this.oldFigure;
  }

  /**
   * @return the {@link PlayFigure} that became active {@link PlayGame#getCurrentFigure() active} after this event. May
   *         be {@code null}.
   */
  public PlayFigure getNewFigure() {

    return this.newFigure;
  }

}
