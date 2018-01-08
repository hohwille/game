/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.game.engine.asset;

import net.sf.mmm.game.engine.object.GameLocation;
import net.sf.mmm.game.engine.object.GameTypedObject;
import net.sf.mmm.game.engine.position.GameAttributePosition;
import net.sf.mmm.game.engine.position.GamePosition;

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
   * @return the current location of this asset. E.g. a {@link net.sf.mmm.game.engine.item.GameItem} or a
   *         {@link net.sf.mmm.game.engine.figure.GameFigure} can be located on a
   *         {@link net.sf.mmm.game.engine.field.GameField}. However a {@link net.sf.mmm.game.engine.item.GamePickItem}
   *         can also be carried by a {@link net.sf.mmm.game.engine.figure.GameFigure}. May be {@code null} if the
   *         {@link GameAsset} is out of the game.
   */
  L getLocation();

  /**
   * <b>ATTENTION:</b> This is a low-level operation. For consistent behavior consider using high-level operations such
   * as {@link net.sf.mmm.game.engine.figure.GameFigure#move()} instead.
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
   * as {@link net.sf.mmm.game.engine.figure.GameFigure#move()} instead.
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
   * @param addOrRemove - {@code true} if this asset shall automatically be
   *        {@link GameAttributeAsset#addAsset(GameAsset) added to the new location} or
   *        {@link GameAttributeAsset#removeAsset(GameAsset) removed from the original location} if the new location is
   *        {@code null}, {@code false} otherwise (if called from {@link GameAttributeAsset#addAsset(GameAsset)}).
   * @return {@code true} if the {@link GameAttributeAsset#addAsset(GameAsset) operation succeeded}, {@code false}
   *         otherwise.
   */
  boolean setLocation(L location, GamePosition position, boolean addOrRemove);

  /**
   * @return {@code true} if the {@link #getLocation() location} is {@code null} and the asset is therefore out of the
   *         game (dead {@link net.sf.mmm.game.engine.figure.GameFigure figure}, fallen
   *         {@link net.sf.mmm.game.engine.item.GameShotItem shot}, exploded dynamite
   *         {@link net.sf.mmm.game.engine.item.GamePickItem item} etc.), {@code false} otherwise.
   */
  default boolean isOutOfGame() {

    return (getLocation() == null);
  }

}
