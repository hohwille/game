/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.ui.fx.field;

import io.github.ghosthopper.field.GameField;
import io.github.ghosthopper.item.GamePickItem;
import io.github.ghosthopper.item.GamePushItem;
import io.github.ghosthopper.position.GamePosition;
import io.github.ghosthopper.ui.fx.GameUiFxColor;
import io.github.ghosthopper.ui.fx.GameUiFxObject;
import io.github.ghosthopper.ui.fx.asset.GameUiFxAsset;
import io.github.ghosthopper.ui.fx.game.GameUiFxGame;
import io.github.ghosthopper.ui.fx.item.GameUiFxPickItem;
import io.github.ghosthopper.ui.fx.item.GameUiFxPushItem;
import io.github.ghosthopper.ui.fx.level.GameUiFxLevel;
import javafx.geometry.Pos;
import javafx.scene.effect.Effect;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

/**
 * JavaFx view for a {@link GameField}.
 */
public class GameUiFxField extends StackPane implements GameUiFxObject {

  private final GameField field;

  private final GameUiFxLevel level;

  /**
   * The constructor.
   *
   * @param field the {@link GameField}.
   * @param level the {@link GameUiFxLevel}.
   */
  public GameUiFxField(GameField field, GameUiFxLevel level) {
    super();
    this.field = field;
    this.level = level;
    getStyleClass().add("field");
    Image image = getFxDataCache().getImage(field);
    ImageView imageView = new ImageView(image);
    Effect effect = GameUiFxColor.getEffect(field.getColor());
    if (effect != null) {
      imageView.setEffect(effect);
    }
    getChildren().add(imageView);
    initItems();
  }

  private void initItems() {

    GameUiFxGame fxGame = getFxGame();
    for (GamePickItem item : this.field.getItems()) {
      GameUiFxPickItem fxItem = fxGame.getFxPickItem(item);
      addFxAsset(fxItem);
    }
    GamePushItem pushItem = this.field.getPushItem();
    if (pushItem != null) {
      GameUiFxPushItem fxItem = fxGame.getFxPushItem(pushItem);
      addFxAsset(fxItem);
    }
  }

  @Override
  public GameUiFxLevel getFxParent() {

    return this.level;
  }

  /**
   * @return the {@link GameField}.
   */
  public GameField getGameField() {

    return this.field;
  }

  /**
   * @param asset the {@link GameUiFxAsset} to remove from this field.
   */
  public void removeFxAsset(GameUiFxAsset asset) {

    getChildren().remove(asset);
  }

  /**
   * @param asset the {@link GameUiFxAsset} to add to this field.
   */
  public void addFxAsset(GameUiFxAsset asset) {

    getChildren().add(asset);
    updateGamePosition(asset);
  }

  /**
   * @param asset the {@link GameUiFxAsset} to update.
   */
  public void updateGamePosition(GameUiFxAsset asset) {

    GamePosition position = asset.getGameAsset().getPosition();
    Pos alignment = getFxUi().getFxPositionMapper().getFxPosition(position);
    setAlignment(asset, alignment);
  }

}
