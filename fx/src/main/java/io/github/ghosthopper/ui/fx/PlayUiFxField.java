/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.ui.fx;

import io.github.ghosthopper.field.PlayField;
import javafx.geometry.Pos;
import javafx.scene.effect.Effect;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

/**
 * JavaFx view for a {@link PlayField}.
 */
public class PlayUiFxField extends StackPane {

  private final PlayField field;

  /**
   * The constructor.
   *
   * @param field the {@link PlayField}.
   * @param dataCache the {@link PlayUiFxDataCache}.
   */
  public PlayUiFxField(PlayField field, PlayUiFxDataCache dataCache) {
    super();
    this.field = field;
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
   * @param asset the {@link PlayUiFxAsset} to remove from this field.
   */
  public void removeFxAsset(PlayUiFxAsset asset) {

    getChildren().remove(asset);
  }

  /**
   * @param asset the {@link PlayUiFxAsset} to add to this field.
   */
  public void addFxAsset(PlayUiFxAsset asset) {

    getChildren().add(asset);
    int size = getChildren().size();
    setAlignment(asset, getPosition(size));
  }

  private Pos getPosition(int size) {

    if (size <= 2) {
      return Pos.CENTER;
    }
    switch (size - 3 % 4) {
      case 0:
        return Pos.TOP_LEFT;
      case 1:
        return Pos.TOP_RIGHT;
      case 2:
        return Pos.BOTTOM_RIGHT;
      default :
        return Pos.BOTTOM_LEFT;
    }
  }

}
