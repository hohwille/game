/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.ui.fx.data;

import java.util.HashMap;
import java.util.Map;

import io.github.ghosthopper.border.PlayBorderType;
import io.github.ghosthopper.move.PlayDirection;
import io.github.ghosthopper.type.PlayType;
import io.github.ghosthopper.type.PlayTypeAccess;
import javafx.scene.Node;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

/**
 * A container for an {@link Image} with the ability to enhance it.
 */
class PlayUiFxImageContainer {

  private final Image image;

  private final PlayType type;

  private final PlayUiFxDataCache dataCache;

  private Map<PlayUiFxImageKey, Image> colorMap;

  /**
   * The constructor.
   *
   * @param image the original {@link Image}.
   * @param type the {@link PlayType} leading to the {@link Image}.
   * @param dataCache the owning {@link PlayUiFxDataCache} creating this container.
   */
  public PlayUiFxImageContainer(Image image, PlayType type, PlayUiFxDataCache dataCache) {
    super();
    this.image = image;
    this.type = type;
    this.dataCache = dataCache;
  }

  /**
   * @return the image
   */
  public Image getImage() {

    return this.image;
  }

  /**
   * @param direction the optional {@link PlayDirection} to rotate.
   * @param overlay the optional {@link PlayType#getOverlay() overlay}.
   * @return the {@link Image} with optional rotation and overlay.
   */
  public Image getImage(PlayDirection direction, PlayTypeAccess overlay) {

    if ((direction == null) && (overlay == null)) {
      return this.image;
    }
    PlayUiFxImageKey imageKey = new PlayUiFxImageKey(direction, overlay);
    if (this.colorMap == null) {
      this.colorMap = new HashMap<>();
    }
    return this.colorMap.computeIfAbsent(imageKey, this::modify);
  }

  private Image modify(PlayUiFxImageKey key) {

    Node imageTransformator = new ImageView(this.image);
    PlayTypeAccess overlay = key.getOverlay();
    if (overlay != null) {
      Image image2 = this.dataCache.getOverlayImage(overlay);
      if (image2 != null) {
        ImageView imageView = new ImageView(image2);
        double height = Math.min(this.image.getHeight(), this.image.getWidth());
        imageView.setFitWidth(height);
        imageView.setFitHeight(height);
        imageTransformator = new StackPane(imageTransformator, imageView);
      }
    }
    PlayDirection direction = key.getDirection();
    if (direction != null) {
      if (this.type.getTypeName() == PlayBorderType.TYPE_NAME) {
        direction = direction.getInverse();
      }
      double rotation = direction.getRotationZ();
      if (rotation > 0) {
        imageTransformator.setRotate(rotation);
      }
    }
    SnapshotParameters params = new SnapshotParameters();
    params.setFill(Color.TRANSPARENT);
    return imageTransformator.snapshot(params, null);
  }

}
