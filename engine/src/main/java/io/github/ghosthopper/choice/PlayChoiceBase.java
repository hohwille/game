/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.choice;

import io.github.ghosthopper.game.PlayGame;
import io.github.ghosthopper.object.PlayObjectWithId;

/**
 * A choice for the user. Either to configure or initialize the game or potentially also for quiz games.
 *
 * @param <O> the type of the option.
 */
public abstract class PlayChoiceBase<O> extends PlayObjectWithId implements PlayChoice<O> {

  private final PlayGame game;

  private final String question;

  /**
   * The constructor.
   *
   * @param game - see {@link #getGame()}.
   * @param id - see {@link #getId()}.
   */
  public PlayChoiceBase(PlayGame game, String id) {
    this(game, id, null);
  }

  /**
   * The constructor.
   *
   * @param game - see {@link #getGame()}.
   * @param id - see {@link #getId()}.
   * @param description - see {@link #getDescription()}.
   */
  public PlayChoiceBase(PlayGame game, String id, String description) {
    super(id);
    this.game = game;
    this.question = description;
  }

  @Override
  public PlayGame getGame() {

    return this.game;
  }

  @Override
  public String getDescription() {

    return this.question;
  }

}
