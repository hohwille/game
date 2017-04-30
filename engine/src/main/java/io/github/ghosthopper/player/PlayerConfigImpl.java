/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.player;

import io.github.ghosthopper.choice.PlayChoice;
import io.github.ghosthopper.game.PlayGame;

/**
 * Implementation of {@link PlayerConfigBase}.
 */
public class PlayerConfigImpl extends PlayerConfigBase {

  /**
   * The constructor.
   *
   * @param game
   */
  public PlayerConfigImpl(PlayGame game) {
    super(game);
  }

  /**
   * The constructor.
   *
   * @param game
   * @param minHumans
   * @param players
   */
  public PlayerConfigImpl(PlayGame game, int minHumans, Player... players) {
    super(game, minHumans, players);
    // TODO Auto-generated constructor stub
  }

  @Override
  public PlayChoice<Player> getChoice() {

    // TODO Auto-generated method stub
    return null;
  }

}
