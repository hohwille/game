/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.game.engine.item;

/**
 * Extends {@link GameFieldItemType} for {@link GameAssetProxy}.
 *
 * @author Joerg Hohwiller (hohwille at users.sourceforge.net)
 * @since 1.0.0
 */
public class GameAssetProxyType extends GameFieldItemType {

  /**
   * The constructor.
   *
   * @param id - see {@link #getId()}.
   */
  public GameAssetProxyType(String id) {

    super(id);
  }

  @Override
  public boolean isPushable() {

    return false;
  }

}
