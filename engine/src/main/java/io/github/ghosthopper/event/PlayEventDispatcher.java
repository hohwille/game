/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.event;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A {@link PlayEventSender} responsible for a specific type of {@link PlayEvent}s.
 *
 * @param <E> the type of the {@link PlayEvent}s.
 */
public class PlayEventDispatcher<E extends PlayEvent> implements PlayEventSender<E> {

  private static final Logger LOG = LoggerFactory.getLogger(PlayEventDispatcher.class);

  private final PlayEventDispatcher<? super E> parent;

  private final List<ListenerContainer<E>> listeners;

  /**
   * The constructor.
   */
  public PlayEventDispatcher() {
    this(null);
  }

  /**
   * The constructor.
   *
   * @param parent the parent {@link PlayEventDispatcher} to extend.
   */
  public PlayEventDispatcher(PlayEventDispatcher<? super E> parent) {
    super();
    this.parent = parent;
    this.listeners = new ArrayList<>();
  }

  @Override
  public void sendEvent(E event) {

    Iterator<ListenerContainer<E>> iterator = this.listeners.iterator();
    while (iterator.hasNext()) {
      ListenerContainer<E> container = iterator.next();
      PlayEventListener<E> listener = container.listener;
      if (listener == null) {
        iterator.remove();
      } else {
        try {
          listener.handle(event);
        } catch (Throwable exception) {
          LOG.error("Exception thrown from event listener {}", listener.getClass().getName(), exception);
        }
      }
    }
    if (this.parent != null) {
      this.parent.sendEvent(event);
    }
  }

  @SuppressWarnings({ "unchecked", "rawtypes" })
  @Override
  public <T extends E> void addListener(Class<T> type, PlayEventListener<T> listener) {

    this.listeners.add(new ListenerContainer(listener));
  }

  @Override
  public boolean removeListener(PlayEventListener<? extends E> listener) {

    for (ListenerContainer<E> container : this.listeners) {
      if (container.listener == listener) {
        container.listener = null;
        return true;
      }
    }
    return false;
  }

  private static class ListenerContainer<E extends PlayEvent> {

    private PlayEventListener<E> listener;

    public ListenerContainer(PlayEventListener<E> listener) {
      super();
      this.listener = listener;
    }

  }

}
