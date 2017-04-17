/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.move;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;

import io.github.ghosthopper.field.PlayField;
import io.github.ghosthopper.object.PlayObjectWithId;

/**
 * Represents a direction (move) supported by the game. For flexibility the directions are not hard-coded so it can be
 * possible to also move diagonal or have hexagonal {@link PlayField}s if desired.
 */
public class PlayDirection extends PlayObjectWithId {

  /** {@link PlayDirection} moving to the left (west). */
  public static final PlayDirection LEFT = new PlayDirection("Left");

  /** {@link PlayDirection} moving upwards (north, to top). */
  public static final PlayDirection UP = new PlayDirection("Up");

  /** {@link PlayDirection} moving to the right (east). */
  public static final PlayDirection RIGHT = new PlayDirection("Right");

  /** {@link PlayDirection} moving downwards (south, to bottom). */
  public static final PlayDirection DOWN = new PlayDirection("Down");

  /** {@link PlayDirection} moving {@link #UP} and {@link #LEFT} (diagonal). */
  public static final PlayDirection UP_LEFT = new PlayDirection(UP, LEFT);

  /** {@link PlayDirection} moving {@link #UP} and {@link #RIGHT} (diagonal). */
  public static final PlayDirection UP_RIGHT = new PlayDirection(UP, RIGHT);

  /** {@link PlayDirection} moving {@link #DOWN} and {@link #LEFT} (diagonal). */
  public static final PlayDirection DOWN_LEFT = new PlayDirection(DOWN, LEFT);

  /** {@link PlayDirection} moving {@link #DOWN} and {@link #RIGHT} (diagonal). */
  public static final PlayDirection DOWN_RIGHT = new PlayDirection(DOWN, RIGHT);

  private static final PlayDirection[] TURN_CLOCKWISE = new PlayDirection[] { UP, UP_RIGHT, RIGHT, DOWN_RIGHT, DOWN, DOWN_LEFT, LEFT, UP_LEFT };

  private final Collection<PlayDirection> combinations;

  /**
   * The constructor for an atomic {@link PlayDirection}.
   *
   * @param id - see {@link #getId()}.
   */
  public PlayDirection(String id) {
    super(id);
    this.combinations = Collections.emptyList();
  }

  /**
   * The constructor for a {@link #isCombined() combined} {@link PlayDirection}.
   *
   * @param combinations the {@link #getCombinations() combined directions}.
   */
  public PlayDirection(PlayDirection... combinations) {
    super(id(combinations));
    this.combinations = Collections.unmodifiableList(Arrays.asList(combinations));
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
   * The natural {@link PlayDirection}s are is {@link #RIGHT} and {@link #DOWN} because the dominating text systems are
   * using left-to-right and top-to-bottom as default.
   *
   * @return {@code true} if this {@link PlayDirection} is considered as a natural direction, {@code false} otherwise
   *         (if reversed or {@link #isCombined() combined}).
   */
  public boolean isNatural() {

    return ((this == RIGHT) || (this == DOWN));
  }

  /**
   * @return the inverse {@link PlayDirection}.
   */
  public PlayDirection getInverse() {

    if (this == LEFT) {
      return RIGHT;
    } else if (this == RIGHT) {
      return LEFT;
    } else if (this == UP) {
      return DOWN;
    } else if (this == DOWN) {
      return UP;
    } else if (this == UP_LEFT) {
      return DOWN_RIGHT;
    } else if (this == UP_RIGHT) {
      return DOWN_LEFT;
    } else if (this == DOWN_LEFT) {
      return UP_RIGHT;
    } else if (this == DOWN_RIGHT) {
      return UP_LEFT;
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

}
