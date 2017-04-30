/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.border;

import io.github.ghosthopper.asset.PlayAsset;
import io.github.ghosthopper.object.PlayStateObjectBase;

/**
 * A {@link PlayBorderType} that wraps a {@link #getDelegate() delegate border type} that is hidden until the
 * {@link PlayBorder} is {@link PlayBorder#pass(PlayAsset) passed} for the first time.
 */
public class PlayBorderTypeHidden extends PlayBorderType {

  private final PlayBorderType delegate;

  private boolean hidden;

  /**
   * The constructor.
   *
   * @param delegate - see {@link #getDelegate()}.
   */
  private PlayBorderTypeHidden(PlayBorderType delegate) {
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
   * @return the wrapped {@link PlayBorderType}.
   */
  public PlayBorderType getDelegate() {

    return this.delegate;
  }

  /**
   * @return {@code true} if this border is hidden, {@code false} otherwise.
   */
  public boolean isHidden() {

    return this.hidden;
  }

  @Override
  public PlayStateObjectBase getOverlay() {

    if (this.hidden) {
      return super.getOverlay();
    }
    return this.delegate.getOverlay();
  }

  @Override
  public boolean canPass(PlayAsset<?> asset, PlayBorder border, boolean move) {

    if (this.hidden) {
      this.hidden = false;
      getGame().sendEvent(border);
    }
    return this.delegate.canPass(asset, border, move);
  }

  /**
   * @param type the {@link PlayBorderType} to hide.
   * @return an instance of this border type.
   */
  public static final PlayBorderTypeHidden get(PlayBorderType type) {

    return new PlayBorderTypeHidden(type);
  }

}
