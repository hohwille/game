/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.object;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.github.ghosthopper.item.GameAttributePickItems;
import io.github.ghosthopper.item.GamePickItem;

/**
 * This is the abstract base class for an object that optionally can have a {@link #getColor() color}.
 */
public abstract class GameTypedObjectWithItems extends GameTypedObjectBase implements GameAttributePickItems {

  private final List<GamePickItem> items;

  private final List<GamePickItem> itemsView;

  /**
   * The constructor.
   */
  public GameTypedObjectWithItems() {
    super();
    this.items = new ArrayList<>();
    this.itemsView = Collections.unmodifiableList(this.items);
  }

  @Override
  public List<GamePickItem> getItems() {

    return this.itemsView;
  }

  @Override
  public boolean canAddItem(GamePickItem item) {

    return true;
  }

  @Override
  public boolean addItem(GamePickItem item) {

    GameAttributePickItems oldLocation = item.getLocation();
    if (oldLocation == this) {
      return true;
    }
    if (!canAddItem(item)) {
      return false;
    }
    if (oldLocation != null) {
      boolean success = oldLocation.removeItem(item, false);
      if (!success) {
        return false;
      }
    }
    this.items.add(item);
    item.setLocation(this, item.getPosition(), false);
    return true;
  }

  @Override
  public boolean removeItem(GamePickItem item, boolean updateLocation) {

    boolean success = this.items.remove(item);
    if (updateLocation) {
      item.setLocation(null);
    }
    return success;
  }

}
