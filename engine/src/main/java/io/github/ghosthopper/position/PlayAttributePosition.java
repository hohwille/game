/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.position;

/**
 * This is the interface for an object that {@link #getPosition() has} a {@link PlayPosition}.
 */
public interface PlayAttributePosition {

  /**
   * @return the current {@link PlayPosition} of this asset. Defaults to {@link PlayPosition#CENTER} if not set.
   */
  PlayPosition getPosition();

  /**
   * @param position the new {@link PlayPosition}.
   */
  void setPosition(PlayPosition position);

}
