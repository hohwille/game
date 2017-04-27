/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.figure;

import io.github.ghosthopper.asset.PlayAssetMoveEvent;
import io.github.ghosthopper.event.PlayEvent;

/**
 * A {@link PlayAssetMoveEvent} that notifies about a {@link #getFigure() figure} that has been #.
 */
public class PlayFigureGroupEvent implements PlayEvent {

  private final PlayFigure figure;

  private final PlayFigureGroup group;

  private final boolean added;

  /**
   * The constructor.
   *
   * @param figure the {@link PlayFigure}.
   * @param group the {@link PlayFigureGroup}.
   * @param added - see {@link #isAdded()}.
   */
  public PlayFigureGroupEvent(PlayFigure figure, PlayFigureGroup group, boolean added) {
    super();
    this.figure = figure;
    this.group = group;
    this.added = added;
  }

  /**
   * @return {@code true} if the {@link #getFigure() figure} has been added to the {@link PlayFigureGroup},
   *         {@code false} otherwise (if it was removed).
   */
  public boolean isAdded() {

    return this.added;
  }

  /**
   * @return the {@link PlayFigure} that has been grouped or un-grouped.
   */
  public PlayFigure getFigure() {

    return this.figure;
  }

  /**
   * @return the {@link PlayFigureGroup} the {@link #getFigure() figure} has been added to or removed from.
   */
  public PlayFigureGroup getGroup() {

    return this.group;
  }
}
