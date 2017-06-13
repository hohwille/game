/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.game.engine.position;

import net.sf.mmm.game.engine.field.GameField;
import net.sf.mmm.game.engine.object.GameObjectWithId;

/**
 * Represents a position on a {@link GameField}. By default a {@link GameField} is divided into {@code 9}
 * {@link GamePosition}s provided as constants here.
 */
public class GamePosition extends GameObjectWithId {

  /** {@link GamePosition} north (top center). */
  public static final GamePosition NORTH = new GamePosition("North", 1, 0);

  /** {@link GamePosition} east (right center). */
  public static final GamePosition EAST = new GamePosition("East", 2, 1);

  /** {@link GamePosition} south (bottom center). */
  public static final GamePosition SOUTH = new GamePosition("South", 1, 2);

  /** {@link GamePosition} west (left center). */
  public static final GamePosition WEST = new GamePosition("West", 0, 1);

  /** {@link GamePosition} center. */
  public static final GamePosition CENTER = new GamePosition("Center", 1, 1);

  /** {@link GamePosition} north-west (top left). */
  public static final GamePosition NORTH_WEST = new GamePosition("North-West", 0, 0);

  /** {@link GamePosition} north-east (top right). */
  public static final GamePosition NORTH_EAST = new GamePosition("North-East", 2, 0);

  /** {@link GamePosition} south-west (bottom left). */
  public static final GamePosition SOUTH_WEST = new GamePosition("South-West", 0, 2);

  /** {@link GamePosition} south-east (bottom right). */
  public static final GamePosition SOUTH_EAST = new GamePosition("South-East", 2, 2);

  private final int x;

  private final int y;

  /**
   * The constructor for an atomic {@link GamePosition}.
   *
   * @param id - see {@link #getId()}.
   * @param x - see #getX
   * @param y - see #getY
   */
  public GamePosition(String id, int x, int y) {
    super(id);
    this.x = x;
    this.y = y;
  }

  /**
   * @return the inverse {@link GamePosition}.
   */
  public GamePosition getInverse() {

    if (this == WEST) {
      return EAST;
    } else if (this == EAST) {
      return WEST;
    } else if (this == NORTH) {
      return SOUTH;
    } else if (this == SOUTH) {
      return NORTH;
    } else if (this == NORTH_WEST) {
      return SOUTH_EAST;
    } else if (this == NORTH_EAST) {
      return SOUTH_WEST;
    } else if (this == SOUTH_WEST) {
      return NORTH_EAST;
    } else if (this == SOUTH_EAST) {
      return NORTH_WEST;
    } else if (this == CENTER) {
      return CENTER;
    } else {
      throw new IllegalStateException("You have to override this method if you introduce new positions!");
    }
  }

  /**
   * @return the position part on the X-axis (the column) in the range of {@code [0,2]}.
   */
  public int getX() {

    return this.x;
  }

  /**
   * @return the position part on the Y-axis (the row) in the range of {@code [0,2]}.
   */
  public int getY() {

    return this.y;
  }

  /**
   * @param position the {@link GamePosition} that may be {@code null}.
   * @return the given {@link GamePosition} or {@link #CENTER} if {@code null} was given.
   */
  public static GamePosition nonNull(GamePosition position) {

    if (position == null) {
      return CENTER;
    }
    return position;
  }

  /**
   * @param clockwise {@code true} to rotate clockwise, {@code false} otherwise (opposite direction).
   * @return the new {@link GamePosition}.
   */
  public GamePosition rotate(boolean clockwise) {

    if (this == WEST) {
      if (clockwise) {
        return NORTH;
      } else {
        return SOUTH;
      }
    } else if (this == EAST) {
      if (clockwise) {
        return SOUTH;
      } else {
        return NORTH;
      }
    } else if (this == NORTH) {
      if (clockwise) {
        return EAST;
      } else {
        return WEST;
      }
    } else if (this == SOUTH) {
      if (clockwise) {
        return WEST;
      } else {
        return EAST;
      }
    } else if (this == NORTH_WEST) {
      if (clockwise) {
        return NORTH_EAST;
      } else {
        return SOUTH_WEST;
      }
    } else if (this == NORTH_EAST) {
      if (clockwise) {
        return SOUTH_EAST;
      } else {
        return NORTH_WEST;
      }
    } else if (this == SOUTH_WEST) {
      if (clockwise) {
        return NORTH_WEST;
      } else {
        return SOUTH_EAST;
      }
    } else if (this == SOUTH_EAST) {
      if (clockwise) {
        return SOUTH_WEST;
      } else {
        return NORTH_EAST;
      }
    } else if (this == CENTER) {
      return CENTER;
    } else {
      throw new IllegalStateException("You have to override this method if you introduce new positions!");
    }
  }
}
