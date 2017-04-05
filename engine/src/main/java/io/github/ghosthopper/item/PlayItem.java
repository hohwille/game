/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.item;

import io.github.ghosthopper.PlayColor;
import io.github.ghosthopper.PlayObjectWithColorAndType;
import io.github.ghosthopper.game.PlayGame;

/**
 * An item of the {@link PlayGame} such as a key or a gem.
 */
public class PlayItem extends PlayObjectWithColorAndType {

  private final PlayItemType type;

  /**
   * The constructor.
   *
   * @param type the {@link PlayItemType} of this item.
   */
  public PlayItem(PlayItemType type) {
    super();
    this.type = type;
  }

  /**
   * The constructor.
   *
   * @param color - see {@link #getColor()}.
   * @param type the {@link PlayItemType} of this item.
   */
  public PlayItem(PlayColor color, PlayItemType type) {
    super(color);
    this.type = type;
  }

  /**
   * @return the {@link PlayItemType} of this {@link PlayItem}.
   */
  public PlayItemType getType() {

    return this.type;
  }

}
