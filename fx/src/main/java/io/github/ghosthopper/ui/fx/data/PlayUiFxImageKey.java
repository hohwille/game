/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.ui.fx.data;

import java.util.Objects;

import io.github.ghosthopper.move.PlayAttributeDirection;
import io.github.ghosthopper.move.PlayDirection;
import io.github.ghosthopper.type.PlayAttributeOverlay;
import io.github.ghosthopper.type.PlayTypeAccess;

class PlayUiFxImageKey implements PlayAttributeDirection, PlayAttributeOverlay {

  private final PlayDirection direction;

  private final PlayTypeAccess overlay;

  /**
   * The constructor.
   *
   * @param color - see {@link #getColor()}.
   * @param direction - see {@link #getDirection()}.
   * @param overlay - see {@link #getOverlay()}.
   */
  PlayUiFxImageKey(PlayDirection direction, PlayTypeAccess overlay) {
    super();
    this.direction = direction;
    this.overlay = overlay;
  }

  @Override
  public PlayDirection getDirection() {

    return this.direction;
  }

  @Override
  public PlayTypeAccess getOverlay() {

    return this.overlay;
  }

  @Override
  public int hashCode() {

    return Objects.hash(this.direction, this.overlay);
  }

  @Override
  public boolean equals(Object obj) {

    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    PlayUiFxImageKey other = (PlayUiFxImageKey) obj;
    if (!Objects.equals(this.direction, other.direction)) {
      return false;
    }
    if (!Objects.equals(this.overlay, other.overlay)) {
      return false;
    }
    return true;
  }

}
