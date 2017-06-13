/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.game.engine.properties;

/**
 * Implementation of {@link GamePropertyValueMutable} as Java Bean.
 *
 * @param <V> the type of the {@link #getValue() value} of this {@link GameProperty}.
 * @param <T> the type of this {@link GamePropertyContainer} itself.
 */
public abstract class GamePropertyValueImpl<V, T extends GamePropertyValueImpl<V, T>> extends GamePropertyContainerImpl<T>
    implements GamePropertyValueMutable<V, T> {

  private V value;

  /**
   * The constructor.
   *
   * @param name - see {@link #getName()}.
   * @param value the {@link #getValue() value} used for {@link #getDefaultValue()}.
   */
  public GamePropertyValueImpl(String name, V value) {
    super(name);
    this.value = value;
  }

  /**
   * The constructor.
   *
   * @param defaultValue - see {@link #getDefaultValue()}.
   */
  protected GamePropertyValueImpl(T defaultValue) {
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
