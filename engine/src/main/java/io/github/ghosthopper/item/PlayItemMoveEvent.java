/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.item;

import io.github.ghosthopper.asset.PlayAssetMoveEvent;
import io.github.ghosthopper.object.PlayLocation;
import io.github.ghosthopper.position.PlayPosition;

/**
 * A {@link PlayAssetMoveEvent} that notifies about a {@link #getAsset() item} that has changed its
 * {@link PlayPickItem#getLocation() location} from an {@link #getOldLocation() old location} to a
 * {@link #getNewLocation() new location}.
 *
 * @param <L> the type of the {@link #getOldLocation() old} and {@link #getNewLocation() new location}.
 * @param <A> the type of the {@link #getAsset() asset}.
 */
public abstract class PlayItemMoveEvent<L extends PlayLocation, A extends PlayItem<L, ?>> extends PlayAssetMoveEvent<L, A> {

  /**
   * The constructor.
   *
   * @param oldLocation the {@link #getOldLocation() old location}.
   * @param oldPosition the {@link #getOldPosition() old position}.
   * @param asset the {@link #getAsset() item}.
   */
  public PlayItemMoveEvent(L oldLocation, PlayPosition oldPosition, A asset) {
    super(oldLocation, oldPosition, asset);
  }

  /**
   * The constructor.
   *
   * @param oldLocation the {@link #getOldLocation() old location}.
   * @param oldPosition the {@link #getOldPosition() old position}.
   * @param asset the {@link #getAsset() item}.
   * @param newLocation the {@link #getNewLocation() new location}.
   * @param newPosition the {@link #getNewPosition() new position}.
   */
  public PlayItemMoveEvent(L oldLocation, PlayPosition oldPosition, A asset, L newLocation, PlayPosition newPosition) {
    super(oldLocation, oldPosition, asset, newLocation, newPosition);
  }

}
