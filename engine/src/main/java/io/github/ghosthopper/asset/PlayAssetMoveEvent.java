/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.asset;

import io.github.ghosthopper.event.PlayEvent;
import io.github.ghosthopper.figure.PlayFigure;
import io.github.ghosthopper.object.PlayLocation;

/**
 * A {@link PlayEvent} that notifies about a {@link #getAsset() asset} that changed its {@link PlayAsset#getLocation()
 * location} from an {@link #getOldLocation() old location} to a {@link #getNewLocation() new location}.
 *
 * @param <L> the type of the {@link #getOldLocation() old} and {@link #getNewLocation() new location}.
 * @param <A> the type of the {@link #getAsset() asset}.
 */
public abstract class PlayAssetMoveEvent<L extends PlayLocation, A extends PlayAsset<L>> implements PlayEvent {

  private final L oldLocation;

  private final A asset;

  private final L newLocation;

  /**
   * The constructor.
   *
   * @param oldLocation the {@link #getOldLocation() old location}.
   * @param asset the {@link #getAsset() asset}.
   */
  public PlayAssetMoveEvent(L oldLocation, A asset) {
    this(oldLocation, asset, asset.getLocation());
  }

  /**
   * The constructor.
   *
   * @param oldLocation the {@link #getOldLocation() old location}.
   * @param asset the {@link #getAsset() asset}.
   * @param newLocation the {@link #getNewLocation() new location}.
   */
  public PlayAssetMoveEvent(L oldLocation, A asset, L newLocation) {
    super();
    this.oldLocation = oldLocation;
    this.asset = asset;
    this.newLocation = newLocation;
  }

  /**
   * @return the {@link PlayAsset} that has changed its {@link PlayAsset#getLocation() location}.
   */
  public A getAsset() {

    return this.asset;
  }

  /**
   * @return the old {@link PlayAsset#getLocation() location} of the {@link #getAsset() asset} before this event.
   */
  public L getOldLocation() {

    return this.oldLocation;
  }

  /**
   * @return the new {@link PlayFigure#getLocation() location} of the {@link #getAsset() asset} after this event.
   */
  public L getNewLocation() {

    return this.newLocation;
  }

}
