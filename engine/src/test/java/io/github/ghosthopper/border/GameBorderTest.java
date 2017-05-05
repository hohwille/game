package io.github.ghosthopper.border;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import io.github.ghosthopper.color.GameColor;
import io.github.ghosthopper.direction.GameDirection;
import io.github.ghosthopper.field.GameField;
import io.github.ghosthopper.figure.GameFigure;
import io.github.ghosthopper.figure.GameFigureType;
import io.github.ghosthopper.game.Game;
import io.github.ghosthopper.game.GameNone;
import io.github.ghosthopper.item.GamePickItem;
import io.github.ghosthopper.item.GamePickItemType;
import io.github.ghosthopper.level.GameLevel;
import io.github.ghosthopper.player.GamePlayer;
import io.github.ghosthopper.player.GamePlayerConfigBase;

/**
 * Test for {@link GameBorder} with arbitrary {@link GameBorderType}s.
 */
public class GameBorderTest extends Assertions {

  private static final GameFigureType MOUSE = new GameFigureType("Mouse");

  private static final GameFigureType RABBIT = new GameFigureType("Rabbit");

  private static final GameBorderType MOUSE_HOLE = GameBorderTypeHole.get(MOUSE);

  private static final GamePlayer BLUE_MOUSE = new GamePlayer(GameNone.INSTANCE, GameColor.BLUE, MOUSE);

  private static final GameFigure BLUE_MOUSE_FIGURE = BLUE_MOUSE.getFigures().get(0);

  private static final GamePlayer GREEN_RABBIT = new GamePlayer(GameNone.INSTANCE, GameColor.GREEN, RABBIT);

  private static final GameFigure GREEN_RABBIT_FIGURE = GREEN_RABBIT.getFigures().get(0);

  private static final GamePickItem RED_KEY = new GamePickItem(GameColor.RED, GamePickItemType.KEY);

  /**
   * Test of {@link GameBorder} with {@link GameBorderTypeHole}.
   */
  @Test
  public void testHole() {

    // given
    GameBorder mouseHole = new GameBorder(null, GameDirection.EAST, null, MOUSE_HOLE);

    // when + then
    assertThat(mouseHole.pass(BLUE_MOUSE_FIGURE)).isTrue();
    assertThat(mouseHole.pass(GREEN_RABBIT_FIGURE)).isFalse();
  }

  /**
   * Test of {@link GameBorder} with {@link GameBorderTypeWall}.
   */
  @Test
  public void testWall() {

    // given
    GameBorder wall = new GameBorder(null, GameDirection.EAST, null, GameBorderTypeWall.get());

    // when + then
    assertThat(wall.pass(BLUE_MOUSE_FIGURE)).isFalse();
    assertThat(wall.pass(GREEN_RABBIT_FIGURE)).isFalse();
  }

  /**
   * Test of {@link GameBorder} with {@link GameBorderTypeOpen}.
   */
  @Test
  public void testOpen() {

    // given
    GameBorder open = new GameBorder(null, GameDirection.EAST, null, GameBorderTypeOpen.get());

    // when + then
    assertThat(open.pass(BLUE_MOUSE_FIGURE)).isTrue();
    assertThat(open.pass(GREEN_RABBIT_FIGURE)).isTrue();
  }

  /**
   * Test of {@link GameBorder} with {@link GameBorderTypeDoor}.
   */
  @Test
  public void testDoor() {

    // given
    BLUE_MOUSE_FIGURE.addItem(RED_KEY);
    GameBorder open = new GameBorder(null, GameDirection.EAST, null, GameBorderTypeDoor.get(RED_KEY));

    // when + then
    assertThat(open.pass(BLUE_MOUSE_FIGURE)).isTrue();
    assertThat(open.pass(GREEN_RABBIT_FIGURE)).isFalse();
  }

  /**
   * Test of {@link GameBorder} with {@link GameBorderTypeMagicDoor}.
   */
  @Test
  public void testMagicDoor() {

    // given
    BLUE_MOUSE_FIGURE.addItem(RED_KEY);
    Game game = GameNone.INSTANCE;
    GamePlayerConfigBase playerConfig = (GamePlayerConfigBase) game.getPlayerConfig();
    playerConfig.addPlayer(BLUE_MOUSE);
    playerConfig.addPlayer(GREEN_RABBIT);
    GameLevel level = game.getCurrentLevel();
    GameField sourceField = level.getStartField();
    GameField targetField = new GameField(level);
    GameBorder open = new GameBorder(sourceField, GameDirection.EAST, targetField, GameBorderTypeMagicDoor.get());

    // when + then
    assertThat(open.pass(GREEN_RABBIT_FIGURE)).isFalse();
    BLUE_MOUSE_FIGURE.setLocation(sourceField);
    assertThat(open.pass(BLUE_MOUSE_FIGURE)).isFalse();
    GREEN_RABBIT_FIGURE.setLocation(targetField);
    assertThat(open.pass(BLUE_MOUSE_FIGURE)).isTrue();
    assertThat(open.pass(GREEN_RABBIT_FIGURE)).isTrue();
  }

}
