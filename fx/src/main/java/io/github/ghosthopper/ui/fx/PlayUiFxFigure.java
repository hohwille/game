/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.ui.fx;

import io.github.ghosthopper.figure.PlayFigure;

/**
 * JavaFx view for a {@link PlayFigure}.
 */
public class PlayUiFxFigure extends PlayUiFxAsset {

  private final PlayFigure figure;

  /**
   * The constructor.
   *
   * @param figure the {@link PlayFigure}.
   * @param dataCache the {@link PlayUiFxDataCache}.
   */
  public PlayUiFxFigure(PlayFigure figure, PlayUiFxDataCache dataCache) {
    super(figure, dataCache);
    this.figure = figure;
  }

  @Override
  public PlayFigure getPlayAsset() {

    return this.figure;
  }

}
