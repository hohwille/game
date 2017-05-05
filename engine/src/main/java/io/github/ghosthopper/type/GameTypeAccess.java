/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.type;

import io.github.ghosthopper.object.GameStateObject;
import io.github.ghosthopper.object.GameTypedObject;

/**
 * Interface for an object that either implements {@link GameType} or {@link GameTypedObject}. May only be extended or
 * implemented directly by these two type.
 */
public interface GameTypeAccess extends GameStateObject {

  // nothing to add...

}
