/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.object;

import io.github.ghosthopper.color.GameAttributeColorAdvanced;
import io.github.ghosthopper.type.GameType;
import io.github.ghosthopper.type.GameTypeAccess;

/**
 * A {@link GameStateObject} that has a {@link #getType() type} (and a {@link #getColor() color}).
 */
public interface GameTypedObject extends GameTypeAccess, GameAttributeColorAdvanced {

  /**
   * @return the type that classifies this object.
   */
  GameType getType();

}
