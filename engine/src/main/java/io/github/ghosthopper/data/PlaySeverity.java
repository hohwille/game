/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.data;

import io.github.ghosthopper.type.PlayTypeBase;

/**
 * Represents a severity of a status message such as {@link #INFO}, {@link #WARNING}, or {@link #ERROR}.
 */
public class PlaySeverity extends PlayTypeBase {

  /** Severity for an information (hint or help message). */
  public static final PlaySeverity INFO = new PlaySeverity("Info");

  /** Severity for a warning. */
  public static final PlaySeverity WARNING = new PlaySeverity("Warning");

  /** Severity for an error. */
  public static final PlaySeverity ERROR = new PlaySeverity("Error");

  /**
   * The constructor.
   *
   * @param id the {@link #getId() id}.
   */
  public PlaySeverity(String id) {
    super(id);
  }

  @Override
  public String getTypeName() {

    return "Severity";
  }

}
