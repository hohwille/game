/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.game.engine.item;

import net.sf.mmm.game.engine.Game;
import net.sf.mmm.game.engine.color.GameColor;
import net.sf.mmm.game.engine.field.GameField;
import net.sf.mmm.game.engine.position.GamePosition;

/**
 * An item of the {@link Game} such as a key or a gem.
 */
public class GamePushItem extends GameItem<GameField, GamePushItem> {

  private final GamePushItemType type;

  /**
   * The constructor.
   *
   * @param type the {@link GamePushItemType} of this item.
   */
  public GamePushItem(GamePushItemType type) {
    super();
    this.type = type;
  }

  /**
   * The constructor.
   *
   * @param color - see {@link #getColor()}.
   * @param type the {@link GamePushItemType} of this item.
   */
  public GamePushItem(GameColor color, GamePushItemType type) {
    super();
    this.type = type;
    setColor(color);
  }

  /**
   * @return the {@link GamePushItemType} of this {@link GamePushItem}.
   */
  @Override
  public GamePushItemType getType() {

    return this.type;
  }

  @Override
  public double getWeight() {

    return this.type.getWeight();
  }

  @Override
  protected GamePushItemMoveEvent createMoveEvent(GameField oldLocation, GamePosition oldPosition, GameField newLocation, GamePosition newPosition) {

    return new GamePushItemMoveEvent(oldLocation, oldPosition, this, newLocation, newPosition);
  }

}
