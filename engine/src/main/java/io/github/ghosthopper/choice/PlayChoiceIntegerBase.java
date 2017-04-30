/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.choice;

import io.github.ghosthopper.game.PlayGame;

/**
 * The abstract base implementation of {@link PlayChoiceInteger}.
 */
public abstract class PlayChoiceIntegerBase extends PlayChoiceBase<Integer> implements PlayChoiceInteger {

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
  public PlayChoiceIntegerBase(PlayGame game, String id, String description, int min, int max) {
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
