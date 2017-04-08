/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.properties;

/**
 * Extends {@link PlayPropertyValue} with the ability to {@link #setValue(Object) set} the {@link #getValue() contained
 * value}.
 *
 * @param <V> the type of the contained {@link #getValue() value}.
 * @param <T> the type of this {@link PlayPropertyValue} itself.
 */
public interface PlayPropertyValueMutable<V, T extends PlayPropertyValueMutable<V, T>> extends PlayPropertyValue<V, T> {

  /**
   * @param value the new value of {@link #getValue()}.
   */
  void setValue(V value);

}
