/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.properties;

import io.github.ghosthopper.figure.PlayFigure;
import io.github.ghosthopper.item.PlayPickItem;

/**
 * Implementation of {@link PlayPropertyValueMutable} as Java Bean with primitive {@code int} value.
 */
public final class PlayPropertyValueInt extends PlayPropertyContainerImpl<PlayPropertyValueInt>
    implements PlayPropertyValueMutable<Integer, PlayPropertyValueInt> {

  /** The maximum number of {@link PlayPickItem items} (e.g. inventory size of {@link PlayFigure}). */
  public static final PlayPropertyValueInt MAX_ITEMS = new PlayPropertyValueInt("Maximum Items", Integer.MAX_VALUE);

  /** The maximum number of {@link PlayPickItem items} (e.g. inventory size of {@link PlayFigure}). */
  public static final PlayPropertyValueInt MAX_FIGURES = new PlayPropertyValueInt("Maximum Figures", 9);

  private int value;

  /**
   * The constructor.
   *
   * @param name - see {@link #getName()}.
   * @param value the {@link #getValue() value} used for {@link #getDefaultValue()}.
   */
  public PlayPropertyValueInt(String name, int value) {
    super(name);
    this.value = value;
  }

  /**
   * The constructor.
   *
   * @param defaultValue - see {@link #getDefaultValue()}.
   */
  private PlayPropertyValueInt(PlayPropertyValueInt defaultValue) {

    super(defaultValue);
    this.value = defaultValue.value;
  }

  /**
   * @return the primitive {@link #getValue() value}.
   */
  public int get() {

    return this.value;
  }

  /**
   * @param newValue the new value of {@link #get()}.
   */
  public void set(int newValue) {

    this.value = newValue;
  }

  @Override
  public Integer getValue() {

    return Integer.valueOf(this.value);
  }

  @Override
  public void setValue(Integer value) {

    if (value == null) {
      this.value = 0;
    } else {
      this.value = value.intValue();
    }
  }

  @Override
  protected PlayPropertyValueInt copy() {

    return new PlayPropertyValueInt(this);
  }

  /**
   * @param object the {@link PlayAttributeProperties}.
   * @return the {@link #get() value}.
   */
  public int get(PlayAttributeProperties object) {

    return object.getProperties().get(this).get();
  }

}
