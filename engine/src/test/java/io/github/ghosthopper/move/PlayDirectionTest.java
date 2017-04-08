/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.move;

import java.util.HashSet;
import java.util.Set;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import io.github.ghosthopper.game.PlayGame;

/**
 * Test of {@link PlayDirection}.
 */
public class PlayDirectionTest extends Assertions {

  /**
   * Test of {@link PlayDirection#getCombinations()}.
   */
  @Test
  public void testCombinations() {

    assertThat(PlayDirection.RIGHT.getCombinations()).isEmpty();
    assertThat(PlayDirection.DOWN_RIGHT.getInverse()).isSameAs(PlayDirection.UP_LEFT);
    assertThat(PlayDirection.DOWN.getInverse()).isSameAs(PlayDirection.UP);
    assertThat(PlayDirection.DOWN_LEFT.getInverse()).isSameAs(PlayDirection.UP_RIGHT);
    assertThat(PlayDirection.LEFT.getInverse()).isSameAs(PlayDirection.RIGHT);
    assertThat(PlayDirection.UP_LEFT.getInverse()).isSameAs(PlayDirection.DOWN_RIGHT);
    assertThat(PlayDirection.UP.getInverse()).isSameAs(PlayDirection.DOWN);
    assertThat(PlayDirection.UP_RIGHT.getInverse()).isSameAs(PlayDirection.DOWN_LEFT);
  }

  /**
   * Test of {@link PlayDirection#getInverse()}.
   */
  @Test
  public void testInverse() {

    assertThat(PlayDirection.RIGHT.getInverse()).isSameAs(PlayDirection.LEFT);
    assertThat(PlayDirection.DOWN_RIGHT.getInverse()).isSameAs(PlayDirection.UP_LEFT);
    assertThat(PlayDirection.DOWN.getInverse()).isSameAs(PlayDirection.UP);
    assertThat(PlayDirection.DOWN_LEFT.getInverse()).isSameAs(PlayDirection.UP_RIGHT);
    assertThat(PlayDirection.LEFT.getInverse()).isSameAs(PlayDirection.RIGHT);
    assertThat(PlayDirection.UP_LEFT.getInverse()).isSameAs(PlayDirection.DOWN_RIGHT);
    assertThat(PlayDirection.UP.getInverse()).isSameAs(PlayDirection.DOWN);
    assertThat(PlayDirection.UP_RIGHT.getInverse()).isSameAs(PlayDirection.DOWN_LEFT);
  }

  /**
   * Test of {@link PlayDirection#turn(boolean)} with 90 degrees per step (default).
   */
  @Test
  public void testTurn90() {

    PlayGame.getCurrentGame().end();
    // clockwise
    assertThat(PlayDirection.RIGHT.turn(true)).isSameAs(PlayDirection.DOWN);
    assertThat(PlayDirection.DOWN.turn(true)).isSameAs(PlayDirection.LEFT);
    assertThat(PlayDirection.LEFT.turn(true)).isSameAs(PlayDirection.UP);
    assertThat(PlayDirection.UP.turn(true)).isSameAs(PlayDirection.RIGHT);

    // counter-clockwise
    assertThat(PlayDirection.RIGHT.turn(false)).isSameAs(PlayDirection.UP);
    assertThat(PlayDirection.UP.turn(false)).isSameAs(PlayDirection.LEFT);
    assertThat(PlayDirection.LEFT.turn(false)).isSameAs(PlayDirection.DOWN);
    assertThat(PlayDirection.DOWN.turn(false)).isSameAs(PlayDirection.RIGHT);
  }

  /**
   * Test of {@link PlayDirection#turn(boolean)} with 45 degrees per step (custom game).
   */
  @Test
  public void testTurn45() {

    PlayGame game = new PlayGame("45") {

      @Override
      protected boolean isSupportingDiagonalDirections() {

        return true;
      }
    };
    game.start();

    // clockwise
    assertThat(PlayDirection.RIGHT.turn(true)).isSameAs(PlayDirection.DOWN_RIGHT);
    assertThat(PlayDirection.DOWN_RIGHT.turn(true)).isSameAs(PlayDirection.DOWN);
    assertThat(PlayDirection.DOWN.turn(true)).isSameAs(PlayDirection.DOWN_LEFT);
    assertThat(PlayDirection.DOWN_LEFT.turn(true)).isSameAs(PlayDirection.LEFT);
    assertThat(PlayDirection.LEFT.turn(true)).isSameAs(PlayDirection.UP_LEFT);
    assertThat(PlayDirection.UP_LEFT.turn(true)).isSameAs(PlayDirection.UP);
    assertThat(PlayDirection.UP.turn(true)).isSameAs(PlayDirection.UP_RIGHT);
    assertThat(PlayDirection.UP_RIGHT.turn(true)).isSameAs(PlayDirection.RIGHT);

    // counter-clockwise
    assertThat(PlayDirection.RIGHT.turn(false)).isSameAs(PlayDirection.UP_RIGHT);
    assertThat(PlayDirection.UP_RIGHT.turn(false)).isSameAs(PlayDirection.UP);
    assertThat(PlayDirection.UP.turn(false)).isSameAs(PlayDirection.UP_LEFT);
    assertThat(PlayDirection.UP_LEFT.turn(false)).isSameAs(PlayDirection.LEFT);
    assertThat(PlayDirection.LEFT.turn(false)).isSameAs(PlayDirection.DOWN_LEFT);
    assertThat(PlayDirection.DOWN_LEFT.turn(false)).isSameAs(PlayDirection.DOWN);
    assertThat(PlayDirection.DOWN.turn(false)).isSameAs(PlayDirection.DOWN_RIGHT);
    assertThat(PlayDirection.DOWN_RIGHT.turn(false)).isSameAs(PlayDirection.RIGHT);

    game.end();
  }

  /**
   * Test of {@link PlayDirection#turn(boolean)} with 180 degrees per step (custom game).
   */
  @Test
  public void testTurn180() {

    PlayGame game = new PlayGame("180") {

      @Override
      protected Set<PlayDirection> createDirections() {

        Set<PlayDirection> set = new HashSet<>();
        set.add(PlayDirection.LEFT);
        set.add(PlayDirection.RIGHT);
        return set;
      }
    };
    game.start();

    // clockwise
    assertThat(PlayDirection.RIGHT.turn(true)).isSameAs(PlayDirection.LEFT);
    assertThat(PlayDirection.LEFT.turn(true)).isSameAs(PlayDirection.RIGHT);

    // counter-clockwise
    assertThat(PlayDirection.RIGHT.turn(false)).isSameAs(PlayDirection.LEFT);
    assertThat(PlayDirection.LEFT.turn(false)).isSameAs(PlayDirection.RIGHT);

    game.end();
  }

}
