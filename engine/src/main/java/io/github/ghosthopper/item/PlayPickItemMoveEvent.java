/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.item;

import io.github.ghosthopper.position.PlayPosition;

/**
 * A {@link PlayItemMoveEvent} that notifies about a {@link #getAsset() pick item} that has changed its
 * {@link PlayPickItem#getLocation() location} from an {@link #getOldLocation() old location} to a
 * {@link #getNewLocation() new location}.
 */
public class PlayPickItemMoveEvent extends PlayItemMoveEvent<PlayAttributePickItems, PlayPickItem> {

  /**
   * The constructor.
   *
   * @param oldLocation the {@link #getOldLocation() old location}.
   * @param oldPosition the {@link #getOldPosition() old position}.
   * @param asset the {@link #getAsset() item}.
   */
  public PlayPickItemMoveEvent(PlayAttributePickItems oldLocation, PlayPosition oldPosition, PlayPickItem asset) {
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
  public PlayPickItemMoveEvent(PlayAttributePickItems oldLocation, PlayPosition oldPosition, PlayPickItem asset, PlayAttributePickItems newLocation,
      PlayPosition newPosition) {
    super(oldLocation, oldPosition, asset, newLocation, newPosition);
  }

}
