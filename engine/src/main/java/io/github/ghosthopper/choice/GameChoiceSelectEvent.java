/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.choice;

import java.util.List;

/**
 * A {@link GameChoiceEvent} that notifies about the {@link GameChoice#select(List) selection} of a {@link GameChoice}.
 */
public class GameChoiceSelectEvent extends GameChoiceEvent {

  private final List<?> options;

  /**
   * The constructor.
   *
   * @param choice the {@link GameChoice} to notify about.
   * @param options - see {@link #getOptions()}.
   */
  public GameChoiceSelectEvent(GameChoice<?> choice, List<?> options) {
    super(choice);
    this.options = options;
  }

  /**
   * @return the {@link GameChoice#select(List) selected} option(s).
   */
  public List<?> getOptions() {

    return this.options;
  }

}
