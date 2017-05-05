/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.asset;

import io.github.ghosthopper.figure.GameAttributeFiguresAdvanced;
import io.github.ghosthopper.figure.GameFigure;
import io.github.ghosthopper.item.GameAttributePickItems;
import io.github.ghosthopper.item.GameAttributePushItem;
import io.github.ghosthopper.item.GamePickItem;
import io.github.ghosthopper.item.GamePushItem;
import io.github.ghosthopper.item.GameShotItem;
import io.github.ghosthopper.object.GameLocation;

/**
 * Interface for the holder of {@link GameAsset}s.
 */
public interface GameAttributeAsset extends GameLocation {

  /**
   * @param asset the {@link GameAsset} to check.
   * @return {@code true} if this holder (field) can hold the given {@link GameAsset} so that it can become the new
   *         {@link GameAsset#getLocation() location} of the {@link GameAsset}, {@code false} otherwise.
   */
  default boolean canAddAsset(GameAsset<?> asset) {

    if (asset instanceof GamePickItem) {
      if (this instanceof GameAttributePickItems) {
        return ((GameAttributePickItems) this).canAddItem((GamePickItem) asset);
      }
    } else if (asset instanceof GameFigure) {
      if (this instanceof GameAttributeFiguresAdvanced) {
        return ((GameAttributeFiguresAdvanced) this).canAddFigure((GameFigure) asset);
      }
    } else if (asset instanceof GamePushItem) {
      if (this instanceof GameAttributePushItem) {
        return ((GameAttributePushItem) this).canAddPushItem((GamePushItem) asset);
      }
    } else if (asset instanceof GameShotItem) {
      return true;
    }
    return false;
  }

  /**
   * @param asset the {@link GameAsset} to add to this holder (e.g. field). This happens when a {@link GameFigure} is
   *        moved to this field, a {@link GamePickItem} is {@link GameFigure#dropItem() dropped}, a {@link GamePushItem}
   *        is pushed, etc. Unlike {@link #canAddAsset(GameAsset)} this method changes the state of this holder.
   * @return {@code true} if the {@link GameAsset} {@link #canAddAsset(GameAsset) can be hold}, {@code false} otherwise.
   */
  default boolean addAsset(GameAsset<?> asset) {

    if (asset instanceof GamePickItem) {
      if (this instanceof GameAttributePickItems) {
        return ((GameAttributePickItems) this).addItem((GamePickItem) asset);
      }
    } else if (asset instanceof GameFigure) {
      if (this instanceof GameAttributeFiguresAdvanced) {
        return ((GameAttributeFiguresAdvanced) this).addFigure((GameFigure) asset);
      }
    } else if (asset instanceof GamePushItem) {
      if (this instanceof GameAttributePushItem) {
        return ((GameAttributePushItem) this).setPushItem((GamePushItem) asset);
      }
    } else if (asset instanceof GameShotItem) {
      return true;
    }
    return false;
  }

  /**
   * @param asset the {@link GameAsset} to remove from this holder (field). Called when a {@link GameFigure} is moved
   *        from this field, a {@link GamePickItem} is {@link GameFigure#dropItem() dropped}, a {@link GamePushItem} is
   *        pushed, etc.
   * @return {@code true} if the {@link GameAsset} could successfully be removed, {@code false} otherwise (exotic case
   *         but field might be glued, magnetic or whatever).
   */
  default boolean removeAsset(GameAsset<?> asset) {

    return removeAsset(asset, true);
  }

  /**
   * @param asset the {@link GameAsset} to remove from this holder (field). Called when a {@link GameFigure} is moved
   *        from this field, a {@link GamePickItem} is {@link GameFigure#dropItem() dropped}, a {@link GamePushItem} is
   *        pushed, etc.
   * @param updateLocation - {@code true} if the {@link GameAsset#getLocation() location} shall also be
   *        {@link GameAsset#setLocation(io.github.ghosthopper.object.GameLocation) updated} and a
   *        {@link GameAssetMoveEvent} should be send, {@code false} otherwise (if called from
   *        {@link #addAsset(GameAsset)} or from
   *        {@link GameAsset#setLocation(io.github.ghosthopper.object.GameLocation)}).
   * @return {@code true} if the {@link GameAsset} could successfully be removed, {@code false} otherwise (exotic case
   *         but field might be glued, magnetic or whatever).
   */
  default boolean removeAsset(GameAsset<?> asset, boolean updateLocation) {

    if (asset instanceof GamePickItem) {
      if (this instanceof GameAttributePickItems) {
        return ((GameAttributePickItems) this).removeItem((GamePickItem) asset);
      }
    } else if (asset instanceof GameFigure) {
      if (this instanceof GameAttributeFiguresAdvanced) {
        return ((GameAttributeFiguresAdvanced) this).removeFigure((GameFigure) asset);
      }
    } else if (asset instanceof GamePushItem) {
      if (this instanceof GameAttributePushItem) {
        return ((GameAttributePushItem) this).setPushItem(null);
      }
    } else if (asset instanceof GameShotItem) {
      return true;
    }
    return false;
  }

}
