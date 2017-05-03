/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.color;

import io.github.ghosthopper.properties.PlayAttributeProperties;
import io.github.ghosthopper.properties.PlayPropertyKey;

/**
 * This is the interface for an object that optionally can have a {@link #getColor() color}.
 */
public interface PlayAttributeColorAdvanced extends PlayAttributeColor, PlayAttributeProperties {

  @Override
  default PlayColor getColor() {

    return getProperties().get(PlayPropertyKey.COLOR);
  }

  /**
   * @param color the new value of {@link #getColor()}.
   */
  default void setColor(PlayColor color) {

    getProperties().set(PlayPropertyKey.COLOR, color);
  }

}
