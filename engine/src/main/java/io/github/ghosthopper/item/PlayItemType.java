/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.item;

import io.github.ghosthopper.PlayObjectWithId;

/**
 * The {@link PlayItem#getType() type} of a {@link PlayItem}. Such as
 */
public class PlayItemType extends PlayObjectWithId {

  /** A key to open doors. */
  public static final PlayItemType KEY = new PlayItemType("Key");

  /** A key to open doors. */
  public static final PlayItemType GEM = new PlayItemType("Gem");

  /**
   * The constructor.
   *
   * @param id - see {@link #getId()}.
   */
  public PlayItemType(String id) {
    super(id);
  }

}
