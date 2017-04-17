/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.ui.fx;

import io.github.ghosthopper.figure.PlayFigure;
import javafx.scene.effect.Effect;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * JavaFx view for a {@link PlayFigure}.
 */
public class PlayUiFxFigure extends ImageView {

  private final PlayFigure figure;

  private final PlayUiFxDataCache dataCache;

  private PlayUiFxField field;

  /**
   * The constructor.
   *
   * @param figure the {@link PlayFigure}.
   * @param dataCache the {@link PlayUiFxDataCache}.
   */
  public PlayUiFxFigure(PlayFigure figure, PlayUiFxDataCache dataCache) {
    super();
    this.figure = figure;
    this.dataCache = dataCache;
    Image image = this.dataCache.getImage(figure);
    setImage(image);
    Effect effect = PlayUiFxColor.getEffect(figure.getColor());
    if (effect != null) {
      setEffect(effect);
    }
  }

  /**
   * @return the {@link PlayFigure}.
   */
  public PlayFigure getPlayFigure() {

    return this.figure;
  }

  /**
   * @return the {@link PlayUiFxField} where the {@link PlayFigure} is currently {@link PlayFigure#getField() on}.
   */
  public PlayUiFxField getPlayField() {

    return this.field;
  }

  /**
   * @param newField the new {@link #getPlayField() field} to move this figure to.
   */
  public void setPlayField(PlayUiFxField newField) {

    if (this.field != null) {
      this.field.getChildren().remove(this);
    }
    newField.getChildren().add(this);
    this.field = newField;
    this.figure.setField(newField.getPlayField());
  }

}
