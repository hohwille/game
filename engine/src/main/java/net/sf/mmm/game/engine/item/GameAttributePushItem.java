/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.game.engine.item;

import net.sf.mmm.game.engine.field.GameField;

/**
 * This is the interface for an object that may {@link #getPushItem() have} a {@link GamePushItem}.
 */
public interface GameAttributePushItem {

  /**
   * @param pushItem the {@link GamePushItem} that shall potentially be {@link #setPushItem(GamePushItem) added}.
   * @return {@code true} if it can be {@link #setPushItem(GamePushItem) added}, {@code false} otherwise.
   */
  default boolean canAddPushItem(GamePushItem pushItem) {

    return (getPushItem() == null);
  }

  /**
   * @return the {@link GamePushItem} that is on this {@link GameField} or {@code null} if there is no such item here.
   */
  GamePushItem getPushItem();

  /**
   * @param pushItem the new value of {@link #getPushItem()}.
   * @return {@code true} if the {@link GamePushItem} has been set successfully, {@code false} otherwise.
   */
  boolean setPushItem(GamePushItem pushItem);

}
