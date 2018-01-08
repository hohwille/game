/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.game.engine.properties;

/**
 * Implementation of {@link GamePropertyValueMutable} as Java Bean with primitive {@code int} value.
 */
public final class GamePropertyValueInt extends GamePropertyContainerImpl<GamePropertyValueInt>
    implements GamePropertyValueMutable<Integer, GamePropertyValueInt> {

  /**
   * The maximum number of {@link net.sf.mmm.game.engine.item.GamePickItem items} (e.g. inventory size of
   * {@link net.sf.mmm.game.engine.figure.GameFigure}).
   */
  public static final GamePropertyValueInt MAX_ITEMS = new GamePropertyValueInt("Maximum Items", Integer.MAX_VALUE);

  /**
   * The maximum number of {@link net.sf.mmm.game.engine.figure.GameFigure figures} (e.g. figure capacity of
   * {@link net.sf.mmm.game.engine.field.GameField}).
   */
  public static final GamePropertyValueInt MAX_FIGURES = new GamePropertyValueInt("Maximum Figures", 9);

  private int value;

  /**
   * The constructor.
   *
   * @param name - see {@link #getName()}.
   * @param value the {@link #getValue() value} used for {@link #getDefaultValue()}.
   */
  public GamePropertyValueInt(String name, int value) {

    super(name);
    this.value = value;
  }

  /**
   * The constructor.
   *
   * @param defaultValue - see {@link #getDefaultValue()}.
   */
  private GamePropertyValueInt(GamePropertyValueInt defaultValue) {

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
  protected GamePropertyValueInt copy() {

    return new GamePropertyValueInt(this);
  }

  /**
   * @param object the {@link GameAttributeProperties}.
   * @return the {@link #get() value}.
   */
  public int get(GameAttributeProperties object) {

    return object.getProperties().get(this).get();
  }

}
