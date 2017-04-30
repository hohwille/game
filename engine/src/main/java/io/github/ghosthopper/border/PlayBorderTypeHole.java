package io.github.ghosthopper.border;

import io.github.ghosthopper.asset.PlayAsset;
import io.github.ghosthopper.figure.PlayFigure;
import io.github.ghosthopper.figure.PlayFigureType;
import io.github.ghosthopper.item.PlayPickItem;
import io.github.ghosthopper.object.PlayStateObjectBase;

/**
 * A {@link PlayBorderType} that is a hole where only {@link PlayFigure}s of specific {@link PlayFigure#getType() types}
 * {@link #canPass(PlayAsset, PlayBorder, boolean) can pass through}.
 */
public class PlayBorderTypeHole extends PlayBorderType {

  private final PlayFigureType figureType;

  private PlayBorderTypeHole(PlayFigureType figureType) {
    super("Hole");
    this.figureType = figureType;
  }

  @Override
  public boolean canPass(PlayAsset<?> asset, PlayBorder border, boolean move) {

    if (asset instanceof PlayPickItem) {
      return true;
    }
    return (this.figureType == asset.getType());
  }

  @Override
  public PlayStateObjectBase getOverlay() {

    return this.figureType;
  }

  /**
   * @param figureType the {@link PlayFigureType} that is allowed to {@link #canPass(PlayAsset, PlayBorder, boolean)
   *        pass through}.
   * @return an instance of this border type.
   */
  public static final PlayBorderTypeHole get(PlayFigureType figureType) {

    return new PlayBorderTypeHole(figureType);
  }

}
