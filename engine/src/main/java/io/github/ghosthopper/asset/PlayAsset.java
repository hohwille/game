/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.asset;

import io.github.ghosthopper.object.PlayLocation;
import io.github.ghosthopper.object.PlayTypedObject;
import io.github.ghosthopper.position.PlayAttributePosition;

/**
 * Interface for a {@link PlayTypedObject} that represents an <em>asset</em> that can be
 * {@link #setLocation(PlayLocation) placed} on a {@link PlayLocation}.
 *
 * @param <L> the generic type of the {@link #getLocation() location}.
 */
public interface PlayAsset<L extends PlayLocation> extends PlayTypedObject, PlayAttributePosition {

  @Override
  PlayAssetType getType();

  /**
   * @return the current location of this asset. E.g. a {@link io.github.ghosthopper.item.PlayItem} or a
   *         {@link io.github.ghosthopper.figure.PlayFigure} can be located on a
   *         {@link io.github.ghosthopper.field.PlayField}. However a {@link io.github.ghosthopper.item.PlayPickItem}
   *         can also be carried by a {@link io.github.ghosthopper.figure.PlayFigure}. May be {@code null} if the
   *         {@link PlayAsset} is out of the game.
   */
  L getLocation();

  /**
   * <b>ATTENTION:</b> This is a low-level operation. For consistent behavior consider using high-level operations such
   * as {@link io.github.ghosthopper.figure.PlayFigure#move()} instead.
   *
   * @param location the new {@link #getLocation() location} of this asset.
   * @return {@code true} if the {@link PlayAttributeAsset#addAsset(PlayAsset) operation succeeded}, {@code false}
   *         otherwise.
   */
  default boolean setLocation(L location) {

    return setLocation(location, true);
  }

  /**
   * @param location the new {@link #getLocation() location} of this asset.
   * @param addOrRemove - {@code true} if this asset shall also be {@link PlayAttributeAsset#removeAsset(PlayAsset)
   *        removed from the original location} and {@link PlayAttributeAsset#addAsset(PlayAsset) added to the new
   *        location} automatically, {@code false} otherwise (if called from
   *        {@link PlayAttributeAsset#addAsset(PlayAsset)}).
   * @return {@code true} if the {@link PlayAttributeAsset#addAsset(PlayAsset) operation succeeded}, {@code false}
   *         otherwise.
   */
  boolean setLocation(L location, boolean addOrRemove);

}
