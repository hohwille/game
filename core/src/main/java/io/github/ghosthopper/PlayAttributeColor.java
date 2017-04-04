/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper;

/**
 * This is the interface for an object that optionally can have a {@link #getColor() color}.
 */
public interface PlayAttributeColor {

  /**
   * @return the {@link PlayColor} of this object. May be {@code null} if undefined. If the {@link PlayColor} is
   *         defined, the game-engine can visualize that color in the UI of the game.
   */
  PlayColor getColor();

}
