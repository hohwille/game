/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.choice;

import java.util.List;

/**
 * A {@link PlayChoiceEvent} that notifies about the {@link PlayChoice#select(List) selection} of a {@link PlayChoice}.
 */
public class PlayChoiceSelectEvent extends PlayChoiceEvent {

  private final List<?> options;

  /**
   * The constructor.
   *
   * @param choice the {@link PlayChoice} to notify about.
   * @param options - see {@link #getOptions()}.
   */
  public PlayChoiceSelectEvent(PlayChoice<?> choice, List<?> options) {
    super(choice);
    this.options = options;
  }

  /**
   * @return the {@link PlayChoice#select(List) selected} option(s).
   */
  public List<?> getOptions() {

    return this.options;
  }

}
