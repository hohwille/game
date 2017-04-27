/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.asset;

import io.github.ghosthopper.event.PlayEvent;
import io.github.ghosthopper.figure.PlayFigure;
import io.github.ghosthopper.position.PlayPosition;

/**
 * A {@link PlayEvent} that notifies about a {@link #getAsset() asset} that changed its {@link PlayAsset#getPosition()
 * position} from an {@link #getOldPosition() old position} to a {@link #getNewPosition() new position}.
 *
 * @param <A> the type of the {@link #getAsset() asset}.
 */
public abstract class PlayAssetPositionEvent<A extends PlayAsset<?>> implements PlayEvent {

  private final PlayPosition oldPosition;

  private final A asset;

  private final PlayPosition newPosition;

  /**
   * The constructor.
   *
   * @param oldPosition the {@link #getOldPosition() old position}.
   * @param asset the {@link #getAsset() asset}.
   */
  public PlayAssetPositionEvent(PlayPosition oldPosition, A asset) {
    this(oldPosition, asset, asset.getPosition());
  }

  /**
   * The constructor.
   *
   * @param oldPosition the {@link #getOldPosition() old position}.
   * @param asset the {@link #getAsset() asset}.
   * @param newPosition the {@link #getNewPosition() new position}.
   */
  public PlayAssetPositionEvent(PlayPosition oldPosition, A asset, PlayPosition newPosition) {
    super();
    this.oldPosition = oldPosition;
    this.asset = asset;
    this.newPosition = newPosition;
  }

  /**
   * @return the {@link PlayAsset} that has changed its {@link PlayAsset#getPosition() position}.
   */
  public A getAsset() {

    return this.asset;
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

}
