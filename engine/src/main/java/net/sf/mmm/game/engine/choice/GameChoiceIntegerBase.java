/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.game.engine.choice;

import net.sf.mmm.game.engine.Game;

/**
 * Base implementation of {@link GameChoiceInteger}.
 */
public class GameChoiceIntegerBase extends GameChoiceSingleBase<Integer> implements GameChoiceInteger {

  private final int minValue;

  private final int maxValue;

  /**
   * The constructor.
   *
   * @param game - see {@link #getGame()}.
   * @param id - see {@link #getId()}.
   * @param description - see {@link #getDescription()}.
   * @param min - see {@link #getMinValue()}.
   * @param max - see {@link #getMaxValue()}.
   */
  public GameChoiceIntegerBase(Game game, String id, String description, int min, int max) {
    super(game, id, description);
    this.minValue = min;
    this.maxValue = max;
  }

  @Override
  public int getMinValue() {

    return this.minValue;
  }

  @Override
  public int getMaxValue() {

    return this.maxValue;
  }

}
