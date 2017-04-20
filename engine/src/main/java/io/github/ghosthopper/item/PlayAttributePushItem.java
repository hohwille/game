/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.item;

import io.github.ghosthopper.field.PlayField;

/**
 * This is the interface for an object that may {@link #getPushItem() have} a {@link PlayPushItem}.
 */
public interface PlayAttributePushItem {

  /**
   * @return the {@link PlayPushItem} that is on this {@link PlayField} or {@code null} if there is no such item here.
   */
  PlayPushItem getPushItem();
}
