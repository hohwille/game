/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.ui.fx.item;

import io.github.ghosthopper.item.GameItem;
import io.github.ghosthopper.ui.fx.GameUiFxObject;
import io.github.ghosthopper.ui.fx.asset.GameUiFxAsset;
import io.github.ghosthopper.ui.fx.game.GameUiFxGame;

/**
 * JavaFx view for a {@link GameItem}.
 */
public abstract class GameUiFxItem extends GameUiFxAsset {

  private final GameUiFxGame fxGame;

  /**
   * The constructor.
   *
   * @param item the {@link GameItem}.
   * @param fxGame the {@link #getFxParent() parent} {@link GameUiFxGame game}.
   */
  public GameUiFxItem(GameItem<?, ?> item, GameUiFxGame fxGame) {
    super(item, fxGame);
    this.fxGame = fxGame;
  }

  @Override
  public GameUiFxObject getFxParent() {

    return this.fxGame;
  }

}
