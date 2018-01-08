package net.sf.mmm.game.engine.border;

import net.sf.mmm.game.engine.asset.GameAsset;
import net.sf.mmm.game.engine.type.GameTypeBase;

/**
 * The abstract class for the {@link GameBorder#getType() type} of a {@link GameBorder}.
 * {@link GameBorder#isPassable(GameAsset)} and {@link GameBorder#pass(GameAsset)} delegate to
 * {@link #isPassable(GameAsset, boolean, GameBorder)} what makes the actual decision according to this
 * {@link GameBorderType} and its potential state.
 */
public abstract class GameBorderType extends GameTypeBase implements GameAttributePassable {

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

  @Override
  public boolean isPassable(GameAsset<?> asset, boolean move) {

    return isPassable(asset, move, null);
  }

  /**
   * @see GameBorder#isPassable(GameAsset)
   * @see GameBorder#pass(GameAsset)
   *
   * @param asset the {@link GameAsset} that will potentially pass this object. Typically a
   *        {@link net.sf.mmm.game.engine.figure.GameFigure}.
   * @param move {@code true} if the {@link GameAsset} actually {@link #pass(GameAsset) performs the move to enter} if
   *        possible, {@code false} otherwise (only to {@link #isPassable(GameAsset) check a simulated entering}).
   * @param border the {@link GameBorder} to pass.
   * @return {@code true} if this object (or objects of this type) can be passed by the given {@link GameAsset},
   *         {@code false} otherwise.
   */
  public abstract boolean isPassable(GameAsset<?> asset, boolean move, GameBorder border);

}
