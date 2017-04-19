/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.event;

/**
 * The interface for an object that can {@link #sendEvent(PlayEvent) send} {@link PlayEvent}s.
 *
 * @param <E> the type of the {@link PlayEvent}.
 */
public interface PlayEventSender<E extends PlayEvent> {

  /**
   * @param event the {@link PlayEvent} to send.
   */
  void sendEvent(E event);

  /**
   * @param <T> the type of the {@link PlayEvent}.
   * @param eventType the {@link Class} reflecting the {@link PlayEvent}.
   * @param listener the {@link PlayEventListener} to add.
   */
  <T extends E> void addListener(Class<T> eventType, PlayEventListener<T> listener);

  /**
   * @param listener the {@link PlayEventListener} to remove.
   * @return {@code true} if the given {@link PlayEventListener} was successfully removed, {@code false} otherwise (it
   *         has not been previously {@link #addListener(Class, PlayEventListener) added}).
   */
  boolean removeListener(PlayEventListener<? extends E> listener);

}
