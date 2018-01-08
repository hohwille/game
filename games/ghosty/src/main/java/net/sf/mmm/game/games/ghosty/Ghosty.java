package net.sf.mmm.game.games.ghosty;

import java.util.ArrayList;
import java.util.List;

import net.sf.mmm.game.engine.Game;
import net.sf.mmm.game.engine.border.GameBorder;
import net.sf.mmm.game.engine.border.GameBorderTypeDoor;
import net.sf.mmm.game.engine.border.GameBorderTypeHidden;
import net.sf.mmm.game.engine.border.GameBorderTypeHole;
import net.sf.mmm.game.engine.border.GameBorderTypeMagicDoor;
import net.sf.mmm.game.engine.border.GameBorderTypeOpen;
import net.sf.mmm.game.engine.border.GameBorderTypeWall;
import net.sf.mmm.game.engine.color.GameColor;
import net.sf.mmm.game.engine.direction.GameDirection;
import net.sf.mmm.game.engine.field.GameField;
import net.sf.mmm.game.engine.figure.GameFigure;
import net.sf.mmm.game.engine.figure.GameFigureGroup;
import net.sf.mmm.game.engine.figure.GameFigureType;
import net.sf.mmm.game.engine.item.GamePickItem;
import net.sf.mmm.game.engine.item.GamePickItemType;
import net.sf.mmm.game.engine.item.GameFieldItem;
import net.sf.mmm.game.engine.item.GameFieldItemType;
import net.sf.mmm.game.engine.level.GameLevel;
import net.sf.mmm.game.engine.player.GamePlayer;
import net.sf.mmm.game.engine.player.GamePlayerConfigBase;
import net.sf.mmm.game.engine.player.GamePlayerConfigChoiceGroup;
import net.sf.mmm.game.engine.position.GamePosition;

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
  public static final GameFieldItemType DIAMOND = new GameFieldItemType("Diamond");

  private static final int WIDTH = 4;

  private static final int HEIGHT = 4;

  private List<GameBorderTypeMagicDoor> magicDoors;

  private boolean ghostActive;

  /**
   * The constructor.
   */
  public Ghosty() {

    super("Ghosty");
    GameLevel level = getCurrentLevel();
    initLevel(level);

    GameFieldItem pushItem = new GameFieldItem(this, DIAMOND, GameColor.WHITE);
    level.getField(3, 4).setItem(pushItem);
    pushItem = new GameFieldItem(this, DIAMOND, GameColor.WHITE);
    level.getField(4, 4).setItem(pushItem);
    addListener(GameBorder.class, this::onBorderChange);
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

    this.magicDoors = new ArrayList<>();
    // currently hardcoded only for initial testing...
    GameField startField = level.getStartField();
    GameBorder border = startField.getBorder(GameDirection.EAST);
    border.setType(GameBorderTypeHidden.get(GameBorderTypeHole.get(FROG)));
    border = border.getTargetField().getBorder(GameDirection.EAST);
    GameBorderTypeMagicDoor magicDoor = GameBorderTypeMagicDoor.get();
    border.setType(GameBorderTypeHidden.get(magicDoor));
    this.magicDoors.add(magicDoor);
    border = border.getTargetField().getBorder(GameDirection.EAST);
    border.setType(GameBorderTypeHidden.get(GameBorderTypeWall.get()));

    border = startField.getBorder(GameDirection.SOUTH);
    border.setType(GameBorderTypeHidden.get(GameBorderTypeHole.get(BUG)));
    GameField field = border.getTargetField();
    border = field.getBorder(GameDirection.SOUTH);
    GamePickItem key = new GamePickItem(this, GamePickItemType.KEY, GameColor.GREEN);
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
    magicDoor = GameBorderTypeMagicDoor.get();
    border.setType(GameBorderTypeHidden.get(magicDoor));
    this.magicDoors.add(magicDoor);

    border = field.getBorder(GameDirection.WEST);
    border.setType(GameBorderTypeHidden.get(GameBorderTypeWall.get()));
    field = border.getSourceField();
    field.addItem(new GamePickItem(this, GamePickItemType.EMERALD, GameColor.BLUE));
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

  private void onBorderChange(GameBorder border) {

    if (this.ghostActive) {
      return;
    }
    if (areAllMagicDoorsOpened()) {
      System.out.println("ACHTUNG: Ghosty kommt!");
      this.ghostActive = true;
    }
  }

  private boolean areAllMagicDoorsOpened() {

    for (GameBorderTypeMagicDoor magicDoor : this.magicDoors) {
      if (!magicDoor.isOpen()) {
        return false;
      }
    }
    return true;
  }

  @Override
  protected void move(GameDirection direction) {

    super.move(direction);
    if (this.ghostActive) {
      moveGhosty();
    }
  }

  private void moveGhosty() {

    // TODO
  }

  @Override
  public boolean isAutoPickItem() {

    return true;
  }
}
