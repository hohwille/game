/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.position;

import io.github.ghosthopper.field.PlayField;
import io.github.ghosthopper.object.PlayObjectWithId;

/**
 * Represents a position on a {@link PlayField}. By default a {@link PlayField} is divided into {@code 9}
 * {@link PlayPosition}s provided as constants here.
 */
public class PlayPosition extends PlayObjectWithId {

  /** {@link PlayPosition} north (top center). */
  public static final PlayPosition NORTH = new PlayPosition("North", 1, 0);

  /** {@link PlayPosition} east (right center). */
  public static final PlayPosition EAST = new PlayPosition("East", 2, 1);

  /** {@link PlayPosition} south (bottom center). */
  public static final PlayPosition SOUTH = new PlayPosition("South", 1, 2);

  /** {@link PlayPosition} west (left center). */
  public static final PlayPosition WEST = new PlayPosition("West", 0, 1);

  /** {@link PlayPosition} center. */
  public static final PlayPosition CENTER = new PlayPosition("Center", 1, 1);

  /** {@link PlayPosition} north-west (top left). */
  public static final PlayPosition NORTH_WEST = new PlayPosition("North-West", 0, 0);

  /** {@link PlayPosition} north-east (top right). */
  public static final PlayPosition NORTH_EAST = new PlayPosition("North-East", 2, 0);

  /** {@link PlayPosition} south-west (bottom left). */
  public static final PlayPosition SOUTH_WEST = new PlayPosition("South-West", 0, 2);

  /** {@link PlayPosition} south-east (bottom right). */
  public static final PlayPosition SOUTH_EAST = new PlayPosition("South-East", 2, 2);

  private final int x;

  private final int y;

  /**
   * The constructor for an atomic {@link PlayPosition}.
   *
   * @param id - see {@link #getId()}.
   * @param x - see #getX
   * @param y - see #getY
   */
  public PlayPosition(String id, int x, int y) {
    super(id);
    this.x = x;
    this.y = y;
  }

  /**
   * @return the inverse {@link PlayPosition}.
   */
  public PlayPosition getInverse() {

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
   * @param position the {@link PlayPosition} that may be {@code null}.
   * @return the given {@link PlayPosition} or {@link #CENTER} if {@code null} was given.
   */
  public static PlayPosition nonNull(PlayPosition position) {

    if (position == null) {
      return CENTER;
    }
    return position;
  }

  /**
   * @param clockwise {@code true} to rotate clockwise, {@code false} otherwise (opposite direction).
   * @return the new {@link PlayPosition}.
   */
  public PlayPosition rotate(boolean clockwise) {

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
