/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.game.engine.figure;

import net.sf.mmm.game.engine.Game;
import net.sf.mmm.game.engine.event.GameEvent;

/**
 * A {@link GameEvent} that notifies about a change in turn from a {@link #getOldFigure() old figure} to a
 * {@link #getNewFigure() new figure} in a {@link Game#isTurnGame() turn game}.
 */
public class GameFigureTurnEvent implements GameEvent {

  private final GameFigure oldFigure;

  private final GameFigure newFigure;

  /**
   * The constructor.
   *
   * @param oldFigure the {@link #getOldFigure() old figure}.
   * @param newFigure the {@link #getNewFigure() new figure}.
   */
  public GameFigureTurnEvent(GameFigure oldFigure, GameFigure newFigure) {
    super();
    this.oldFigure = oldFigure;
    this.newFigure = newFigure;
  }

  /**
   * @return the {@link GameFigure} that was previously {@link Game#getCurrentFigure() active} before this event.
   *         May be {@code null}.
   */
  public GameFigure getOldFigure() {

    return this.oldFigure;
  }

  /**
   * @return the {@link GameFigure} that became active {@link Game#getCurrentFigure() active} after this event. May
   *         be {@code null}.
   */
  public GameFigure getNewFigure() {

    return this.newFigure;
  }

}
