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

  private final PlayPushItemType type;

  private PlayField location;

  /**
   * The constructor.
   *
   * @param type the {@link PlayPushItemType} of this item.
   */
  public PlayPushItem(PlayPushItemType type) {
    super();
    this.type = type;
  }

  /**
   * The constructor.
   *
   * @param color - see {@link #getColor()}.
   * @param type the {@link PlayPushItemType} of this item.
   */
  public PlayPushItem(PlayColor color, PlayPushItemType type) {
    super();
    this.type = type;
    setColor(color);
  }

  /**
   * @return the {@link PlayPushItemType} of this {@link PlayPushItem}.
   */
  @Override
  public PlayPushItemType getType() {

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
  public boolean setLocation(PlayField location, boolean addOrRemove) {

    if (this.location == location) {
      return true;
    }
    boolean success = true;
    PlayField oldLocation = this.location;
    if ((location == null) && addOrRemove) {
      success = oldLocation.removeAsset(this, false);
    }
    if ((location != null) && addOrRemove) {
      success = location.addAsset(this);
    }
    this.location = location;
    if (success) {
      getGame().sendEvent(new PlayPushItemMoveEvent(oldLocation, this, location));
    }
    return success;
  }

}
