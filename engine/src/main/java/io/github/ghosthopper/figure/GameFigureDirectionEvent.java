/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.figure;

import io.github.ghosthopper.direction.GameDirection;
import io.github.ghosthopper.event.GameEvent;

/**
 * A {@link GameEvent} that notifies about a {@link #getFigure() figure} that changed its
 * {@link GameFigure#getDirection() direction} from an {@link #getOldDirection() old direction} to a
 * {@link #getNewDirection() new direction}.
 */
public class GameFigureDirectionEvent implements GameEvent {

  private final GameDirection oldDirection;

  private final GameFigure figure;

  private final GameDirection newDirection;

  /**
   * The constructor.
   *
   * @param oldDirection the {@link #getOldDirection() old direction}.
   * @param figure the moved {@link #getFigure() figure}.
   */
  public GameFigureDirectionEvent(GameDirection oldDirection, GameFigure figure) {
    this(oldDirection, figure, figure.getDirection());
  }

  /**
   * The constructor.
   *
   * @param oldDirection the {@link #getOldDirection() old direction}.
   * @param figure the moved {@link #getFigure() figure}.
   * @param newDirection the {@link #getNewDirection() new direction}.
   */
  public GameFigureDirectionEvent(GameDirection oldDirection, GameFigure figure, GameDirection newDirection) {
    super();
    this.oldDirection = oldDirection;
    this.figure = figure;
    this.newDirection = newDirection;
  }

  /**
   * @return the {@link GameFigure} that changed its {@link GameFigure#getDirection() direction}.
   */
  public GameFigure getFigure() {

    return this.figure;
  }

  /**
   * @return the old {@link GameFigure#getDirection() direction} of the {@link #getFigure() figure} before this event.
   */
  public GameDirection getOldDirection() {

    return this.oldDirection;
  }

  /**
   * @return the new {@link GameFigure#getDirection() direction} of the {@link #getFigure() figure} after this event.
   */
  public GameDirection getNewDirection() {

    return this.newDirection;
  }

}
