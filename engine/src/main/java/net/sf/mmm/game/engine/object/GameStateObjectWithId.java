/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.game.engine.object;

/**
 * A {@link GameStateObjectBase} that can be identified by and {@link #getId() ID}.
 */
public abstract class GameStateObjectWithId extends GameStateObjectBase {

  private final String id;

  /**
   * The constructor.
   *
   * @param id - see {@link #getId()}.
   */
  public GameStateObjectWithId(String id) {
    super();
    this.id = id;
  }

  /**
   * @return the ID of this item used to find corresponding graphics or audio information.
   */
  @Override
  public String getId() {

    return this.id;
  }

  @Override
  public String toString() {

    return this.id;
  }

}
