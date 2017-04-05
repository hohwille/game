/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper;

/**
 * Abstract base class for an object type.
 */
public abstract class PlayObjectType extends PlayObjectWithId {

  /**
   * The constructor.
   *
   * @param id - see {@link #getId()}.
   */
  public PlayObjectType(String id) {
    super(id);
  }

}
