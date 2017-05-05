/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.figure;

import io.github.ghosthopper.asset.GameAssetMoveEvent;
import io.github.ghosthopper.event.GameEvent;

/**
 * A {@link GameAssetMoveEvent} that notifies about a {@link #getFigure() figure} that has been #.
 */
public class GameFigureGroupEvent implements GameEvent {

  private final GameFigure figure;

  private final GameFigureGroup group;

  private final boolean added;

  /**
   * The constructor.
   *
   * @param figure the {@link GameFigure}.
   * @param group the {@link GameFigureGroup}.
   * @param added - see {@link #isAdded()}.
   */
  public GameFigureGroupEvent(GameFigure figure, GameFigureGroup group, boolean added) {
    super();
    this.figure = figure;
    this.group = group;
    this.added = added;
  }

  /**
   * @return {@code true} if the {@link #getFigure() figure} has been added to the {@link GameFigureGroup},
   *         {@code false} otherwise (if it was removed).
   */
  public boolean isAdded() {

    return this.added;
  }

  /**
   * @return the {@link GameFigure} that has been grouped or un-grouped.
   */
  public GameFigure getFigure() {

    return this.figure;
  }

  /**
   * @return the {@link GameFigureGroup} the {@link #getFigure() figure} has been added to or removed from.
   */
  public GameFigureGroup getGroup() {

    return this.group;
  }
}
