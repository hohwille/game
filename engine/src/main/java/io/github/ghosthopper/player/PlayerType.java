/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.player;

import io.github.ghosthopper.object.PlayObjectType;

/**
 * The type of a {@link Player}.
 *
 * @see Player#getType()
 */
public class PlayerType extends PlayObjectType {

  /** The default {@link PlayerType}. */
  public static final PlayerType DEFAULT = new PlayerType("Player");

  /**
   * The constructor.
   *
   * @param id - see {@link #getId()}.
   */
  public PlayerType(String id) {
    super(id);
  }

}
