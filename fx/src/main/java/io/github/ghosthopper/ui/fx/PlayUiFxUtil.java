/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.ui.fx;

import java.net.URL;
import java.util.Locale;

import io.github.ghosthopper.object.PlayObject;
import io.github.ghosthopper.object.PlayTypedObject;

/**
 * TODO: this class ...
 */
public final class PlayUiFxUtil {

  private static final String DATA_PACKAGE = "io/github/ghosthopper/data/";

  private static final String IMG_PACKAGE = DATA_PACKAGE + "img/";

  private static final String AUD_PACKAGE = DATA_PACKAGE + "aud/";

  private static final String ROOT = "root";

  private PlayUiFxUtil() {}

  public static URL getImage(PlayTypedObject object) {

    return getResource(IMG_PACKAGE, object);
  }

  private static String getId(PlayObject object) {

    return object.getId().toLowerCase(Locale.US);
  }

  private static URL getResource(String pkg, PlayTypedObject object) {

    ClassLoader ccl = Thread.currentThread().getContextClassLoader();
    String gameId = getId(object.getGame());
    URL resource = getResource(pkg, object, ccl, gameId);
    if (resource == null) {
      resource = getResource(pkg, object, ccl, ROOT);
    }
    if (resource == null) {
      // TODO - fallback to default ?
      throw new IllegalStateException("resource for '" + object.getId() + "' not found!");
    }
    return resource;
  }

  private static URL getResource(String pkg, PlayTypedObject object, ClassLoader ccl, String gameId) {

    String gamePkg = pkg + gameId + "/";
    String resource = gamePkg + object.getId() + ".png";
    return ccl.getResource(resource);
  }

}
