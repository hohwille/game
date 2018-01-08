/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.game.engine.item;

import net.sf.mmm.game.engine.Game;
import net.sf.mmm.game.engine.asset.GameAssetBase;
import net.sf.mmm.game.engine.color.GameColor;
import net.sf.mmm.game.engine.object.GameLocation;

/**
 * An item of the {@link Game} such as a key or a gem.
 *
 * @param <T> generic type of {@link #getType()}.
 * @param <L> the type of the {@link #getLocation() location}.
 * @param <S> this {@link GameItem} itself.
 */
public abstract class GameItem<T extends GameItemType<T>, L extends GameLocation, S extends GameItem<T, L, S>> extends GameAssetBase<T, L>
    implements GameAttributeWeight<S> {

  private final Game game;

  /**
   * The constructor.
   *
   * @param type the {@link #getType() type} of this object.
   * @param game the owning {@link #getGame() game}.
   * @param color the optional {@link #getColor() color}. May be {@code null} for default.
   */
  public GameItem(Game game, T type, GameColor color) {

    super(type, color);
    this.game = game;
  }

  @Override
  public Game getGame() {

    return this.game;
  }

}
