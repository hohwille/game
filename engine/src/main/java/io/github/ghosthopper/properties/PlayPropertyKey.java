/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.properties;

import io.github.ghosthopper.color.PlayColor;
import io.github.ghosthopper.field.PlayField;
import io.github.ghosthopper.figure.PlayFigure;
import io.github.ghosthopper.game.PlayGame;
import io.github.ghosthopper.player.Player;

/**
 * Simple implementation of {@link PlayProperty}. This class already defines common {@link PlayPropertyKey property
 * keys} such as {@link #SCORE}. For additional properties see the constants defined in {@link PlayPropertyLifeValue}.
 * However, feel free to define your own custom {@link PlayPropertyKey}s for your {@link PlayGame}.
 *
 * @param <T> the type of the value of this {@link PlayProperty}.
 */
public class PlayPropertyKey<T> implements PlayProperty<T> {

  /** The {@link PlayPropertyKey} for the <em>score</em> of an object (e.g. {@link Player}). */
  public static final PlayProperty<Double> SCORE = new PlayPropertyKey<>("Score");

  /** The {@link PlayPropertyKey} for the <em>color</em> of an object (e.g. {@link PlayField} or {@link PlayFigure}). */
  public static final PlayProperty<PlayColor> COLOR = new PlayPropertyKey<>("Color");

  /** The {@link PlayPropertyKey} for the <em>score</em> of an object (e.g. {@link Player}). */
  public static final PlayProperty<Character> ASCII = new PlayPropertyKey<>("Ascii", Character.valueOf(' '));

  private final String name;

  private final T defaultValue;

  /**
   * The constructor.
   *
   * @param name - see {@link #getName()}.
   */
  public PlayPropertyKey(String name) {
    this(name, null);
  }

  /**
   * The constructor.
   *
   * @param name - see {@link #getName()}.
   * @param defaultValue - see {@link #getDefaultValue()}.
   */
  public PlayPropertyKey(String name, T defaultValue) {
    super();
    this.name = name;
    this.defaultValue = defaultValue;
  }

  @Override
  public String getName() {

    return this.name;
  }

  @Override
  public T getDefaultValue() {

    return this.defaultValue;
  }

}
