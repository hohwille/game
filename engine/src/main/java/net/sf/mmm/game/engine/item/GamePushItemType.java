/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.game.engine.item;

import net.sf.mmm.game.engine.direction.GameDirection;
import net.sf.mmm.game.engine.field.GameField;
import net.sf.mmm.game.engine.figure.GameFigure;

/**
 * The {@link GamePushItem#getType() type} of a {@link GamePushItem} such as a {@link #ROCK}.
 */
public class GamePushItemType extends GameItemType<GamePushItemType> {

  /** A rock to push around. */
  public static final GamePushItemType ROCK = new GamePushItemType("Rock");

  /**
   * The constructor.
   *
   * @param id - see {@link #getId()}.
   */
  public GamePushItemType(String id) {
    super(id);
  }

  /**
   * @param figure the pushing {@link GameFigure}.
   * @param sourceField the {@link GameField} where this item is currently placed.
   * @param direction the {@link GameDirection} to push.
   * @param targetField the new {@link GameField} where this item is to be pushed to.
   * @return {@code true} if the {@link GamePickItem} can be pushed to the given {@link GameField}, {@code false}
   *         otherwise.
   */
  public boolean isPushable(GameFigure figure, GameField sourceField, GameDirection direction, GameField targetField) {

    // Override to customize
    return true;
  }
}
