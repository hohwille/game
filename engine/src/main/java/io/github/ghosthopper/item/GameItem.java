/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.item;

import io.github.ghosthopper.asset.GameAssetBase;
import io.github.ghosthopper.game.Game;
import io.github.ghosthopper.object.GameLocation;

/**
 * An item of the {@link Game} such as a key or a gem.
 *
 * @param <L> the type of the {@link #getLocation() location}.
 * @param <S> this {@link GameItem} itself.
 */
public abstract class GameItem<L extends GameLocation, S extends GameItem<L, S>> extends GameAssetBase<L> implements GameAttributeWeight<S> {

  /**
   * The constructor.
   */
  public GameItem() {
    super();
  }

}
