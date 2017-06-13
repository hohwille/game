/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.game.engine.position;

/**
 * This is the interface for an object that {@link #getPosition() has} a {@link GamePosition}.
 */
public interface GameAttributePosition {

  /**
   * @return the current {@link GamePosition} of this asset. Defaults to {@link GamePosition#CENTER} if not set.
   */
  GamePosition getPosition();

  /**
   * @param position the new {@link GamePosition}.
   */
  void setPosition(GamePosition position);

}
