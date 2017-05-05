/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.properties;

/**
 * Implementation of {@link GamePropertyValueMutable} as Java Bean with primitive {@code long} value.
 */
public final class GamePropertyValueLong extends GamePropertyContainerImpl<GamePropertyValueLong>
    implements GamePropertyValueMutable<Long, GamePropertyValueLong> {

  private long value;

  /**
   * The constructor.
   *
   * @param name - see {@link #getName()}.
   * @param value the {@link #getValue() value} used for {@link #getDefaultValue()}.
   */
  public GamePropertyValueLong(String name, long value) {
    super(name);
    this.value = value;
  }

  /**
   * The constructor.
   *
   * @param defaultValue - see {@link #getDefaultValue()}.
   */
  private GamePropertyValueLong(GamePropertyValueLong defaultValue) {

    super(defaultValue);
    this.value = defaultValue.value;
  }

  /**
   * @return the primitive {@link #getValue() value}.
   */
  public long get() {

    return this.value;
  }

  /**
   * @param newValue the new value of {@link #get()}.
   */
  public void set(long newValue) {

    this.value = newValue;
  }

  @Override
  public Long getValue() {

    return Long.valueOf(this.value);
  }

  @Override
  public void setValue(Long value) {

    if (value == null) {
      this.value = 0;
    } else {
      this.value = value.longValue();
    }
  }

  @Override
  protected GamePropertyValueLong copy() {

    return new GamePropertyValueLong(this);
  }

  /**
   * @param object the {@link GameAttributeProperties}.
   * @return the {@link #get() value}.
   */
  public long get(GameAttributeProperties object) {

    return object.getProperties().get(this).get();
  }

}
