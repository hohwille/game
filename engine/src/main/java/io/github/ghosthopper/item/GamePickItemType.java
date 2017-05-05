/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.item;

import io.github.ghosthopper.field.GameField;
import io.github.ghosthopper.figure.GameFigure;

/**
 * The {@link GamePickItem#getType() type} of a {@link GamePickItem}. Such as {@link #KEY} or {@link #GEM}.
 */
public class GamePickItemType extends GameItemType<GamePickItemType> {

  /** A key to open doors. */
  public static final GamePickItemType KEY = new GamePickItemType("Key");

  /** A valuable and beautiful gem. */
  public static final GamePickItemType GEM = new GamePickItemType("Gem");

  /** A valuable and beautiful emerald. */
  public static final GamePickItemType EMERALD = new GamePickItemType("Emerald");

  /** A valuable and beautiful diamond. */
  public static final GamePickItemType DIAMOND = new GamePickItemType("Diamond");

  /**
   * The constructor.
   *
   * @param id - see {@link #getId()}.
   */
  public GamePickItemType(String id) {
    super(id);
  }

  /**
   * @param figure the {@link GameFigure} that wants to {@link GameFigure#pickItem() pick} the {@link GamePickItem}.
   * @return {@code true} if the {@link GamePickItem} can be {@link GameFigure#pickItem() picked} and carried around by
   *         the given {@link GameFigure}, {@code false} otherwise.
   */
  public boolean isPickable(GameFigure figure) {

    // override to customize
    return true;
  }

  /**
   * @param field the {@link GameField} where the {@link GamePickItem} shall be {@link GameFigure#dropItem() dropped}.
   * @return {@code true} if the {@link GamePickItem} can be {@link GameFigure#dropItem() dropped} to the given
   *         {@link GameField}, {@code false} otherwise.
   */
  public boolean isDroppable(GameField field) {

    // override to customize
    return true;
  }

}
