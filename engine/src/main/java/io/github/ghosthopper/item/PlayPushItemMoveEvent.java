/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.item;

import io.github.ghosthopper.field.PlayField;

/**
 * A {@link PlayItemMoveEvent} that notifies about a {@link #getAsset() push item} that has changed its
 * {@link PlayPushItem#getLocation() location} from an {@link #getOldLocation() old location} to a
 * {@link #getNewLocation() new location}.
 */
public class PlayPushItemMoveEvent extends PlayItemMoveEvent<PlayField, PlayPushItem> {

  /**
   * The constructor.
   *
   * @param oldLocation the {@link #getOldLocation() old location}.
   * @param asset the {@link #getAsset() push item}.
   */
  public PlayPushItemMoveEvent(PlayField oldLocation, PlayPushItem asset) {
    super(oldLocation, asset);
  }

  /**
   * The constructor.
   *
   * @param oldLocation the {@link #getOldLocation() old location}.
   * @param asset the {@link #getAsset() push item}.
   * @param newLocation the {@link #getNewLocation() new location}.
   */
  public PlayPushItemMoveEvent(PlayField oldLocation, PlayPushItem asset, PlayField newLocation) {
    super(oldLocation, asset, newLocation);
  }

}
