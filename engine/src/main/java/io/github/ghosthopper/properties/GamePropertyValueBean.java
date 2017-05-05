/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.properties;

/**
 * Implementation of {@link GamePropertyValueMutable} as Java Bean.
 *
 * @param <V> the type of the {@link #getValue() value} of this {@link GameProperty}.
 */
public final class GamePropertyValueBean<V> extends GamePropertyValueImpl<V, GamePropertyValueBean<V>> {

  /**
   * The constructor.
   *
   * @param name - see {@link #getName()}.
   * @param defaultValue the {@link #getValue() value} used for {@link #getDefaultValue()}.
   */
  public GamePropertyValueBean(String name, V defaultValue) {
    super(name, defaultValue);
  }

  /**
   * The constructor.
   *
   * @param defaultValue - see {@link #getDefaultValue()}.
   */
  private GamePropertyValueBean(GamePropertyValueBean<V> defaultValue) {
    super(defaultValue);
  }

  @Override
  protected GamePropertyValueBean<V> copy() {

    return new GamePropertyValueBean<>(this);
  }

}
