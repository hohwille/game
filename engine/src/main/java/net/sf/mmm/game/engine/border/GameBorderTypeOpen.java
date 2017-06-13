package net.sf.mmm.game.engine.border;

import net.sf.mmm.game.engine.asset.GameAsset;
import net.sf.mmm.game.engine.figure.GameFigure;

/**
 * A {@link GameBorderType} that is an open gate so every {@link GameFigure}
 * {@link #canPass(GameAsset, GameBorder, boolean) can always pass through}.
 */
public class GameBorderTypeOpen extends GameBorderType {

  static final String ID = "Open";

  private static final GameBorderTypeOpen INSTANCE = new GameBorderTypeOpen();

  /**
   * The constructor.
   */
  public GameBorderTypeOpen() {
    super(ID);
  }

  @Override
  public boolean canPass(GameAsset<?> asset, GameBorder border, boolean move) {

    return true;
  }

  /**
   * @return an instance of this border type.
   */
  public static GameBorderTypeOpen get() {

    return INSTANCE;
  }

}
