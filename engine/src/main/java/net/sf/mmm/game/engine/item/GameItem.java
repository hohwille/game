/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.game.engine.item;

import net.sf.mmm.game.engine.Game;
import net.sf.mmm.game.engine.asset.GameAssetBase;
import net.sf.mmm.game.engine.object.GameLocation;

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
