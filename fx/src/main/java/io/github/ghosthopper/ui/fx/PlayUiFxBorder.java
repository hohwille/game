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

  private final PlayUiFxDataCache dataCache;

  private final ImageView imageView;

  /**
   * The constructor.
   *
   * @param border the {@link PlayBorder}.
   * @param dataCache the {@link PlayUiFxDataCache}.
   */
  public PlayUiFxBorder(PlayBorder border, PlayUiFxDataCache dataCache) {
    super();
    this.border = border;
    this.dataCache = dataCache;
    getStyleClass().add("border");
    Image image = dataCache.getImage(border);
    this.imageView = new ImageView(image);
    getChildren().add(this.imageView);
  }

  /**
   * @return the {@link PlayBorder}.
   */
  public PlayBorder getPlayBorder() {

    return this.border;
  }

  /**
   * Updates this border.
   */
  public void update() {

    Image image = this.dataCache.getImage(this.border);
    this.imageView.setImage(image);
  }

}
