/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.choice;

import io.github.ghosthopper.event.PlayEvent;

/**
 * A {@link PlayEvent} that informs about a {@link PlayChoice}.
 */
public abstract class PlayChoiceEvent implements PlayEvent {

  private final PlayChoice<?> choice;

  /**
   * The constructor.
   *
   * @param choice the {@link PlayChoice} to notify about.
   */
  public PlayChoiceEvent(PlayChoice<?> choice) {
    super();
    this.choice = choice;
  }

  /**
   * @return the {@link PlayChoice} to notify about.
   */
  public PlayChoice<?> getChoice() {

    return this.choice;
  }

}
