/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.ui.fx;

import java.util.Arrays;
import java.util.List;

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

  private static final List<Pos> ALIGNMENTS = Arrays.asList(Pos.CENTER, Pos.TOP_LEFT, Pos.TOP_RIGHT, Pos.BOTTOM_RIGHT, Pos.BOTTOM_LEFT, Pos.CENTER_LEFT,
      Pos.CENTER_RIGHT, Pos.TOP_CENTER, Pos.BOTTOM_CENTER);

  private final PlayField field;

  private final int[] alignmentCounts;

  /**
   * The constructor.
   *
   * @param field the {@link PlayField}.
   * @param dataCache the {@link PlayUiFxDataCache}.
   */
  public PlayUiFxField(PlayField field, PlayUiFxDataCache dataCache) {
    super();
    this.field = field;
    this.alignmentCounts = new int[ALIGNMENTS.size()];
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

    Pos alignment = getAlignment(asset);
    if (alignment != null) {
      int index = ALIGNMENTS.indexOf(alignment);
      if ((index >= 0) && (this.alignmentCounts[index] > 0)) {
        this.alignmentCounts[index]--;
      }
    }
    getChildren().remove(asset);
  }

  /**
   * @param asset the {@link PlayUiFxAsset} to add to this field.
   */
  public void addFxAsset(PlayUiFxAsset asset) {

    int index = findMinimumAlignment();
    this.alignmentCounts[index]++;
    Pos alignment = ALIGNMENTS.get(index);
    getChildren().add(asset);
    setAlignment(asset, alignment);
  }

  private int findMinimumAlignment() {

    int minAlignment = this.alignmentCounts[0];
    if (minAlignment == 0) {
      return 0;
    }
    int index = 0;
    for (int i = 1; i < this.alignmentCounts.length; i++) {
      if (this.alignmentCounts[i] < minAlignment) {
        index = i;
        minAlignment = this.alignmentCounts[i];
      }
    }
    return index;
  }

}
