/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.game.engine.item;

import net.sf.mmm.game.engine.asset.GameAsset;

/**
 * The {@link GameFieldItem#getType() type} of a {@link GameFieldItem} such as a {@link #ROCK}.
 */
public class GameFieldItemType extends GameItemType<GameFieldItemType> implements GameAttributePushable, GameAttributeEnterable {

  /** A rock to push around. */
  public static final GameFieldItemType ROCK = new GameFieldItemType("Rock");

  /**
   * The constructor.
   *
   * @param id - see {@link #getId()}.
   */
  public GameFieldItemType(String id) {

    super(id);
  }

  @Override
  public boolean isPushable() {

    // Override to customize
    return true;
  }

  @Override
  public boolean isEnterable(GameAsset<?> asset, boolean move) {

    // Override to customize
    return false;
  }

  /**
   * @return {@code true} if this a {@link GameFieldItem} of this type is blocking {@link GameFieldItem#getLocation()
   *         its} {@link net.sf.mmm.game.engine.field.GameField} so {@link net.sf.mmm.game.engine.figure.GameFigure}s
   *         can not move on it, {@code false} otherwise.
   */
  public boolean isBlocking() {

    return true;
  }
}
