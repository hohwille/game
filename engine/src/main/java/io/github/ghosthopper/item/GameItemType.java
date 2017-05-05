/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.item;

import io.github.ghosthopper.asset.GameAssetType;
import io.github.ghosthopper.type.GameTypeBase;

/**
 * The {@link GameItem#getType() type} of a {@link GameItem}. It is either a {@link GamePickItemType} or a
 * {@link GamePushItemType}.
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
