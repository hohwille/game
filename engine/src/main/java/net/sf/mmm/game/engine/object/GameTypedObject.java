/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.game.engine.object;

import net.sf.mmm.game.engine.color.GameAttributeColorAdvanced;
import net.sf.mmm.game.engine.type.GameType;
import net.sf.mmm.game.engine.type.GameTypeAccess;

/**
 * A {@link GameStateObject} that has a {@link #getType() type} (and a {@link #getColor() color}).
 */
public interface GameTypedObject extends GameTypeAccess, GameAttributeColorAdvanced {

  /**
   * @return the type that classifies this object.
   */
  GameType getType();

}
