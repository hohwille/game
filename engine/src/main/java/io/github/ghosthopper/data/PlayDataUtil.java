/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.data;

import java.net.URL;
import java.util.logging.Logger;

import io.github.ghosthopper.game.PlayGame;

/**
 * A utility class that gives access to data resources such as {@link #getImageUrl(PlayView, String, PlayDataKey) image}
 * or {@link #getAudioUrl(String, PlayDataKey) audio} files.
 */
public final class PlayDataUtil {

  private static final Logger LOG = Logger.getLogger(PlayDataUtil.class.getName());

  private static final String DATA_PACKAGE = "game/data/";

  private static final String FOLDER_ROOT = "Root";

  private static final String FOLDER_AUDIO = "Audio";

  private static final String IMG_EXTENSION = ".png";

  private static final String AUDIO_EXTENSION = ".mp3";

  private PlayDataUtil() {}

  /**
   * @param view the {@link PlayView}.
   * @param gameId the {@link PlayGame#getId() ID} of the {@link PlayGame}.
   * @param key the {@link PlayDataKey}.
   * @return the {@link URL} of the according image.
   */
  public static URL getImageUrl(PlayView view, String gameId, PlayDataKey key) {

    return getResource(view.getId(), gameId, key, IMG_EXTENSION);
  }

  /**
   * @param gameId the {@link PlayGame#getId() ID} of the {@link PlayGame}.
   * @param key the {@link PlayDataKey}.
   * @return the {@link URL} of the according audio file.
   */
  public static URL getAudioUrl(String gameId, PlayDataKey key) {

    return getResource(FOLDER_AUDIO, gameId, key, AUDIO_EXTENSION);
  }

  private static URL getResource(String view, String gameId, PlayDataKey key, String extension) {

    ClassLoader ccl = Thread.currentThread().getContextClassLoader();
    StringBuilder pathBuffer = new StringBuilder(48);
    pathBuffer.append(view);
    pathBuffer.append('/');
    pathBuffer.append(key.getTypeName());
    pathBuffer.append('/');

    // PlayColor color = key.getColor(); // TODO optional?
    // if (color != null) {
    // pathBuffer.append(color.getId());
    // pathBuffer.append('/');
    // }
    // PlayDirection direction = key.getDirection();
    // if (direction != null) {
    // pathBuffer.append(direction.getId());
    // pathBuffer.append('/');
    // }
    String objectId = key.getObjectId();
    if (objectId != null) {
      pathBuffer.append(objectId);
      pathBuffer.append('/');
    }
    pathBuffer.append(key.getTypeId());
    String path = pathBuffer.toString();
    URL resource = getResource(path, key, ccl, gameId, extension);
    if (resource == null) {
      resource = getResource(path, key, ccl, FOLDER_ROOT, extension);
    }
    if (resource == null) {
      // TODO - fallback to default ?
      throw new IllegalStateException("resource for '" + key + "' not found!");
    }
    return resource;
  }

  private static URL getResource(String path, PlayDataKey key, ClassLoader ccl, String gameId, String extension) {

    String resource = DATA_PACKAGE + gameId + "/" + path + extension;
    LOG.fine(resource);
    return ccl.getResource(resource);
  }

}
