package io.github.ghosthopper.game.ghosty;

import io.github.ghosthopper.border.GameBorder;
import io.github.ghosthopper.border.GameBorderTypeDoor;
import io.github.ghosthopper.border.GameBorderTypeHidden;
import io.github.ghosthopper.border.GameBorderTypeHole;
import io.github.ghosthopper.border.GameBorderTypeMagicDoor;
import io.github.ghosthopper.border.GameBorderTypeOpen;
import io.github.ghosthopper.border.GameBorderTypeWall;
import io.github.ghosthopper.color.GameColor;
import io.github.ghosthopper.direction.GameDirection;
import io.github.ghosthopper.field.GameField;
import io.github.ghosthopper.figure.GameFigure;
import io.github.ghosthopper.figure.GameFigureGroup;
import io.github.ghosthopper.figure.GameFigureType;
import io.github.ghosthopper.game.Game;
import io.github.ghosthopper.item.GamePickItem;
import io.github.ghosthopper.item.GamePickItemType;
import io.github.ghosthopper.item.GamePushItem;
import io.github.ghosthopper.item.GamePushItemType;
import io.github.ghosthopper.level.GameLevel;
import io.github.ghosthopper.player.GamePlayer;
import io.github.ghosthopper.player.GamePlayerConfigBase;
import io.github.ghosthopper.player.GamePlayerConfigChoiceGroup;
import io.github.ghosthopper.position.GamePosition;

/**
 * The game <em>ghosty</em>: {@link GamePlayer}s of different {@link GameFigureType}s hunt for the ghost.
 */
public class Ghosty extends Game {

  /** A frog figure. */
  public static final GameFigureType FROG = new GameFigureType("Frog");

  /** A bug figure. */
  public static final GameFigureType BUG = new GameFigureType("Bug");

  /** A ghost figure. */
  public static final GameFigureType GHOST = new GameFigureType("Ghost", true);

  /** A hole for frogs. */
  public static final GameBorderTypeHole FROG_HOLE = GameBorderTypeHole.get(FROG);

  /** A hole for bugs. */
  public static final GameBorderTypeHole BUG_HOLE = GameBorderTypeHole.get(BUG);

  /** A diamond to push around. */
  public static final GamePushItemType DIAMOND = new GamePushItemType("Diamond");

  private static final int WIDTH = 4;

  private static final int HEIGHT = 4;

  /**
   * The constructor.
   */
  public Ghosty() {
    super("Ghosty");
    GameLevel level = getCurrentLevel();
    initLevel(level);

    GamePushItem pushItem = new GamePushItem(GameColor.WHITE, DIAMOND);
    level.getField(3, 4).setPushItem(pushItem);
    pushItem = new GamePushItem(GameColor.WHITE, DIAMOND);
    level.getField(4, 4).setPushItem(pushItem);
  }

  @Override
  protected GamePlayerConfigBase createPlayerConfig() {

    GameLevel level = getCurrentLevel();

    GamePlayer player1 = new GamePlayer(this, GameColor.GREEN, FROG);
    GameFigure figure = player1.getFigures().get(0);
    figure.setLocation(level.getField(0, 0));
    figure.setPosition(GamePosition.NORTH_WEST);

    GamePlayer player2 = new GamePlayer(this, GameColor.RED, BUG);
    figure = player2.getFigures().get(0);
    figure.setLocation(level.getField(WIDTH - 1, 0));
    figure.setPosition(GamePosition.NORTH_EAST);

    GamePlayer player3 = new GamePlayer(this, GameColor.BLUE, FROG);
    figure = player3.getFigures().get(0);
    figure.setLocation(level.getField(WIDTH - 1, HEIGHT - 1));
    figure.setPosition(GamePosition.SOUTH_EAST);

    GamePlayer player4 = new GamePlayer(this, GameColor.YELLOW, BUG, FROG);
    GameFigureGroup group = player4.createGroup();
    figure = player4.getFigures().get(0);
    figure.setLocation(level.getField(0, HEIGHT - 1));
    figure.setPosition(GamePosition.NORTH_WEST);
    group.addFigure(figure);
    figure = player4.getFigures().get(1);
    figure.setLocation(level.getField(0, HEIGHT - 1));
    figure.setPosition(GamePosition.SOUTH);
    group.addFigure(figure);

    GamePlayerConfigBase playerConfig = new GamePlayerConfigChoiceGroup(this, 2);
    playerConfig.addPlayers(player1, player2, player3, player4);
    return playerConfig;
  }

  @Override
  protected GameLevel createFirstLevel() {

    GameLevel firstLevel = super.createFirstLevel();
    initLevelAsRectangular(firstLevel, WIDTH + 4, HEIGHT + 2);
    return firstLevel;
  }

  private void initLevel(GameLevel level) {

    // currently hardcoded only for initial testing...
    GameField startField = level.getStartField();
    GameBorder border = startField.getBorder(GameDirection.EAST);
    border.setType(GameBorderTypeHidden.get(GameBorderTypeHole.get(FROG)));
    border = border.getTargetField().getBorder(GameDirection.EAST);
    border.setType(GameBorderTypeHidden.get(GameBorderTypeMagicDoor.get()));
    border = border.getTargetField().getBorder(GameDirection.EAST);
    border.setType(GameBorderTypeHidden.get(GameBorderTypeWall.get()));

    border = startField.getBorder(GameDirection.SOUTH);
    border.setType(GameBorderTypeHidden.get(GameBorderTypeHole.get(BUG)));
    GameField field = border.getTargetField();
    border = field.getBorder(GameDirection.SOUTH);
    GamePickItem key = new GamePickItem(GameColor.GREEN, GamePickItemType.KEY);
    field.addItem(key);
    border.setType(GameBorderTypeHidden.get(GameBorderTypeOpen.get()));
    border = border.getTargetField().getBorder(GameDirection.SOUTH);
    border.setType(GameBorderTypeHidden.get(GameBorderTypeWall.get()));

    border = border.getTargetField().getBorder(GameDirection.EAST);
    border.setType(GameBorderTypeHidden.get(GameBorderTypeHole.get(BUG)));
    border = border.getTargetField().getBorder(GameDirection.EAST);
    border.setType(GameBorderTypeHidden.get(GameBorderTypeOpen.get()));
    border = border.getTargetField().getBorder(GameDirection.EAST);
    border.setType(GameBorderTypeHidden.get(GameBorderTypeOpen.get()));

    border = border.getTargetField().getBorder(GameDirection.NORTH);
    border.setType(GameBorderTypeHidden.get(GameBorderTypeHole.get(BUG)));
    field = border.getSourceField();
    border = field.getBorder(GameDirection.WEST);
    border.setType(GameBorderTypeHidden.get(GameBorderTypeWall.get()));
    border = field.getBorder(GameDirection.NORTH);
    border.setType(GameBorderTypeHidden.get(GameBorderTypeDoor.get(key)));
    field = border.getSourceField();
    border = field.getBorder(GameDirection.NORTH);
    border.setType(GameBorderTypeHidden.get(GameBorderTypeMagicDoor.get()));

    border = field.getBorder(GameDirection.WEST);
    border.setType(GameBorderTypeHidden.get(GameBorderTypeWall.get()));
    field = border.getSourceField();
    field.addItem(new GamePickItem(GameColor.BLUE, GamePickItemType.EMERALD));
    border = field.getBorder(GameDirection.NORTH);
    border.setType(GameBorderTypeHidden.get(GameBorderTypeHole.get(BUG)));
    border = field.getBorder(GameDirection.SOUTH);
    border.setType(GameBorderTypeHidden.get(GameBorderTypeOpen.get()));
    border = field.getBorder(GameDirection.WEST);
    border.setType(GameBorderTypeHidden.get(GameBorderTypeWall.get()));
    field = border.getSourceField();
    border = field.getBorder(GameDirection.NORTH);
    border.setType(GameBorderTypeHidden.get(GameBorderTypeOpen.get()));
    border = field.getBorder(GameDirection.WEST);
    border.setType(GameBorderTypeHidden.get(GameBorderTypeWall.get()));
    border = field.getBorder(GameDirection.SOUTH);
    border.setType(GameBorderTypeHidden.get(GameBorderTypeOpen.get()));
    field = border.getTargetField();
    border = field.getBorder(GameDirection.WEST);
    border.setType(GameBorderTypeHidden.get(GameBorderTypeHole.get(FROG)));
    border = field.getBorder(GameDirection.SOUTH);
    border.setType(GameBorderTypeHidden.get(GameBorderTypeWall.get()));
    border = field.getBorder(GameDirection.EAST);
    border.setType(GameBorderTypeHidden.get(GameBorderTypeOpen.get()));
    field = border.getTargetField();
    border = field.getBorder(GameDirection.SOUTH);
    border.setType(GameBorderTypeHidden.get(GameBorderTypeOpen.get()));
  }

  @Override
  protected void move(GameDirection direction) {

    super.move(direction);
    // nextPlayer();
  }

  @Override
  public boolean isAutoPickItem() {

    return true;
  }
}
