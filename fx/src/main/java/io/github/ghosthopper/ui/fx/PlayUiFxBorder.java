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
public class PlayUiFxBorder extends StackPane implements PlayUiFxNode {

  private final PlayBorder border;

  private final PlayUiFxGame fxGame;

  private final ImageView imageView;

  /**
   * The constructor.
   *
   * @param border the {@link PlayBorder}.
   * @param fxGame the {@link #getFxParent() parent} {@link PlayUiFxGame game}.
   */
  public PlayUiFxBorder(PlayBorder border, PlayUiFxGame fxGame) {
    super();
    this.border = border;
    this.fxGame = fxGame;
    getStyleClass().add("border");
    Image image = getFxDataCache().getImage(border);
    this.imageView = new ImageView(image);
    getChildren().add(this.imageView);
    this.fxGame.addFxBorder(this);
  }

  @Override
  public PlayUiFxNode getFxParent() {

    return this.fxGame;
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

    Image image = getFxDataCache().getImage(this.border);
    this.imageView.setImage(image);
  }

}
