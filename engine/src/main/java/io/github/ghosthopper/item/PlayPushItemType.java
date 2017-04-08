/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.item;

import io.github.ghosthopper.field.PlayField;
import io.github.ghosthopper.figure.PlayFigure;
import io.github.ghosthopper.move.PlayDirection;

/**
 * The {@link PlayPickItem#getType() type} of a {@link PlayPickItem}. Such as
 */
public class PlayPushItemType extends PlayItemType<PlayPushItemType> {

  /** A rock to push around. */
  public static final PlayPushItemType ROCK = new PlayPushItemType("Rock");

  /**
   * The constructor.
   *
   * @param id - see {@link #getId()}.
   */
  public PlayPushItemType(String id) {
    super(id);
  }

  /**
   * @param figure the pushing {@link PlayFigure}.
   * @param sourceField the {@link PlayField} where this item is currently placed.
   * @param direction the {@link PlayDirection} to push.
   * @param targetField the new {@link PlayField} where this item is to be pushed to.
   * @return {@code true} if the {@link PlayPickItem} can be pushed to the given {@link PlayField}, {@code false}
   *         otherwise.
   */
  public boolean isPushable(PlayFigure figure, PlayField sourceField, PlayDirection direction, PlayField targetField) {

    // Override to customize
    return true;
  }
}
