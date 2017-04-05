/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper;

import io.github.ghosthopper.game.PlayGame;

/**
 * This is the interface for any object of this game.
 */
public abstract class PlayObject {

  /**
   * The constructor.
   */
  public PlayObject() {
    super();
  }

  /**
   * @return the {@link PlayGame} owining this object.
   */
  public PlayGame getGame() {

    return PlayGame.getCurrentGame();
  }

}
