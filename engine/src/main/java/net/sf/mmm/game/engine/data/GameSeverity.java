/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.game.engine.data;

import net.sf.mmm.game.engine.type.GameTypeBase;

/**
 * Represents a severity of a status message such as {@link #INFO}, {@link #WARNING}, or {@link #ERROR}.
 */
public class GameSeverity extends GameTypeBase {

  /** Severity for an information (hint or help message). */
  public static final GameSeverity INFO = new GameSeverity("Info");

  /** Severity for a warning. */
  public static final GameSeverity WARNING = new GameSeverity("Warning");

  /** Severity for an error. */
  public static final GameSeverity ERROR = new GameSeverity("Error");

  /**
   * The constructor.
   *
   * @param id the {@link #getId() id}.
   */
  public GameSeverity(String id) {
    super(id);
  }

  @Override
  public String getTypeName() {

    return "Severity";
  }

}
