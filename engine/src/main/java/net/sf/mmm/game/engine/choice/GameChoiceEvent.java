/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.game.engine.choice;

import net.sf.mmm.game.engine.event.GameEvent;

/**
 * A {@link GameEvent} that informs about a {@link GameChoice}.
 */
public abstract class GameChoiceEvent implements GameEvent {

  private final GameChoice<?> choice;

  /**
   * The constructor.
   *
   * @param choice the {@link GameChoice} to notify about.
   */
  public GameChoiceEvent(GameChoice<?> choice) {
    super();
    this.choice = choice;
  }

  /**
   * @return the {@link GameChoice} to notify about.
   */
  public GameChoice<?> getChoice() {

    return this.choice;
  }

}
