/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.i18n;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import io.github.ghosthopper.PlayObjectWithId;
import io.github.ghosthopper.game.PlayGame;
import io.github.ghosthopper.game.PlayGameNone;

/**
 * Localizes text messages to the current locale.
 */
public class PlayTranslator {

  private static final String BUNDLE_PREFIX = "io.github.ghosthopper.i18n.";

  private static final String BUNDLE_ROOT = BUNDLE_PREFIX + "Root";

  private final PlayGame game;

  private final Locale creationLocale;

  private final ResourceBundle rootBundle;

  private final ResourceBundle gameBundle;

  /**
   * The constructor.
   *
   * @param game the current {@link PlayGame}.
   */
  public PlayTranslator(PlayGame game) {
    this(game, Locale.getDefault());
  }

  /**
   * The constructor.
   *
   * @param game the current {@link PlayGame}.
   * @param locale the {@link Locale}.
   */
  public PlayTranslator(PlayGame game, Locale locale) {
    super();
    this.game = game;
    this.creationLocale = locale;
    this.rootBundle = ResourceBundle.getBundle(BUNDLE_ROOT, locale);
    ResourceBundle bundle = null;
    if (game != null) {
      String id = game.getId();
      if ((id != null) && !id.equals(PlayGameNone.ID)) {
        String baseName = BUNDLE_PREFIX + game.getId();
        try {
          bundle = ResourceBundle.getBundle(baseName, locale);
        } catch (Exception e) {
          System.err.println(e.getMessage());
        }
      }
    }
    this.gameBundle = bundle;
  }

  /**
   * @param key the key of the text message to get. Typically {@link PlayObjectWithId#getId()}.
   * @return the text message translated to the {@link Locale#getDefault() default locale}.
   */
  public String translate(String key) {

    return translate(key, Locale.getDefault());
  }

  /**
   * @param key the key of the text message to get. Typically {@link PlayObjectWithId#getId()}.
   * @param locale the {@link Locale} to translate to.
   * @return the text message translated to the given {@link Locale}.
   */
  public String translate(String key, Locale locale) {

    String text = null;
    if (locale == this.creationLocale) {
      text = translate(this.gameBundle, key, false);
      if (text == null) {
        text = translate(this.rootBundle, key, true);
      }
    } else {
      ResourceBundle bundle;
      try {
        bundle = ResourceBundle.getBundle(BUNDLE_PREFIX + this.game.getId(), locale);
        text = translate(bundle, key, false);
      } catch (MissingResourceException e) {
        // ignore
      }
      if (text == null) {
        bundle = ResourceBundle.getBundle(BUNDLE_ROOT, locale);
        text = translate(bundle, key, true);
      }
    }
    return text;
  }

  private String translate(ResourceBundle bundle, String key, boolean fallback) {

    if (bundle == null) {
      return null;
    }
    try {
      return bundle.getString(key);
    } catch (MissingResourceException e) {
      // ignore
      if (fallback) {
        return key;
      } else {
        return null;
      }
    }
  }

}
