/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.ui.fx;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import io.github.ghosthopper.data.PlayDataKey;
import io.github.ghosthopper.data.PlayDataUtil;
import io.github.ghosthopper.game.PlayGame;
import io.github.ghosthopper.object.PlayTypedObject;
import javafx.scene.image.Image;

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
      this.imageMap.put(key, image);
    }
    return image;
  }

  /**
   * @param object the {@link PlayTypedObject}.
   * @return the {@link URL} of the image for the given object.
   */
  public URL getImageUrl(PlayTypedObject object) {

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
