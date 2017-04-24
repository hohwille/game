package io.github.ghosthopper.border;

import io.github.ghosthopper.asset.PlayAsset;
import io.github.ghosthopper.figure.PlayFigure;

/**
 * A {@link PlayBorderType} that is an open gate so every {@link PlayFigure}
 * {@link #canPass(PlayAsset, PlayBorder, boolean) can always pass through}.
 */
public class PlayBorderTypeOpen extends PlayBorderType {

  static final String ID = "Open";

  private static final PlayBorderTypeOpen INSTANCE = new PlayBorderTypeOpen();

  /**
   * The constructor.
   */
  public PlayBorderTypeOpen() {
    super(ID);
  }

  @Override
  public boolean canPass(PlayAsset<?> asset, PlayBorder border, boolean move) {

    return true;
  }

  /**
   * @return an instance of this border type.
   */
  public static PlayBorderTypeOpen get() {

    return INSTANCE;
  }

}
