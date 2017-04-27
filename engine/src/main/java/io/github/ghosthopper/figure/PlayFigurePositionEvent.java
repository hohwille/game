/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.figure;

import io.github.ghosthopper.asset.PlayAsset;
import io.github.ghosthopper.asset.PlayAssetMoveEvent;
import io.github.ghosthopper.asset.PlayAssetPositionEvent;
import io.github.ghosthopper.position.PlayPosition;

/**
 * A {@link PlayAssetMoveEvent} that notifies about a {@link #getAsset() figure} that changed its
 * {@link PlayAsset#getPosition() position} from an {@link #getOldPosition() old position} to a {@link #getNewPosition()
 * new position}.
 */
public class PlayFigurePositionEvent extends PlayAssetPositionEvent<PlayFigure> {

  /**
   * The constructor.
   *
   * @param oldPosition the {@link #getOldPosition() old position}.
   * @param figure the moved {@link #getAsset() asset}.
   */
  public PlayFigurePositionEvent(PlayPosition oldPosition, PlayFigure figure) {
    super(oldPosition, figure);
  }

  /**
   * The constructor.
   *
   * @param oldPosition the {@link #getOldPosition() old position}.
   * @param figure the moved {@link #getAsset() asset}.
   * @param newPosition the {@link #getNewPosition() new position}.
   */
  public PlayFigurePositionEvent(PlayPosition oldPosition, PlayFigure figure, PlayPosition newPosition) {
    super(oldPosition, figure, newPosition);
  }

}
