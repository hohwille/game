/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.properties;

/**
 * Abstract base implementation of {@link PlayPropertyContainer}.
 *
 * @param <T> the type of this {@link PlayPropertyContainer} itself.
 */
public abstract class PlayPropertyContainerImpl<T extends PlayPropertyContainerImpl<T>> extends PlayPropertyKey<T> implements PlayPropertyContainer<T> {

  /**
   * The constructor.
   *
   * @param name - see {@link #getName()}.
   */
  protected PlayPropertyContainerImpl(String name) {
    super(name);
  }

  /**
   * The constructor.
   *
   * @param defaultValue - see {@link #getDefaultValue()}.
   */
  protected PlayPropertyContainerImpl(T defaultValue) {
    super(defaultValue.getName(), defaultValue);
  }

  /**
   * @return creates a copy of this property instance.
   */
  protected abstract T copy();

  @SuppressWarnings("unchecked")
  @Override
  public T getDefaultValue() {

    T defaults = super.getDefaultValue();
    if (defaults == null) {
      defaults = (T) this;
    }
    return defaults.copy();
  }

}
