/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.game.fx.field;

import javafx.geometry.Pos;
import javafx.scene.effect.Effect;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import net.sf.mmm.game.engine.field.GameField;
import net.sf.mmm.game.engine.item.GamePickItem;
import net.sf.mmm.game.engine.item.GamePushItem;
import net.sf.mmm.game.engine.position.GamePosition;
import net.sf.mmm.game.fx.GameUiFxColor;
import net.sf.mmm.game.fx.GameUiFxGame;
import net.sf.mmm.game.fx.GameUiFxObject;
import net.sf.mmm.game.fx.asset.GameUiFxAsset;
import net.sf.mmm.game.fx.item.GameUiFxPickItem;
import net.sf.mmm.game.fx.item.GameUiFxPushItem;
import net.sf.mmm.game.fx.level.GameUiFxLevel;

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
