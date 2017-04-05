/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.game;

import io.github.ghosthopper.PlayLevel;
import io.github.ghosthopper.Player;

/**
 * A {@link DummyGame} for testing.
 */
public class DummyGame extends PlayGame {

  /**
   * The constructor.
   */
  public DummyGame() {
    super("DummyGame");
  }

  @Override
  protected void moveBotPlayer(Player player) {

  }

  @Override
  protected PlayLevel createFirstLevel() {

    return new PlayLevel("Level 1", this);
  }

}
