/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.game.fx.data;

import java.util.HashMap;
import java.util.Map;

import javafx.scene.Node;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import net.sf.mmm.game.engine.border.GameBorderType;
import net.sf.mmm.game.engine.direction.GameDirection;
import net.sf.mmm.game.engine.type.GameType;
import net.sf.mmm.game.engine.type.GameTypeAccess;

/**
 * A container for an {@link Image} with the ability to enhance it.
 */
class GameUiFxImageContainer {

  private final Image image;

  private final GameType type;

  private final GameUiFxDataCache dataCache;

  private Map<GameUiFxImageKey, Image> colorMap;

  /**
   * The constructor.
   *
   * @param image the original {@link Image}.
   * @param type the {@link GameType} leading to the {@link Image}.
   * @param dataCache the owning {@link GameUiFxDataCache} creating this container.
   */
  public GameUiFxImageContainer(Image image, GameType type, GameUiFxDataCache dataCache) {
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
   * @param direction the optional {@link GameDirection} to rotate.
   * @param overlay the optional {@link GameType#getOverlay() overlay}.
   * @return the {@link Image} with optional rotation and overlay.
   */
  public Image getImage(GameDirection direction, GameTypeAccess overlay) {

    if ((direction == null) && (overlay == null)) {
      return this.image;
    }
    GameUiFxImageKey imageKey = new GameUiFxImageKey(direction, overlay);
    if (this.colorMap == null) {
      this.colorMap = new HashMap<>();
    }
    return this.colorMap.computeIfAbsent(imageKey, this::modify);
  }

  private Image modify(GameUiFxImageKey key) {

    Node imageTransformator = new ImageView(this.image);
    GameTypeAccess overlay = key.getOverlay();
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
    GameDirection direction = key.getDirection();
    if (direction != null) {
      if (this.type.getTypeName() == GameBorderType.TYPE_NAME) {
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
