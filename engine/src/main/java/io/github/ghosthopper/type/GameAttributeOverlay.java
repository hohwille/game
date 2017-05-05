/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.type;

/**
 * Interface for an object that may have an {@link #getOverlay() overlay}.
 */
public interface GameAttributeOverlay {

  /**
   * @return the optional overlay type or {@code null} for none. If an overlay is present, its image will be added as
   *         small overlay icon to the image of this object in the view.
   */
  default GameTypeAccess getOverlay() {

    return null;
  }
}
