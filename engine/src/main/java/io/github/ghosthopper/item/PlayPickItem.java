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
  public boolean setLocation(PlayAttributePickItems location, boolean addOrRemove) {

    if (this.location == location) {
      return true;
    }
    boolean success = true;
    PlayAttributePickItems oldLocation = this.location;
    if ((location == null) && addOrRemove) {
      success = oldLocation.removeItem(this, false);
    }
    if ((location != null) && addOrRemove) {
      success = location.addItem(this);
    }
    this.location = location;
    if (success) {
      getGame().sendEvent(new PlayPickItemMoveEvent(oldLocation, this, location));
    }
    return success;
  }

}
