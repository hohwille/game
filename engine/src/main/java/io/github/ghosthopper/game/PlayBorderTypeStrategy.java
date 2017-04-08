/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.game;

import io.github.ghosthopper.border.PlayBorder;
import io.github.ghosthopper.border.PlayBorderType;
import io.github.ghosthopper.field.PlayField;
import io.github.ghosthopper.move.PlayDirection;

/**
 * {@link FunctionalInterface} for the strategy that decides about the {@link PlayBorder#getType() types} of the
 * {@link PlayBorder}s.
 */
@FunctionalInterface
public interface PlayBorderTypeStrategy {

  /**
   * @param field the current {@link PlayField}.
   * @param x the position in x-direction starting with {@code 1}.
   * @param y the position in y-direction starting with {@code 1}.
   * @param width the maximum number in x-direction.
   * @param height the maximum number in y-direction.
   * @param direction
   * @return the {@link PlayBorderType} used to {@link PlayField#createBorder(PlayBorderType, PlayDirection) create} a
   *         new {@link PlayBorder} for the given {@link PlayField} in the given {@link PlayDirection}. May be
   *         {@code null} to initialize at a later moment.
   */
  PlayBorderType getType(PlayField field, int x, int y, int width, int height, PlayDirection direction);

}
