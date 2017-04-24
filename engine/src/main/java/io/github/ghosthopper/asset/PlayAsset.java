/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.asset;

import io.github.ghosthopper.field.PlayField;
import io.github.ghosthopper.figure.PlayFigure;
import io.github.ghosthopper.item.PlayItem;
import io.github.ghosthopper.item.PlayPickItem;
import io.github.ghosthopper.object.AbstractPlayTypedObject;
import io.github.ghosthopper.object.PlayLocation;
import io.github.ghosthopper.object.PlayTypedObject;

/**
 * This is the abstract base class for a {@link AbstractPlayTypedObject} that represents an <em>asset</em> that can be
 * {@link #setLocation(PlayLocation) placed} on a {@link PlayLocation}.
 *
 * @param <L> the generic type of the {@link #getLocation() location}.
 */
public interface PlayAsset<L extends PlayLocation> extends PlayTypedObject {

  @Override
  PlayAssetType getType();

  /**
   * @return the current location of this asset. E.g. a {@link PlayItem} or a {@link PlayFigure} can be located on a
   *         {@link PlayField}. However a {@link PlayPickItem} can also be carried by a {@link PlayFigure}. May be
   *         {@code null} if the {@link PlayAsset} is out of the game.
   */
  public abstract L getLocation();

  /**
   * @param location the new {@link #getLocation() location} of this asset.
   */
  public abstract void setLocation(L location);

}
