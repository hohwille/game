/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.ui.fx;

import io.github.ghosthopper.game.PlayGame;

/**
 * The interface for any {@link PlayUiFx}-{@link javafx.scene.Node}.
 */
public interface PlayUiFxNode {

  /**
   * @return the parent {@link PlayUiFxNode} of this object (here parent must not strictly correspond to the JavaFx
   *         scene graph) or {@code null} if this is the {@link PlayUiFx root object}.
   */
  PlayUiFxNode getFxParent();

  /**
   * @return the owning {@link PlayUiFxGame}.
   */
  default PlayUiFxGame getFxGame() {

    return getFxParent().getFxGame();
  }

  /**
   * @return the owning {@link PlayGame}.
   */
  default PlayGame getPlayGame() {

    return getFxGame().getPlayGame();
  }

  /**
   * @return the owning {@link PlayUiFx} (root object).
   */
  default PlayUiFx getPlayUiFx() {

    return getFxParent().getPlayUiFx();
  }

  /**
   * @return the {@link PlayUiFxDataCache}.
   */
  default PlayUiFxDataCache getFxDataCache() {

    return getFxGame().getFxDataCache();
  }

}
