/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.properties;

/**
 * Implementation of {@link PlayPropertyValueMutable} as Java Bean with primitive {@code double} value.
 */
public final class PlayPropertyValueDouble extends PlayPropertyContainerImpl<PlayPropertyValueDouble>
    implements PlayPropertyValueMutable<Double, PlayPropertyValueDouble> {

  /**
   * The weight (e.g. of a {@link io.github.ghosthopper.item.PlayItem}).<br>
   * <b>Attention:</b><br>
   * {@link io.github.ghosthopper.item.PlayItemType} already defines
   * {@link io.github.ghosthopper.item.PlayItemType#getWeight()}. Be careful not to
   * {@link PlayProperties#get(PlayProperty) accidentally create} this {@link PlayProperty} overriding with a default
   * value of {@code 0}.
   */
  public static final PlayPropertyValueDouble WEIGHT = new PlayPropertyValueDouble("Maximum Weight", 0);

  /**
   * The maximum {@link #WEIGHT weight} (e.g. for the {@link io.github.ghosthopper.figure.PlayFigure#getItems() items}
   * of a {@link io.github.ghosthopper.figure.PlayFigure}).
   */
  public static final PlayPropertyValueDouble MAX_WEIGHT = new PlayPropertyValueDouble("Maximum Weight", Double.MAX_VALUE);

  private double value;

  /**
   * The constructor.
   *
   * @param name - see {@link #getName()}.
   * @param value the {@link #getValue() value} used for {@link #getDefaultValue()}.
   */
  public PlayPropertyValueDouble(String name, double value) {
    super(name);
    this.value = value;
  }

  /**
   * The constructor.
   *
   * @param name - see {@link #getName()}.
   * @param defaultValue - see {@link #getDefaultValue()}.
   */
  private PlayPropertyValueDouble(PlayPropertyValueDouble defaultValue) {
    super(defaultValue);
    this.value = defaultValue.value;
  }

  /**
   * @return the primitive {@link #getValue() value}.
   */
  public double get() {

    return this.value;
  }

  /**
   * @param newValue the new value of {@link #get()}.
   */
  public void set(double newValue) {

    this.value = newValue;
  }

  @Override
  public Double getValue() {

    return Double.valueOf(this.value);
  }

  @Override
  public void setValue(Double value) {

    if (value == null) {
      this.value = 0;
    } else {
      this.value = value.doubleValue();
    }
  }

  @Override
  protected PlayPropertyValueDouble copy() {

    return new PlayPropertyValueDouble(this);
  }

  /**
   * @param object the {@link PlayAttributeProperties}.
   * @return the {@link #get() value}.
   */
  public double get(PlayAttributeProperties object) {

    return object.getProperties().get(this).get();
  }

}
