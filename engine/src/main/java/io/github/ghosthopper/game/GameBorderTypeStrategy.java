/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.game;

import io.github.ghosthopper.border.GameBorder;
import io.github.ghosthopper.border.GameBorderType;
import io.github.ghosthopper.direction.GameDirection;
import io.github.ghosthopper.field.GameField;

/**
 * {@link FunctionalInterface} for the strategy that decides about the {@link GameBorder#getType() types} of the
 * {@link GameBorder}s.
 */
@FunctionalInterface
public interface GameBorderTypeStrategy {

  /**
   * @param field the current {@link GameField}.
   * @param x the position in x-direction starting with {@code 1}.
   * @param y the position in y-direction starting with {@code 1}.
   * @param width the maximum number in x-direction.
   * @param height the maximum number in y-direction.
   * @param direction
   * @return the {@link GameBorderType} used to {@link GameField#createBorder(GameBorderType, GameDirection) create} a
   *         new {@link GameBorder} for the given {@link GameField} in the given {@link GameDirection}. May be
   *         {@code null} to initialize at a later moment.
   */
  GameBorderType getType(GameField field, int x, int y, int width, int height, GameDirection direction);

}
