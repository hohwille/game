/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.border;

import io.github.ghosthopper.type.PlayTypeBase;

/**
 * The type of an edge between {@link PlayBorder}s.
 */
public class PlayEdgeType extends PlayTypeBase {

  /** The default edge type. */
  public static final PlayEdgeType DEFAULT = new PlayEdgeType("Normal");

  /**
   * The constructor.
   *
   * @param id - see {@link #getId()}.
   */
  public PlayEdgeType(String id) {
    super(id);
  }

  @Override
  public String getTypeName() {

    return "Edge";
  }

}
