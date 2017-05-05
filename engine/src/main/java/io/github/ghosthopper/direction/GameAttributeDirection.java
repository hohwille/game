/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.direction;

/**
 * This is the interface for an object that optionally can have a {@link #getDirection() direction}.
 */
public interface GameAttributeDirection {

  /**
   * @return the current {@link GameDirection} this object is pointing to.
   */
  GameDirection getDirection();

}
