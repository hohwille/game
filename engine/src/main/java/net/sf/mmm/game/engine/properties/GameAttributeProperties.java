/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.game.engine.properties;

/**
 * This is the interface for an object that may have {@link #getProperties() properties}.
 */
public interface GameAttributeProperties {

  /**
   * @return the {@link GameProperties} of this object.
   */
  GameProperties getProperties();
}
