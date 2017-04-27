/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.ui.fx;

import io.github.ghosthopper.field.PlayField;
import io.github.ghosthopper.item.PlayPickItem;
import io.github.ghosthopper.item.PlayPushItem;
import io.github.ghosthopper.position.PlayPosition;
import javafx.geometry.Pos;
import javafx.scene.effect.Effect;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

/**
 * JavaFx view for a {@link PlayField}.
 */
public class PlayUiFxField extends StackPane implements PlayUiFxNode {

  private final PlayField field;

  private final PlayUiFxLevel level;

  /**
   * The constructor.
   *
   * @param field the {@link PlayField}.
   * @param level the {@link PlayUiFxLevel}.
   */
  public PlayUiFxField(PlayField field, PlayUiFxLevel level) {
    super();
    this.field = field;
    this.level = level;
    getStyleClass().add("field");
    Image image = getFxDataCache().getImage(field);
    ImageView imageView = new ImageView(image);
    Effect effect = PlayUiFxColor.getEffect(field.getColor());
    if (effect != null) {
      imageView.setEffect(effect);
    }
    getChildren().add(imageView);
    initItems();
  }

  private void initItems() {

    PlayUiFxGame fxGame = getFxGame();
    for (PlayPickItem item : this.field.getItems()) {
      PlayUiFxPickItem fxItem = new PlayUiFxPickItem(item, fxGame);
      addFxAsset(fxItem);
    }
    PlayPushItem pushItem = this.field.getPushItem();
    if (pushItem != null) {
      PlayUiFxPushItem fxItem = new PlayUiFxPushItem(pushItem, fxGame);
      addFxAsset(fxItem);
    }
  }

  @Override
  public PlayUiFxLevel getFxParent() {

    return this.level;
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

    PlayPosition position = asset.getPlayAsset().getPosition();
    Pos alignment = getPlayUiFx().getPositionMapper().getFxPosition(position);
    getChildren().add(asset);
    setAlignment(asset, alignment);
  }

}
