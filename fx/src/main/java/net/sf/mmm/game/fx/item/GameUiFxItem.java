/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.game.fx.item;

import net.sf.mmm.game.engine.item.GameItem;
import net.sf.mmm.game.fx.GameUiFxGame;
import net.sf.mmm.game.fx.GameUiFxObject;
import net.sf.mmm.game.fx.asset.GameUiFxAsset;

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
  public GameUiFxItem(GameItem<?, ?, ?> item, GameUiFxGame fxGame) {

    super(item, fxGame);
    this.fxGame = fxGame;
  }

  @Override
  public GameUiFxObject getFxParent() {

    return this.fxGame;
  }

}
