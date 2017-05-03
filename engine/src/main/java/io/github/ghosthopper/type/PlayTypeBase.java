/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.type;

import io.github.ghosthopper.object.PlayStateObjectWithId;

/**
 * Abstract base class for an object type.
 */
public abstract class PlayTypeBase extends PlayStateObjectWithId implements PlayType {

  /**
   * The constructor.
   *
   * @param id - see {@link #getId()}.
   */
  public PlayTypeBase(String id) {
    super(id);
  }

}