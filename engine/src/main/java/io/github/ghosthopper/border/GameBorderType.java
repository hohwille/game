package io.github.ghosthopper.border;

import io.github.ghosthopper.asset.GameAsset;
import io.github.ghosthopper.figure.GameFigure;
import io.github.ghosthopper.type.GameTypeBase;

/**
 * The abstract class for the {@link GameBorder#getType() type} of a {@link GameBorder}.
 * {@link GameBorder#canPass(GameAsset)} and {@link GameBorder#pass(GameAsset)} delegate to
 * {@link #canPass(GameAsset, GameBorder, boolean)} what makes the actual decision according to this
 * {@link GameBorderType} and its potential state.
 */
public abstract class GameBorderType extends GameTypeBase {

  /** The {@link #getTypeName() type name}. */
  public static final String TYPE_NAME = "Border";

  /**
   * The constructor.
   *
   * @param id - see {@link #getId()}.
   */
  public GameBorderType(String id) {
    super(id);
  }

  @Override
  public String getTypeName() {

    return TYPE_NAME;
  }

  @Override
  public boolean isMutable() {

    return true;
  }

  /**
   * @see GameBorder#canPass(GameAsset)
   *
   * @param asset the {@link GameFigure}.
   * @param border the {@link GameBorder}.
   * @param move {@code true} if the {@link GameFigure} actually {@link GameBorder#pass(GameAsset) performs the move} if
   *        possible, {@code false} otherwise (only to {@link GameBorder#canPass(GameAsset) check a simulated move}).
   * @return {@code true} if the given {@link GameFigure} can pass this border, {@code false} otherwise.
   */
  public abstract boolean canPass(GameAsset<?> asset, GameBorder border, boolean move);

}
