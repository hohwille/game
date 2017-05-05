/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.asset;

import io.github.ghosthopper.object.GameLocation;
import io.github.ghosthopper.object.GameTypedObject;
import io.github.ghosthopper.position.GameAttributePosition;
import io.github.ghosthopper.position.GamePosition;

/**
 * Interface for a {@link GameTypedObject} that represents an <em>asset</em> that can be
 * {@link #setLocation(GameLocation) placed} on a {@link GameLocation}.
 *
 * @param <L> the generic type of the {@link #getLocation() location}.
 */
public interface GameAsset<L extends GameLocation> extends GameTypedObject, GameAttributePosition {

  @Override
  GameAssetType getType();

  /**
   * @return the current location of this asset. E.g. a {@link io.github.ghosthopper.item.GameItem} or a
   *         {@link io.github.ghosthopper.figure.GameFigure} can be located on a
   *         {@link io.github.ghosthopper.field.GameField}. However a {@link io.github.ghosthopper.item.GamePickItem}
   *         can also be carried by a {@link io.github.ghosthopper.figure.GameFigure}. May be {@code null} if the
   *         {@link GameAsset} is out of the game.
   */
  L getLocation();

  /**
   * <b>ATTENTION:</b> This is a low-level operation. For consistent behavior consider using high-level operations such
   * as {@link io.github.ghosthopper.figure.GameFigure#move()} instead.
   *
   * @param location the new {@link #getLocation() location} of this asset.
   * @return {@code true} if the {@link GameAttributeAsset#addAsset(GameAsset) operation succeeded}, {@code false}
   *         otherwise.
   */
  default boolean setLocation(L location) {

    return setLocation(location, getPosition(), true);
  }

  /**
   * <b>ATTENTION:</b> This is a low-level operation. For consistent behavior consider using high-level operations such
   * as {@link io.github.ghosthopper.figure.GameFigure#move()} instead.
   *
   * @param location the new {@link #getLocation() location} of this asset.
   * @param position the new {@link #getPosition() position} of this asset (allows to move assets in smaller steps than
   *        fields via position).
   * @return {@code true} if the {@link GameAttributeAsset#addAsset(GameAsset) operation succeeded}, {@code false}
   *         otherwise.
   */
  default boolean setLocation(L location, GamePosition position) {

    return setLocation(location, position, true);
  }

  /**
   * <b>ATTENTION:</b> This is to be considered an internal operation.
   *
   * @param location the new {@link #getLocation() location} of this asset.
   * @param position the new {@link #getPosition() position} of this asset (allows to move assets in smaller steps than
   *        fields via position).
   * @param addOrRemove - {@code true} if this asset shall also be {@link GameAttributeAsset#removeAsset(GameAsset)
   *        removed from the original location} and {@link GameAttributeAsset#addAsset(GameAsset) added to the new
   *        location} automatically, {@code false} otherwise (if called from
   *        {@link GameAttributeAsset#addAsset(GameAsset)}).
   * @return {@code true} if the {@link GameAttributeAsset#addAsset(GameAsset) operation succeeded}, {@code false}
   *         otherwise.
   */
  boolean setLocation(L location, GamePosition position, boolean addOrRemove);

  /**
   * @return {@code true} if the {@link #getLocation() location} is {@code null} and the asset is therefore out of the
   *         game (dead {@link io.github.ghosthopper.figure.GameFigure figure}, fallen
   *         {@link io.github.ghosthopper.item.GameShotItem shot}, exploded dynamite
   *         {@link io.github.ghosthopper.item.GamePickItem item} etc.), {@code false} otherwise.
   */
  default boolean isOutOfGame() {

    return (getLocation() == null);
  }

}
