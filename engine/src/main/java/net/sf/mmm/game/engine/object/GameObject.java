/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.game.engine.object;

import java.util.Locale;
import java.util.Objects;

import net.sf.mmm.game.engine.Game;
import net.sf.mmm.game.engine.GameAttributeGame;

/**
 * Interface for any play object of this game.
 */
public interface GameObject extends GameAttributeGame {

  @Override
  default Game getGame() {

    Game game = Game.getCurrentGame();
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
