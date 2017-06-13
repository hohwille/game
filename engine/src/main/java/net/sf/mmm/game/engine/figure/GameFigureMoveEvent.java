/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.game.engine.figure;

import net.sf.mmm.game.engine.asset.GameAssetMoveEvent;
import net.sf.mmm.game.engine.field.GameField;
import net.sf.mmm.game.engine.position.GamePosition;

/**
 * A {@link GameAssetMoveEvent} that notifies about a {@link #getAsset() figure} that {@link GameFigure#move() moved}
 * from an {@link #getOldLocation() old field} to a {@link #getNewLocation() new field}.
 */
public class GameFigureMoveEvent extends GameAssetMoveEvent<GameField, GameFigure> {

  /**
   * The constructor.
   *
   * @param oldLocation the {@link #getOldLocation() old location}.
   * @param oldPosition the {@link #getOldPosition() old position}.
   * @param asset the {@link #getAsset() asset}.
   */
  public GameFigureMoveEvent(GameField oldLocation, GamePosition oldPosition, GameFigure asset) {
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
  public GameFigureMoveEvent(GameField oldLocation, GamePosition oldPosition, GameFigure asset, GameField newLocation, GamePosition newPosition) {
    super(oldLocation, oldPosition, asset, newLocation, newPosition);
  }

}
