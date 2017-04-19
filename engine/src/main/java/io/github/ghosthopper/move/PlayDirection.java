/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.move;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Set;

import io.github.ghosthopper.field.PlayField;
import io.github.ghosthopper.object.PlayObjectWithId;

/**
 * Represents a direction (move) supported by the game. For flexibility the directions are not hard-coded so it can be
 * possible to also move diagonal or have hexagonal {@link PlayField}s if desired.
 */
public class PlayDirection extends PlayObjectWithId {

  /** {@link PlayDirection} moving north (up when looking from above). */
  public static final PlayDirection NORTH = new PlayDirection("North", 0);

  /** {@link PlayDirection} moving to the east (right when looking from above). */
  public static final PlayDirection EAST = new PlayDirection("East", 90);

  /** {@link PlayDirection} moving south (down when looking from above). */
  public static final PlayDirection SOUTH = new PlayDirection("South", 180);

  /** {@link PlayDirection} moving to the west (left when looking from above). */
  public static final PlayDirection WEST = new PlayDirection("West", 270);

  /** {@link PlayDirection} moving upwards (level or layer above). */
  public static final PlayDirection UP = new PlayDirection("Up", -1);

  /** {@link PlayDirection} moving downwards (level or layer below). */
  public static final PlayDirection DOWN = new PlayDirection("Down", -1);

  /** {@link PlayDirection} moving {@link #NORTH} and {@link #WEST} (diagonal). */
  public static final PlayDirection NORTH_WEST = new PlayDirection(NORTH, WEST);

  /** {@link PlayDirection} moving {@link #NORTH} and {@link #EAST} (diagonal). */
  public static final PlayDirection NORTH_EAST = new PlayDirection(NORTH, EAST);

  /** {@link PlayDirection} moving {@link #SOUTH} and {@link #WEST} (diagonal). */
  public static final PlayDirection SOUTH_WEST = new PlayDirection(SOUTH, WEST);

  /** {@link PlayDirection} moving {@link #SOUTH} and {@link #EAST} (diagonal). */
  public static final PlayDirection SOUTH_EAST = new PlayDirection(SOUTH, EAST);

  private static final PlayDirection[] TURN_CLOCKWISE = new PlayDirection[] { NORTH, NORTH_EAST, EAST, SOUTH_EAST, SOUTH, SOUTH_WEST, WEST, NORTH_WEST };

  private final Collection<PlayDirection> combinations;

  private final double rotationZ;

  /**
   * The constructor for an atomic {@link PlayDirection}.
   *
   * @param id - see {@link #getId()}.
   * @param rotationZ - see {@link #getRotationZ()}.
   */
  public PlayDirection(String id, double rotationZ) {
    super(id);
    if ((rotationZ != -1) && ((rotationZ < 0) || (rotationZ >= 360))) {
      throw new IllegalArgumentException(Double.toString(rotationZ));
    }
    this.rotationZ = rotationZ;
    this.combinations = Collections.emptyList();
  }

  /**
   * The constructor for a {@link #isCombined() combined} {@link PlayDirection}.
   *
   * @param combinations the {@link #getCombinations() combined directions}.
   */
  public PlayDirection(PlayDirection... combinations) {
    super(id(combinations));
    Objects.requireNonNull(combinations, "combinations");
    if (combinations.length < 2) {
      throw new IllegalArgumentException("combinations.length is " + combinations.length + " - at least 2 directions have to be combined!");
    }
    Map<PlayDirection, Counter> map = createDirectionCounterMap(combinations);
    this.rotationZ = calculateRotationZ(map);
    this.combinations = Collections.unmodifiableList(Arrays.asList(combinations));
  }

  private static double calculateRotationZ(Map<PlayDirection, Counter> map) {

    double totalValue = 0;
    int totalCount = 0;
    int countZero = 0;
    int countSteep = 0;
    for (Entry<PlayDirection, Counter> entry : map.entrySet()) {
      PlayDirection dir = entry.getKey();
      int count = entry.getValue().i;
      double rotation;
      if (count > 0) {
        rotation = dir.rotationZ;
      } else if (count < 0) {
        rotation = dir.getInverse().rotationZ;
        count = -count;
      } else {
        rotation = -1;
      }
      if (rotation >= 0) {
        if (rotation == 0) {
          if (countSteep > 0) {
            rotation = 360;
          } else {
            countZero = count;
          }
        } else if (rotation >= 270) {
          if (countZero > 0) {
            totalValue += countZero * 360;
            countZero = 0;
          } else {
            countSteep = count;
          }
        }
        totalCount += count;
        totalValue += count * rotation;
      }
    }
    if (totalCount > 0) {
      return totalValue / totalCount;
    }
    return -1;
  }

  private static Map<PlayDirection, Counter> createDirectionCounterMap(PlayDirection... directions) {

    Map<PlayDirection, Counter> map = new HashMap<>();
    PlayDirection inverse = null;
    for (PlayDirection dir : directions) {
      if (dir == inverse) {
        throw new IllegalArgumentException("Inverse directions should not be combined directly!");
      }
      if (dir.isCombined()) {
        throw new IllegalArgumentException("Direction " + dir + " is already combined and cannot be combined again!");
      }
      inverse = dir.getInverse();
      PlayDirection natural;
      int increment;
      if (dir.isNatural()) {
        increment = 1;
        natural = dir;
      } else {
        increment = -1;
        natural = inverse;
        assert (natural.isNatural());
      }
      Counter counter = map.computeIfAbsent(natural, x -> new Counter());
      counter.i += increment;
    }
    return map;
  }

  private static String id(PlayDirection... combinations) {

    StringBuilder buffer = new StringBuilder();
    for (PlayDirection direction : combinations) {
      if (buffer.length() > 0) {
        buffer.append('-');
      }
      buffer.append(direction);
    }
    return buffer.toString();
  }

  /**
   * @return {@code true} if this {@link PlayDirection} can also be
   */
  public boolean isCombined() {

    return !this.combinations.isEmpty();
  }

  /**
   * The natural {@link PlayDirection}s are {@link #EAST}, {@link #SOUTH}, and {@link #DOWN} (because the dominating
   * text systems are using left-to-right and top-to-bottom as default and because gravity is pushing down).
   *
   * @return {@code true} if this {@link PlayDirection} is considered as a natural direction, {@code false} otherwise
   *         (if reversed or {@link #isCombined() combined}).
   */
  public boolean isNatural() {

    return ((this == EAST) || (this == SOUTH)) || (this == DOWN);
  }

  /**
   * @return the inverse {@link PlayDirection}.
   */
  public PlayDirection getInverse() {

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
    } else if (this == UP) {
      return DOWN;
    } else if (this == DOWN) {
      return UP;
    } else {
      throw new IllegalStateException("You have to override this method if you introduce new directions!");
    }
  }

  /**
   * @return the {@link Collection} with the atomic {@link PlayDirection}s combined by this {@link PlayDirection}.
   */
  public Collection<PlayDirection> getCombinations() {

    return this.combinations;
  }

  /**
   * @return the rotation of this {@link PlayDirection} according to the Z-axis in the range of {@code [0, 360)} or
   *         {@code -1} if this {@link PlayDirection} is along the Z-axis itself.
   */
  public double getRotationZ() {

    return this.rotationZ;
  }

  /**
   * @param clockwise {@code true} to turn clockwise, {@code false} otherwise (opposite direction).
   * @return the new {@link PlayDirection}.
   */
  public PlayDirection turn(boolean clockwise) {

    Set<PlayDirection> directions = getGame().getDirections();
    int index = 0;
    while (index < TURN_CLOCKWISE.length) {
      if (TURN_CLOCKWISE[index] == this) {
        break;
      }
      index++;
    }
    if (index >= TURN_CLOCKWISE.length) {
      throw new IllegalStateException("Custom Implementations of PlayDirection have to override this method!");
    }
    while (true) {
      if (clockwise) {
        index++;
        if (index >= TURN_CLOCKWISE.length) {
          index = 0;
        }
      } else {
        index--;
        if (index < 0) {
          index = TURN_CLOCKWISE.length - 1;
        }
      }
      PlayDirection dir = TURN_CLOCKWISE[index];
      if (directions.contains(dir)) {
        return dir;
      }
    }
  }

  private static class Counter {

    private int i;
  }

}
