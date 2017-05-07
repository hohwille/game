/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.ui.fx.border;

import io.github.ghosthopper.border.GameBorder;
import io.github.ghosthopper.ui.fx.GameUiFxObject;
import io.github.ghosthopper.ui.fx.game.GameUiFxGame;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

/**
 * JavaFx view for a {@link GameBorder}.
 */
public class GameUiFxBorder extends StackPane implements GameUiFxObject {

  private final GameBorder border;

  private final GameUiFxGame fxGame;

  private final ImageView imageView;

  /**
   * The constructor.
   *
   * @param border the {@link GameBorder}.
   * @param fxGame the {@link #getFxParent() parent} {@link GameUiFxGame game}.
   */
  public GameUiFxBorder(GameBorder border, GameUiFxGame fxGame) {
    super();
    this.border = border;
    this.fxGame = fxGame;
    getStyleClass().add("border");
    Image image = getFxDataCache().getImage(border);
    this.imageView = new ImageView(image);
    getChildren().add(this.imageView);
  }

  @Override
  public GameUiFxObject getFxParent() {

    return this.fxGame;
  }

  /**
   * @return the {@link GameBorder}.
   */
  public GameBorder getGameBorder() {

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
