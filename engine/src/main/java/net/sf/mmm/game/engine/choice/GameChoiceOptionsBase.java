/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.game.engine.choice;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import net.sf.mmm.game.engine.Game;

/**
 * A simple implementation of {@link GameChoiceOptions}.
 *
 * @param <O> the type of the option.
 */
public class GameChoiceOptionsBase<O> extends GameChoiceSingleBase<O> implements GameChoiceOptions<O> {

  private final List<O> options;

  private final int minOptions;

  private final int maxOptions;

  /**
   * The constructor.
   *
   * @param game - see {@link #getGame()}.
   * @param id - see {@link #getId()}.
   * @param description - see {@link #getDescription()}.
   * @param options - see {@link #getOptions()}.
   */
  @SafeVarargs
  public GameChoiceOptionsBase(Game game, String id, String description, O... options) {
    this(game, id, description, 1, 1, options);
  }

  /**
   * The constructor.
   *
   * @param game - see {@link #getGame()}.
   * @param id - see {@link #getId()}.
   * @param description - see {@link #getDescription()}.
   * @param minOptions - see {@link #getMinOptions()}.
   * @param options - see {@link #getOptions()}.
   */
  @SafeVarargs
  public GameChoiceOptionsBase(Game game, String id, String description, int minOptions, O... options) {
    this(game, id, description, Arrays.asList(options), minOptions);
  }

  /**
   * The constructor.
   *
   * @param game - see {@link #getGame()}.
   * @param id - see {@link #getId()}.
   * @param description - see {@link #getDescription()}.
   * @param minOptions - see {@link #getMinOptions()}.
   * @param maxOptions - see {@link #getMaxOptions()}.
   * @param options - see {@link #getOptions()}.
   */
  @SafeVarargs
  public GameChoiceOptionsBase(Game game, String id, String description, int minOptions, int maxOptions, O... options) {
    this(game, id, description, Arrays.asList(options), minOptions, maxOptions);
  }

  /**
   * The constructor.
   *
   * @param game - see {@link #getGame()}.
   * @param id - see {@link #getId()}.
   * @param description - see {@link #getDescription()}.
   * @param options - see {@link #getOptions()}.
   * @param minOptions - see {@link #getMinOptions()}.
   */
  public GameChoiceOptionsBase(Game game, String id, String description, List<O> options, int minOptions) {
    this(game, id, description, options, minOptions, options.size());
  }

  /**
   * The constructor.
   *
   * @param game - see {@link #getGame()}.
   * @param id - see {@link #getId()}.
   * @param description - see {@link #getDescription()}.
   * @param options - see {@link #getOptions()}.
   * @param minOptions - see {@link #getMinOptions()}.
   * @param maxOptions - see {@link #getMaxOptions()}.
   */
  public GameChoiceOptionsBase(Game game, String id, String description, List<O> options, int minOptions, int maxOptions) {
    super(game, id, description);
    this.minOptions = minOptions;
    this.maxOptions = maxOptions;
    this.options = Collections.unmodifiableList(options);
  }

  /**
   * @return the options
   */
  @Override
  public List<O> getOptions() {

    return this.options;
  }

  @Override
  public int getMinOptions() {

    return this.minOptions;
  }

  @Override
  public int getMaxOptions() {

    return this.maxOptions;
  }

  /**
   * @param <O> the type of the option.
   * @param game - see {@link #getGame()}.
   * @param id - see {@link #getId()}.
   * @param description - see {@link #getDescription()}.
   * @param options - see {@link #getOptions()}.
   * @return a new instance of {@link GameChoiceOptionsBase} that allows an opt out selection. In such case all given
   *         {@link #getOptions() options} are chosen by default and the user can de-select (opt out)
   *         {@link #getOptions() options} until the {@link #getMinOptions() minimum} is reached.
   */
  @SafeVarargs
  public static <O> GameChoiceOptionsBase<O> ofOptOut(Game game, String id, String description, O... options) {

    return new GameChoiceOptionsBase<>(game, id, description, options.length, options);
  }

}
