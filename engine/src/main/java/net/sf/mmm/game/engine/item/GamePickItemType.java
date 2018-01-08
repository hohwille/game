/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.game.engine.item;

import net.sf.mmm.game.engine.figure.GameFigure;
import net.sf.mmm.game.engine.object.GameTypedObjectWithItems;

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
   * @param place the {@link GameTypedObjectWithItems} where the {@link GamePickItem} shall be
   *        {@link GameTypedObjectWithItems#addItem(GamePickItem) added}.
   * @return {@code true} if this {@link GamePickItem} can be placed (e.g. {@link GameFigure#dropItem() dropped}) to the
   *         given {@link GameTypedObjectWithItems place}, {@code false} otherwise.
   */
  public boolean isPlaceable(GameTypedObjectWithItems<?> place) {

    // override to customize
    return true;
  }

}
