/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.game.engine.item;

import net.sf.mmm.game.engine.asset.GameAsset;
import net.sf.mmm.game.engine.field.GameField;
import net.sf.mmm.game.engine.time.GameDuration;
import net.sf.mmm.game.engine.time.GameTime;

/**
 * A {@link GameFieldItem} that wraps an {@link #getAsset() asset} until it is ready.
 *
 * @param <A> generic type of {@link #getAsset() asset}.
 * @author Joerg Hohwiller (hohwille at users.sourceforge.net)
 * @since 1.0.0
 */
public class GameAssetProxy<A extends GameAsset<?>> extends GameFieldItem {

  private final GameDuration duration;

  private final GameTime start;

  private A asset;

  private boolean ready;

  /**
   * The constructor.
   *
   * @param asset the {@link #getAsset() asset} to wrap.
   * @param duration the {@link GameDuration} required from
   */
  public GameAssetProxy(A asset, GameDuration duration) {

    this(asset, duration, asset.getGame().getCurrentTime());
  }

  /**
   * The constructor.
   *
   * @param asset the {@link #getAsset() asset} to wrap.
   * @param duration the {@link GameDuration} required from {@code start}.
   * @param start the {@link GameTime} used as start for the required duration.
   */
  public GameAssetProxy(A asset, GameDuration duration, GameTime start) {

    super(asset.getGame(), new GameAssetProxyType(asset.getType().getId()), asset.getColor());
    this.asset = asset;
    this.duration = duration;
    this.start = start;
  }

  /**
   * @return the {@link GameAsset} contained in this proxy.
   */
  public A getAsset() {

    return this.asset;
  }

  /**
   * @return {@code true} if this {@link GameAssetProxy} is ready and the contained {@link #getAsset() asset} can be
   *         unwrap, {@code false} otherwise.
   */
  public boolean isReady() {

    if (this.ready) {
      return true;
    }
    GameTime delta = getGame().getCurrentTime().subtract(this.start);
    int deltaAmount = delta.get(this.duration.getUnit());
    if (deltaAmount > this.duration.getAmount()) {
      this.ready = true;
    }
    return this.ready;
  }

  /**
   * @return {@code true} if the {@link #getAsset() asset} was successfully unwrapped from this proxy, {@code false}
   *         otherwise.
   */
  public boolean unwrap() {

    if (!isReady()) {
      return false;
    }
    if (this.asset == null) {
      return false; // was already unwrapped
    }
    GameField location = getLocation();
    if (location == null) {
      return false;
    }
    if (!location.canAddAsset(this.asset)) {
      return false;
    }
    setLocation(null);
    boolean added = location.addAsset(this.asset);
    this.asset = null;
    assert (added);
    return added;
  }

}
