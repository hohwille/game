package io.github.ghosthopper.border;

import io.github.ghosthopper.asset.PlayAsset;
import io.github.ghosthopper.figure.PlayFigure;

/**
 * A {@link PlayBorderType} that is an open gate so no {@link PlayFigure}
 * {@link #canPass(PlayAsset, PlayBorder, boolean) can ever pass through}.
 */
public class PlayBorderTypeWall extends PlayBorderType {

  private static final PlayBorderTypeWall INSTANCE = new PlayBorderTypeWall();

  private PlayBorderTypeWall() {
    super("Wall");
  }

  @Override
  public boolean canPass(PlayAsset<?> asset, PlayBorder border, boolean move) {

    return false;
  }

  /**
   * @return an instance of this border type.
   */
  public static PlayBorderTypeWall get() {

    return INSTANCE;
  }

}
