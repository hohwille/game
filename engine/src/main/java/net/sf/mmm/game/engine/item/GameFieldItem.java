/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.game.engine.item;

import net.sf.mmm.game.engine.Game;
import net.sf.mmm.game.engine.asset.GameAsset;
import net.sf.mmm.game.engine.color.GameColor;
import net.sf.mmm.game.engine.field.GameField;
import net.sf.mmm.game.engine.position.GamePosition;

/**
 * An {@link GameItem item} of the {@link Game} covering a field. It can not be
 * {@link net.sf.mmm.game.engine.figure.GameFigure#pickItem(GamePickItem) picked}. However it may be pushed
 */
public class GameFieldItem extends GameItem<GameFieldItemType, GameField, GameFieldItem> implements GameAttributePushable, GameAttributeEnterable {

  /**
   * The constructor.
   *
   * @param game the owning {@link #getGame() game}.
   * @param type the {@link GameFieldItemType} of this item.
   */
  public GameFieldItem(Game game, GameFieldItemType type) {

    this(game, type, null);
  }

  /**
   * The constructor.
   *
   * @param game the owning {@link #getGame() game}.
   * @param type the {@link GameFieldItemType} of this item.
   * @param color - see {@link #getColor()}.
   */
  public GameFieldItem(Game game, GameFieldItemType type, GameColor color) {

    super(game, type, color);
  }

  @Override
  protected GameFieldItemMoveEvent createMoveEvent(GameField oldLocation, GamePosition oldPosition, GameField newLocation, GamePosition newPosition) {

    return new GameFieldItemMoveEvent(oldLocation, oldPosition, this, newLocation, newPosition);
  }

  /**
   * @return {@code true} if this a {@link GameFieldItem} of this type is blocking {@link GameFieldItem#getLocation()
   *         its} {@link net.sf.mmm.game.engine.field.GameField} so {@link net.sf.mmm.game.engine.figure.GameFigure}s
   *         can not move on it, {@code false} otherwise.
   */
  public boolean isBlocking() {

    return getType().isBlocking();
  }

  @Override
  public boolean isPushable() {

    return getType().isPushable();
  }

  @Override
  public boolean isEnterable(GameAsset<?> asset, boolean move) {

    return getType().isEnterable(asset, move);
  }

}
