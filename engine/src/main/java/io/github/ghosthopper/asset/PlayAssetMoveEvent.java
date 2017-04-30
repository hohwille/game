/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.asset;

import io.github.ghosthopper.event.PlayEvent;
import io.github.ghosthopper.figure.PlayFigure;
import io.github.ghosthopper.object.PlayLocation;
import io.github.ghosthopper.position.PlayPosition;

/**
 * A {@link PlayEvent} that notifies about a {@link #getAsset() asset} that changed its {@link PlayAsset#getLocation()
 * location} from an {@link #getOldLocation() old location} to a {@link #getNewLocation() new location}.
 *
 * @param <L> the type of the {@link #getOldLocation() old} and {@link #getNewLocation() new location}.
 * @param <A> the type of the {@link #getAsset() asset}.
 */
public abstract class PlayAssetMoveEvent<L extends PlayLocation, A extends PlayAsset<L>> implements PlayEvent {

  private final L oldLocation;

  private final PlayPosition oldPosition;

  private final A asset;

  private final L newLocation;

  private PlayPosition newPosition;

  /**
   * The constructor.
   *
   * @param oldLocation the {@link #getOldLocation() old location}.
   * @param oldPosition the {@link #getOldPosition() old position}.
   * @param asset the {@link #getAsset() asset}.
   */
  public PlayAssetMoveEvent(L oldLocation, PlayPosition oldPosition, A asset) {
    this(oldLocation, oldPosition, asset, asset.getLocation(), asset.getPosition());
  }

  /**
   * The constructor.
   *
   * @param oldLocation the {@link #getOldLocation() old location}.
   * @param oldPosition the {@link #getOldPosition() old position}.
   * @param asset the {@link #getAsset() asset}.
   * @param newLocation the {@link #getNewLocation() new location}.
   * @param newPosition the {@link #getNewPosition() new position}.
   */
  public PlayAssetMoveEvent(L oldLocation, PlayPosition oldPosition, A asset, L newLocation, PlayPosition newPosition) {
    super();
    this.oldLocation = oldLocation;
    this.oldPosition = oldPosition;
    this.asset = asset;
    this.newLocation = newLocation;
    this.newPosition = newPosition;
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

  /**
   * @return the old {@link PlayAsset#getPosition() position} of the {@link #getAsset() asset} before this event.
   */
  public PlayPosition getOldPosition() {

    return this.oldPosition;
  }

  /**
   * @return the new {@link PlayFigure#getPosition() position} of the {@link #getAsset() asset} after this event.
   */
  public PlayPosition getNewPosition() {

    return this.newPosition;
  }

  /**
   * @return {@code true} if position has changed ({@link #getOldPosition()} != {@link #getNewPosition()}),
   *         {@code false} otherwise.
   */
  public boolean isPositionChange() {

    return (this.oldPosition != this.newPosition);
  }

  /**
   * @return {@code true} if location has changed ({@link #getOldLocation()} != {@link #getNewLocation()}),
   *         {@code false} otherwise.
   */
  public boolean isLocationChange() {

    return (this.oldLocation != this.newLocation);
  }
}
