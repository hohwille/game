/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.ui.fx;

import io.github.ghosthopper.item.PlayItem;

/**
 * JavaFx view for a {@link PlayItem}.
 */
public abstract class PlayUiFxItem extends PlayUiFxAsset {

  private final PlayUiFxGame fxGame;

  /**
   * The constructor.
   *
   * @param item the {@link PlayItem}.
   * @param fxGame the {@link #getFxParent() parent} {@link PlayUiFxGame game}.
   */
  public PlayUiFxItem(PlayItem<?, ?> item, PlayUiFxGame fxGame) {
    super(item, fxGame);
    this.fxGame = fxGame;
  }

  @Override
  public PlayUiFxNode getFxParent() {

    return this.fxGame;
  }

}
