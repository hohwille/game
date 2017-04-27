/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.item;

import io.github.ghosthopper.asset.AbstractPlayAsset;
import io.github.ghosthopper.asset.PlayAssetPositionEvent;
import io.github.ghosthopper.game.PlayGame;
import io.github.ghosthopper.object.PlayLocation;
import io.github.ghosthopper.position.PlayPosition;

/**
 * An item of the {@link PlayGame} such as a key or a gem.
 *
 * @param <L> the type of the {@link #getLocation() location}.
 * @param <S> this {@link PlayItem} itself.
 */
public abstract class PlayItem<L extends PlayLocation, S extends PlayItem<L, S>> extends AbstractPlayAsset<L> implements PlayAttributeWeight<S> {

  /**
   * The constructor.
   */
  public PlayItem() {
    super();
  }

  @Override
  protected PlayAssetPositionEvent<?> createPositionEvent(PlayPosition oldPosition, PlayPosition newPosition) {

    return new PlayItemPositionEvent(oldPosition, this, newPosition);
  }

}
