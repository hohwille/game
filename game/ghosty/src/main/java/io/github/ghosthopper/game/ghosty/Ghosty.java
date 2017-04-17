package io.github.ghosthopper.game.ghosty;

import java.util.List;

import io.github.ghosthopper.PlayLevel;
import io.github.ghosthopper.border.PlayBorderTypeHole;
import io.github.ghosthopper.color.PlayColor;
import io.github.ghosthopper.field.PlayField;
import io.github.ghosthopper.figure.PlayFigureType;
import io.github.ghosthopper.game.PlayGameSimple;
import io.github.ghosthopper.player.Player;

/**
 * The game <em>ghosty</em>: {@link Player}s of different {@link PlayFigureType}s hunt for the ghost.
 */
public class Ghosty extends PlayGameSimple {

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

  /**
   * The constructor.
   */
  public Ghosty() {
    this(4, 4);
  }

  /**
   * The constructor.
   *
   * @param width the number of {@link PlayField}s in {@link #getDirectionX() x-direction}.
   * @param height the number of {@link PlayField}s in {@link #getDirectionY() y-direction}.
   */
  public Ghosty(int width, int height) {
    super("Ghosty", width, height);
    List<Player> players = getPlayers();
    PlayLevel level = getCurrentLevel();

    Player player1 = new Player(PlayColor.GREEN, FROG);
    players.add(player1);
    player1.getFigures().get(0).setField(level.getField(0, 0));

    Player player2 = new Player(PlayColor.RED, BUG);
    players.add(player2);
    player2.getFigures().get(0).setField(level.getField(width - 1, 0));

    Player player3 = new Player(PlayColor.BLUE, FROG);
    players.add(player3);
    player3.getFigures().get(0).setField(level.getField(width - 1, height - 1));

    Player player4 = new Player(PlayColor.YELLOW, BUG);
    players.add(player4);
    player4.getFigures().get(0).setField(level.getField(0, height - 1));
  }
}
