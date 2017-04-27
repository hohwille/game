/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.figure;

import java.util.List;

import io.github.ghosthopper.object.PlayLocation;

/**
 * This is the interface for an object that may have {@link #getFigures() figures}.
 */
public interface PlayAttributeFiguresAdvanced extends PlayAttributeFigures, PlayLocation {

  /**
   * @param figure the {@link PlayFigure} that shall potentially be {@link #addFigure(PlayFigure) added}.
   * @return {@code true} if it can be {@link #addFigure(PlayFigure) added}, {@code false} otherwise.
   */
  boolean canAddFigure(PlayFigure figure);

  @Override
  default boolean removeFigure(PlayFigure figure) {

    return removeFigure(figure, true);
  }

  /**
   * @param figure the {@link PlayFigure} to {@link List#remove(Object) remove} from the {@link #getFigures() figures}.
   * @param updateLocation - {@code true} if the {@link PlayFigure#getLocation() location} shall also be
   *        {@link PlayFigure#setLocation(io.github.ghosthopper.field.PlayField) updated} and a
   *        {@link PlayFigureMoveEvent} should be send, {@code false} otherwise (if called from
   *        {@link #addFigure(PlayFigure)} or from
   *        {@link PlayFigure#setLocation(io.github.ghosthopper.field.PlayField)}).
   * @return {@code true} if the operation was successful, {@code false} otherwise (e.g. this object can not take the
   *         given {@link PlayFigure}).
   */
  boolean removeFigure(PlayFigure figure, boolean updateLocation);

}
