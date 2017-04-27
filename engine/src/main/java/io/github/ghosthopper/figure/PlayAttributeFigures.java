/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.figure;

import java.util.List;

/**
 * This is the interface for an object that may have {@link #getFigures() figures}.
 */
public interface PlayAttributeFigures {

  /**
   * @return an {@link java.util.Collections#unmodifiableList(List) unmodifiable list} of {@link PlayFigure}s contained
   *         in this object.
   */
  List<PlayFigure> getFigures();

  /**
   * @param figure the {@link PlayFigure} to {@link List#add(Object) add} to the {@link #getFigures() figures}.
   * @return {@code true} if the operation was successful, {@code false} otherwise (e.g. this object can not take the
   *         given {@link PlayFigure}).
   */
  boolean addFigure(PlayFigure figure);

  /**
   * @param figure the {@link PlayFigure} to {@link List#remove(Object) remove} from the {@link #getFigures() figures}.
   * @return {@code true} if the operation was successful, {@code false} otherwise (e.g. this object can not take the
   *         given {@link PlayFigure}).
   */
  boolean removeFigure(PlayFigure figure);

}
