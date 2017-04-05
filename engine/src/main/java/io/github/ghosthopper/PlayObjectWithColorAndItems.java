/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper;

import java.util.ArrayList;
import java.util.List;

import io.github.ghosthopper.item.PlayItem;

/**
 * This is the abstract base class for an object that optionally can have a {@link #getColor() color}.
 */
public abstract class PlayObjectWithColorAndItems extends PlayObjectWithColor {

  private final List<PlayItem> items;

  /**
   * The constructor.
   */
  public PlayObjectWithColorAndItems() {
    this(null);
  }

  /**
   * The constructor.
   *
   * @param color - see {@link #getColor()}.
   */
  public PlayObjectWithColorAndItems(PlayColor color) {
    super(color);
    this.items = new ArrayList<>();
  }

  /**
   * @return the {@link PlayItem}s contained in this object.
   */
  public List<PlayItem> getItems() {

    return this.items;
  }

}
