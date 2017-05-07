/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.ui.fx.item;

import io.github.ghosthopper.item.GamePickItem;
import io.github.ghosthopper.ui.fx.game.GameUiFxGame;

/**
 * JavaFx view for a {@link GamePickItem}.
 */
public class GameUiFxPickItem extends GameUiFxItem {

  private final GamePickItem item;

  /**
   * The constructor.
   *
   * @param item the {@link GamePickItem}.
   * @param fxGame the {@link #getFxParent() parent} {@link GameUiFxGame game}.
   */
  public GameUiFxPickItem(GamePickItem item, GameUiFxGame fxGame) {
    super(item, fxGame);
    this.item = item;
  }

  @Override
  public GamePickItem getGameAsset() {

    return this.item;
  }

}
