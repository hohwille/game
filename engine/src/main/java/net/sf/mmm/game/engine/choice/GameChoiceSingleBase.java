/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.game.engine.choice;

import java.util.List;

import net.sf.mmm.game.engine.Game;

/**
 * Base implementation of {@link GameChoiceSingle}.
 *
 * @param <O> the type of the option.
 */
public abstract class GameChoiceSingleBase<O> extends GameChoiceBase<O, O> implements GameChoiceSingle<O> {

  private List<O> selection;

  /**
   * The constructor.
   *
   * @param game - see {@link #getGame()}.
   * @param id - see {@link #getId()}.
   */
  public GameChoiceSingleBase(Game game, String id) {
    super(game, id);
  }

  /**
   * The constructor.
   *
   * @param game - see {@link #getGame()}.
   * @param id - see {@link #getId()}.
   * @param description - see {@link #getDescription()}.
   */
  public GameChoiceSingleBase(Game game, String id, String description) {
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
