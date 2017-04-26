/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.object;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.github.ghosthopper.item.PlayAttributePickItems;
import io.github.ghosthopper.item.PlayPickItem;

/**
 * This is the abstract base class for an object that optionally can have a {@link #getColor() color}.
 */
public abstract class PlayTypedObjectWithItems extends AbstractPlayTypedObject implements PlayAttributePickItems {

  private final List<PlayPickItem> items;

  private final List<PlayPickItem> itemsView;

  /**
   * The constructor.
   */
  public PlayTypedObjectWithItems() {
    super();
    this.items = new ArrayList<>();
    this.itemsView = Collections.unmodifiableList(this.items);
  }

  @Override
  public List<PlayPickItem> getItems() {

    return this.itemsView;
  }

  @Override
  public boolean canAddItem(PlayPickItem item) {

    return true;
  }

  @Override
  public boolean addItem(PlayPickItem item) {

    PlayAttributePickItems oldLocation = item.getLocation();
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
    item.setLocation(this, false);
    return true;
  }

  @Override
  public boolean removeItem(PlayPickItem item, boolean updateLocation) {

    boolean success = this.items.remove(item);
    if (updateLocation) {
      item.setLocation(null);
    }
    return success;
  }

}
