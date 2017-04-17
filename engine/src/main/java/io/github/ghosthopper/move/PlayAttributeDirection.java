/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.move;

/**
 * This is the interface for an object that optionally can have a {@link #getDirection() direction}.
 */
public interface PlayAttributeDirection {

  /**
   * @return the current {@link PlayDirection} this object is pointing to.
   */
  PlayDirection getDirection();

}
