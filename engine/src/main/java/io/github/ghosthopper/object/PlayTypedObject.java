/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.object;

import io.github.ghosthopper.color.PlayAttributeColorAdvanced;
import io.github.ghosthopper.type.PlayType;
import io.github.ghosthopper.type.PlayTypeAccess;

/**
 * A {@link PlayStateObject} that has a {@link #getType() type} (and a {@link #getColor() color}).
 */
public interface PlayTypedObject extends PlayTypeAccess, PlayAttributeColorAdvanced {

  /**
   * @return the type that classifies this object.
   */
  PlayType getType();

}
