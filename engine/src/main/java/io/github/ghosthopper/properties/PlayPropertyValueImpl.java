/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.properties;

/**
 * Implementation of {@link PlayPropertyValueMutable} as Java Bean.
 *
 * @param <V> the type of the {@link #getValue() value} of this {@link PlayProperty}.
 * @param <T> the type of this {@link PlayPropertyContainer} itself.
 */
public abstract class PlayPropertyValueImpl<V, T extends PlayPropertyValueImpl<V, T>> extends PlayPropertyContainerImpl<T>
    implements PlayPropertyValueMutable<V, T> {

  private V value;

  /**
   * The constructor.
   *
   * @param name - see {@link #getName()}.
   * @param value the {@link #getValue() value} used for {@link #getDefaultValue()}.
   */
  public PlayPropertyValueImpl(String name, V value) {
    super(name);
    this.value = value;
  }

  /**
   * The constructor.
   *
   * @param defaultValue - see {@link #getDefaultValue()}.
   */
  protected PlayPropertyValueImpl(T defaultValue) {
    super(defaultValue);
  }

  @Override
  public V getValue() {

    return this.value;
  }

  @Override
  public void setValue(V value) {

    this.value = value;
  }

}
