/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.item;

import io.github.ghosthopper.figure.GameFigure;

/**
 * The {@link GameShotItem#getType() type} of a {@link GameShotItem} such as a {@link #BULLET} or
 */
public class GameShotItemType extends GameItemType<GameShotItemType> {

  /** A bullet. */
  public static final GameShotItemType BULLET = new GameShotItemType("Bullet");

  /** A fireball. */
  public static final GameShotItemType FIREBALL = new GameShotItemType("Fireball");

  /** A lightning (bolt). */
  public static final GameShotItemType LIGHTNING = new GameShotItemType("Lightning");

  /**
   * The constructor.
   *
   * @param id - see {@link #getId()}.
   */
  public GameShotItemType(String id) {
    super(id);
  }

  /**
   * @param figure the {@link GameFigure} that was hit by this shot.
   */
  public void hit(GameFigure figure) {

  }

}
