/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.figure;

import io.github.ghosthopper.asset.PlayAssetMoveEvent;
import io.github.ghosthopper.field.PlayField;

/**
 * A {@link PlayAssetMoveEvent} that notifies about a {@link #getAsset() figure} that {@link PlayFigure#move() moved}
 * from an {@link #getOldLocation() old field} to a {@link #getNewLocation() new field}.
 */
public class PlayFigureMoveEvent extends PlayAssetMoveEvent<PlayField, PlayFigure> {

  /**
   * The constructor.
   *
   * @param oldField the {@link #getOldLocation() old field}.
   * @param figure the moved {@link #getAsset() asset}.
   */
  public PlayFigureMoveEvent(PlayField oldField, PlayFigure figure) {
    super(oldField, figure);
  }

  /**
   * The constructor.
   *
   * @param oldField the {@link #getOldLocation() old field}.
   * @param figure the moved {@link #getAsset() asset}.
   * @param newField the {@link #getNewLocation() new field}.
   */
  public PlayFigureMoveEvent(PlayField oldField, PlayFigure figure, PlayField newField) {
    super(oldField, figure, newField);
  }

}
