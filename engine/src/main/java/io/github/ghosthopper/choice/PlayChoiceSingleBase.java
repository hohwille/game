/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.choice;

import java.util.List;

import io.github.ghosthopper.game.PlayGame;

/**
 * Base implementation of {@link PlayChoiceSingle}.
 *
 * @param <O> the type of the option.
 */
public abstract class PlayChoiceSingleBase<O> extends PlayChoiceBase<O, O> implements PlayChoiceSingle<O> {

  private List<O> selection;

  /**
   * The constructor.
   *
   * @param game - see {@link #getGame()}.
   * @param id - see {@link #getId()}.
   */
  public PlayChoiceSingleBase(PlayGame game, String id) {
    super(game, id);
  }

  /**
   * The constructor.
   *
   * @param game - see {@link #getGame()}.
   * @param id - see {@link #getId()}.
   * @param description - see {@link #getDescription()}.
   */
  public PlayChoiceSingleBase(PlayGame game, String id, String description) {
    super(game, id, description);
  }

  @Override
  public String select(List<O> selectedOptions) {

    String error = super.select(selectedOptions);
    if (error == null) {
      this.selection = selectedOptions;
    }
    return error;
  }

  /**
   * @return the {@link #select(List) selected} options or {@code null} if {@link #select(List)} has not yet been
   *         called.
   */
  public List<O> getSelection() {

    return this.selection;
  }

}
