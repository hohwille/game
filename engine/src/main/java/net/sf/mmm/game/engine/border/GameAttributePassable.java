/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.game.engine.border;

import net.sf.mmm.game.engine.asset.GameAsset;

/**
 * Interface for an object that may be {@link #isPassable(GameAsset) passable}.
 *
 * @see GameBorder
 *
 * @author Joerg Hohwiller (hohwille at users.sourceforge.net)
 * @since 1.0.0
 */
public interface GameAttributePassable {

  /**
   * @param asset the {@link GameAsset} that will potentially pass this object. Typically a
   *        {@link net.sf.mmm.game.engine.figure.GameFigure}.
   * @return {@code true} if this object (or objects of this type) can be passed by the given {@link GameAsset},
   *         {@code false} otherwise.
   */
  default boolean isPassable(GameAsset<?> asset) {

    return isPassable(asset, false);
  }

  /**
   * @param asset the {@link GameAsset} that wants to pass this object. Typically a
   *        {@link net.sf.mmm.game.engine.figure.GameFigure}.
   * @return {@code true} if this object (or objects of this type) can successfully be passed by the given
   *         {@link GameAsset}, {@code false} otherwise.
   */
  default boolean pass(GameAsset<?> asset) {

    return isPassable(asset, true);
  }

  /**
   * @param asset the {@link GameAsset} that will potentially pass this object. Typically a
   *        {@link net.sf.mmm.game.engine.figure.GameFigure}.
   * @param move {@code true} if the {@link GameAsset} actually {@link #pass(GameAsset) performs the move to enter} if
   *        possible, {@code false} otherwise (only to {@link #isPassable(GameAsset) check a simulated entering}).
   * @return {@code true} if this object (or objects of this type) can be passed by the given {@link GameAsset},
   *         {@code false} otherwise.
   */
  boolean isPassable(GameAsset<?> asset, boolean move);
}
