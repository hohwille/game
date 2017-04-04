/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import io.github.ghosthopper.PlayDirection;
import io.github.ghosthopper.PlayLevel;
import io.github.ghosthopper.PlayObjectWithId;
import io.github.ghosthopper.Player;
import io.github.ghosthopper.border.PlayBorderType;
import io.github.ghosthopper.field.PlayField;

/**
 * This is the main object and represents an actual game with its rules.
 */
public abstract class PlayGame extends PlayObjectWithId {

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
    this.players = new ArrayList<>();
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
  protected abstract void moveBotPlayer(Player player);

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
  protected abstract PlayLevel createFirstLevel();

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
