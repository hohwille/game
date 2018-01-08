/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.game.engine.item;

import net.sf.mmm.game.engine.asset.GameAssetType;
import net.sf.mmm.game.engine.type.GameTypeBase;

/**
 * The {@link GameItem#getType() type} of a {@link GameItem}. It is either a {@link GamePickItemType} or a
 * {@link GameFieldItemType}.
 *
 * @param <S> this {@link GameItemType} itself.
 */
public abstract class GameItemType<S extends GameItemType<S>> extends GameTypeBase implements GameAssetType, GameAttributeWeight<S> {

  /**
   * The constructor.
   *
   * @param id - see {@link #getId()}.
   */
  public GameItemType(String id) {
    super(id);
  }

  @Override
  public String getTypeName() {

    return "Item";
  }

}
