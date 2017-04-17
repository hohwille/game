/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.ui.fx;

import io.github.ghosthopper.field.PlayField;
import io.github.ghosthopper.figure.PlayFigure;
import javafx.scene.effect.Effect;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

/**
 * JavaFx view for a {@link PlayField}.
 */
public class PlayUiFxField extends StackPane {

  private final PlayField field;

  private final PlayUiFxDataCache dataCache;

  /**
   * The constructor.
   *
   * @param field the {@link PlayField}.
   * @param dataCache the {@link PlayUiFxDataCache}.
   */
  public PlayUiFxField(PlayField field, PlayUiFxDataCache dataCache) {
    super();
    this.field = field;
    this.dataCache = dataCache;
    getStyleClass().add("field");
    Image image = dataCache.getImage(field);
    ImageView imageView = new ImageView(image);
    Effect effect = PlayUiFxColor.getEffect(field.getColor());
    if (effect != null) {
      imageView.setEffect(effect);
    }
    getChildren().add(imageView);
  }

  /**
   * @return the {@link PlayField}.
   */
  public PlayField getPlayField() {

    return this.field;
  }

  /**
   * @param figure the {@link PlayFigure} to add.
   */
  public void addFigure(PlayFigure figure) {

    Image image = this.dataCache.getImage(figure);
    ImageView imageView = new ImageView(image);
    getChildren().add(imageView);
  }

}
