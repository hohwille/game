/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.item;

import io.github.ghosthopper.color.PlayColor;
import io.github.ghosthopper.game.PlayGame;

/**
 * An item of the {@link PlayGame} such as a key or a gem.
 */
public class PlayPickItem extends PlayItem<PlayAttributePickItems, PlayPickItem> {

  private final PlayPickItemType type;

  private PlayAttributePickItems location;

  /**
   * The constructor.
   *
   * @param type the {@link PlayPickItemType} of this item.
   */
  public PlayPickItem(PlayPickItemType type) {
    super();
    this.type = type;
  }

  /**
   * The constructor.
   *
   * @param color - see {@link #getColor()}.
   * @param type the {@link PlayPickItemType} of this item.
   */
  public PlayPickItem(PlayColor color, PlayPickItemType type) {
    super();
    this.type = type;
    setColor(color);
  }

  /**
   * @return the {@link PlayPickItemType} of this {@link PlayPickItem}.
   */
  @Override
  public PlayPickItemType getType() {

    return this.type;
  }

  @Override
  public PlayAttributePickItems getLocation() {

    return this.location;
  }

  @Override
  public void setLocation(PlayAttributePickItems location) {

    if (this.location == location) {
      return;
    }
    if (this.location != null) {
      this.location.getItems().remove(this);
    }
    this.location = location;
    if (this.location != null) {
      this.location.getItems().add(this);
    }
    getGame().sendEvent(this);
  }

}
