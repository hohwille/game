/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.properties;

import io.github.ghosthopper.figure.GameFigure;
import io.github.ghosthopper.game.Game;

/**
 * An implementation of {@link GamePropertyContainer} that represents a life value (typically of a {@link GameFigure}).
 * The current value can be read as {@link #getFactor() factor} (relative) or {@link #getAbsolute() absolute value}.
 * Further there is a {@link #getMax() maximum value} as the limit for the {@link #getAbsolute() absolute value}.<br>
 * The predefined constants define various {@link GamePropertyLifeValue}s as defaults. You can
 * {@link GameProperties#get(GameProperty) request} a {@link GamePropertyLifeValue} for any of these constants. The
 * constant will act as default if the property does not yet exist. A {@link Game} may even modify the constants to
 * change the defaults on initialization (or even during the game).
 */
public class GamePropertyLifeValue extends GamePropertyContainerImpl<GamePropertyLifeValue> {

  /** {@link GamePropertyLifeValue} for <em>health</em>. */
  public static final GamePropertyLifeValue HEALTH = new GamePropertyLifeValue("Health");

  /** {@link GamePropertyLifeValue} for <em>energy</em>. */
  public static final GamePropertyLifeValue ENERGY = new GamePropertyLifeValue("Energy");

  /** {@link GamePropertyLifeValue} for <em>mana</em> (magical power). */
  public static final GamePropertyLifeValue MANA = new GamePropertyLifeValue("Mana");

  /** {@link GamePropertyLifeValue} for <em>stamina</em>. */
  public static final GamePropertyLifeValue STAMINA = new GamePropertyLifeValue("Stamina");

  /** {@link GamePropertyLifeValue} for <em>strength</em>. */
  public static final GamePropertyLifeValue STRENGTH = new GamePropertyLifeValue("Strength");

  /** {@link GamePropertyLifeValue} for <em>vitality</em>. */
  public static final GamePropertyLifeValue VITALITY = new GamePropertyLifeValue("Vitality");

  /** {@link GamePropertyLifeValue} for <em>dexterity</em>. */
  public static final GamePropertyLifeValue DEXTERITY = new GamePropertyLifeValue("Dexterity");

  /** {@link GamePropertyLifeValue} for <em>dexterity</em>. */
  public static final GamePropertyLifeValue WISDOM = new GamePropertyLifeValue("Wisdom");

  /** {@link GamePropertyLifeValue} for <em>dexterity</em>. */
  public static final GamePropertyLifeValue LUCK = new GamePropertyLifeValue("Luck");

  /** {@link GamePropertyLifeValue} for <em>dexterity</em>. */
  public static final GamePropertyLifeValue FOOD = new GamePropertyLifeValue("Food");

  /** {@link GamePropertyLifeValue} for <em>dexterity</em>. */
  public static final GamePropertyLifeValue WATER = new GamePropertyLifeValue("Water");

  /** {@link GamePropertyLifeValue} for <em>load</em> (e.g. in kg). */
  public static final GamePropertyLifeValue LOAD = new GamePropertyLifeValue("Load");

  private double factor;

  private double max;

  /**
   * The constructor.
   *
   * @param defaults - see {@link #getDefaultValue()}.
   */
  protected GamePropertyLifeValue(GamePropertyLifeValue defaults) {
    super(defaults);
    this.factor = defaults.factor;
    this.max = defaults.max;
  }

  /**
   * The constructor.
   *
   * @param name - see {@link #getName()}.
   */
  public GamePropertyLifeValue(String name) {
    this(name, 1, 100);
  }

  /**
   * The constructor.
   *
   * @param name - see {@link #getName()}.
   * @param factor - see {@link #getFactor()}.
   */
  public GamePropertyLifeValue(String name, double factor) {
    this(name, factor, 100);
  }

  /**
   * The constructor.
   *
   * @param name - see {@link #getName()}.
   * @param factor - see {@link #getFactor()}.
   * @param max - see {@link #getMax()}.
   */
  public GamePropertyLifeValue(String name, double factor, double max) {
    super(name);
    setMax(max);
    setFactor(factor);
  }

  /**
   * @see #getFactor()
   * @return the current absolute value in the range from {@code 0} to {@link #getMax()}.
   */
  public double getAbsolute() {

    return this.factor * this.max;
  }

  /**
   * @param absolute the new value of {@link #getAbsolute()}.
   */
  public void setAbsolute(double absolute) {

    if ((absolute < 0) || (absolute > this.max)) {
      throw new IllegalArgumentException("Illegal absolute: " + absolute);
    }
    this.factor = absolute / this.max;
  }

  /**
   * @param add the partial {@link #getAbsolute() absolute} to add (e.g. {@code 1}) as bonus/regeneration (or
   *        penalty/loss if negative).
   * @return the new {@link #getFactor() factor}.
   */
  public double addAbsolute(double add) {

    if (this.factor < 1) {
      addFactor(this.max / add);
    }
    return getAbsolute();
  }

  /**
   * @return the current life value as factor in the range from {@code 0} (empty) to {@code 1} (full). E.g. a value of
   *         {@code 0.5} means that {@code 50%} of the {@link #getMax() max} value is the {@link #getAbsolute() current
   *         absolute value}.
   */
  public double getFactor() {

    return this.factor;
  }

  /**
   * @param factor the new value of {@link #getFactor()}.
   */
  public void setFactor(double factor) {

    if ((factor < 0) || (factor > 1)) {
      throw new IllegalArgumentException("Illegal factor: " + factor);
    }
    this.factor = factor;
  }

  /**
   * @param add the partial {@link #getFactor() factor} to add (e.g. {@code 0.01}) as bonus/regeneration (or
   *        penalty/loss if negative).
   * @return the new {@link #getFactor() factor}.
   */
  public double addFactor(double add) {

    double newFactor = this.factor + add;
    if (newFactor > 1) {
      newFactor = 1;
    }
    if (newFactor < 0) {
      newFactor = 0;
    }
    this.factor = newFactor;
    return this.factor;
  }

  /**
   * @return the (current) maximum the {@link #getAbsolute() absolute value} can reach. Can be absolute per
   *         {@link Game} or may also differ per object (typically {@link GameFigure}).
   */
  public double getMax() {

    return this.max;
  }

  /**
   * @param max the new value of {@link #getMax()}.
   */
  public void setMax(double max) {

    if (max <= 0) {
      throw new IllegalArgumentException("Illegal max value: " + max);
    }
    this.max = max;
  }

  @Override
  protected GamePropertyLifeValue copy() {

    return new GamePropertyLifeValue(this);
  }

}
