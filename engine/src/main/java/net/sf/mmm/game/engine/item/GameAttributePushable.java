/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.game.engine.item;

/**
 * Interface for an item that may be {@link #isPushable() pushable}.
 *
 * @see GameFieldItem
 *
 * @author Joerg Hohwiller (hohwille at users.sourceforge.net)
 * @since 1.0.0
 */
public interface GameAttributePushable {

  /**
   * @return {@code true} if this item (or items of this type) may be pushed, {@code false} otherwise.
   */
  public boolean isPushable();
}
