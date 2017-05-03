/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.ui.fx.data;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import io.github.ghosthopper.data.PlayDataUtil;
import io.github.ghosthopper.data.PlayView;
import io.github.ghosthopper.game.PlayGame;
import io.github.ghosthopper.move.PlayAttributeDirection;
import io.github.ghosthopper.move.PlayDirection;
import io.github.ghosthopper.object.PlayTypedObject;
import io.github.ghosthopper.type.PlayType;
import io.github.ghosthopper.type.PlayTypeAccess;
import io.github.ghosthopper.type.PlayTypeGeneric;
import javafx.scene.image.Image;

/**
 * A cache for data resources (images and audio).
 */
public class PlayUiFxDataCache {

  private final String gameId;

  private final PlayView view;

  private final Map<PlayType, PlayUiFxImageContainer> imageMap;

  private final Map<PlayType, URL> urlMap;

  /**
   * The constructor.
   *
   * @param gameId the {@link PlayGame#getId() ID} of the {@link PlayGame}.
   * @param view the {@link PlayView}.
   */
  public PlayUiFxDataCache(String gameId, PlayView view) {
    super();
    this.gameId = gameId;
    this.view = view;
    this.imageMap = new HashMap<>();
    this.urlMap = new HashMap<>();
  }

  /**
   * @param object the {@link PlayTypedObject}.
   * @return the {@link Image} for the given object.
   */
  public Image getImage(PlayTypedObject object) {

    PlayType type = object.getType();
    PlayUiFxImageContainer container = getImageContainer(type);
    PlayDirection direction = null;
    if (object instanceof PlayAttributeDirection) {
      direction = ((PlayAttributeDirection) object).getDirection();
    }
    return container.getImage(direction, type.getOverlay());
  }

  /**
   * @param type the {@link PlayType}.
   * @return the {@link Image} for the given type.
   */
  public Image getImage(PlayType type) {

    return getImageContainer(type).getImage();
  }

  /**
   * @see PlayType#getOverlay()
   * @param typeAccess the {@link PlayTypeAccess}.
   * @return the {@link Image} for the given object.
   */
  public Image getOverlayImage(PlayTypeAccess typeAccess) {

    Objects.requireNonNull(typeAccess, "typeAccess");
    if (typeAccess instanceof PlayTypedObject) {
      PlayTypedObject typedObject = (PlayTypedObject) typeAccess;
      assert (typedObject.getType().getOverlay() == null);
      return getImage(typedObject);
    } else if (typeAccess instanceof PlayType) {
      PlayType type = (PlayType) typeAccess;
      assert (type.getOverlay() == null);
      return getImage(type);
    } else {
      throw new IllegalArgumentException(typeAccess.getClass().getName());
    }
  }

  private PlayUiFxImageContainer getImageContainer(PlayType type) {

    PlayType key;
    if (type.isMutable()) {
      key = new PlayTypeGeneric(type);
    } else {
      key = type;
    }
    PlayUiFxImageContainer imageContainer = this.imageMap.get(key);
    if (imageContainer == null) {
      URL url = getImageUrl(key);
      Image image = new Image(url.toString());
      imageContainer = new PlayUiFxImageContainer(image, key, this);
      this.imageMap.put(key, imageContainer);
    }
    return imageContainer;
  }

  /**
   * @param type the {@link PlayType}.
   * @return the {@link URL} of the image for the given type.
   */
  public URL getImageUrl(PlayType type) {

    URL url = this.urlMap.get(type);
    if (url == null) {
      url = PlayDataUtil.getImageUrl(this.view, this.gameId, type);
      this.urlMap.put(type, url);
    }
    return url;
  }

}
