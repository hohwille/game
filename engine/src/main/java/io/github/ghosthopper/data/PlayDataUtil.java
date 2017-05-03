/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.data;

import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.github.ghosthopper.game.PlayGame;
import io.github.ghosthopper.type.PlayType;

/**
 * A utility class that gives access to data resources such as {@link #getImageUrl(PlayView, String, PlayType) image} or
 * {@link #getAudioUrl(String, PlayType) audio} files.
 */
public final class PlayDataUtil {

  private static final Logger LOG = LoggerFactory.getLogger(PlayDataUtil.class);

  private static final String DATA_PACKAGE = "game/data/";

  private static final String FOLDER_ROOT = "Root";

  private static final String FOLDER_AUDIO = "Audio";

  private static final String FOLDER_GRAPHICS = "Graphics";

  private static final String IMG_EXTENSION = ".png";

  private static final String AUDIO_EXTENSION = ".mp3";

  private PlayDataUtil() {}

  /**
   * @param view the {@link PlayView}.
   * @param gameId the {@link PlayGame#getId() ID} of the {@link PlayGame}.
   * @param type the {@link PlayType}.
   * @return the {@link URL} of the according image.
   */
  public static URL getImageUrl(PlayView view, String gameId, PlayType type) {

    return getResource(FOLDER_GRAPHICS + "/" + view.getId(), gameId, type, IMG_EXTENSION);
  }

  /**
   * @param gameId the {@link PlayGame#getId() ID} of the {@link PlayGame}.
   * @param type the {@link PlayType}.
   * @return the {@link URL} of the according audio file.
   */
  public static URL getAudioUrl(String gameId, PlayType type) {

    return getResource(FOLDER_AUDIO, gameId, type, AUDIO_EXTENSION);
  }

  private static URL getResource(String folder, String gameId, PlayType type, String extension) {

    ClassLoader ccl = Thread.currentThread().getContextClassLoader();
    StringBuilder pathBuffer = new StringBuilder(48);
    pathBuffer.append(folder);
    pathBuffer.append('/');
    pathBuffer.append(type.getTypeName());
    pathBuffer.append('/');
    pathBuffer.append(type.getId());
    String path = pathBuffer.toString();
    URL resource = getResource(path, ccl, gameId, extension);
    if (resource == null) {
      resource = getResource(path, ccl, FOLDER_ROOT, extension);
    }
    if (resource == null) {
      // TODO - fallback to default ?
      throw new IllegalStateException("resource for '" + path + "' not found!");
    }
    return resource;
  }

  private static URL getResource(String path, ClassLoader classloader, String gameId, String extension) {

    String resource = DATA_PACKAGE + gameId + "/" + path + extension;
    LOG.debug("Searching data resource {}", resource);
    return classloader.getResource(resource);
  }

}
