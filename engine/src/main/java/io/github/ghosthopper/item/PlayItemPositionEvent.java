/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.item;

import io.github.ghosthopper.asset.PlayAsset;
import io.github.ghosthopper.asset.PlayAssetMoveEvent;
import io.github.ghosthopper.asset.PlayAssetPositionEvent;
import io.github.ghosthopper.position.PlayPosition;

/**
 * A {@link PlayAssetMoveEvent} that notifies about a {@link #getAsset() item} that has changed its
 * {@link PlayAsset#getPosition() position} from an {@link #getOldPosition() old position} to a {@link #getNewPosition()
 * new position}.
 */
public class PlayItemPositionEvent extends PlayAssetPositionEvent<PlayItem<?, ?>> {

  /**
   * The constructor.
   *
   * @param oldPosition the {@link #getOldPosition() old position}.
   * @param asset the {@link #getAsset() item}.
   */
  public PlayItemPositionEvent(PlayPosition oldPosition, PlayItem<?, ?> asset) {
    super(oldPosition, asset);
  }

  /**
   * The constructor.
   *
   * @param oldPosition the {@link #getOldPosition() old position}.
   * @param asset the {@link #getAsset() item}.
   * @param newPosition the {@link #getNewPosition() new position}.
   */
  public PlayItemPositionEvent(PlayPosition oldPosition, PlayItem<?, ?> asset, PlayPosition newPosition) {
    super(oldPosition, asset, newPosition);
  }

}
