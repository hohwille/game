/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.object;

import java.util.Locale;
import java.util.Objects;

import io.github.ghosthopper.game.PlayGame;

/**
 * This is the abstract base class for any object of this game.
 */
public abstract class PlayObject {

  /**
   * The constructor.
   */
  public PlayObject() {
    super();
  }

  /**
   * @return the {@link PlayGame} owining this object.
   */
  public PlayGame getGame() {

    PlayGame game = PlayGame.getCurrentGame();
    Objects.requireNonNull(game, "game");
    return game;
  }

  /**
   * @return the ID of this item used to find corresponding graphics or audio information.
   */
  public abstract String getId();

  /**
   * @return the localized name of this object.
   */
  public String getLocalizedName() {

    return getGame().getTranslator().translate(getId());
  }

  /**
   * @param locale the explicit {@link Locale} to translate to.
   * @return the localized name of this object.
   */
  public String getLocalizedName(Locale locale) {

    return getGame().getTranslator().translate(getId(), locale);
  }

  @Override
  public String toString() {

    return getId();
  }

}
