/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.game.engine.choice;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import net.sf.mmm.game.engine.Game;
import net.sf.mmm.game.engine.type.GameType;

/**
 * A simple implementation of {@link GameChoiceGroupBase}.
 *
 * @param <O> the type of the option. May also be {@link Void} as {@link #select(List)} will be called with an empty
 *        {@link List}.
 * @param <C> the type of the {@link #getChoices() choices} options.
 */
public class GameChoiceGroupBase<O, C> extends GameChoiceBase<O, GameChoice<C>> implements GameChoiceGroup<O, C> {

  private final List<? extends GameChoice<C>> choices;

  private GameType imageType;

  /**
   * The constructor.
   *
   * @param game - see {@link #getGame()}.
   * @param id - see {@link #getId()}.
   * @param description - see {@link #getDescription()}.
   * @param choices - see {@link #getChoices()}.
   */
  @SafeVarargs
  public GameChoiceGroupBase(Game game, String id, String description, GameChoice<C>... choices) {
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
  public GameChoiceGroupBase(Game game, String id, String description, GameType imageType, GameChoice<C>... choices) {
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
  public GameChoiceGroupBase(Game game, String id, String description, GameType imageType, List<? extends GameChoice<C>> choices) {
    super(game, id, description);
    this.imageType = imageType;
    this.choices = Collections.unmodifiableList(choices);
  }

  @Override
  public List<? extends GameChoice<C>> getChoices() {

    return this.choices;
  }

  @Override
  protected List<? extends GameChoice<C>> convertSelection(List<O> selection) {

    return this.choices;
  }

  @Override
  public GameType getImageType() {

    return this.imageType;
  }

  /**
   * @param imageType the new value of {@link #getImageType()}.
   */
  public void setImageType(GameType imageType) {

    this.imageType = imageType;
  }

}
