/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.object;

import java.util.ArrayList;
import java.util.List;

import io.github.ghosthopper.color.PlayColor;
import io.github.ghosthopper.item.PlayPickItem;

/**
 * This is the abstract base class for an object that optionally can have a {@link #getColor() color}.
 */
public abstract class PlayTypedObjectWithItems extends PlayTypedObject {

  private final List<PlayPickItem> items;

  /**
   * The constructor.
   */
  public PlayTypedObjectWithItems() {
    this(null);
  }

  /**
   * The constructor.
   *
   * @param color - see {@link #getColor()}.
   */
  public PlayTypedObjectWithItems(PlayColor color) {
    super(color);
    this.items = new ArrayList<>();
  }

  /**
   * @return the {@link PlayPickItem}s contained in this object.
   */
  public List<PlayPickItem> getItems() {

    return this.items;
  }

}
