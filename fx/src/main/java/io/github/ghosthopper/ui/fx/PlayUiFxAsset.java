/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.ui.fx;

import io.github.ghosthopper.figure.PlayFigure;
import io.github.ghosthopper.object.PlayAsset;
import javafx.scene.effect.Effect;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * JavaFx view for a {@link PlayFigure}.
 */
public abstract class PlayUiFxAsset extends ImageView {

  private final PlayUiFxDataCache dataCache;

  private PlayUiFxField field;

  /**
   * The constructor.
   *
   * @param asset the {@link PlayAsset}.
   * @param dataCache the {@link PlayUiFxDataCache}.
   */
  public PlayUiFxAsset(PlayAsset<?> asset, PlayUiFxDataCache dataCache) {
    super();
    this.dataCache = dataCache;
    Image image = this.dataCache.getImage(asset);
    setImage(image);
    Effect effect = PlayUiFxColor.getEffect(asset.getColor());
    if (effect != null) {
      setEffect(effect);
    }
  }

  /**
   * @return the {@link PlayAsset}.
   */
  public abstract PlayAsset<?> getPlayAsset();

  /**
   * @return the {@link PlayUiFxField} where the {@link PlayAsset} is currently {@link PlayAsset#getLocation() on}.
   */
  public PlayUiFxField getPlayField() {

    return this.field;
  }

  /**
   * @param newField the new {@link #getPlayField() field} to move this figure to.
   */
  public void setPlayField(PlayUiFxField newField) {

    if (this.field != null) {
      this.field.removeFxAsset(this);
    }
    this.field = newField;
    if (this.field != null) {
      assert (getPlayAsset().getLocation() == this.field.getPlayField());
      newField.addFxAsset(this);
    }
  }

}
