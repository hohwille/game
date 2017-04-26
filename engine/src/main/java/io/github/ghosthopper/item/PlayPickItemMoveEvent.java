/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.item;

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
   * @param asset the {@link #getAsset() pick item}.
   */
  public PlayPickItemMoveEvent(PlayAttributePickItems oldLocation, PlayPickItem asset) {
    super(oldLocation, asset);
  }

  /**
   * The constructor.
   *
   * @param oldLocation the {@link #getOldLocation() old location}.
   * @param asset the {@link #getAsset() pick item}.
   * @param newLocation the {@link #getNewLocation() new location}.
   */
  public PlayPickItemMoveEvent(PlayAttributePickItems oldLocation, PlayPickItem asset, PlayAttributePickItems newLocation) {
    super(oldLocation, asset, newLocation);
  }

}
