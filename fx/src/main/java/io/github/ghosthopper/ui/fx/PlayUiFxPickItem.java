/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.ui.fx;

import io.github.ghosthopper.figure.PlayFigure;
import io.github.ghosthopper.item.PlayPickItem;

/**
 * JavaFx view for a {@link PlayFigure}.
 */
public class PlayUiFxPickItem extends PlayUiFxItem {

  private final PlayPickItem item;

  /**
   * The constructor.
   *
   * @param item the {@link PlayPickItem}.
   * @param fxGame the {@link #getFxParent() parent} {@link PlayUiFxGame game}.
   */
  public PlayUiFxPickItem(PlayPickItem item, PlayUiFxGame fxGame) {
    super(item, fxGame);
    this.item = item;
    fxGame.addFxPickItem(this);
  }

  @Override
  public PlayPickItem getPlayAsset() {

    return this.item;
  }

}
