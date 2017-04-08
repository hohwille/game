/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.properties;

import io.github.ghosthopper.figure.PlayFigure;
import io.github.ghosthopper.game.PlayGame;

/**
 * An implementation of {@link PlayPropertyContainer} that represents a life value (typically of a {@link PlayFigure}).
 * The current value can be read as {@link #getFactor() factor} (relative) or {@link #getAbsolute() absolute value}.
 * Further there is a {@link #getMax() maximum value} as the limit for the {@link #getAbsolute() absolute value}.<br>
 * The predefined constants define various {@link PlayPropertyLifeValue}s as defaults. You can
 * {@link PlayProperties#get(PlayProperty) request} a {@link PlayPropertyLifeValue} for any of these constants. The
 * constant will act as default if the property does not yet exist. A {@link PlayGame} may even modify the constants to
 * change the defaults on initialization (or even during the game).
 */
public class PlayPropertyLifeValue extends PlayPropertyContainerImpl<PlayPropertyLifeValue> {

  /** {@link PlayPropertyLifeValue} for <em>health</em>. */
  public static final PlayPropertyLifeValue HEALTH = new PlayPropertyLifeValue("Health");

  /** {@link PlayPropertyLifeValue} for <em>energy</em>. */
  public static final PlayPropertyLifeValue ENERGY = new PlayPropertyLifeValue("Energy");

  /** {@link PlayPropertyLifeValue} for <em>mana</em> (magical power). */
  public static final PlayPropertyLifeValue MANA = new PlayPropertyLifeValue("Mana");

  /** {@link PlayPropertyLifeValue} for <em>stamina</em>. */
  public static final PlayPropertyLifeValue STAMINA = new PlayPropertyLifeValue("Stamina");

  /** {@link PlayPropertyLifeValue} for <em>strength</em>. */
  public static final PlayPropertyLifeValue STRENGTH = new PlayPropertyLifeValue("Strength");

  /** {@link PlayPropertyLifeValue} for <em>vitality</em>. */
  public static final PlayPropertyLifeValue VITALITY = new PlayPropertyLifeValue("Vitality");

  /** {@link PlayPropertyLifeValue} for <em>dexterity</em>. */
  public static final PlayPropertyLifeValue DEXTERITY = new PlayPropertyLifeValue("Dexterity");

  /** {@link PlayPropertyLifeValue} for <em>dexterity</em>. */
  public static final PlayPropertyLifeValue WISDOM = new PlayPropertyLifeValue("Wisdom");

  /** {@link PlayPropertyLifeValue} for <em>dexterity</em>. */
  public static final PlayPropertyLifeValue LUCK = new PlayPropertyLifeValue("Luck");

  /** {@link PlayPropertyLifeValue} for <em>dexterity</em>. */
  public static final PlayPropertyLifeValue FOOD = new PlayPropertyLifeValue("Food");

  /** {@link PlayPropertyLifeValue} for <em>dexterity</em>. */
  public static final PlayPropertyLifeValue WATER = new PlayPropertyLifeValue("Water");

  /** {@link PlayPropertyLifeValue} for <em>load</em> (e.g. in kg). */
  public static final PlayPropertyLifeValue LOAD = new PlayPropertyLifeValue("Load");

  private double factor;

  private double max;

  /**
   * The constructor.
   *
   * @param defaults - see {@link #getDefaultValue()}.
   */
  protected PlayPropertyLifeValue(PlayPropertyLifeValue defaults) {
    super(defaults);
    this.factor = defaults.factor;
    this.max = defaults.max;
  }

  /**
   * The constructor.
   *
   * @param name - see {@link #getName()}.
   */
  public PlayPropertyLifeValue(String name) {
    this(name, 1, 100);
  }

  /**
   * The constructor.
   *
   * @param name - see {@link #getName()}.
   * @param factor - see {@link #getFactor()}.
   */
  public PlayPropertyLifeValue(String name, double factor) {
    this(name, factor, 100);
  }

  /**
   * The constructor.
   *
   * @param name - see {@link #getName()}.
   * @param factor - see {@link #getFactor()}.
   * @param max - see {@link #getMax()}.
   */
  public PlayPropertyLifeValue(String name, double factor, double max) {
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
   *         {@link PlayGame} or may also differ per object (typically {@link PlayFigure}).
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
  protected PlayPropertyLifeValue copy() {

    return new PlayPropertyLifeValue(this);
  }

}
