/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.event;

/**
 * Interface for a listener that {@link #handle(GameEvent) handles} specific {@link GameEvent}s.
 *
 * @param <E> the type of the {@link #handle(GameEvent) handled} {@link GameEvent}.
 */
public interface GameEventListener<E extends GameEvent> {

  /**
   * @param event the event to handle.
   */
  void handle(E event);

}
