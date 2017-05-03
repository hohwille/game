/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.type;

import io.github.ghosthopper.game.PlayGame;
import io.github.ghosthopper.object.PlayTypedObject;

/**
 * Interface for the {@link PlayTypedObject#getType() type} of a {@link PlayTypedObject}.<br>
 * <b>Attention:</b><br>
 * A {@link PlayType} may only be modified (e.g. its {@link #getProperties() properties}) to setup a {@link PlayGame}
 * but <b>never</b> during a {@link PlayGame} to track status information. During the game modify the instances of
 * {@link PlayTypedObject} instead. These should be re-created when-ever a {@link PlayGame} is {@link PlayGame#begin()
 * begun}.
 *
 * @see PlayTypedObject#getType()
 */
public interface PlayType extends PlayTypeAccess, PlayAttributeOverlay {

  /**
   * @return the name of this type.
   */
  String getTypeName();

  /**
   * @return {@code true} if this {@link PlayType} is mutable and may change its state during the game, {@code false}
   *         otherwise (if immutable).
   */
  default boolean isMutable() {

    return false;
  }
}
