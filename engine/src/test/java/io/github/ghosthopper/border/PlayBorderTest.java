package io.github.ghosthopper.border;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import io.github.ghosthopper.PlayLevel;
import io.github.ghosthopper.color.PlayColor;
import io.github.ghosthopper.field.PlayField;
import io.github.ghosthopper.figure.PlayFigure;
import io.github.ghosthopper.figure.PlayFigureType;
import io.github.ghosthopper.game.PlayGame;
import io.github.ghosthopper.game.PlayGameNone;
import io.github.ghosthopper.item.PlayPickItem;
import io.github.ghosthopper.item.PlayPickItemType;
import io.github.ghosthopper.move.PlayDirection;
import io.github.ghosthopper.player.Player;

/**
 * Test for {@link PlayBorder} with arbitrary {@link PlayBorderType}s.
 */
public class PlayBorderTest extends Assertions {

  private static final PlayFigureType MOUSE = new PlayFigureType("Mouse");

  private static final PlayFigureType RABBIT = new PlayFigureType("Rabbit");

  private static final PlayBorderType MOUSE_HOLE = PlayBorderTypeHole.get(MOUSE);

  private static final Player BLUE_MOUSE = new Player(PlayColor.BLUE, MOUSE);

  private static final PlayFigure BLUE_MOUSE_FIGURE = BLUE_MOUSE.getFigures().get(0);

  private static final Player GREEN_RABBIT = new Player(PlayColor.GREEN, RABBIT);

  private static final PlayFigure GREEN_RABBIT_FIGURE = GREEN_RABBIT.getFigures().get(0);

  private static final PlayPickItem RED_KEY = new PlayPickItem(PlayColor.RED, PlayPickItemType.KEY);

  /**
   * Test of {@link PlayBorder} with {@link PlayBorderTypeHole}.
   */
  @Test
  public void testHole() {

    // given
    PlayBorder mouseHole = new PlayBorder(null, PlayDirection.RIGHT, null, MOUSE_HOLE);

    // when + then
    assertThat(mouseHole.pass(BLUE_MOUSE_FIGURE)).isTrue();
    assertThat(mouseHole.pass(GREEN_RABBIT_FIGURE)).isFalse();
  }

  /**
   * Test of {@link PlayBorder} with {@link PlayBorderTypeWall}.
   */
  @Test
  public void testWall() {

    // given
    PlayBorder wall = new PlayBorder(null, PlayDirection.RIGHT, null, PlayBorderTypeWall.get());

    // when + then
    assertThat(wall.pass(BLUE_MOUSE_FIGURE)).isFalse();
    assertThat(wall.pass(GREEN_RABBIT_FIGURE)).isFalse();
  }

  /**
   * Test of {@link PlayBorder} with {@link PlayBorderTypeOpen}.
   */
  @Test
  public void testOpen() {

    // given
    PlayBorder open = new PlayBorder(null, PlayDirection.RIGHT, null, PlayBorderTypeOpen.get());

    // when + then
    assertThat(open.pass(BLUE_MOUSE_FIGURE)).isTrue();
    assertThat(open.pass(GREEN_RABBIT_FIGURE)).isTrue();
  }

  /**
   * Test of {@link PlayBorder} with {@link PlayBorderTypeDoor}.
   */
  @Test
  public void testDoor() {

    // given
    BLUE_MOUSE_FIGURE.getItems().add(RED_KEY);
    PlayBorder open = new PlayBorder(null, PlayDirection.RIGHT, null, PlayBorderTypeDoor.get(RED_KEY));

    // when + then
    assertThat(open.pass(BLUE_MOUSE_FIGURE)).isTrue();
    assertThat(open.pass(GREEN_RABBIT_FIGURE)).isFalse();
  }

  /**
   * Test of {@link PlayBorder} with {@link PlayBorderTypeMagicDoor}.
   */
  @Test
  public void testMagicDoor() {

    // given
    BLUE_MOUSE_FIGURE.getItems().add(RED_KEY);
    PlayGame game = new PlayGameNone();
    List<Player> players = game.getPlayers();
    players.add(BLUE_MOUSE);
    players.add(GREEN_RABBIT);
    PlayLevel level = game.getCurrentLevel();
    PlayField sourceField = level.getStartField();
    PlayField targetField = new PlayField(level);
    PlayBorder open = new PlayBorder(sourceField, PlayDirection.RIGHT, targetField, PlayBorderTypeMagicDoor.get());

    // when + then
    assertThat(open.pass(GREEN_RABBIT_FIGURE)).isFalse();
    BLUE_MOUSE_FIGURE.setField(sourceField);
    assertThat(open.pass(BLUE_MOUSE_FIGURE)).isFalse();
    GREEN_RABBIT_FIGURE.setField(targetField);
    assertThat(open.pass(BLUE_MOUSE_FIGURE)).isTrue();
    assertThat(open.pass(GREEN_RABBIT_FIGURE)).isTrue();
  }

}
