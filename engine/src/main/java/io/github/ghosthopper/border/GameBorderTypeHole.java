package io.github.ghosthopper.border;

import io.github.ghosthopper.asset.GameAsset;
import io.github.ghosthopper.figure.GameFigure;
import io.github.ghosthopper.figure.GameFigureType;
import io.github.ghosthopper.item.GamePickItem;
import io.github.ghosthopper.type.GameTypeAccess;

/**
 * A {@link GameBorderType} that is a hole where only {@link GameFigure}s of specific {@link GameFigure#getType() types}
 * {@link #canPass(GameAsset, GameBorder, boolean) can pass through}.
 */
public class GameBorderTypeHole extends GameBorderType {

  private final GameFigureType figureType;

  private GameBorderTypeHole(GameFigureType figureType) {
    super("Hole");
    this.figureType = figureType;
  }

  @Override
  public boolean canPass(GameAsset<?> asset, GameBorder border, boolean move) {

    if (asset instanceof GamePickItem) {
      return true;
    }
    return (this.figureType == asset.getType());
  }

  @Override
  public GameTypeAccess getOverlay() {

    return this.figureType;
  }

  /**
   * @param figureType the {@link GameFigureType} that is allowed to {@link #canPass(GameAsset, GameBorder, boolean)
   *        pass through}.
   * @return an instance of this border type.
   */
  public static final GameBorderTypeHole get(GameFigureType figureType) {

    return new GameBorderTypeHole(figureType);
  }

}
