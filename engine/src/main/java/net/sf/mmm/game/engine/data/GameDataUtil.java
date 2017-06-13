/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.game.engine.data;

import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.mmm.game.engine.Game;
import net.sf.mmm.game.engine.type.GameType;

/**
 * A utility class that gives access to data resources such as {@link #getImageUrl(GameView, String, GameType) image} or
 * {@link #getAudioUrl(String, GameType) audio} files.
 */
public final class GameDataUtil {

  private static final Logger LOG = LoggerFactory.getLogger(GameDataUtil.class);

  private static final String DATA_PACKAGE = "game/data/";

  private static final String FOLDER_ROOT = "Root";

  private static final String FOLDER_AUDIO = "Audio";

  private static final String FOLDER_GRAPHICS = "Graphics";

  private static final String IMG_EXTENSION = ".png";

  private static final String AUDIO_EXTENSION = ".mp3";

  private GameDataUtil() {}

  /**
   * @param view the {@link GameView}.
   * @param gameId the {@link Game#getId() ID} of the {@link Game}.
   * @param type the {@link GameType}.
   * @return the {@link URL} of the according image.
   */
  public static URL getImageUrl(GameView view, String gameId, GameType type) {

    return getResource(FOLDER_GRAPHICS, view.getId(), gameId, type.getId(), type.getTypeName(), IMG_EXTENSION);
  }

  /**
   * @param gameId the {@link Game#getId() ID} of the {@link Game}.
   * @param type the {@link GameType}.
   * @return the {@link URL} of the according audio file.
   */
  public static URL getAudioUrl(String gameId, GameType type) {

    return getResource(FOLDER_AUDIO, null, gameId, type.getId(), type.getTypeName(), AUDIO_EXTENSION);
  }

  private static URL getResource(String folder, String view, String gameId, String typeId, String typeName, String extension) {

    ClassLoader ccl = Thread.currentThread().getContextClassLoader();
    StringBuilder pathBuffer = new StringBuilder(48);
    pathBuffer.append(folder);
    pathBuffer.append('/');
    if (view != null) {
      pathBuffer.append(view);
      pathBuffer.append('/');
    }
    pathBuffer.append(typeName);
    pathBuffer.append('/');
    pathBuffer.append(typeId);
    String path = pathBuffer.toString();
    URL resource = getResource(path, ccl, gameId, extension);
    if (resource == null) {
      resource = getResource(path, ccl, FOLDER_ROOT, extension);
    }
    if (resource == null) {
      if ((view != null) && !GameView.DEFAULT_ID.equals(view)) {
        return getResource(folder, GameView.DEFAULT_ID, gameId, typeId, typeName, extension);
      } else if (!typeId.equals(typeName)) {
        return getResource(folder, view, gameId, typeName, typeName, extension);
      }
      throw new IllegalStateException("resource for '" + path + extension + "' not found!");
    }
    return resource;
  }

  private static URL getResource(String path, ClassLoader classloader, String gameId, String extension) {

    String resource = DATA_PACKAGE + gameId + "/" + path + extension;
    LOG.debug("Searching data resource {}", resource);
    return classloader.getResource(resource);
  }

}
