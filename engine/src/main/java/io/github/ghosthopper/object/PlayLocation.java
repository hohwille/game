/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.object;

import io.github.ghosthopper.asset.PlayAsset;
import io.github.ghosthopper.asset.PlayAssetPositionEvent;
import io.github.ghosthopper.position.PlayPosition;

/**
 * Interface for the {@link PlayAsset#getLocation() location} of a {@link PlayAsset}.
 */
public interface PlayLocation {

  /**
   * Called from {@link io.github.ghosthopper.position.PlayAttributePosition#setPosition(PlayPosition, boolean)} if
   * {@code updateLocation} is {@code true}.
   *
   * @param positionEvent the {@link PlayAssetPositionEvent}.
   */
  default void updatePosition(PlayAssetPositionEvent<?> positionEvent) {
    // nothing by default. Override to change...
  }
}
