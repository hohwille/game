package io.github.ghosthopper.game.ghosty;

import io.github.ghosthopper.PlayLevel;
import io.github.ghosthopper.border.PlayBorder;
import io.github.ghosthopper.border.PlayBorderTypeDoor;
import io.github.ghosthopper.border.PlayBorderTypeHidden;
import io.github.ghosthopper.border.PlayBorderTypeHole;
import io.github.ghosthopper.border.PlayBorderTypeMagicDoor;
import io.github.ghosthopper.border.PlayBorderTypeOpen;
import io.github.ghosthopper.border.PlayBorderTypeWall;
import io.github.ghosthopper.color.PlayColor;
import io.github.ghosthopper.field.PlayField;
import io.github.ghosthopper.figure.PlayFigureType;
import io.github.ghosthopper.game.PlayGame;
import io.github.ghosthopper.item.PlayPickItem;
import io.github.ghosthopper.item.PlayPickItemType;
import io.github.ghosthopper.item.PlayPushItem;
import io.github.ghosthopper.item.PlayPushItemType;
import io.github.ghosthopper.move.PlayDirection;
import io.github.ghosthopper.player.Player;

/**
 * The game <em>ghosty</em>: {@link Player}s of different {@link PlayFigureType}s hunt for the ghost.
 */
public class Ghosty extends PlayGame {

  /** A frog figure. */
  public static final PlayFigureType FROG = new PlayFigureType("Frog");

  /** A bug figure. */
  public static final PlayFigureType BUG = new PlayFigureType("Bug");

  /** A ghost figure. */
  public static final PlayFigureType GHOST = new PlayFigureType("Ghost", true);

  /** A hole for frogs. */
  public static final PlayBorderTypeHole FROG_HOLE = PlayBorderTypeHole.get(FROG);

  /** A hole for bugs. */
  public static final PlayBorderTypeHole BUG_HOLE = PlayBorderTypeHole.get(BUG);

  /** A diamond to push around. */
  public static final PlayPushItemType DIAMOND = new PlayPushItemType("Diamond");

  private static final int WIDTH = 4;

  private static final int HEIGHT = 4;

  /**
   * The constructor.
   */
  public Ghosty() {
    super("Ghosty");
    PlayLevel level = getCurrentLevel();
    initLevel(level);

    Player player1 = new Player(PlayColor.GREEN, FROG);
    addPlayer(player1);
    player1.getFigures().get(0).setLocation(level.getField(0, 0));

    Player player2 = new Player(PlayColor.RED, BUG);
    addPlayer(player2);
    player2.getFigures().get(0).setLocation(level.getField(WIDTH - 1, 0));

    Player player3 = new Player(PlayColor.BLUE, FROG);
    addPlayer(player3);
    player3.getFigures().get(0).setLocation(level.getField(WIDTH - 1, HEIGHT - 1));

    Player player4 = new Player(PlayColor.YELLOW, BUG);
    addPlayer(player4);
    player4.getFigures().get(0).setLocation(level.getField(0, HEIGHT - 1));

    PlayPushItem pushItem = new PlayPushItem(PlayColor.WHITE, DIAMOND);
    level.getField(3, 4).setPushItem(pushItem);
    pushItem = new PlayPushItem(PlayColor.WHITE, DIAMOND);
    level.getField(4, 4).setPushItem(pushItem);
  }

  @Override
  protected PlayLevel createFirstLevel() {

    PlayLevel firstLevel = super.createFirstLevel();
    initLevelAsRectangular(firstLevel, WIDTH + 4, HEIGHT + 2);
    return firstLevel;
  }

  private void initLevel(PlayLevel level) {

    // currently hardcoded only for initial testing...
    PlayField startField = level.getStartField();
    PlayBorder border = startField.getBorder(PlayDirection.EAST);
    border.setType(PlayBorderTypeHidden.get(PlayBorderTypeHole.get(FROG)));
    border = border.getTargetField().getBorder(PlayDirection.EAST);
    border.setType(PlayBorderTypeHidden.get(PlayBorderTypeMagicDoor.get()));
    border = border.getTargetField().getBorder(PlayDirection.EAST);
    border.setType(PlayBorderTypeHidden.get(PlayBorderTypeWall.get()));

    border = startField.getBorder(PlayDirection.SOUTH);
    border.setType(PlayBorderTypeHidden.get(PlayBorderTypeHole.get(BUG)));
    PlayField field = border.getTargetField();
    border = field.getBorder(PlayDirection.SOUTH);
    PlayPickItem key = new PlayPickItem(PlayColor.GREEN, PlayPickItemType.KEY);
    field.addItem(key);
    border.setType(PlayBorderTypeHidden.get(PlayBorderTypeOpen.get()));
    border = border.getTargetField().getBorder(PlayDirection.SOUTH);
    border.setType(PlayBorderTypeHidden.get(PlayBorderTypeWall.get()));

    border = border.getTargetField().getBorder(PlayDirection.EAST);
    border.setType(PlayBorderTypeHidden.get(PlayBorderTypeHole.get(BUG)));
    border = border.getTargetField().getBorder(PlayDirection.EAST);
    border.setType(PlayBorderTypeHidden.get(PlayBorderTypeOpen.get()));
    border = border.getTargetField().getBorder(PlayDirection.EAST);
    border.setType(PlayBorderTypeHidden.get(PlayBorderTypeOpen.get()));

    border = border.getTargetField().getBorder(PlayDirection.NORTH);
    border.setType(PlayBorderTypeHidden.get(PlayBorderTypeHole.get(BUG)));
    field = border.getSourceField();
    border = field.getBorder(PlayDirection.WEST);
    border.setType(PlayBorderTypeHidden.get(PlayBorderTypeWall.get()));
    border = field.getBorder(PlayDirection.NORTH);
    border.setType(PlayBorderTypeHidden.get(PlayBorderTypeDoor.get(key)));
    field = border.getSourceField();
    border = field.getBorder(PlayDirection.NORTH);
    border.setType(PlayBorderTypeHidden.get(PlayBorderTypeMagicDoor.get()));

    border = field.getBorder(PlayDirection.WEST);
    border.setType(PlayBorderTypeHidden.get(PlayBorderTypeWall.get()));
    field = border.getSourceField();
    field.addItem(new PlayPickItem(PlayColor.BLUE, PlayPickItemType.EMERALD));
    border = field.getBorder(PlayDirection.NORTH);
    border.setType(PlayBorderTypeHidden.get(PlayBorderTypeHole.get(BUG)));
    border = field.getBorder(PlayDirection.SOUTH);
    border.setType(PlayBorderTypeHidden.get(PlayBorderTypeOpen.get()));
    border = field.getBorder(PlayDirection.WEST);
    border.setType(PlayBorderTypeHidden.get(PlayBorderTypeWall.get()));
    field = border.getSourceField();
    border = field.getBorder(PlayDirection.NORTH);
    border.setType(PlayBorderTypeHidden.get(PlayBorderTypeOpen.get()));
    border = field.getBorder(PlayDirection.WEST);
    border.setType(PlayBorderTypeHidden.get(PlayBorderTypeWall.get()));
    border = field.getBorder(PlayDirection.SOUTH);
    border.setType(PlayBorderTypeHidden.get(PlayBorderTypeOpen.get()));
    field = border.getTargetField();
    border = field.getBorder(PlayDirection.WEST);
    border.setType(PlayBorderTypeHidden.get(PlayBorderTypeHole.get(FROG)));
    border = field.getBorder(PlayDirection.SOUTH);
    border.setType(PlayBorderTypeHidden.get(PlayBorderTypeWall.get()));
    border = field.getBorder(PlayDirection.EAST);
    border.setType(PlayBorderTypeHidden.get(PlayBorderTypeOpen.get()));
    field = border.getTargetField();
    border = field.getBorder(PlayDirection.SOUTH);
    border.setType(PlayBorderTypeHidden.get(PlayBorderTypeOpen.get()));
  }

  @Override
  protected void move(PlayDirection direction) {

    super.move(direction);
    // nextPlayer();
  }

  @Override
  public boolean isAutoPickItem() {

    return true;
  }
}
