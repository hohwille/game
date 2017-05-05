package io.github.ghosthopper.level;

import io.github.ghosthopper.direction.GameDirection;
import io.github.ghosthopper.field.GameField;
import io.github.ghosthopper.game.Game;
import io.github.ghosthopper.object.GameStateObjectWithId;
import io.github.ghosthopper.player.GamePlayer;

/**
 * The {@link GameLevel} represents a level or stage of a {@link #getGame() game}. It contains the
 * {@link #getStartField() start field} that is connected with all other {@link GameField}s.
 */
public class GameLevel extends GameStateObjectWithId {

  private final Game game;

  private final GameField startField;

  /**
   * The constructor.
   *
   * @param id - see {@link #getId()}.
   * @param game - see {@link #getGame()}.
   */
  public GameLevel(String id, Game game) {
    super(id);
    this.game = game;
    this.startField = new GameField(this);
  }

  @Override
  public Game getGame() {

    return this.game;
  }

  /**
   * @return the <em>start</em> {@link GameField} spanning this {@link GameLevel}. It is typically the top left field of
   *         the {@link GameLevel} but may also be any other field. It can be the {@link GameField} where all
   *         {@link Game#getPlayers() players} start but {@link GamePlayer}s might also start somewhere else. From this
   *         {@link GameField} you can reach all other {@link GameField}s and navigate the entire {@link GameLevel} via
   *         {@link GameField#getField(GameDirection)}.
   */
  public GameField getStartField() {

    return this.startField;
  }

  /**
   * Gets the {@link GameField} at the given coordinate assuming the {@link GameLevel} is a square rectangular field of
   * at least the given dimension.
   *
   * @param x the number of moves to the {@link GameDirection#EAST right} (the column of the requested
   *        {@link GameField}).
   * @param y the number of moves {@link GameDirection#SOUTH downwards} (the row of the requested {@link GameField}).
   * @return the requested {@link GameField}. May be {@code null}.
   */
  public GameField getField(int x, int y) {

    return getField(GameDirection.EAST, x, GameDirection.SOUTH, y);
  }

  /**
   * @see #getField(int, int)
   *
   * @param dir1 the first {@link GameDirection} to move to.
   * @param count1 the number of moves in direction {@code dir1}.
   * @param dir2 the second {@link GameDirection} to move to.
   * @param count2 the number of moves in direction {@code dir2}.
   * @return the resulting {@link GameField}.
   */
  public GameField getField(GameDirection dir1, int count1, GameDirection dir2, int count2) {

    GameField field = this.startField.getField(dir1, count1, false);
    if (field == null) {
      return null;
    }
    return field.getField(dir2, count2, false);
  }

}
