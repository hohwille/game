/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.item;

import io.github.ghosthopper.color.PlayColor;
import io.github.ghosthopper.field.PlayField;
import io.github.ghosthopper.game.PlayGame;
import io.github.ghosthopper.position.PlayPosition;

/**
 * An item of the {@link PlayGame} such as a key or a gem.
 */
public class PlayPushItem extends PlayItem<PlayField, PlayPushItem> {

  private final PlayPushItemType type;

  /**
   * The constructor.
   *
   * @param type the {@link PlayPushItemType} of this item.
   */
  public PlayPushItem(PlayPushItemType type) {
    super();
    this.type = type;
  }

  /**
   * The constructor.
   *
   * @param color - see {@link #getColor()}.
   * @param type the {@link PlayPushItemType} of this item.
   */
  public PlayPushItem(PlayColor color, PlayPushItemType type) {
    super();
    this.type = type;
    setColor(color);
  }

  /**
   * @return the {@link PlayPushItemType} of this {@link PlayPushItem}.
   */
  @Override
  public PlayPushItemType getType() {

    return this.type;
  }

  @Override
  public double getWeight() {

    return this.type.getWeight();
  }

  @Override
  protected PlayPushItemMoveEvent createMoveEvent(PlayField oldLocation, PlayPosition oldPosition, PlayField newLocation, PlayPosition newPosition) {

    return new PlayPushItemMoveEvent(oldLocation, oldPosition, this, newLocation, newPosition);
  }

}
