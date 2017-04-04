/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.game;

import io.github.ghosthopper.PlayDirection;
import io.github.ghosthopper.PlayLevel;
import io.github.ghosthopper.Player;
import io.github.ghosthopper.field.PlayField;

/**
 * Extends {@link PlayGame} for simple games with a {@link PlayLevel} that is a square field with a fixed number or rows
 * and columns.
 */
public abstract class PlayGameSimple extends PlayGame {

  private final int width;

  private final int height;

  /**
   * The constructor.
   *
   * @param id - see {@link #getId()}.
   * @param width the number of horizontal {@link PlayField}s
   * @param height the number of vertical {@link PlayField}s.
   */
  public PlayGameSimple(String id, int width, int height) {
    super(id);
    this.width = width;
    this.height = height;
  }

  @Override
  protected PlayLevel createFirstLevel() {

    PlayLevel level = new PlayLevel("Level 1", this);
    initLevelAsRectangular(level, PlayDirection.RIGHT, this.width, PlayDirection.DOWN, this.height, getBorderStrategy());
    return level;
  }

  /**
   * @return the {@link PlayBorderTypeStrategy}.
   */
  protected PlayBorderTypeStrategy getBorderStrategy() {

    return PlayBorderTypeStrategyStatic.OPEN;
  }

  @Override
  protected void moveBotPlayer(Player player) {

  }

}
