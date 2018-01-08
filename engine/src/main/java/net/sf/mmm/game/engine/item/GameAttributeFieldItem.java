/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.game.engine.item;

import net.sf.mmm.game.engine.field.GameField;

/**
 * This is the interface for an object that may {@link #getItem() have} a {@link GameFieldItem}.
 */
public interface GameAttributeFieldItem {

  /**
   * @param item the {@link GameFieldItem} that shall potentially be {@link #setItem(GameFieldItem) added}.
   * @return {@code true} if it can be {@link #setItem(GameFieldItem) added}, {@code false} otherwise.
   */
  default boolean canAddItem(GameFieldItem item) {

    return (getItem() == null);
  }

  /**
   * @return the {@link GameFieldItem} that is on this {@link GameField} or {@code null} if there is no such item here.
   */
  GameFieldItem getItem();

  /**
   * @param item the new value of {@link #getItem()}.
   * @return {@code true} if the {@link GameFieldItem} has been set successfully, {@code false} otherwise.
   */
  boolean setItem(GameFieldItem item);

}
