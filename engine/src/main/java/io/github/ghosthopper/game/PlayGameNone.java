/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.game;

import io.github.ghosthopper.PlayLevel;
import io.github.ghosthopper.Player;

/**
 * A dummy implementation of {@link PlayGame} only used as fall-back instance. Shall only be used internally or by
 * tests.
 */
public class PlayGameNone extends PlayGame {

  public static final String ID = "None";

  /** The singleton instance. */
  public static final PlayGameNone INSTANCE = new PlayGameNone();

  /**
   * The constructor.
   */
  public PlayGameNone() {
    super(ID);
  }

  @Override
  protected void moveBotPlayer(Player player) {

  }

  @Override
  protected PlayLevel createFirstLevel() {

    return new PlayLevel("Level 1", this);
  }

}
