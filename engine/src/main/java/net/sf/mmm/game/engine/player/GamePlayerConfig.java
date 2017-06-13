/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.game.engine.player;

import net.sf.mmm.game.engine.Game;
import net.sf.mmm.game.engine.GameAttributeGame;
import net.sf.mmm.game.engine.choice.GameChoice;

/**
 * This is the interface for the {@link GamePlayerConfig}
 */
public interface GamePlayerConfig extends GameAttributePlayers, GameAttributeGame {

  /**
   * @return the minimum number of {@link GamePlayer}s required to play this {@link Game game}.
   */
  int getMinPlayers();

  /**
   * @return the maximum number of {@link GamePlayer}s required to play this {@link Game game}.
   */
  int getMaxPlayers();

  /**
   * @return the minimum number of {Player#isHuman() human} {@link GamePlayer}s required to play this {@link Game game}.
   *         Override this method in your {@link Game}.
   */
  int getMinHumans();

  /**
   * @return the maximum number of {Player#isHuman() human} {@link GamePlayer}s allowed to play this {@link Game game}.
   */
  int getMaxHumans();

  /**
   * @return the minimum number of non-{Player#isHuman() human} (bot) {@link GamePlayer}s required to play this
   *         {@link Game game}.
   */
  int getMinBots();

  /**
   * @return the maximum number of non-{Player#isHuman() human} (bot) {@link GamePlayer}s allowed to play this
   *         {@link Game game}.
   */
  int getMaxBots();

  /**
   * @return the initial {@link GameChoice} to setup the {@link #getPlayers() players}.
   */
  GameChoice<GamePlayer> getChoice();

}
