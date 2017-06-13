/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.game.engine.properties;

/**
 * Abstract base implementation of {@link GamePropertyContainer}.
 *
 * @param <T> the type of this {@link GamePropertyContainer} itself.
 */
public abstract class GamePropertyContainerImpl<T extends GamePropertyContainerImpl<T>> extends GamePropertyKey<T> implements GamePropertyContainer<T> {

  /**
   * The constructor.
   *
   * @param name - see {@link #getName()}.
   */
  protected GamePropertyContainerImpl(String name) {
    super(name);
  }

  /**
   * The constructor.
   *
   * @param defaultValue - see {@link #getDefaultValue()}.
   */
  protected GamePropertyContainerImpl(T defaultValue) {
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
