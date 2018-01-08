/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.game.engine.item;

import net.sf.mmm.game.engine.color.GameColor;
import net.sf.mmm.game.engine.direction.GameAttributeDirection;
import net.sf.mmm.game.engine.direction.GameDirection;
import net.sf.mmm.game.engine.field.GameField;
import net.sf.mmm.game.engine.position.GamePosition;

/**
 * A {@link GameItem} that represents a shot like a {@link GameShotItemType#BULLET bullet} from a gun or a
 * {@link GameShotItemType#FIREBALL fireball} from a magic wand or flame-thrower. Unlike other {@link GameItem}s a shot
 * exists only temporary. To mark a shot as "gone" its {@link #getLocation() location} as well as its
 * {@link #getDirection() direction} are both set to {@code null}.
 */
public class GameShotItem extends GameItem<GameShotItemType, GameField, GameShotItem> implements GameAttributeDirection {

  private GameDirection direction;

  /**
   * The constructor.
   *
   * @param type the {@link GameShotItemType} of this item.
   * @param location the start {@link #getLocation() location}.
   * @param position the start {@link #getPosition() position}.
   * @param direction the {@link #getDirection() direction}.
   */
  public GameShotItem(GameShotItemType type, GameField location, GamePosition position, GameDirection direction) {

    this(type, location, position, direction, null);
  }

  /**
   * The constructor.
   *
   * @param type the {@link GameShotItemType} of this item.
   * @param location the start {@link #getLocation() location}.
   * @param position the start {@link #getPosition() position}.
   * @param direction the {@link #getDirection() direction}.
   * @param color - see {@link #getColor()}.
   */
  public GameShotItem(GameShotItemType type, GameField location, GamePosition position, GameDirection direction, GameColor color) {

    super(location.getGame(), type, color);
    this.direction = direction;
    setLocation(location, position);
  }

  @Override
  public GameDirection getDirection() {

    return this.direction;
  }

  /**
   * <b>Attention:</b><br>
   * Use this method with care as a shot typically does not change its direction.
   *
   * @param direction the new value of {@link #getDirection()}.
   */
  public void setDirection(GameDirection direction) {

    this.direction = direction;
  }

  @Override
  protected GameShotItemMoveEvent createMoveEvent(GameField oldLocation, GamePosition oldPosition, GameField newLocation, GamePosition newPosition) {

    return new GameShotItemMoveEvent(oldLocation, oldPosition, this, newLocation, newPosition);
  }

}
