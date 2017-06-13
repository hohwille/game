/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.game.engine.direction;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import net.sf.mmm.game.engine.field.GameField;
import net.sf.mmm.game.engine.object.GameObjectWithId;

import java.util.Objects;

/**
 * Represents a direction (move) supported by the game. For flexibility the directions are not hard-coded so it can be
 * possible to also move diagonal or have hexagonal {@link GameField}s if desired.
 */
public class GameDirection extends GameObjectWithId {

  /** {@link GameDirection} moving north (up when looking from above). */
  public static final GameDirection NORTH = new GameDirection("North", 0);

  /** {@link GameDirection} moving to the east (right when looking from above). */
  public static final GameDirection EAST = new GameDirection("East", 90);

  /** {@link GameDirection} moving south (down when looking from above). */
  public static final GameDirection SOUTH = new GameDirection("South", 180);

  /** {@link GameDirection} moving to the west (left when looking from above). */
  public static final GameDirection WEST = new GameDirection("West", 270);

  /** {@link GameDirection} moving upwards (level or layer above). */
  public static final GameDirection UP = new GameDirection("Up", -1);

  /** {@link GameDirection} moving downwards (level or layer below). */
  public static final GameDirection DOWN = new GameDirection("Down", -1);

  /** {@link GameDirection} moving {@link #NORTH} and {@link #WEST} (diagonal). */
  public static final GameDirection NORTH_WEST = new GameDirection(NORTH, WEST);

  /** {@link GameDirection} moving {@link #NORTH} and {@link #EAST} (diagonal). */
  public static final GameDirection NORTH_EAST = new GameDirection(NORTH, EAST);

  /** {@link GameDirection} moving {@link #SOUTH} and {@link #WEST} (diagonal). */
  public static final GameDirection SOUTH_WEST = new GameDirection(SOUTH, WEST);

  /** {@link GameDirection} moving {@link #SOUTH} and {@link #EAST} (diagonal). */
  public static final GameDirection SOUTH_EAST = new GameDirection(SOUTH, EAST);

  private static final GameDirection[] ROTATE_CLOCKWISE = new GameDirection[] { NORTH, NORTH_EAST, EAST, SOUTH_EAST, SOUTH, SOUTH_WEST, WEST, NORTH_WEST };

  private final List<GameDirection> combinations;

  private final double rotationZ;

  /**
   * The constructor for an atomic {@link GameDirection}.
   *
   * @param id - see {@link #getId()}.
   * @param rotationZ - see {@link #getRotationZ()}.
   */
  public GameDirection(String id, double rotationZ) {
    super(id);
    if ((rotationZ != -1) && ((rotationZ < 0) || (rotationZ >= 360))) {
      throw new IllegalArgumentException(Double.toString(rotationZ));
    }
    this.rotationZ = rotationZ;
    this.combinations = Collections.emptyList();
  }

  /**
   * The constructor for a {@link #isCombined() combined} {@link GameDirection}.
   *
   * @param combinations the {@link #getCombinations() combined directions}.
   */
  public GameDirection(GameDirection... combinations) {
    super(id(combinations));
    Objects.requireNonNull(combinations, "combinations");
    if (combinations.length < 2) {
      throw new IllegalArgumentException("combinations.length is " + combinations.length + " - at least 2 directions have to be combined!");
    }
    Map<GameDirection, Counter> map = createDirectionCounterMap(combinations);
    this.rotationZ = calculateRotationZ(map);
    this.combinations = Collections.unmodifiableList(Arrays.asList(combinations));
  }

  private static double calculateRotationZ(Map<GameDirection, Counter> map) {

    double totalValue = 0;
    int totalCount = 0;
    int countZero = 0;
    int countSteep = 0;
    for (Entry<GameDirection, Counter> entry : map.entrySet()) {
      GameDirection dir = entry.getKey();
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

  private static Map<GameDirection, Counter> createDirectionCounterMap(GameDirection... directions) {

    Map<GameDirection, Counter> map = new HashMap<>();
    GameDirection inverse = null;
    for (GameDirection dir : directions) {
      if (dir == inverse) {
        throw new IllegalArgumentException("Inverse directions should not be combined directly!");
      }
      if (dir.isCombined()) {
        throw new IllegalArgumentException("Direction " + dir + " is already combined and cannot be combined again!");
      }
      inverse = dir.getInverse();
      GameDirection natural;
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

  private static String id(GameDirection... combinations) {

    StringBuilder buffer = new StringBuilder();
    for (GameDirection direction : combinations) {
      if (buffer.length() > 0) {
        buffer.append('-');
      }
      buffer.append(direction);
    }
    return buffer.toString();
  }

  /**
   * @return {@code true} if this {@link GameDirection} can also be
   */
  public boolean isCombined() {

    return !this.combinations.isEmpty();
  }

  /**
   * The natural {@link GameDirection}s are {@link #EAST}, {@link #SOUTH}, and {@link #DOWN} (because the dominating
   * text systems are using left-to-right and top-to-bottom as default and because gravity is pushing down).
   *
   * @return {@code true} if this {@link GameDirection} is considered as a natural direction, {@code false} otherwise
   *         (if reversed or {@link #isCombined() combined}).
   */
  public boolean isNatural() {

    return ((this == EAST) || (this == SOUTH)) || (this == DOWN);
  }

  /**
   * @return the inverse {@link GameDirection}.
   */
  public GameDirection getInverse() {

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
   * @return the {@link List} with the atomic {@link GameDirection}s combined by this {@link GameDirection}.
   */
  public List<GameDirection> getCombinations() {

    return this.combinations;
  }

  /**
   * @return the rotation of this {@link GameDirection} according to the Z-axis in the range of {@code [0, 360)} or
   *         {@code -1} if this {@link GameDirection} is along the Z-axis itself.
   */
  public double getRotationZ() {

    return this.rotationZ;
  }

  /**
   * @param clockwise {@code true} to rotate clockwise, {@code false} otherwise (opposite direction).
   * @return the new {@link GameDirection}.
   */
  public GameDirection rotate(boolean clockwise) {

    Collection<GameDirection> directions = getGame().getDirections();
    int index = 0;
    while (index < ROTATE_CLOCKWISE.length) {
      if (ROTATE_CLOCKWISE[index] == this) {
        break;
      }
      index++;
    }
    if (index >= ROTATE_CLOCKWISE.length) {
      throw new IllegalStateException("Custom Implementations of PlayDirection have to override this method!");
    }
    while (true) {
      if (clockwise) {
        index++;
        if (index >= ROTATE_CLOCKWISE.length) {
          index = 0;
        }
      } else {
        index--;
        if (index < 0) {
          index = ROTATE_CLOCKWISE.length - 1;
        }
      }
      GameDirection dir = ROTATE_CLOCKWISE[index];
      if (directions.contains(dir)) {
        return dir;
      }
    }
  }

  private static class Counter {

    private int i;
  }

}
