/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.game.engine.item;

import java.util.List;

import net.sf.mmm.game.engine.asset.GameAttributeAsset;
import net.sf.mmm.game.engine.object.GameLocation;

/**
 * This is the interface for an object that may have {@link #getItems() items}.
 */
public interface GameAttributePickItems extends GameAttributeAsset, GameLocation {

  /**
   * @return an {@link java.util.Collections#unmodifiableList(List) unmodifiable list} of {@link GamePickItem}s
   *         contained in this object.
   */
  List<GamePickItem> getItems();

  /**
   * @param item the {@link GamePickItem} that shall potentially be {@link #addItem(GamePickItem) added}.
   * @return {@code true} if it can be {@link #addItem(GamePickItem) added}, {@code false} otherwise.
   */
  boolean canAddItem(GamePickItem item);

  /**
   * @param item the {@link GamePickItem} to {@link List#add(Object) add} to the {@link #getItems() items}.
   * @return {@code true} if the operation was successful, {@code false} otherwise (e.g. this object can not take the
   *         given {@link GamePickItem}).
   */
  boolean addItem(GamePickItem item);

  /**
   * @param item the {@link GamePickItem} to {@link List#remove(Object) remove} from the {@link #getItems() items}.
   * @return {@code true} if the operation was successful, {@code false} otherwise (e.g. this object can not take the
   *         given {@link GamePickItem}).
   */
  default boolean removeItem(GamePickItem item) {

    return removeItem(item, true);
  }

  /**
   * @param item the {@link GamePickItem} to {@link List#remove(Object) remove} from the {@link #getItems() items}.
   * @param updateLocation - {@code true} if the {@link GamePickItem#getLocation() location} shall also be
   *        {@link GamePickItem#setLocation(GameAttributePickItems) updated} and a {@link GamePickItemMoveEvent} should
   *        be send, {@code false} otherwise (if called from {@link #addItem(GamePickItem)} or from
   *        {@link GamePickItem#setLocation(GameAttributePickItems)}).
   * @return {@code true} if the operation was successful, {@code false} otherwise (e.g. this object can not take the
   *         given {@link GamePickItem}).
   */
  boolean removeItem(GamePickItem item, boolean updateLocation);

}
