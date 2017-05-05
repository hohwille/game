/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.border;

import io.github.ghosthopper.type.GameTypeBase;

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
