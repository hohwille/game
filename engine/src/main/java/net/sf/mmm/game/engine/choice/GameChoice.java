/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.game.engine.choice;

import java.util.List;

import net.sf.mmm.game.engine.Game;
import net.sf.mmm.game.engine.i18n.GameTranslator;
import net.sf.mmm.game.engine.object.GameObject;

/**
 * A choice that the player has to take. The generic UI can render a screen where the user can pick the option(s) that
 * should be {@link #select(List) selected}. A {@link GameChoice} may e.g. allow to {@link #select(List) select} the
 * number of players for the game.
 *
 * @param <O> the type of the option.
 */
public interface GameChoice<O> extends GameObject {

  /** The default title (ID) used if no {@link GameChoiceGroup} is present. */
  String DEFAULT_TITLE = "Choice";

  /** The default {@link net.sf.mmm.game.engine.type.GameType#getTypeName() type name} for a {@link GameChoice}. */
  String TYPE_NAME = "Choice";

  /**
   * This ID is required to render the label for this {@link GameChoice} via {@link #getLocalizedName() localized name}.
   */
  @Override
  String getId();

  /**
   * @return an optional description of this choice more detailed than the {@link #getId() id}
   *         ({@link #getLocalizedName() localized name}). Will be
   *         {@link net.sf.mmm.game.engine.i18n.GameTranslator#translate(String) localized} and used as additional info
   *         text or tooltip. May be {@link String#isEmpty() empty} or {@code null} to omit additional details.
   */
  default String getDescription() {

    return null;
  }

  /**
   * @return the {@link GameTranslator#translate(String) localized} {@link #getDescription() description} or
   *         {@code null} if the no decsription is available.
   */
  default String getLocalizedDescription() {

    String description = getDescription();
    if ((description == null) || description.isEmpty()) {
      return null;
    }
    return getGame().getTranslator().translate(description);
  }

  /**
   * @param selection the {@link List} of options for {@link #select(List) selection}.
   * @return {@code null} if the given {@code selection} is valid, or a validation error message (will be
   *         {@link net.sf.mmm.game.engine.i18n.GameTranslator#translate(String) localized}).
   */
  default String validate(List<O> selection) {

    if (selection == null) {
      return "Selection must not be null";
    }
    return GameValidator.validateRange(selection.size(), getMinOptions(), getMaxOptions());
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
   * @param selection the option(s) that has/have been selected. Will be called from the view after the user has
   *        submitted his choice. The number of options will be in the range from {@link #getMinOptions()} to
   *        {@link #getMaxOptions()}.
   * @return {@code null} if valid or otherwise a validation error message. See {@link #validate(List)} for further
   *         details.
   */
  default String select(List<O> selection) {

    String error = validate(selection);
    if (error != null) {
      return error;
    }
    getGame().sendEvent(new GameChoiceSelectEvent(this, selection));
    return null;
  }

  /**
   * @return the {@link Game} owning this {@link GameChoice}.
   */
  @Override
  Game getGame();

}
