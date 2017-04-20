/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.ui.fx;

import io.github.ghosthopper.figure.PlayFigure;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.Effect;
import javafx.scene.effect.Glow;

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
    update();
  }

  @Override
  public PlayFigure getPlayAsset() {

    return this.figure;
  }

  /**
   * Updates this figure.
   */
  public void update() {

    boolean selected = this.figure.isCurrentFigure();
    Effect effect = getEffect();
    if (selected) {
      Glow glow = new Glow(1);
      if (effect == null) {
        setEffect(glow);
      } else if (effect instanceof ColorAdjust) {
        ((ColorAdjust) effect).setInput(glow);
      }
    } else {
      if (effect instanceof ColorAdjust) {
        ((ColorAdjust) effect).setInput(null);
      } else {
        setEffect(null);
      }
    }
  }

}
