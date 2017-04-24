/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.ui.fx;

import io.github.ghosthopper.figure.PlayFigure;
import javafx.scene.effect.Effect;
import javafx.scene.effect.Glow;

/**
 * JavaFx view for a {@link PlayFigure}.
 */
public class PlayUiFxFigure extends PlayUiFxAsset {

  private final PlayFigure figure;

  private final Glow glow;

  /**
   * The constructor.
   *
   * @param figure the {@link PlayFigure}.
   * @param dataCache the {@link PlayUiFxDataCache}.
   */
  public PlayUiFxFigure(PlayFigure figure, PlayUiFxDataCache dataCache) {
    super(figure, dataCache);
    this.figure = figure;
    this.glow = new Glow();
    Effect effect = getEffect();
    if (effect != null) {
      this.glow.setInput(effect);
    }
    setEffect(this.glow);
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
    if (selected) {
      this.glow.setLevel(0.8);
    } else {
      this.glow.setLevel(0);
    }
  }

}
