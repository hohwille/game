/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.properties;

/**
 * Extends {@link GamePropertyValue} with the ability to {@link #setValue(Object) set} the {@link #getValue() contained
 * value}.
 *
 * @param <V> the type of the contained {@link #getValue() value}.
 * @param <T> the type of this {@link GamePropertyValue} itself.
 */
public interface GamePropertyValueMutable<V, T extends GamePropertyValueMutable<V, T>> extends GamePropertyValue<V, T> {

  /**
   * @param value the new value of {@link #getValue()}.
   */
  void setValue(V value);

}
