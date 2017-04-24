/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.ui.fx;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import io.github.ghosthopper.data.PlayDataKey;
import io.github.ghosthopper.data.PlayDataUtil;
import io.github.ghosthopper.game.PlayGame;
import io.github.ghosthopper.move.PlayDirection;
import io.github.ghosthopper.object.AbstractPlayTypedObject;
import io.github.ghosthopper.object.PlayTypedObject;
import javafx.scene.Node;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

/**
 * A cache for data resources (images and audio).
 */
public class PlayUiFxDataCache {

  private final String gameId;

  private final Map<PlayDataKey, Image> imageMap;

  private final Map<PlayDataKey, URL> urlMap;

  /**
   * The constructor.
   *
   * @param gameId the {@link PlayGame#getId() ID} of the {@link PlayGame}.
   */
  public PlayUiFxDataCache(String gameId) {
    super();
    this.gameId = gameId;
    this.imageMap = new HashMap<>();
    this.urlMap = new HashMap<>();
  }

  /**
   * @param object the {@link PlayTypedObject}.
   * @return the {@link Image} for the given object.
   */
  public Image getImage(PlayTypedObject object) {

    PlayDataKey key = new PlayDataKey(object);
    return getImage(key);
  }

  /**
   * @param key the {@link PlayDataKey}.
   * @return the {@link Image} for the given object.
   */
  public Image getImage(PlayDataKey key) {

    Image image = this.imageMap.get(key);
    if (image == null) {
      URL url = getImageUrl(key);
      image = new Image(url.toString());
      Node imageTransformator = null;
      PlayDataKey overlay = key.getOverlay();
      if (overlay != null) {
        assert (overlay.getOverlay() == null);
        Image image2 = getImage(overlay);
        if (image2 != null) {
          ImageView imageView = new ImageView(image);
          ImageView imageView2 = new ImageView(image2);
          double height = image.getHeight();
          imageView2.setFitWidth(height);
          imageView2.setFitHeight(height);
          imageTransformator = new StackPane(imageView, imageView2);
        }
      }
      PlayDirection direction = key.getDirection();
      if (direction != null) {
        double rotation = direction.getRotationZ() - 180;
        if (rotation < 0) {
          rotation += 360;
        }
        if (rotation > 0) {
          if (imageTransformator == null) {
            imageTransformator = new ImageView(image);
          }
          imageTransformator.setRotate(rotation);
        }
      }
      if (imageTransformator != null) {
        SnapshotParameters params = new SnapshotParameters();
        params.setFill(Color.TRANSPARENT);
        image = imageTransformator.snapshot(params, null);
      }
      this.imageMap.put(key, image);
    }
    return image;
  }

  /**
   * @param object the {@link AbstractPlayTypedObject}.
   * @return the {@link URL} of the image for the given object.
   */
  public URL getImageUrl(AbstractPlayTypedObject object) {

    PlayDataKey key = new PlayDataKey(object);
    return getImageUrl(key);
  }

  private URL getImageUrl(PlayDataKey key) {

    URL url = this.urlMap.get(key);
    if (url == null) {
      url = PlayDataUtil.getImageUrl(this.gameId, key);
      this.urlMap.put(key, url);
    }
    return url;
  }

}
