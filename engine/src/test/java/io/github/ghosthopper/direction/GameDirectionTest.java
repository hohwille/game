/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.direction;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import io.github.ghosthopper.direction.GameDirection;
import io.github.ghosthopper.game.Game;

/**
 * Test of {@link GameDirection}.
 */
public class GameDirectionTest extends Assertions {

  /**
   * Test of {@link GameDirection#getCombinations()}.
   */
  @Test
  public void testCombinations() {

    assertThat(GameDirection.EAST.getCombinations()).isEmpty();
    assertThat(GameDirection.SOUTH_EAST.getInverse()).isSameAs(GameDirection.NORTH_WEST);
    assertThat(GameDirection.SOUTH.getInverse()).isSameAs(GameDirection.NORTH);
    assertThat(GameDirection.SOUTH_WEST.getInverse()).isSameAs(GameDirection.NORTH_EAST);
    assertThat(GameDirection.WEST.getInverse()).isSameAs(GameDirection.EAST);
    assertThat(GameDirection.NORTH_WEST.getInverse()).isSameAs(GameDirection.SOUTH_EAST);
    assertThat(GameDirection.NORTH.getInverse()).isSameAs(GameDirection.SOUTH);
    assertThat(GameDirection.NORTH_EAST.getInverse()).isSameAs(GameDirection.SOUTH_WEST);
  }

  /**
   * Test of {@link GameDirection#getInverse()}.
   */
  @Test
  public void testInverse() {

    assertThat(GameDirection.EAST.getInverse()).isSameAs(GameDirection.WEST);
    assertThat(GameDirection.SOUTH_EAST.getInverse()).isSameAs(GameDirection.NORTH_WEST);
    assertThat(GameDirection.SOUTH.getInverse()).isSameAs(GameDirection.NORTH);
    assertThat(GameDirection.SOUTH_WEST.getInverse()).isSameAs(GameDirection.NORTH_EAST);
    assertThat(GameDirection.WEST.getInverse()).isSameAs(GameDirection.EAST);
    assertThat(GameDirection.NORTH_WEST.getInverse()).isSameAs(GameDirection.SOUTH_EAST);
    assertThat(GameDirection.NORTH.getInverse()).isSameAs(GameDirection.SOUTH);
    assertThat(GameDirection.NORTH_EAST.getInverse()).isSameAs(GameDirection.SOUTH_WEST);
  }

  /**
   * Test of {@link GameDirection#rotate(boolean)} with 90 degrees per step (default).
   */
  @Test
  public void testTurn90() {

    Game.getCurrentGame().end();
    // clockwise
    assertThat(GameDirection.EAST.rotate(true)).isSameAs(GameDirection.SOUTH);
    assertThat(GameDirection.SOUTH.rotate(true)).isSameAs(GameDirection.WEST);
    assertThat(GameDirection.WEST.rotate(true)).isSameAs(GameDirection.NORTH);
    assertThat(GameDirection.NORTH.rotate(true)).isSameAs(GameDirection.EAST);

    // counter-clockwise
    assertThat(GameDirection.EAST.rotate(false)).isSameAs(GameDirection.NORTH);
    assertThat(GameDirection.NORTH.rotate(false)).isSameAs(GameDirection.WEST);
    assertThat(GameDirection.WEST.rotate(false)).isSameAs(GameDirection.SOUTH);
    assertThat(GameDirection.SOUTH.rotate(false)).isSameAs(GameDirection.EAST);
  }

  /**
   * Test of {@link GameDirection#rotate(boolean)} with 45 degrees per step (custom game).
   */
  @Test
  public void testTurn45() {

    Game game = new Game("45") {

      @Override
      protected boolean isSupportingDiagonalDirections() {

        return true;
      }
    };
    game.begin();

    // clockwise
    assertThat(GameDirection.EAST.rotate(true)).isSameAs(GameDirection.SOUTH_EAST);
    assertThat(GameDirection.SOUTH_EAST.rotate(true)).isSameAs(GameDirection.SOUTH);
    assertThat(GameDirection.SOUTH.rotate(true)).isSameAs(GameDirection.SOUTH_WEST);
    assertThat(GameDirection.SOUTH_WEST.rotate(true)).isSameAs(GameDirection.WEST);
    assertThat(GameDirection.WEST.rotate(true)).isSameAs(GameDirection.NORTH_WEST);
    assertThat(GameDirection.NORTH_WEST.rotate(true)).isSameAs(GameDirection.NORTH);
    assertThat(GameDirection.NORTH.rotate(true)).isSameAs(GameDirection.NORTH_EAST);
    assertThat(GameDirection.NORTH_EAST.rotate(true)).isSameAs(GameDirection.EAST);

    // counter-clockwise
    assertThat(GameDirection.EAST.rotate(false)).isSameAs(GameDirection.NORTH_EAST);
    assertThat(GameDirection.NORTH_EAST.rotate(false)).isSameAs(GameDirection.NORTH);
    assertThat(GameDirection.NORTH.rotate(false)).isSameAs(GameDirection.NORTH_WEST);
    assertThat(GameDirection.NORTH_WEST.rotate(false)).isSameAs(GameDirection.WEST);
    assertThat(GameDirection.WEST.rotate(false)).isSameAs(GameDirection.SOUTH_WEST);
    assertThat(GameDirection.SOUTH_WEST.rotate(false)).isSameAs(GameDirection.SOUTH);
    assertThat(GameDirection.SOUTH.rotate(false)).isSameAs(GameDirection.SOUTH_EAST);
    assertThat(GameDirection.SOUTH_EAST.rotate(false)).isSameAs(GameDirection.EAST);

    game.end();
  }

  /**
   * Test of {@link GameDirection#rotate(boolean)} with 180 degrees per step (custom game).
   */
  @Test
  public void testTurn180() {

    Game game = new Game("180") {

      @Override
      protected List<GameDirection> createDirections() {

        List<GameDirection> set = new ArrayList<>();
        set.add(GameDirection.WEST);
        set.add(GameDirection.EAST);
        return set;
      }
    };
    game.begin();

    // clockwise
    assertThat(GameDirection.EAST.rotate(true)).isSameAs(GameDirection.WEST);
    assertThat(GameDirection.WEST.rotate(true)).isSameAs(GameDirection.EAST);

    // counter-clockwise
    assertThat(GameDirection.EAST.rotate(false)).isSameAs(GameDirection.WEST);
    assertThat(GameDirection.WEST.rotate(false)).isSameAs(GameDirection.EAST);

    game.end();
  }

  /**
   * Test of {@link GameDirection#getRotationZ()}.
   */
  @Test
  public void testGetRotationZ() {

    assertThat(GameDirection.NORTH.getRotationZ()).isEqualTo(0);
    assertThat(GameDirection.EAST.getRotationZ()).isEqualTo(90);
    assertThat(GameDirection.SOUTH.getRotationZ()).isEqualTo(180);
    assertThat(GameDirection.WEST.getRotationZ()).isEqualTo(270);
    assertThat(GameDirection.NORTH_EAST.getRotationZ()).isEqualTo(45);
    assertThat(GameDirection.SOUTH_EAST.getRotationZ()).isEqualTo(135);
    assertThat(GameDirection.SOUTH_WEST.getRotationZ()).isEqualTo(225);
    assertThat(GameDirection.NORTH_WEST.getRotationZ()).isEqualTo(315);
    GameDirection chessKnightMove = new GameDirection(GameDirection.NORTH, GameDirection.NORTH, GameDirection.EAST);
    assertThat(chessKnightMove.getRotationZ()).isEqualTo(30);
  }

}
