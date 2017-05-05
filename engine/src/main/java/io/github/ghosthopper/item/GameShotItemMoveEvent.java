/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.item;

import io.github.ghosthopper.field.GameField;
import io.github.ghosthopper.position.GamePosition;

/**
 * A {@link GameItemMoveEvent} that notifies about a {@link #getAsset() shot item} that has changed its
 * {@link GameShotItem#getLocation() location} from an {@link #getOldLocation() old location} to a
 * {@link #getNewLocation() new location}.
 */
public class GameShotItemMoveEvent extends GameItemMoveEvent<GameField, GameShotItem> {

  /**
   * The constructor.
   *
   * @param oldLocation the {@link #getOldLocation() old location}.
   * @param oldPosition the {@link #getOldPosition() old position}.
   * @param asset the {@link #getAsset() item}.
   */
  public GameShotItemMoveEvent(GameField oldLocation, GamePosition oldPosition, GameShotItem asset) {
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
  public GameShotItemMoveEvent(GameField oldLocation, GamePosition oldPosition, GameShotItem asset, GameField newLocation, GamePosition newPosition) {
    super(oldLocation, oldPosition, asset, newLocation, newPosition);
  }

}
