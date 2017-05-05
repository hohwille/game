/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.color;

/**
 * This is the interface for an object that optionally can have a {@link #getColor() color}.
 */
public interface GameAttributeColor {

  /**
   * @return the {@link GameColor} of this object. May be {@code null} if undefined. If the {@link GameColor} is
   *         defined, the game-engine can visualize that color in the UI of the game.
   */
  GameColor getColor();

}
