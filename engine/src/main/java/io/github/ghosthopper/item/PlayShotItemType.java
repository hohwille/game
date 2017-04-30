/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.item;

import io.github.ghosthopper.figure.PlayFigure;

/**
 * The {@link PlayShotItem#getType() type} of a {@link PlayShotItem} such as a {@link #BULLET} or
 */
public class PlayShotItemType extends PlayItemType<PlayShotItemType> {

  /** A bullet. */
  public static final PlayShotItemType BULLET = new PlayShotItemType("Bullet");

  /** A fireball. */
  public static final PlayShotItemType FIREBALL = new PlayShotItemType("Fireball");

  /** A lightning (bolt). */
  public static final PlayShotItemType LIGHTNING = new PlayShotItemType("Lightning");

  /**
   * The constructor.
   *
   * @param id - see {@link #getId()}.
   */
  public PlayShotItemType(String id) {
    super(id);
  }

  public void hit(PlayFigure figure) {

  }

}
