/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.game.engine.asset;

import net.sf.mmm.game.engine.figure.GameAttributeFiguresAdvanced;
import net.sf.mmm.game.engine.figure.GameFigure;
import net.sf.mmm.game.engine.item.GameAttributePickItems;
import net.sf.mmm.game.engine.item.GameAttributeFieldItem;
import net.sf.mmm.game.engine.item.GamePickItem;
import net.sf.mmm.game.engine.item.GameFieldItem;
import net.sf.mmm.game.engine.item.GameShotItem;

/**
 * Interface for the holder of {@link GameAsset}s.
 */
public interface GameAttributeAsset {

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
    } else if (asset instanceof GameFieldItem) {
      if (this instanceof GameAttributeFieldItem) {
        return ((GameAttributeFieldItem) this).canAddItem((GameFieldItem) asset);
      }
    } else if (asset instanceof GameShotItem) {
      return true;
    }
    return false;
  }

  /**
   * @param asset the {@link GameAsset} to add to this holder (e.g. field). This happens when a {@link GameFigure} is
   *        moved to this field, a {@link GamePickItem} is {@link GameFigure#dropItem() dropped}, a {@link GameFieldItem}
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
    } else if (asset instanceof GameFieldItem) {
      if (this instanceof GameAttributeFieldItem) {
        return ((GameAttributeFieldItem) this).setItem((GameFieldItem) asset);
      }
    } else if (asset instanceof GameShotItem) {
      return true;
    }
    return false;
  }

  /**
   * @param asset the {@link GameAsset} to remove from this holder (field). Called when a {@link GameFigure} is moved
   *        from this field, a {@link GamePickItem} is {@link GameFigure#dropItem() dropped}, a {@link GameFieldItem} is
   *        pushed, etc.
   * @return {@code true} if the {@link GameAsset} could successfully be removed, {@code false} otherwise (exotic case
   *         but field might be glued, magnetic or whatever).
   */
  default boolean removeAsset(GameAsset<?> asset) {

    return removeAsset(asset, true);
  }

  /**
   * @param asset the {@link GameAsset} to remove from this holder (field). Called when a {@link GameFigure} is moved
   *        from this field, a {@link GamePickItem} is {@link GameFigure#dropItem() dropped}, a {@link GameFieldItem} is
   *        pushed, etc.
   * @param updateLocation - {@code true} if the {@link GameAsset#getLocation() location} shall also be
   *        {@link GameAsset#setLocation(net.sf.mmm.game.engine.object.GameLocation) updated} and a
   *        {@link GameAssetMoveEvent} should be send, {@code false} otherwise (if called from
   *        {@link #addAsset(GameAsset)} or from
   *        {@link GameAsset#setLocation(net.sf.mmm.game.engine.object.GameLocation)}).
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
    } else if (asset instanceof GameFieldItem) {
      if (this instanceof GameAttributeFieldItem) {
        return ((GameAttributeFieldItem) this).setItem(null);
      }
    } else if (asset instanceof GameShotItem) {
      return true;
    }
    return false;
  }

}
