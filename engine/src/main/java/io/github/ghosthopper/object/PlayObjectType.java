/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.object;

/**
 * Abstract base class for an object type.
 */
public abstract class PlayObjectType extends PlayStateObjectWithId {

  /**
   * The constructor.
   *
   * @param id - see {@link #getId()}.
   */
  public PlayObjectType(String id) {
    super(id);
  }

  /**
   * @return the name of this type.
   */
  public abstract String getTypeName();

  /**
   * @return the optional overlay type or {@code null} for none.
   */
  public PlayStateObject getOverlay() {

    return null;
  }

}
