/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.game.engine.type;

import net.sf.mmm.game.engine.object.GameStateObjectWithId;

/**
 * Abstract base class for an object type.
 */
public abstract class GameTypeBase extends GameStateObjectWithId implements GameType {

  /**
   * The constructor.
   *
   * @param id - see {@link #getId()}.
   */
  public GameTypeBase(String id) {
    super(id);
  }

}
