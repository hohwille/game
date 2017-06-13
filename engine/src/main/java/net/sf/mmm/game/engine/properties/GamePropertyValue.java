/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.game.engine.properties;

/**
 * Extends {@link GamePropertyContainer} with the ability to contain an {@link #getValue() actual value}.
 *
 * @param <V> the type of the contained {@link #getValue() value}.
 * @param <T> the type of this {@link GamePropertyValue} itself.
 */
public interface GamePropertyValue<V, T extends GamePropertyValue<V, T>> extends GamePropertyContainer<T> {

  /**
   * @return the actual value.
   */
  public V getValue();

}
