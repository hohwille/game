/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.game.engine.item;

import net.sf.mmm.game.engine.asset.GameAsset;

/**
 * Interface for an object that may be {@link #isEnterable(GameAsset) enterable}.
 *
 * @see GameFieldItem
 *
 * @author Joerg Hohwiller (hohwille at users.sourceforge.net)
 * @since 1.0.0
 */
public interface GameAttributeEnterable {

  /**
   * @param asset the {@link GameAsset} that will potentially enter this object. Typically a
   *        {@link net.sf.mmm.game.engine.figure.GameFigure}.
   * @return {@code true} if this item (or items of this type) is can be entered by the given {@link GameAsset},
   *         {@code false} otherwise.
   */
  default boolean isEnterable(GameAsset<?> asset) {

    return isEnterable(asset, false);
  }

  /**
   * @param asset the {@link GameAsset} that wants to enter this object. Typically a
   *        {@link net.sf.mmm.game.engine.figure.GameFigure}.
   * @return {@code true} if this item (or items of this type) was successfully entered by the given {@link GameAsset},
   *         {@code false} otherwise.
   */
  default boolean enter(GameAsset<?> asset) {

    return isEnterable(asset, true);
  }

  /**
   * @param asset the {@link GameAsset} that will potentially enter this object. Typically a
   *        {@link net.sf.mmm.game.engine.figure.GameFigure}.
   * @param move {@code true} if the {@link GameAsset} actually {@link #enter(GameAsset) performs the move to enter} if
   *        possible, {@code false} otherwise (only to {@link #isEnterable(GameAsset) check a simulated entering}).
   * @return {@code true} if this item (or items of this type) is can be entered by the given {@link GameAsset},
   *         {@code false} otherwise.
   */
  boolean isEnterable(GameAsset<?> asset, boolean move);
}
