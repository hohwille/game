/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.game.engine.item;

import net.sf.mmm.game.engine.Game;
import net.sf.mmm.game.engine.color.GameColor;
import net.sf.mmm.game.engine.position.GamePosition;

/**
 * A {@link GameItem} that can be {@link net.sf.mmm.game.engine.figure.GameFigure#pickItem(GamePickItem) picked} such as
 * a {@link GamePickItemType#KEY key} or a {@link GamePickItemType#GEM gem}.
 */
public class GamePickItem extends GameItem<GamePickItemType, GameAttributePickItems, GamePickItem> {

  /**
   * The constructor.
   *
   * @param game the owning {@link #getGame() game}.
   * @param type the {@link GamePickItemType} of this item.
   */
  public GamePickItem(Game game, GamePickItemType type) {

    this(game, type, null);
  }

  /**
   * The constructor.
   *
   * @param game the owning {@link #getGame() game}.
   * @param type the {@link GamePickItemType} of this item.
   * @param color - see {@link #getColor()}.
   */
  public GamePickItem(Game game, GamePickItemType type, GameColor color) {

    super(game, type, color);
  }

  @Override
  protected GamePickItemMoveEvent createMoveEvent(GameAttributePickItems oldLocation, GamePosition oldPosition, GameAttributePickItems newLocation,
      GamePosition newPosition) {

    return new GamePickItemMoveEvent(oldLocation, oldPosition, this, newLocation, newPosition);
  }

}
