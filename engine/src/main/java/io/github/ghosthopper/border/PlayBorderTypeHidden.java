/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.border;

import io.github.ghosthopper.figure.PlayFigure;

/**
 * A {@link PlayBorderType} that wraps a {@link #getDelegate() delegate border type} that is hidden until the
 * {@link PlayBorder} is {@link PlayBorder#pass(PlayFigure) passed} for the first time.
 */
public class PlayBorderTypeHidden extends PlayBorderType {

  private final PlayBorderType delegate;

  private boolean hidden;

  /**
   * The constructor.
   *
   * @param delegate - see {@link #getDelegate()}.
   */
  public PlayBorderTypeHidden(PlayBorderType delegate) {
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
  public boolean canPass(PlayFigure figure, PlayBorder border, boolean move) {

    if (this.hidden) {
      this.hidden = false;
    }
    return this.delegate.canPass(figure, border, move);
  }

}
