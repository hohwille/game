/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.properties;

/**
 * Extends {@link PlayPropertyContainer} with the ability to contain an {@link #getValue() actual value}.
 *
 * @param <V> the type of the contained {@link #getValue() value}.
 * @param <T> the type of this {@link PlayPropertyValue} itself.
 */
public interface PlayPropertyValue<V, T extends PlayPropertyValue<V, T>> extends PlayPropertyContainer<T> {

  /**
   * @return the actual value.
   */
  public V getValue();

}
