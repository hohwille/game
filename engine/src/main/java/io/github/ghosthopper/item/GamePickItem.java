/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.item;

import io.github.ghosthopper.color.GameColor;
import io.github.ghosthopper.position.GamePosition;

/**
 * A {@link GameItem} that can be {@link io.github.ghosthopper.figure.GameFigure#pickItem(GamePickItem) picked} such as
 * a {@link GamePickItemType#KEY key} or a {@link GamePickItemType#GEM gem}.
 */
public class GamePickItem extends GameItem<GameAttributePickItems, GamePickItem> {

  private final GamePickItemType type;

  /**
   * The constructor.
   *
   * @param type the {@link GamePickItemType} of this item.
   */
  public GamePickItem(GamePickItemType type) {
    super();
    this.type = type;
  }

  /**
   * The constructor.
   *
   * @param color - see {@link #getColor()}.
   * @param type the {@link GamePickItemType} of this item.
   */
  public GamePickItem(GameColor color, GamePickItemType type) {
    super();
    this.type = type;
    setColor(color);
  }

  /**
   * @return the {@link GamePickItemType} of this {@link GamePickItem}.
   */
  @Override
  public GamePickItemType getType() {

    return this.type;
  }

  @Override
  protected GamePickItemMoveEvent createMoveEvent(GameAttributePickItems oldLocation, GamePosition oldPosition, GameAttributePickItems newLocation,
      GamePosition newPosition) {

    return new GamePickItemMoveEvent(oldLocation, oldPosition, this, newLocation, newPosition);
  }

}
