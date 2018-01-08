/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.game.fx.item;

import net.sf.mmm.game.engine.item.GameFieldItem;
import net.sf.mmm.game.fx.GameUiFxGame;

/**
 * JavaFx view for a {@link GameFieldItem}.
 */
public class GameUiFxPushItem extends GameUiFxItem {

  private final GameFieldItem item;

  /**
   * The constructor.
   *
   * @param item the {@link GameFieldItem}.
   * @param fxGame the {@link #getFxParent() parent} {@link GameUiFxGame game}.
   */
  public GameUiFxPushItem(GameFieldItem item, GameUiFxGame fxGame) {
    super(item, fxGame);
    this.item = item;
  }

  @Override
  public GameFieldItem getGameAsset() {

    return this.item;
  }

}
