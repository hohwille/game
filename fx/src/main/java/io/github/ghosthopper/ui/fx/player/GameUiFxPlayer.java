/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.ui.fx.player;

import io.github.ghosthopper.player.GamePlayer;
import io.github.ghosthopper.ui.fx.GameUiFxObject;
import io.github.ghosthopper.ui.fx.game.GameUiFxGame;

/**
 * JavaFx view for a {@link GamePlayer}.
 */
public class GameUiFxPlayer implements GameUiFxObject {

  private final GameUiFxGame game;

  private final GamePlayer player;

  /**
   * The constructor.
   *
   * @param player the {@link #getGamePlayer() player}.
   * @param game the {@link #getFxParent() parent} {@link GameUiFxGame game}.
   */
  public GameUiFxPlayer(GamePlayer player, GameUiFxGame game) {
    super();
    this.game = game;
    this.player = player;
  }

  @Override
  public GameUiFxObject getFxParent() {

    return this.game;
  }

  /**
   * @return the {@link GamePlayer}.
   */
  public GamePlayer getGamePlayer() {

    return this.player;
  }

}
