/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.game.engine.properties;

/**
 * Implementation of {@link GamePropertyValueMutable} as Java Bean with primitive {@code double} value.
 */
public final class GamePropertyValueDouble extends GamePropertyContainerImpl<GamePropertyValueDouble>
    implements GamePropertyValueMutable<Double, GamePropertyValueDouble> {

  /**
   * The weight (e.g. of a {@link net.sf.mmm.game.engine.item.GameItem}).<br>
   * <b>Attention:</b><br>
   * {@link net.sf.mmm.game.engine.item.GameItemType} already defines
   * {@link net.sf.mmm.game.engine.item.GameItemType#getWeight()}. Be careful not to
   * {@link GameProperties#get(GameProperty) accidentally create} this {@link GameProperty} overriding with a default
   * value of {@code 0}.
   */
  public static final GamePropertyValueDouble WEIGHT = new GamePropertyValueDouble("Maximum Weight", 0);

  /**
   * The maximum {@link #WEIGHT weight} (e.g. for the {@link net.sf.mmm.game.engine.figure.GameFigure#getItems() items}
   * of a {@link net.sf.mmm.game.engine.figure.GameFigure}).
   */
  public static final GamePropertyValueDouble MAX_WEIGHT = new GamePropertyValueDouble("Maximum Weight", Double.MAX_VALUE);

  private double value;

  /**
   * The constructor.
   *
   * @param name - see {@link #getName()}.
   * @param value the {@link #getValue() value} used for {@link #getDefaultValue()}.
   */
  public GamePropertyValueDouble(String name, double value) {
    super(name);
    this.value = value;
  }

  /**
   * The constructor.
   *
   * @param name - see {@link #getName()}.
   * @param defaultValue - see {@link #getDefaultValue()}.
   */
  private GamePropertyValueDouble(GamePropertyValueDouble defaultValue) {
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
  protected GamePropertyValueDouble copy() {

    return new GamePropertyValueDouble(this);
  }

  /**
   * @param object the {@link GameAttributeProperties}.
   * @return the {@link #get() value}.
   */
  public double get(GameAttributeProperties object) {

    return object.getProperties().get(this).get();
  }

}
