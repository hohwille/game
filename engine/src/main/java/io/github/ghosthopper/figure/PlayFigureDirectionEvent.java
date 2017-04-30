/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.figure;

import io.github.ghosthopper.event.PlayEvent;
import io.github.ghosthopper.move.PlayDirection;

/**
 * A {@link PlayEvent} that notifies about a {@link #getFigure() figure} that changed its
 * {@link PlayFigure#getDirection() direction} from an {@link #getOldDirection() old direction} to a
 * {@link #getNewDirection() new direction}.
 */
public class PlayFigureDirectionEvent implements PlayEvent {

  private final PlayDirection oldDirection;

  private final PlayFigure figure;

  private final PlayDirection newDirection;

  /**
   * The constructor.
   *
   * @param oldDirection the {@link #getOldDirection() old direction}.
   * @param figure the moved {@link #getFigure() figure}.
   */
  public PlayFigureDirectionEvent(PlayDirection oldDirection, PlayFigure figure) {
    this(oldDirection, figure, figure.getDirection());
  }

  /**
   * The constructor.
   *
   * @param oldDirection the {@link #getOldDirection() old direction}.
   * @param figure the moved {@link #getFigure() figure}.
   * @param newDirection the {@link #getNewDirection() new direction}.
   */
  public PlayFigureDirectionEvent(PlayDirection oldDirection, PlayFigure figure, PlayDirection newDirection) {
    super();
    this.oldDirection = oldDirection;
    this.figure = figure;
    this.newDirection = newDirection;
  }

  /**
   * @return the {@link PlayFigure} that changed its {@link PlayFigure#getDirection() direction}.
   */
  public PlayFigure getFigure() {

    return this.figure;
  }

  /**
   * @return the old {@link PlayFigure#getDirection() direction} of the {@link #getFigure() figure} before this event.
   */
  public PlayDirection getOldDirection() {

    return this.oldDirection;
  }

  /**
   * @return the new {@link PlayFigure#getDirection() direction} of the {@link #getFigure() figure} after this event.
   */
  public PlayDirection getNewDirection() {

    return this.newDirection;
  }

}
