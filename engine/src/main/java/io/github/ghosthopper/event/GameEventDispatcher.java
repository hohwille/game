/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.event;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A {@link GameEventSender} responsible for a specific type of {@link GameEvent}s.
 *
 * @param <E> the type of the {@link GameEvent}s.
 */
public class GameEventDispatcher<E extends GameEvent> implements GameEventSender<E> {

  private static final Logger LOG = LoggerFactory.getLogger(GameEventDispatcher.class);

  private final GameEventDispatcher<? super E> parent;

  private final List<ListenerContainer<E>> listeners;

  /**
   * The constructor.
   */
  public GameEventDispatcher() {
    this(null);
  }

  /**
   * The constructor.
   *
   * @param parent the parent {@link GameEventDispatcher} to extend.
   */
  public GameEventDispatcher(GameEventDispatcher<? super E> parent) {
    super();
    this.parent = parent;
    this.listeners = new ArrayList<>();
  }

  @Override
  public void sendEvent(E event) {

    Iterator<ListenerContainer<E>> iterator = this.listeners.iterator();
    while (iterator.hasNext()) {
      ListenerContainer<E> container = iterator.next();
      GameEventListener<E> listener = container.listener;
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
  public <T extends E> void addListener(Class<T> type, GameEventListener<T> listener) {

    this.listeners.add(new ListenerContainer(listener));
  }

  @Override
  public boolean removeListener(GameEventListener<? extends E> listener) {

    for (ListenerContainer<E> container : this.listeners) {
      if (container.listener == listener) {
        container.listener = null;
        return true;
      }
    }
    return false;
  }

  private static class ListenerContainer<E extends GameEvent> {

    private GameEventListener<E> listener;

    public ListenerContainer(GameEventListener<E> listener) {
      super();
      this.listener = listener;
    }

  }

}
