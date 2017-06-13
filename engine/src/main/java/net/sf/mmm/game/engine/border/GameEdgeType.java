/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.game.engine.border;

import net.sf.mmm.game.engine.type.GameTypeBase;

/**
 * The type of an edge between {@link GameBorder}s.
 */
public class GameEdgeType extends GameTypeBase {

  /** The default edge type. */
  public static final GameEdgeType DEFAULT = new GameEdgeType("Normal");

  /**
   * The constructor.
   *
   * @param id - see {@link #getId()}.
   */
  public GameEdgeType(String id) {
    super(id);
  }

  @Override
  public String getTypeName() {

    return "Edge";
  }

}
