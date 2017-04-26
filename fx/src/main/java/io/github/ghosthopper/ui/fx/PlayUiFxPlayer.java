/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.ui.fx;

import io.github.ghosthopper.player.Player;

/**
 * JavaFx view for a {@link Player}.
 */
public class PlayUiFxPlayer implements PlayUiFxNode {

  private final PlayUiFxGame game;

  private final Player player;

  /**
   * The constructor.
   *
   * @param player the {@link #getPlayer() player}.
   * @param game the {@link #getFxParent() parent} {@link PlayUiFxGame game}.
   */
  public PlayUiFxPlayer(Player player, PlayUiFxGame game) {
    super();
    this.game = game;
    this.player = player;
    this.game.addFxPlayer(this);
  }

  @Override
  public PlayUiFxNode getFxParent() {

    return this.game;
  }

  /**
   * @return the {@link Player}.
   */
  public Player getPlayer() {

    return this.player;
  }

}
