/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.choice;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import io.github.ghosthopper.game.PlayGame;

/**
 * A simple implementation of {@link PlayChoiceOptions}.
 *
 * @param <O> the type of the option.
 */
public class PlayChoiceOptionsBase<O> extends PlayChoiceBase<O> implements PlayChoiceOptions<O> {

  private final List<O> options;

  private final int maxOptions;

  private List<O> selection;

  /**
   * The constructor.
   *
   * @param game - see {@link #getGame()}.
   * @param id - see {@link #getId()}.
   * @param description - see {@link #getDescription()}.
   * @param options - see {@link #getOptions()}.
   */
  @SafeVarargs
  public PlayChoiceOptionsBase(PlayGame game, String id, String description, O... options) {
    this(game, id, description, 1, options);
  }

  /**
   * The constructor.
   *
   * @param game - see {@link #getGame()}.
   * @param id - see {@link #getId()}.
   * @param description - see {@link #getDescription()}.
   * @param maxOptions - see {@link #getMaxOptions()}.
   * @param options - see {@link #getOptions()}.
   */
  @SafeVarargs
  public PlayChoiceOptionsBase(PlayGame game, String id, String description, int maxOptions, O... options) {
    super(game, id, description);
    this.maxOptions = maxOptions;
    this.options = Collections.unmodifiableList(Arrays.asList(options));
  }

  /**
   * @return the options
   */
  @Override
  public List<O> getOptions() {

    return this.options;
  }

  @Override
  public int getMaxOptions() {

    return this.maxOptions;
  }

  @Override
  public void select(List<O> selectedOptions) {

    this.selection = selectedOptions;
    super.select(selectedOptions);
  }

  /**
   * @return the {@link #select(List) selected} options or {@code null} if {@link #select(List)} has not yet been
   *         called.
   */
  public List<O> getSelection() {

    return this.selection;
  }

  /**
   * @param <O> the type of the option.
   * @param game - see {@link #getGame()}.
   * @param id - see {@link #getId()}.
   * @param description - see {@link #getDescription()}.
   * @param options - see {@link #getOptions()}.
   * @return a new instance of {@link PlayChoiceOptionsBase} that allows an opt out selection. In such case all given
   *         {@link #getOptions() options} are chosen by default and the user can de-select (opt out)
   *         {@link #getOptions() options} until the {@link #getMinOptions() minimum} is reached.
   */
  @SafeVarargs
  public static <O> PlayChoiceOptionsBase<O> ofOptOut(PlayGame game, String id, String description, O... options) {

    return new PlayChoiceOptionsBase<>(game, id, description, options.length, options);
  }

}
