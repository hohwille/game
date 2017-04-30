/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.object;

import java.util.Locale;
import java.util.Objects;

import io.github.ghosthopper.game.PlayAttributeGame;
import io.github.ghosthopper.game.PlayGame;

/**
 * Interface for any play object of this game.
 */
public interface PlayObject extends PlayAttributeGame {

  @Override
  default PlayGame getGame() {

    PlayGame game = PlayGame.getCurrentGame();
    Objects.requireNonNull(game, "game");
    return game;
  }

  /**
   * @return the ID of this item used to find the corresponding {@link #getLocalizedName() localized name} as well as
   *         graphics or audio information.
   */
  String getId();

  /**
   * @return the localized name of this object.
   */
  default String getLocalizedName() {

    return getGame().getTranslator().translate(getId());
  }

  /**
   * @param locale the explicit {@link Locale} to translate to.
   * @return the localized name of this object.
   */
  default String getLocalizedName(Locale locale) {

    return getGame().getTranslator().translate(getId(), locale);
  }

}
