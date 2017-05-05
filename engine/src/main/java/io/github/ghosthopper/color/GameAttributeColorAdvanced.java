/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.color;

import io.github.ghosthopper.properties.GameAttributeProperties;
import io.github.ghosthopper.properties.GamePropertyKey;

/**
 * This is the interface for an object that optionally can have a {@link #getColor() color}.
 */
public interface GameAttributeColorAdvanced extends GameAttributeColor, GameAttributeProperties {

  @Override
  default GameColor getColor() {

    return getProperties().get(GamePropertyKey.COLOR);
  }

  /**
   * @param color the new value of {@link #getColor()}.
   */
  default void setColor(GameColor color) {

    getProperties().set(GamePropertyKey.COLOR, color);
  }

}
