/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.game.engine.choice;

import net.sf.mmm.game.engine.Game;

/**
 * A {@link GameChoiceEvent} a {@link Game} can {@link Game#sendEvent(net.sf.mmm.game.engine.event.GameEvent) send} to
 * notify the view about a {@link GameChoice} to be made. The view will receive this event and render a dialog for the
 * choice.
 */
public class GameChoiceRequestEvent extends GameChoiceEvent {

  /**
   * The constructor.
   *
   * @param choice the {@link GameChoice} to notify about.
   */
  public GameChoiceRequestEvent(GameChoice<?> choice) {

    super(choice);
  }

}
