/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.ui.fx;

import io.github.ghosthopper.item.PlayPushItem;

/**
 * JavaFx view for a {@link PlayPushItem}.
 */
public class PlayUiFxPushItem extends PlayUiFxItem {

  private final PlayPushItem item;

  /**
   * The constructor.
   *
   * @param item the {@link PlayPushItem}.
   * @param fxGame the {@link #getFxParent() parent} {@link PlayUiFxGame game}.
   */
  public PlayUiFxPushItem(PlayPushItem item, PlayUiFxGame fxGame) {
    super(item, fxGame);
    this.item = item;
  }

  @Override
  public PlayPushItem getPlayAsset() {

    return this.item;
  }

}
