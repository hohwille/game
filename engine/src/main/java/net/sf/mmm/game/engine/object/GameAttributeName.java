/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.game.engine.object;

/**
 * This is the interface for an object that has a {@link #getName() name}.
 */
public interface GameAttributeName {

  /**
   * @return the name of this object.
   */
  String getName();

  /**
   * @param name the new value of {@link #getName()}.
   */
  void setName(String name);

}
