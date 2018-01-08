/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.game.engine.time;

/**
 * TODO: this class ...
 *
 * @author Joerg Hohwiller (hohwille at users.sourceforge.net)
 * @since 1.0.0
 */
public enum GameTimeUnit {

  /** Unit for {@link GameTime#getTurnCount()}. */
  TURNS,

  /** Unit for {@link GameTime#getRoundCount()}. */
  ROUNDS,

  /** Unit for {@link GameTime#getDuration()} in seconds. */
  SECONDS,

  /** Unit for {@link GameTime#getDuration()} in minutes. */
  MINUTES,

  /** Unit for {@link GameTime#getDuration()} in hours. */
  HOURS,

  /** Unit for {@link GameTime#getDuration()} in days. */
  DAYS

}
