/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.choice;

import io.github.ghosthopper.game.PlayGame;

/**
 * A {@link PlayChoiceEvent} a {@link PlayGame} can {@link PlayGame#sendEvent(io.github.ghosthopper.event.PlayEvent)
 * send} to notify the view about a {@link PlayChoice} to be made. The view will receive this event and render a dialog
 * for the choice.
 */
public class PlayChoiceRequestEvent extends PlayChoiceEvent {

  /**
   * The constructor.
   *
   * @param choice the {@link PlayChoice} to notify about.
   */
  public PlayChoiceRequestEvent(PlayChoice<?> choice) {
    super(choice);
  }

}
