/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.game.engine;

/**
 * This is the interface for an object that {@link #getGame() gives access to the owning game}.
 */
public interface GameAttributeGame {

  /**
   * @return the {@link Game} owning this object.
   */
  Game getGame();

}
