/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.player;

import io.github.ghosthopper.choice.PlayChoice;
import io.github.ghosthopper.game.PlayAttributeGame;
import io.github.ghosthopper.game.PlayGame;

/**
 * This is the interface for the {@link PlayerConfig}
 */
public interface PlayerConfig extends PlayAttributePlayers, PlayAttributeGame {

  /**
   * @return the minimum number of {@link Player}s required to play this {@link PlayGame game}.
   */
  int getMinPlayers();

  /**
   * @return the maximum number of {@link Player}s required to play this {@link PlayGame game}.
   */
  int getMaxPlayers();

  /**
   * @return the minimum number of {Player#isHuman() human} {@link Player}s required to play this {@link PlayGame game}.
   *         Override this method in your {@link PlayGame}.
   */
  int getMinHumans();

  /**
   * @return the maximum number of {Player#isHuman() human} {@link Player}s allowed to play this {@link PlayGame game}.
   */
  int getMaxHumans();

  /**
   * @return the minimum number of non-{Player#isHuman() human} (bot) {@link Player}s required to play this
   *         {@link PlayGame game}.
   */
  int getMinBots();

  /**
   * @return the maximum number of non-{Player#isHuman() human} (bot) {@link Player}s allowed to play this
   *         {@link PlayGame game}.
   */
  int getMaxBots();

  /**
   * @return the initial {@link PlayChoice} to setup the {@link #getPlayers() players}.
   */
  PlayChoice<Player> getChoice();

}
