/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.game.engine.asset;

import net.sf.mmm.game.engine.event.GameEvent;
import net.sf.mmm.game.engine.figure.GameFigure;
import net.sf.mmm.game.engine.object.GameLocation;
import net.sf.mmm.game.engine.position.GamePosition;

/**
 * A {@link GameEvent} that notifies about a {@link #getAsset() asset} that changed its {@link GameAsset#getLocation()
 * location} from an {@link #getOldLocation() old location} to a {@link #getNewLocation() new location}.
 *
 * @param <L> the type of the {@link #getOldLocation() old} and {@link #getNewLocation() new location}.
 * @param <A> the type of the {@link #getAsset() asset}.
 */
public abstract class GameAssetMoveEvent<L extends GameLocation, A extends GameAsset<L>> implements GameEvent {

  private final L oldLocation;

  private final GamePosition oldPosition;

  private final A asset;

  private final L newLocation;

  private GamePosition newPosition;

  /**
   * The constructor.
   *
   * @param oldLocation the {@link #getOldLocation() old location}.
   * @param oldPosition the {@link #getOldPosition() old position}.
   * @param asset the {@link #getAsset() asset}.
   */
  public GameAssetMoveEvent(L oldLocation, GamePosition oldPosition, A asset) {
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
  public GameAssetMoveEvent(L oldLocation, GamePosition oldPosition, A asset, L newLocation, GamePosition newPosition) {
    super();
    this.oldLocation = oldLocation;
    this.oldPosition = oldPosition;
    this.asset = asset;
    this.newLocation = newLocation;
    this.newPosition = newPosition;
  }

  /**
   * @return the {@link GameAsset} that has changed its {@link GameAsset#getLocation() location}.
   */
  public A getAsset() {

    return this.asset;
  }

  /**
   * @return the old {@link GameAsset#getLocation() location} of the {@link #getAsset() asset} before this event.
   */
  public L getOldLocation() {

    return this.oldLocation;
  }

  /**
   * @return the new {@link GameFigure#getLocation() location} of the {@link #getAsset() asset} after this event.
   */
  public L getNewLocation() {

    return this.newLocation;
  }

  /**
   * @return the old {@link GameAsset#getPosition() position} of the {@link #getAsset() asset} before this event.
   */
  public GamePosition getOldPosition() {

    return this.oldPosition;
  }

  /**
   * @return the new {@link GameFigure#getPosition() position} of the {@link #getAsset() asset} after this event.
   */
  public GamePosition getNewPosition() {

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
