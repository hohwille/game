/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.item;

import io.github.ghosthopper.color.PlayColor;
import io.github.ghosthopper.field.PlayField;
import io.github.ghosthopper.game.PlayGame;

/**
 * An item of the {@link PlayGame} such as a key or a gem.
 */
public class PlayPushItem extends PlayItem<PlayField, PlayPushItem> {

  private final PlayPickItemType type;

  private PlayField location;

  /**
   * The constructor.
   *
   * @param type the {@link PlayPickItemType} of this item.
   */
  public PlayPushItem(PlayPickItemType type) {
    super();
    this.type = type;
  }

  /**
   * The constructor.
   *
   * @param color - see {@link #getColor()}.
   * @param type the {@link PlayPickItemType} of this item.
   */
  public PlayPushItem(PlayColor color, PlayPickItemType type) {
    super();
    this.type = type;
    setColor(color);
  }

  /**
   * @return the {@link PlayPickItemType} of this {@link PlayPushItem}.
   */
  @Override
  public PlayPickItemType getType() {

    return this.type;
  }

  @Override
  public double getWeight() {

    return this.type.getWeight();
  }

  @Override
  public PlayField getLocation() {

    return this.location;
  }

  @Override
  public void setLocation(PlayField location) {

    this.location = location;
  }

}
