package io.github.ghosthopper;

import io.github.ghosthopper.field.PlayField;
import io.github.ghosthopper.game.PlayGame;
import io.github.ghosthopper.player.Player;

/**
 * The {@link PlayLevel} represents a level or stage of a {@link #getGame() game}. It contains the
 * {@link #getStartField() start field} that is connected with all other {@link PlayField}s.
 */
public class PlayLevel extends PlayObjectWithId {

  private final PlayGame game;

  private final PlayField startField;

  /**
   * The constructor.
   *
   * @param id - see {@link #getId()}.
   * @param game - see {@link #getGame()}.
   */
  public PlayLevel(String id, PlayGame game) {
    super(id);
    this.game = game;
    this.startField = new PlayField(this);
  }

  @Override
  public PlayGame getGame() {

    return this.game;
  }

  /**
   * @return the <em>start</em> {@link PlayField} spanning this {@link PlayLevel}. It is typically the top left field of
   *         the {@link PlayLevel} but may also be any other field. It can be the {@link PlayField} where all
   *         {@link PlayGame#getPlayers() players} start but {@link Player}s might also start somewhere else. From this
   *         {@link PlayField} you can reach all other {@link PlayField}s and navigate the entire {@link PlayLevel} via
   *         {@link PlayField#getField(PlayDirection)}.
   */
  public PlayField getStartField() {

    return this.startField;
  }

  /**
   * Gets the {@link PlayField} at the given coordinate assuming the {@link PlayLevel} is a square rectangular field of
   * at least the given dimension.
   *
   * @param x the number of moves to the {@link PlayDirection#RIGHT right} (the column of the requested
   *        {@link PlayField}).
   * @param y the number of moves {@link PlayDirection#DOWN downwards} (the row of the requested {@link PlayField}).
   * @return the requested {@link PlayField}. May be {@code null}.
   */
  public PlayField getField(int x, int y) {

    return getField(PlayDirection.RIGHT, x, PlayDirection.DOWN, y);
  }

  /**
   * @see #getField(int, int)
   *
   * @param dir1 the first {@link PlayDirection} to move to.
   * @param count1 the number of moves in direction {@code dir1}.
   * @param dir2 the second {@link PlayDirection} to move to.
   * @param count2 the number of moves in direction {@code dir2}.
   * @return the resulting {@link PlayField}.
   */
  public PlayField getField(PlayDirection dir1, int count1, PlayDirection dir2, int count2) {

    PlayField field = this.startField.getField(dir1, count1, false);
    if (field == null) {
      return null;
    }
    return field.getField(dir2, count2, false);
  }

}
