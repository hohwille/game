/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.ui.fx.data;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import io.github.ghosthopper.data.GameDataUtil;
import io.github.ghosthopper.data.GameView;
import io.github.ghosthopper.direction.GameAttributeDirection;
import io.github.ghosthopper.direction.GameDirection;
import io.github.ghosthopper.game.Game;
import io.github.ghosthopper.object.GameTypedObject;
import io.github.ghosthopper.type.GameType;
import io.github.ghosthopper.type.GameTypeAccess;
import io.github.ghosthopper.type.GameTypeGeneric;
import javafx.scene.image.Image;

/**
 * A cache for data resources (images and audio).
 */
public class GameUiFxDataCache {

  private final String gameId;

  private final GameView view;

  private final Map<GameType, GameUiFxImageContainer> imageMap;

  private final Map<GameType, URL> urlMap;

  /**
   * The constructor.
   *
   * @param gameId the {@link Game#getId() ID} of the {@link Game}.
   * @param view the {@link GameView}.
   */
  public GameUiFxDataCache(String gameId, GameView view) {
    super();
    this.gameId = gameId;
    this.view = view;
    this.imageMap = new HashMap<>();
    this.urlMap = new HashMap<>();
  }

  /**
   * @param object the {@link GameTypedObject}.
   * @return the {@link Image} for the given object.
   */
  public Image getImage(GameTypedObject object) {

    GameType type = object.getType();
    GameUiFxImageContainer container = getImageContainer(type);
    GameDirection direction = null;
    if (object instanceof GameAttributeDirection) {
      direction = ((GameAttributeDirection) object).getDirection();
    }
    return container.getImage(direction, type.getOverlay());
  }

  /**
   * @param type the {@link GameType}.
   * @return the {@link Image} for the given type.
   */
  public Image getImage(GameType type) {

    return getImageContainer(type).getImage();
  }

  /**
   * @see GameType#getOverlay()
   * @param typeAccess the {@link GameTypeAccess}.
   * @return the {@link Image} for the given object.
   */
  public Image getOverlayImage(GameTypeAccess typeAccess) {

    Objects.requireNonNull(typeAccess, "typeAccess");
    if (typeAccess instanceof GameTypedObject) {
      GameTypedObject typedObject = (GameTypedObject) typeAccess;
      assert (typedObject.getType().getOverlay() == null);
      return getImage(typedObject);
    } else if (typeAccess instanceof GameType) {
      GameType type = (GameType) typeAccess;
      assert (type.getOverlay() == null);
      return getImage(type);
    } else {
      throw new IllegalArgumentException(typeAccess.getClass().getName());
    }
  }

  private GameUiFxImageContainer getImageContainer(GameType type) {

    GameType key;
    if (type.isMutable()) {
      key = new GameTypeGeneric(type);
    } else {
      key = type;
    }
    GameUiFxImageContainer imageContainer = this.imageMap.get(key);
    if (imageContainer == null) {
      URL url = getImageUrl(key);
      Image image = new Image(url.toString());
      imageContainer = new GameUiFxImageContainer(image, key, this);
      this.imageMap.put(key, imageContainer);
    }
    return imageContainer;
  }

  /**
   * @param type the {@link GameType}.
   * @return the {@link URL} of the image for the given type.
   */
  public URL getImageUrl(GameType type) {

    URL url = this.urlMap.get(type);
    if (url == null) {
      url = GameDataUtil.getImageUrl(this.view, this.gameId, type);
      this.urlMap.put(type, url);
    }
    return url;
  }

}
