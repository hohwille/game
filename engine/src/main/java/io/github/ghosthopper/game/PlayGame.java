/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import io.github.ghosthopper.PlayLevel;
import io.github.ghosthopper.border.PlayBorder;
import io.github.ghosthopper.border.PlayBorderType;
import io.github.ghosthopper.field.PlayField;
import io.github.ghosthopper.field.PlayFieldType;
import io.github.ghosthopper.figure.PlayFigure;
import io.github.ghosthopper.figure.PlayFigureType;
import io.github.ghosthopper.i18n.PlayTranslator;
import io.github.ghosthopper.item.PlayItem;
import io.github.ghosthopper.item.PlayItemType;
import io.github.ghosthopper.move.PlayDirection;
import io.github.ghosthopper.object.PlayStateObjectWithId;
import io.github.ghosthopper.player.Player;

/**
 * This is the main object and represents an actual game with its rules. To implement your own game, simply extend this
 * class and implement/override according methods. You can e.g.
 * <ul>
 * <li>Decide for {@link #isTurnGame() turn game} (default) or action game.</li>
 * <li>Define custom {@link PlayFigureType}s and reuse predefined ones.</li>
 * <li>Define custom {@link PlayItemType}s and reuse predefined ones.</li>
 * <li>Define custom {@link PlayFieldType}s and reuse predefined ones.</li>
 * <li>{@link #getPlayers() Predefine} the {@link Player}s with their {@link PlayFigure}s based on the above types.</li>
 * <li>{@link #createDirections() Define} custom {@link PlayDirection}s or use the predefined ones (see
 * {@link #isSupportingDiagonalDirections()}).</li>
 * <li>{@link #createFirstLevel() create} {@link PlayLevel}s with all the {@link PlayField}s typically as
 * {@link #initLevelAsRectangular(PlayLevel, PlayDirection, int, PlayDirection, int, PlayBorderTypeStrategy) rectangular
 * field}.</li>
 * <li>Customize the {@link PlayBorder}s to create complex puzzle/riddle games.</li>
 * <li>Place {@link PlayItem}s and {@link PlayFigure}s on the {@link PlayField}s.</li>
 * <li>Implement a strategy to {@link #moveBotPlayer(Player) move bot players}.</li>
 * </ul>
 */
public class PlayGame extends PlayStateObjectWithId {

  /** @see #getCurrentGame() */
  private static PlayGame currentGame = PlayGameNone.INSTANCE;

  private final PlayTranslator translator;

  private final List<Player> players;

  private int currentPlayer;

  private PlayLevel currentLevel;

  private Set<PlayDirection> directions;

  /**
   * The constructor.
   *
   * @param id - see {@link #getId()}.
   */
  public PlayGame(String id) {
    super(id);
    this.translator = new PlayTranslator(this);
    this.players = new ArrayList<>();
  }

  /**
   * @return the currentGame
   */
  public static PlayGame getCurrentGame() {

    return currentGame;
  }

  @Override
  public PlayGame getGame() {

    return this;
  }

  /**
   * Starts this {@link PlayGame}.
   */
  public final void start() {

    currentGame = this;
  }

  /**
   * Ends this {@link PlayGame}.
   */
  public final void end() {

    currentGame = PlayGameNone.INSTANCE;
  }

  /**
   * Loads the current game from the disc.
   */
  public void load() {

    // TODO
  }

  /**
   * Save the current game to the disc.
   */
  public void save() {

    // TODO
  }

  /**
   * @return the {@link PlayTranslator} used for localization.
   */
  public PlayTranslator getTranslator() {

    return this.translator;
  }

  /**
   * @return {@code true} if this game is played in turns (rounds), where only one {@link Player} can act at a time (for
   *         strategic games like chess, Ludo, etc.), {@code false} if all {@link Player}s can play in parallel (for
   *         action games where speed matters).
   */
  public boolean isTurnGame() {

    return true;
  }

  /**
   * @return the players
   */
  public List<Player> getPlayers() {

    return this.players;
  }

  /**
   * @return the {@link Player} who is currently on turn.
   */
  public Player getCurrentPlayer() {

    return this.players.get(this.currentPlayer);
  }

  /**
   * Ends the turn of the {@link #getCurrentPlayer() current} {@link Player#isHuman() human} {@link Player} and moves
   * all following computer {@link Player}s (bots).
   *
   * @return the next {@link Player#isHuman() human} {@link Player} who is now on turn.
   */
  public Player nextPlayer() {

    this.currentPlayer++;
    if (this.currentPlayer > this.players.size()) {
      this.currentPlayer = 0;
    }
    Player player = getCurrentPlayer();
    if (!player.isHuman()) {
      moveBotPlayer(player);
    }
    return player;
  }

  /**
   * @param player the non-{@link Player#isHuman() human} {@link Player} to move.
   */
  protected void moveBotPlayer(Player player) {

    // should be overridden if bot players are supported by game.
  }

  /**
   * @return the current {@link PlayLevel}.
   */
  public PlayLevel getCurrentLevel() {

    if (this.currentLevel == null) {
      this.currentLevel = initLevels();
    }
    return this.currentLevel;
  }

  /**
   * @return the initial {@link PlayLevel}.
   */
  private PlayLevel initLevels() {

    PlayLevel level = createFirstLevel();
    return level;
  }

  /**
   * @return creates the first (and potentially single) {@link PlayLevel}.
   */
  protected PlayLevel createFirstLevel() {

    return new PlayLevel("Level 1", this);
  }

  /**
   * @param level the {@link PlayLevel} to initialize.
   * @param xDir the first {@link PlayDirection} (x-Axis).
   * @param width the number of {@link PlayField}s in {@code xDir}.
   * @param yDir the second {@link PlayDirection} (y-Axis).
   * @param height the number of {@link PlayField}s in {@code yDir}.
   * @param borderStrategy the {@link PlayBorderTypeStrategy}.
   * @return the given {@link PlayLevel} after initialization.
   */
  protected PlayLevel initLevelAsRectangular(PlayLevel level, PlayDirection xDir, int width, PlayDirection yDir, int height,
      PlayBorderTypeStrategy borderStrategy) {

    assert level.getGame() == this;
    PlayDirection xDirInverse = xDir.getInverse();
    PlayDirection yDirInverse = yDir.getInverse();
    PlayField startField = level.getStartField();
    startField.initEdge();
    PlayField xStartField = startField;
    for (int y = 1; y <= height; y++) {
      startField.createWall(xDirInverse);
      PlayField field = xStartField;
      for (int x = 1; x <= width; x++) {
        if (y == 1) {
          field.createWall(yDirInverse);
        }
        if (y == height) {
          field.createWall(yDir);
        } else {
          PlayBorderType type = borderStrategy.getType(field, x, y, width, height, yDir);
          field.createBorder(type, yDir);
        }
        if (x == width) {
          field.createWall(xDir);
        } else {
          PlayBorderType type = borderStrategy.getType(field, x, y, width, height, xDir);
          if (y == 1) {
            field = field.createBorder(type, xDir);
          } else {
            PlayField targetField = field.getField(yDirInverse).getField(xDir).getField(yDir);
            field.createBorder(type, xDir, targetField);
          }
        }
      }
      xStartField = xStartField.getField(yDir);
    }
    return level;
  }

  /**
   * @return the {@link PlayDirection}s supported by this {@link PlayGame}.
   */
  public Set<PlayDirection> getDirections() {

    if (this.directions == null) {
      this.directions = Collections.unmodifiableSet(createDirections());
    }
    return this.directions;
  }

  /**
   * @return the {@link #getDirections() directions}.
   */
  protected Set<PlayDirection> createDirections() {

    Set<PlayDirection> set = new HashSet<>();
    set.add(PlayDirection.UP);
    set.add(PlayDirection.DOWN);
    set.add(PlayDirection.LEFT);
    set.add(PlayDirection.RIGHT);
    if (isSupportingDiagonalDirections()) {
      set.add(PlayDirection.UP_LEFT);
      set.add(PlayDirection.UP_RIGHT);
      set.add(PlayDirection.DOWN_LEFT);
      set.add(PlayDirection.DOWN_RIGHT);
    }
    return set;
  }

  /**
   * @return {@code true} if also the four diagonal {@link PlayDirection}s such as {@link PlayDirection#UP_LEFT} are
   *         supported (e.g. for chess).
   */
  protected boolean isSupportingDiagonalDirections() {

    return false;
  }

}
