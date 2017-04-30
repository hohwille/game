/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.choice;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import io.github.ghosthopper.game.PlayGame;

/**
 * A simple implementation of {@link PlayChoiceGroupBase}.
 */
public class PlayChoiceGroupBase extends PlayChoiceBase<Void> implements PlayChoiceGroup<Void> {

  private final List<PlayChoice<?>> choices;

  /**
   * The constructor.
   *
   * @param game - see {@link #getGame()}.
   * @param id - see {@link #getId()}.
   * @param description - see {@link #getDescription()}.
   * @param choices - see {@link #getChoices()}.
   */
  public PlayChoiceGroupBase(PlayGame game, String id, String description, PlayChoice<?>... choices) {
    super(game, id, description);
    this.choices = Collections.unmodifiableList(Arrays.asList(choices));
  }

  @Override
  public List<PlayChoice<?>> getChoices() {

    return this.choices;
  }

}
