package net.sf.mmm.game.engine.border;

import net.sf.mmm.game.engine.asset.GameAsset;
import net.sf.mmm.game.engine.figure.GameFigure;

/**
 * A {@link GameBorderType} that is an open gate so no {@link GameFigure}
 * {@link #canPass(GameAsset, GameBorder, boolean) can ever pass through}.
 */
public class GameBorderTypeWall extends GameBorderType {

  private static final GameBorderTypeWall INSTANCE = new GameBorderTypeWall();

  private GameBorderTypeWall() {
    super("Wall");
  }

  @Override
  public boolean canPass(GameAsset<?> asset, GameBorder border, boolean move) {

    return false;
  }

  /**
   * @return an instance of this border type.
   */
  public static GameBorderTypeWall get() {

    return INSTANCE;
  }

}
