/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.game.engine.type;

import net.sf.mmm.game.engine.Game;
import net.sf.mmm.game.engine.object.GameTypedObject;

/**
 * Interface for the {@link GameTypedObject#getType() type} of a {@link GameTypedObject}.<br>
 * <b>Attention:</b><br>
 * A {@link GameType} may only be modified (e.g. its {@link #getProperties() properties}) to setup a {@link Game}
 * but <b>never</b> during a {@link Game} to track status information. During the game modify the instances of
 * {@link GameTypedObject} instead. These should be re-created when-ever a {@link Game} is {@link Game#begin()
 * begun}.
 *
 * @see GameTypedObject#getType()
 */
public interface GameType extends GameTypeAccess, GameAttributeOverlay {

  /**
   * @return the name of this type.
   */
  String getTypeName();

  /**
   * @return {@code true} if this {@link GameType} is mutable and may change its state during the game, {@code false}
   *         otherwise (if immutable).
   */
  default boolean isMutable() {

    return false;
  }
}
