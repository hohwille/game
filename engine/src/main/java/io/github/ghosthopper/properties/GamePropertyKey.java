/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.properties;

import io.github.ghosthopper.color.GameColor;
import io.github.ghosthopper.field.GameField;
import io.github.ghosthopper.figure.GameFigure;
import io.github.ghosthopper.game.Game;
import io.github.ghosthopper.player.GamePlayer;

/**
 * Simple implementation of {@link GameProperty}. This class already defines common {@link GamePropertyKey property
 * keys} such as {@link #SCORE}. For additional properties see the constants defined in {@link GamePropertyLifeValue}.
 * However, feel free to define your own custom {@link GamePropertyKey}s for your {@link Game}.
 *
 * @param <T> the type of the value of this {@link GameProperty}.
 */
public class GamePropertyKey<T> implements GameProperty<T> {

  /** The {@link GamePropertyKey} for the <em>score</em> of an object (e.g. {@link GamePlayer}). */
  public static final GameProperty<Double> SCORE = new GamePropertyKey<>("Score");

  /** The {@link GamePropertyKey} for the <em>color</em> of an object (e.g. {@link GameField} or {@link GameFigure}). */
  public static final GameProperty<GameColor> COLOR = new GamePropertyKey<>("Color");

  /** The {@link GamePropertyKey} for the <em>score</em> of an object (e.g. {@link GamePlayer}). */
  public static final GameProperty<Character> ASCII = new GamePropertyKey<>("Ascii", Character.valueOf(' '));

  private final String name;

  private final T defaultValue;

  /**
   * The constructor.
   *
   * @param name - see {@link #getName()}.
   */
  public GamePropertyKey(String name) {
    this(name, null);
  }

  /**
   * The constructor.
   *
   * @param name - see {@link #getName()}.
   * @param defaultValue - see {@link #getDefaultValue()}.
   */
  public GamePropertyKey(String name, T defaultValue) {
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
