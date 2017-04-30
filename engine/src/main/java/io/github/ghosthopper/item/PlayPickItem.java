/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.item;

import io.github.ghosthopper.color.PlayColor;
import io.github.ghosthopper.position.PlayPosition;

/**
 * A {@link PlayItem} that can be {@link io.github.ghosthopper.figure.PlayFigure#pickItem(PlayPickItem) picked} such as
 * a {@link PlayPickItemType#KEY key} or a {@link PlayPickItemType#GEM gem}.
 */
public class PlayPickItem extends PlayItem<PlayAttributePickItems, PlayPickItem> {

  private final PlayPickItemType type;

  /**
   * The constructor.
   *
   * @param type the {@link PlayPickItemType} of this item.
   */
  public PlayPickItem(PlayPickItemType type) {
    super();
    this.type = type;
  }

  /**
   * The constructor.
   *
   * @param color - see {@link #getColor()}.
   * @param type the {@link PlayPickItemType} of this item.
   */
  public PlayPickItem(PlayColor color, PlayPickItemType type) {
    super();
    this.type = type;
    setColor(color);
  }

  /**
   * @return the {@link PlayPickItemType} of this {@link PlayPickItem}.
   */
  @Override
  public PlayPickItemType getType() {

    return this.type;
  }

  @Override
  protected PlayPickItemMoveEvent createMoveEvent(PlayAttributePickItems oldLocation, PlayPosition oldPosition, PlayAttributePickItems newLocation,
      PlayPosition newPosition) {

    return new PlayPickItemMoveEvent(oldLocation, oldPosition, this, newLocation, newPosition);
  }

}
