/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.item;

import io.github.ghosthopper.color.PlayColor;
import io.github.ghosthopper.game.PlayGame;

/**
 * An item of the {@link PlayGame} such as a key or a gem.
 */
public class PlayPushItem extends PlayItem<PlayPushItem> {

  private final PlayPickItemType type;

  /**
   * The constructor.
   *
   * @param type the {@link PlayPickItemType} of this item.
   */
  public PlayPushItem(PlayPickItemType type) {
    super();
    this.type = type;
  }

  /**
   * The constructor.
   *
   * @param color - see {@link #getColor()}.
   * @param type the {@link PlayPickItemType} of this item.
   */
  public PlayPushItem(PlayColor color, PlayPickItemType type) {
    super(color);
    this.type = type;
  }

  /**
   * @return the {@link PlayPickItemType} of this {@link PlayPushItem}.
   */
  @Override
  public PlayPickItemType getType() {

    return this.type;
  }

  @Override
  public double getWeight() {

    return this.type.getWeight();
  }

}
