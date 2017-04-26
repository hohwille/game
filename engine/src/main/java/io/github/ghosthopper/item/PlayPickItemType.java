/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.item;

import io.github.ghosthopper.field.PlayField;
import io.github.ghosthopper.figure.PlayFigure;

/**
 * The {@link PlayPickItem#getType() type} of a {@link PlayPickItem}. Such as {@link #KEY} or {@link #GEM}.
 */
public class PlayPickItemType extends PlayItemType<PlayPickItemType> {

  /** A key to open doors. */
  public static final PlayPickItemType KEY = new PlayPickItemType("Key");

  /** A valuable and beautiful gem. */
  public static final PlayPickItemType GEM = new PlayPickItemType("Gem");

  /** A valuable and beautiful emerald. */
  public static final PlayPickItemType EMERALD = new PlayPickItemType("Emerald");

  /** A valuable and beautiful diamond. */
  public static final PlayPickItemType DIAMOND = new PlayPickItemType("Diamond");

  /**
   * The constructor.
   *
   * @param id - see {@link #getId()}.
   */
  public PlayPickItemType(String id) {
    super(id);
  }

  /**
   * @param figure the {@link PlayFigure} that wants to {@link PlayFigure#pickItem() pick} the {@link PlayPickItem}.
   * @return {@code true} if the {@link PlayPickItem} can be {@link PlayFigure#pickItem() picked} and carried around by
   *         the given {@link PlayFigure}, {@code false} otherwise.
   */
  public boolean isPickable(PlayFigure figure) {

    // override to customize
    return true;
  }

  /**
   * @param field the {@link PlayField} where the {@link PlayPickItem} shall be {@link PlayFigure#dropItem() dropped}.
   * @return {@code true} if the {@link PlayPickItem} can be {@link PlayFigure#dropItem() dropped} to the given
   *         {@link PlayField}, {@code false} otherwise.
   */
  public boolean isDroppable(PlayField field) {

    // override to customize
    return true;
  }

}
