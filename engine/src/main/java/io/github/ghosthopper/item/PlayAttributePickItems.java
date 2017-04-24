/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.item;

import java.util.List;

import io.github.ghosthopper.object.PlayLocation;

/**
 * This is the interface for an object that may have {@link #getItems() items}.
 */
public interface PlayAttributePickItems extends PlayLocation {

  /**
   * @return an {@link java.util.Collections#unmodifiableList(List) unmodifiable list} of {@link PlayPickItem}s
   *         contained in this object.
   */
  List<PlayPickItem> getItems();

  /**
   * @param item the {@link PlayPickItem} that shall potentially be {@link #addItem(PlayPickItem) added}.
   * @return {@code true} if it can be {@link #addItem(PlayPickItem) added}, {@code false} otherwise.
   */
  boolean canAddItem(PlayPickItem item);

  /**
   * @param item the {@link PlayPickItem} to {@link List#add(Object) add} to the {@link #getItems() items}.
   * @return {@code true} if the operation was successful, {@code false} otherwise (e.g. this object can not take the
   *         given {@link PlayPickItem}).
   */
  boolean addItem(PlayPickItem item);

  /**
   * @param item the {@link PlayPickItem} to {@link List#remove(Object) remove} from the {@link #getItems() items}.
   * @return {@code true} if the operation was successful, {@code false} otherwise (e.g. this object can not take the
   *         given {@link PlayPickItem}).
   */
  default boolean removeItem(PlayPickItem item) {

    return removeItem(item, true);
  }

  /**
   * @param item the {@link PlayPickItem} to {@link List#remove(Object) remove} from the {@link #getItems() items}.
   * @param sendEvent - {@code true} if an {@link PlayPickItemMoveEvent} should be send, {@code false} otherwise (if
   *        called from {@link #addItem(PlayPickItem)} to move from the old {@link PlayPickItem#getLocation() location}
   *        to the new one and send a single event).
   * @return {@code true} if the operation was successful, {@code false} otherwise (e.g. this object can not take the
   *         given {@link PlayPickItem}).
   */
  boolean removeItem(PlayPickItem item, boolean sendEvent);

}
