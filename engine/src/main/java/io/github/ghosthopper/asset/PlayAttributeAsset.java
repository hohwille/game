/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.asset;

import io.github.ghosthopper.figure.PlayAttributeFiguresAdvanced;
import io.github.ghosthopper.figure.PlayFigure;
import io.github.ghosthopper.item.PlayAttributePickItems;
import io.github.ghosthopper.item.PlayAttributePushItem;
import io.github.ghosthopper.item.PlayPickItem;
import io.github.ghosthopper.item.PlayPushItem;
import io.github.ghosthopper.item.PlayShotItem;
import io.github.ghosthopper.object.PlayLocation;

/**
 * Interface for the holder of {@link PlayAsset}s.
 */
public interface PlayAttributeAsset extends PlayLocation {

  /**
   * @param asset the {@link PlayAsset} to check.
   * @return {@code true} if this holder (field) can hold the given {@link PlayAsset} so that it can become the new
   *         {@link PlayAsset#getLocation() location} of the {@link PlayAsset}, {@code false} otherwise.
   */
  default boolean canAddAsset(PlayAsset<?> asset) {

    if (asset instanceof PlayPickItem) {
      if (this instanceof PlayAttributePickItems) {
        return ((PlayAttributePickItems) this).canAddItem((PlayPickItem) asset);
      }
    } else if (asset instanceof PlayFigure) {
      if (this instanceof PlayAttributeFiguresAdvanced) {
        return ((PlayAttributeFiguresAdvanced) this).canAddFigure((PlayFigure) asset);
      }
    } else if (asset instanceof PlayPushItem) {
      if (this instanceof PlayAttributePushItem) {
        return ((PlayAttributePushItem) this).canAddPushItem((PlayPushItem) asset);
      }
    } else if (asset instanceof PlayShotItem) {
      return true;
    }
    return false;
  }

  /**
   * @param asset the {@link PlayAsset} to add to this holder (e.g. field). This happens when a {@link PlayFigure} is
   *        moved to this field, a {@link PlayPickItem} is {@link PlayFigure#dropItem() dropped}, a {@link PlayPushItem}
   *        is pushed, etc. Unlike {@link #canAddAsset(PlayAsset)} this method changes the state of this holder.
   * @return {@code true} if the {@link PlayAsset} {@link #canAddAsset(PlayAsset) can be hold}, {@code false} otherwise.
   */
  default boolean addAsset(PlayAsset<?> asset) {

    if (asset instanceof PlayPickItem) {
      if (this instanceof PlayAttributePickItems) {
        return ((PlayAttributePickItems) this).addItem((PlayPickItem) asset);
      }
    } else if (asset instanceof PlayFigure) {
      if (this instanceof PlayAttributeFiguresAdvanced) {
        return ((PlayAttributeFiguresAdvanced) this).addFigure((PlayFigure) asset);
      }
    } else if (asset instanceof PlayPushItem) {
      if (this instanceof PlayAttributePushItem) {
        return ((PlayAttributePushItem) this).setPushItem((PlayPushItem) asset);
      }
    } else if (asset instanceof PlayShotItem) {
      return true;
    }
    return false;
  }

  /**
   * @param asset the {@link PlayAsset} to remove from this holder (field). Called when a {@link PlayFigure} is moved
   *        from this field, a {@link PlayPickItem} is {@link PlayFigure#dropItem() dropped}, a {@link PlayPushItem} is
   *        pushed, etc.
   * @return {@code true} if the {@link PlayAsset} could successfully be removed, {@code false} otherwise (exotic case
   *         but field might be glued, magnetic or whatever).
   */
  default boolean removeAsset(PlayAsset<?> asset) {

    return removeAsset(asset, true);
  }

  /**
   * @param asset the {@link PlayAsset} to remove from this holder (field). Called when a {@link PlayFigure} is moved
   *        from this field, a {@link PlayPickItem} is {@link PlayFigure#dropItem() dropped}, a {@link PlayPushItem} is
   *        pushed, etc.
   * @param updateLocation - {@code true} if the {@link PlayAsset#getLocation() location} shall also be
   *        {@link PlayAsset#setLocation(io.github.ghosthopper.object.PlayLocation) updated} and a
   *        {@link PlayAssetMoveEvent} should be send, {@code false} otherwise (if called from
   *        {@link #addAsset(PlayAsset)} or from
   *        {@link PlayAsset#setLocation(io.github.ghosthopper.object.PlayLocation)}).
   * @return {@code true} if the {@link PlayAsset} could successfully be removed, {@code false} otherwise (exotic case
   *         but field might be glued, magnetic or whatever).
   */
  default boolean removeAsset(PlayAsset<?> asset, boolean updateLocation) {

    if (asset instanceof PlayPickItem) {
      if (this instanceof PlayAttributePickItems) {
        return ((PlayAttributePickItems) this).removeItem((PlayPickItem) asset);
      }
    } else if (asset instanceof PlayFigure) {
      if (this instanceof PlayAttributeFiguresAdvanced) {
        return ((PlayAttributeFiguresAdvanced) this).removeFigure((PlayFigure) asset);
      }
    } else if (asset instanceof PlayPushItem) {
      if (this instanceof PlayAttributePushItem) {
        return ((PlayAttributePushItem) this).setPushItem(null);
      }
    } else if (asset instanceof PlayShotItem) {
      return true;
    }
    return false;
  }

}
