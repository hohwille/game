/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.game.engine.figure;

import java.util.List;

/**
 * This is the interface for an object that may have {@link #getFigures() figures}.
 */
public interface GameAttributeFigures {

  /**
   * @return an {@link java.util.Collections#unmodifiableList(List) unmodifiable list} of {@link GameFigure}s contained
   *         in this object.
   */
  List<GameFigure> getFigures();

  /**
   * @param figure the {@link GameFigure} to {@link List#add(Object) add} to the {@link #getFigures() figures}.
   * @return {@code true} if the operation was successful, {@code false} otherwise (e.g. this object can not take the
   *         given {@link GameFigure}).
   */
  boolean addFigure(GameFigure figure);

  /**
   * @param figure the {@link GameFigure} to {@link List#remove(Object) remove} from the {@link #getFigures() figures}.
   * @return {@code true} if the operation was successful, {@code false} otherwise (e.g. this object can not take the
   *         given {@link GameFigure}).
   */
  boolean removeFigure(GameFigure figure);

}
