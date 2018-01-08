/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.game.engine.figure;

import java.util.List;

import net.sf.mmm.game.engine.object.GameLocation;

/**
 * This is the interface for an object that may have {@link #getFigures() figures}.
 */
public interface GameAttributeFiguresAdvanced extends GameAttributeFigures, GameLocation {

  /**
   * @param figure the {@link GameFigure} that shall potentially be {@link #addFigure(GameFigure) added}.
   * @return {@code true} if it can be {@link #addFigure(GameFigure) added}, {@code false} otherwise.
   */
  boolean canAddFigure(GameFigure figure);

  @Override
  default boolean removeFigure(GameFigure figure) {

    return removeFigure(figure, true);
  }

  /**
   * @param figure the {@link GameFigure} to {@link List#remove(Object) remove} from the {@link #getFigures() figures}.
   * @param updateLocation - {@code true} if the {@link GameFigure#getLocation() location} shall also be
   *        {@link GameFigure#setLocation(net.sf.mmm.game.engine.field.GameField) updated} and a
   *        {@link GameFigureMoveEvent} should be send, {@code false} otherwise (if called from
   *        {@link #addFigure(GameFigure)} or from
   *        {@link GameFigure#setLocation(net.sf.mmm.game.engine.field.GameField)}).
   * @return {@code true} if the operation was successful, {@code false} otherwise (e.g. this object can not take the
   *         given {@link GameFigure}).
   */
  boolean removeFigure(GameFigure figure, boolean updateLocation);

}
