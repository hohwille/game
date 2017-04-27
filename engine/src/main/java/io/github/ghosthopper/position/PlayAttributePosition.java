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
  default void setPosition(PlayPosition position) {

    setPosition(position, true);
  }

  /**
   * @param position the new {@link PlayPosition}.
   * @param updateLocation - {@code true} if the {@link io.github.ghosthopper.asset.PlayAsset#getLocation() location}
   *        shall be updated, {@code false} otherwise (if called from the location).
   */
  void setPosition(PlayPosition position, boolean updateLocation);

}
