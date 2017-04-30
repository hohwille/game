/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.item;

import io.github.ghosthopper.color.PlayColor;
import io.github.ghosthopper.field.PlayField;
import io.github.ghosthopper.move.PlayAttributeDirection;
import io.github.ghosthopper.move.PlayDirection;
import io.github.ghosthopper.position.PlayPosition;

/**
 * A {@link PlayItem} that represents a shot like a {@link PlayShotItemType#BULLET bullet} from a gun or a
 * {@link PlayShotItemType#FIREBALL fireball} from a magic wand or flame-thrower. Unlike other {@link PlayItem}s a shot
 * exists only temporary. To mark a shot as "gone" its {@link #getLocation() location} as well as its
 * {@link #getDirection() direction} are both set to {@code null}.
 */
public class PlayShotItem extends PlayItem<PlayField, PlayShotItem> implements PlayAttributeDirection {

  private final PlayShotItemType type;

  private PlayDirection direction;

  /**
   * The constructor.
   *
   * @param type the {@link PlayShotItemType} of this item.
   * @param location the start {@link #getLocation() location}.
   * @param position the start {@link #getPosition() position}.
   * @param direction the {@link #getDirection() direction}.
   */
  public PlayShotItem(PlayShotItemType type, PlayField location, PlayPosition position, PlayDirection direction) {
    this(type, location, position, direction, null);
  }

  /**
   * The constructor.
   *
   * @param type the {@link PlayShotItemType} of this item.
   * @param location the start {@link #getLocation() location}.
   * @param position the start {@link #getPosition() position}.
   * @param direction the {@link #getDirection() direction}.
   * @param color - see {@link #getColor()}.
   */
  public PlayShotItem(PlayShotItemType type, PlayField location, PlayPosition position, PlayDirection direction, PlayColor color) {
    super();
    this.type = type;
    this.direction = direction;
    setLocation(location, position);
    if (color != null) {
      setColor(color);
    }
  }

  /**
   * @return the {@link PlayShotItemType} of this {@link PlayShotItem}.
   */
  @Override
  public PlayShotItemType getType() {

    return this.type;
  }

  @Override
  public PlayDirection getDirection() {

    return this.direction;
  }

  /**
   * <b>Attention:</b><br>
   * Use this method with care as a shot typically does not change its direction.
   *
   * @param direction the new value of {@link #getDirection()}.
   */
  public void setDirection(PlayDirection direction) {

    this.direction = direction;
  }

  @Override
  protected PlayShotItemMoveEvent createMoveEvent(PlayField oldLocation, PlayPosition oldPosition, PlayField newLocation, PlayPosition newPosition) {

    return new PlayShotItemMoveEvent(oldLocation, oldPosition, this, newLocation, newPosition);
  }

}
