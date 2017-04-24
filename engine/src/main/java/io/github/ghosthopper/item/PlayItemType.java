/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.item;

import io.github.ghosthopper.asset.PlayAssetType;
import io.github.ghosthopper.object.PlayObjectType;

/**
 * The {@link PlayItem#getType() type} of a {@link PlayItem}. It is either a {@link PlayPickItemType} or a
 * {@link PlayPushItemType}.
 *
 * @param <S> this {@link PlayItemType} itself.
 */
public abstract class PlayItemType<S extends PlayItemType<S>> extends PlayObjectType implements PlayAssetType, PlayAttributeWeight<S> {

  /**
   * The constructor.
   *
   * @param id - see {@link #getId()}.
   */
  public PlayItemType(String id) {
    super(id);
  }

  @Override
  public String getTypeName() {

    return "Item";
  }

}
