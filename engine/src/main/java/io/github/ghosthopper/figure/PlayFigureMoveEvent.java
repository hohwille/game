/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.figure;

import io.github.ghosthopper.asset.PlayAssetMoveEvent;
import io.github.ghosthopper.field.PlayField;
import io.github.ghosthopper.position.PlayPosition;

/**
 * A {@link PlayAssetMoveEvent} that notifies about a {@link #getAsset() figure} that {@link PlayFigure#move() moved}
 * from an {@link #getOldLocation() old field} to a {@link #getNewLocation() new field}.
 */
public class PlayFigureMoveEvent extends PlayAssetMoveEvent<PlayField, PlayFigure> {

  /**
   * The constructor.
   *
   * @param oldLocation the {@link #getOldLocation() old location}.
   * @param oldPosition the {@link #getOldPosition() old position}.
   * @param asset the {@link #getAsset() asset}.
   */
  public PlayFigureMoveEvent(PlayField oldLocation, PlayPosition oldPosition, PlayFigure asset) {
    super(oldLocation, oldPosition, asset);
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
  public PlayFigureMoveEvent(PlayField oldLocation, PlayPosition oldPosition, PlayFigure asset, PlayField newLocation, PlayPosition newPosition) {
    super(oldLocation, oldPosition, asset, newLocation, newPosition);
  }

}
