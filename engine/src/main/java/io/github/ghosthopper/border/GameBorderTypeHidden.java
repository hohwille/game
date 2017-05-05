/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.border;

import io.github.ghosthopper.asset.GameAsset;
import io.github.ghosthopper.type.GameTypeAccess;

/**
 * A {@link GameBorderType} that wraps a {@link #getDelegate() delegate border type} that is hidden until the
 * {@link GameBorder} is {@link GameBorder#pass(GameAsset) passed} for the first time.
 */
public class GameBorderTypeHidden extends GameBorderType {

  private final GameBorderType delegate;

  private boolean hidden;

  /**
   * The constructor.
   *
   * @param delegate - see {@link #getDelegate()}.
   */
  private GameBorderTypeHidden(GameBorderType delegate) {
    super("Hidden");
    this.delegate = delegate;
    this.hidden = true;
  }

  @Override
  public String getId() {

    if (this.hidden) {
      return super.getId();
    }
    return this.delegate.getId();
  }

  /**
   * @return the wrapped {@link GameBorderType}.
   */
  public GameBorderType getDelegate() {

    return this.delegate;
  }

  /**
   * @return {@code true} if this border is hidden, {@code false} otherwise.
   */
  public boolean isHidden() {

    return this.hidden;
  }

  @Override
  public GameTypeAccess getOverlay() {

    if (this.hidden) {
      return super.getOverlay();
    }
    return this.delegate.getOverlay();
  }

  @Override
  public boolean canPass(GameAsset<?> asset, GameBorder border, boolean move) {

    if (this.hidden) {
      this.hidden = false;
      getGame().sendEvent(border);
    }
    return this.delegate.canPass(asset, border, move);
  }

  /**
   * @param type the {@link GameBorderType} to hide.
   * @return an instance of this border type.
   */
  public static final GameBorderTypeHidden get(GameBorderType type) {

    return new GameBorderTypeHidden(type);
  }

}
