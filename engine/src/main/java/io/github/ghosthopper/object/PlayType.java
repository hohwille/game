/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.object;

/**
 * Interface for any object type.
 *
 * @see AbstractPlayTypedObject#getType()
 */
public interface PlayType extends PlayStateObject {

  /**
   * @return the name of this type.
   */
  String getTypeName();

  /**
   * @return the optional overlay type or {@code null} for none.
   */
  default AbstractPlayStateObject getOverlay() {

    return null;
  }
}
