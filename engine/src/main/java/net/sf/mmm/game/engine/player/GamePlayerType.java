/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.game.engine.player;

import net.sf.mmm.game.engine.type.GameTypeBase;

/**
 * The type of a {@link GamePlayer}.
 *
 * @see GamePlayer#getType()
 */
public class GamePlayerType extends GameTypeBase {

  /** The default {@link GamePlayerType}. */
  public static final GamePlayerType DEFAULT = new GamePlayerType("Player");

  /**
   * The constructor.
   *
   * @param id - see {@link #getId()}.
   */
  public GamePlayerType(String id) {
    super(id);
  }

  @Override
  public String getTypeName() {

    return "Player";
  }

}
