/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.ui.fx;

import io.github.ghosthopper.border.PlayBorder;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

/**
 * JavaFx view for a {@link PlayBorder}.
 */
public class PlayUiFxBorder extends StackPane {

  private final PlayBorder border;

  /**
   * The constructor.
   *
   * @param border the {@link PlayBorder}.
   * @param dataCache the {@link PlayUiFxDataCache}.
   */
  public PlayUiFxBorder(PlayBorder border, PlayUiFxDataCache dataCache) {
    super();
    this.border = border;
    getStyleClass().add("border");
    Image image = dataCache.getImage(border);
    ImageView imageView = new ImageView(image);
    getChildren().add(imageView);
  }

  /**
   * @return the {@link PlayBorder}.
   */
  public PlayBorder getPlayBorder() {

    return this.border;
  }

}
