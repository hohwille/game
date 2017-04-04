package io.github.ghosthopper.game.ghosty;

import io.github.ghosthopper.Player;
import io.github.ghosthopper.border.PlayBorderTypeHole;
import io.github.ghosthopper.figure.PlayFigureType;
import io.github.ghosthopper.game.PlayGameSimple;

/**
 * The game <em>ghosty</em>: {@link Player}s of different {@link PlayFigureType}s hunt for the ghost.
 */
public class Ghosty extends PlayGameSimple {

  public static final PlayFigureType MOUSE = new PlayFigureType("Mouse", 'M');

  public static final PlayFigureType RABBIT = new PlayFigureType("Rabbit", 'R');

  public static final PlayFigureType GHOST = new PlayFigureType("Ghost", ' ', true);

  public static final PlayBorderTypeHole MOUSE_HOME = PlayBorderTypeHole.get(MOUSE);

  public static final PlayBorderTypeHole RABBIT_WINDOW = PlayBorderTypeHole.get(RABBIT);

  /**
   * The constructor.
   */
  public Ghosty() {
    this(4, 4);
  }

  /**
   * The constructor.
   *
   * @param width
   * @param height
   */
  public Ghosty(int width, int height) {
    super("Ghosty", width, height);
  }
}
