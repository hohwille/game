/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.ui.fx.data;

import java.util.Objects;

import io.github.ghosthopper.direction.GameAttributeDirection;
import io.github.ghosthopper.direction.GameDirection;
import io.github.ghosthopper.type.GameAttributeOverlay;
import io.github.ghosthopper.type.GameTypeAccess;

class GameUiFxImageKey implements GameAttributeDirection, GameAttributeOverlay {

  private final GameDirection direction;

  private final GameTypeAccess overlay;

  /**
   * The constructor.
   *
   * @param color - see {@link #getColor()}.
   * @param direction - see {@link #getDirection()}.
   * @param overlay - see {@link #getOverlay()}.
   */
  GameUiFxImageKey(GameDirection direction, GameTypeAccess overlay) {
    super();
    this.direction = direction;
    this.overlay = overlay;
  }

  @Override
  public GameDirection getDirection() {

    return this.direction;
  }

  @Override
  public GameTypeAccess getOverlay() {

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
    GameUiFxImageKey other = (GameUiFxImageKey) obj;
    if (!Objects.equals(this.direction, other.direction)) {
      return false;
    }
    if (!Objects.equals(this.overlay, other.overlay)) {
      return false;
    }
    return true;
  }

}
