/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.choice;

import java.util.List;
import java.util.Objects;

import io.github.ghosthopper.game.PlayGame;
import io.github.ghosthopper.object.PlayObject;

/**
 * A choice that the player has to take. The generic UI can render a screen where the user can pick the option(s) that
 * should be {@link #select(List) selected}. A {@link PlayChoice} may e.g. allow to {@link #select(List) select} the
 * number of players for the game.
 *
 * @param <O> the type of the option.
 */
public interface PlayChoice<O> extends PlayObject {

  /**
   * This ID is required to render the label for this {@link PlayChoice} via {@link #getLocalizedName() localized name}.
   */
  @Override
  String getId();

  /**
   * @return an optional description of this choice more detailed than the {@link #getId() id}
   *         ({@link #getLocalizedName() localized name}). Will be
   *         {@link io.github.ghosthopper.i18n.PlayTranslator#translate(String) localized} and used as additional info
   *         text or tooltip. May be {@link String#isEmpty() empty} or {@code null} to omit additional details.
   */
  default String getDescription() {

    return null;
  }

  /**
   * @return the minimum number of {@link #select(List) options to select}.
   */
  default int getMinOptions() {

    return 1;
  }

  /**
   * @return the maximum number of {@link #select(List) options to select}.
   */
  default int getMaxOptions() {

    return 1;
  }

  /**
   * @return {@code true} if the {@link #select(List) selected options} have to be unique, {@code false} if the same
   *         option can be {@link #select(List) selected} more than once. Only relevant if {@link #getMaxOptions() max
   *         options} is greater than {@code 1}.
   */
  default boolean isUniqueSelection() {

    return true;
  }

  /**
   * @return {@code true} if the order of the {@link #select(List) selected options} matters (the user will then be
   *         allowed to change the order of the options to select), {@code false} otherwise. Only relevant if
   *         {@link #getMaxOptions() max options} is greater than {@code 1}.
   */
  default boolean isOrderedSelection() {

    return false;
  }

  /**
   * @param options the option(s) that has/have been selected. Will be called from the view after the user has submitted
   *        his choice. The number of options will be in the range from {@link #getMinOptions()} to
   *        {@link #getMaxOptions()}.
   */
  default void select(List<O> options) {

    Objects.requireNonNull(options, "options");
    assert (options.size() >= getMinOptions());
    assert (options.size() <= getMaxOptions());
    getGame().sendEvent(new PlayChoiceSelectEvent(this, options));
  }

  /**
   * @return the {@link PlayGame} owning this {@link PlayChoice}.
   */
  @Override
  PlayGame getGame();

}
