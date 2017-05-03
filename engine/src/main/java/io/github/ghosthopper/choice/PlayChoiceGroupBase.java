/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.choice;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import io.github.ghosthopper.game.PlayGame;
import io.github.ghosthopper.type.PlayType;

/**
 * A simple implementation of {@link PlayChoiceGroupBase}.
 *
 * @param <O> the type of the option. May also be {@link Void} as {@link #select(List)} will be called with an empty
 *        {@link List}.
 * @param <C> the type of the {@link #getChoices() choices} options.
 */
public class PlayChoiceGroupBase<O, C> extends PlayChoiceBase<O, PlayChoice<C>> implements PlayChoiceGroup<O, C> {

  private final List<? extends PlayChoice<C>> choices;

  private PlayType imageType;

  /**
   * The constructor.
   *
   * @param game - see {@link #getGame()}.
   * @param id - see {@link #getId()}.
   * @param description - see {@link #getDescription()}.
   * @param choices - see {@link #getChoices()}.
   */
  @SafeVarargs
  public PlayChoiceGroupBase(PlayGame game, String id, String description, PlayChoice<C>... choices) {
    this(game, id, description, null, choices);
  }

  /**
   * The constructor.
   *
   * @param game - see {@link #getGame()}.
   * @param id - see {@link #getId()}.
   * @param description - see {@link #getDescription()}.
   * @param imageType - see {@link #getImageType()}.
   * @param choices - see {@link #getChoices()}.
   */
  @SafeVarargs
  public PlayChoiceGroupBase(PlayGame game, String id, String description, PlayType imageType, PlayChoice<C>... choices) {
    this(game, id, description, imageType, Arrays.asList(choices));
  }

  /**
   * The constructor.
   *
   * @param game - see {@link #getGame()}.
   * @param id - see {@link #getId()}.
   * @param description - see {@link #getDescription()}.
   * @param imageType - see {@link #getImageType()}.
   * @param choices - see {@link #getChoices()}.
   */
  public PlayChoiceGroupBase(PlayGame game, String id, String description, PlayType imageType, List<? extends PlayChoice<C>> choices) {
    super(game, id, description);
    this.imageType = imageType;
    this.choices = Collections.unmodifiableList(choices);
  }

  @Override
  public List<? extends PlayChoice<C>> getChoices() {

    return this.choices;
  }

  @Override
  protected List<? extends PlayChoice<C>> convertSelection(List<O> selection) {

    return this.choices;
  }

  @Override
  public PlayType getImageType() {

    return this.imageType;
  }

  /**
   * @param imageType the new value of {@link #getImageType()}.
   */
  public void setImageType(PlayType imageType) {

    this.imageType = imageType;
  }

}
