/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.game.engine;

/**
 * A dummy implementation of {@link Game} only used as fall-back instance. Shall only be used internally or by
 * tests.
 */
public class GameNone extends Game {

  /** {@link #getId() ID} of {@link GameNone}. */
  public static final String ID = "None";

  /** The singleton instance. */
  public static final GameNone INSTANCE = new GameNone();

  /**
   * The constructor.
   */
  public GameNone() {
    super(ID);
    begin();
  }

}
