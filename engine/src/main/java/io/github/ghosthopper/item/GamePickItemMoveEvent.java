/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.item;

import io.github.ghosthopper.position.GamePosition;

/**
 * A {@link GameItemMoveEvent} that notifies about a {@link #getAsset() pick item} that has changed its
 * {@link GamePickItem#getLocation() location} from an {@link #getOldLocation() old location} to a
 * {@link #getNewLocation() new location}.
 */
public class GamePickItemMoveEvent extends GameItemMoveEvent<GameAttributePickItems, GamePickItem> {

  /**
   * The constructor.
   *
   * @param oldLocation the {@link #getOldLocation() old location}.
   * @param oldPosition the {@link #getOldPosition() old position}.
   * @param asset the {@link #getAsset() item}.
   */
  public GamePickItemMoveEvent(GameAttributePickItems oldLocation, GamePosition oldPosition, GamePickItem asset) {
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
  public GamePickItemMoveEvent(GameAttributePickItems oldLocation, GamePosition oldPosition, GamePickItem asset, GameAttributePickItems newLocation,
      GamePosition newPosition) {
    super(oldLocation, oldPosition, asset, newLocation, newPosition);
  }

}
