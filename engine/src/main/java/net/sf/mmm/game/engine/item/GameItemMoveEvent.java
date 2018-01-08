/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.game.engine.item;

import net.sf.mmm.game.engine.asset.GameAssetMoveEvent;
import net.sf.mmm.game.engine.object.GameLocation;
import net.sf.mmm.game.engine.position.GamePosition;

/**
 * A {@link GameAssetMoveEvent} that notifies about a {@link #getAsset() item} that has changed its
 * {@link GameItem#getLocation() location} from an {@link #getOldLocation() old location} to a {@link #getNewLocation()
 * new location}.
 *
 * @param <L> the type of the {@link #getOldLocation() old} and {@link #getNewLocation() new location}.
 * @param <A> the type of the {@link #getAsset() asset}.
 */
public abstract class GameItemMoveEvent<L extends GameLocation, A extends GameItem<?, L, ?>> extends GameAssetMoveEvent<L, A> {

  /**
   * The constructor.
   *
   * @param oldLocation the {@link #getOldLocation() old location}.
   * @param oldPosition the {@link #getOldPosition() old position}.
   * @param asset the {@link #getAsset() item}.
   */
  public GameItemMoveEvent(L oldLocation, GamePosition oldPosition, A asset) {

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
  public GameItemMoveEvent(L oldLocation, GamePosition oldPosition, A asset, L newLocation, GamePosition newPosition) {

    super(oldLocation, oldPosition, asset, newLocation, newPosition);
  }

}
