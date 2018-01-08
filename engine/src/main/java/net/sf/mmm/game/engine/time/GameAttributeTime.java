/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.game.engine.time;

/**
 * This is the interface for an object that {@link #getCurrentTime() gives access} to the current {@link GameTime}.
 */
public interface GameAttributeTime {

  /**
   * @return the current {@link GameTime}.
   */
  GameTime getCurrentTime();

}
