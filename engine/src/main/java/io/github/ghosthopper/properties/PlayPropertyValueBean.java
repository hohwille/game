/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.properties;

/**
 * Implementation of {@link PlayPropertyValueMutable} as Java Bean.
 *
 * @param <V> the type of the {@link #getValue() value} of this {@link PlayProperty}.
 */
public final class PlayPropertyValueBean<V> extends PlayPropertyValueImpl<V, PlayPropertyValueBean<V>> {

  /**
   * The constructor.
   *
   * @param name - see {@link #getName()}.
   * @param defaultValue the {@link #getValue() value} used for {@link #getDefaultValue()}.
   */
  public PlayPropertyValueBean(String name, V defaultValue) {
    super(name, defaultValue);
  }

  /**
   * The constructor.
   *
   * @param defaultValue - see {@link #getDefaultValue()}.
   */
  private PlayPropertyValueBean(PlayPropertyValueBean<V> defaultValue) {
    super(defaultValue);
  }

  @Override
  protected PlayPropertyValueBean<V> copy() {

    return new PlayPropertyValueBean<>(this);
  }

}
