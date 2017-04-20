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
   * @return the {@link PlayPickItem}s contained in this object.
   */
  public List<PlayPickItem> getItems();
}
