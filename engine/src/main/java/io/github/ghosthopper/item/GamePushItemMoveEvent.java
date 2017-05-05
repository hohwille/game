/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.item;

import io.github.ghosthopper.field.GameField;
import io.github.ghosthopper.position.GamePosition;

/**
 * A {@link GameItemMoveEvent} that notifies about a {@link #getAsset() push item} that has changed its
 * {@link GamePushItem#getLocation() location} from an {@link #getOldLocation() old location} to a
 * {@link #getNewLocation() new location}.
 */
public class GamePushItemMoveEvent extends GameItemMoveEvent<GameField, GamePushItem> {

  /**
   * The constructor.
   *
   * @param oldLocation the {@link #getOldLocation() old location}.
   * @param oldPosition the {@link #getOldPosition() old position}.
   * @param asset the {@link #getAsset() item}.
   */
  public GamePushItemMoveEvent(GameField oldLocation, GamePosition oldPosition, GamePushItem asset) {
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
  public GamePushItemMoveEvent(GameField oldLocation, GamePosition oldPosition, GamePushItem asset, GameField newLocation, GamePosition newPosition) {
    super(oldLocation, oldPosition, asset, newLocation, newPosition);
  }

}
