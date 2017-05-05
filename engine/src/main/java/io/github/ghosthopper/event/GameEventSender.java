/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.event;

/**
 * The interface for an object that can {@link #sendEvent(GameEvent) send} {@link GameEvent}s.
 *
 * @param <E> the type of the {@link GameEvent}.
 */
public interface GameEventSender<E extends GameEvent> {

  /**
   * @param event the {@link GameEvent} to send.
   */
  void sendEvent(E event);

  /**
   * @param <T> the type of the {@link GameEvent}.
   * @param eventType the {@link Class} reflecting the {@link GameEvent}.
   * @param listener the {@link GameEventListener} to add.
   */
  <T extends E> void addListener(Class<T> eventType, GameEventListener<T> listener);

  /**
   * @param listener the {@link GameEventListener} to remove.
   * @return {@code true} if the given {@link GameEventListener} was successfully removed, {@code false} otherwise (it
   *         has not been previously {@link #addListener(Class, GameEventListener) added}).
   */
  boolean removeListener(GameEventListener<? extends E> listener);

}
