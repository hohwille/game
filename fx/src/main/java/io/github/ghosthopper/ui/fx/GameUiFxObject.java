/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.ui.fx;

import io.github.ghosthopper.game.Game;
import io.github.ghosthopper.ui.fx.data.GameUiFxDataCache;

/**
 * The interface for any {@link GameUiFx}-object.
 */
public interface GameUiFxObject {

  /**
   * @return the parent {@link GameUiFxObject} of this object (here parent must not strictly correspond to the JavaFx
   *         scene graph) or {@code null} if this is the {@link GameUiFx root object}.
   */
  GameUiFxObject getFxParent();

  /**
   * @return the owning {@link GameUiFxGame}.
   */
  default GameUiFxGame getFxGame() {

    return getFxParent().getFxGame();
  }

  /**
   * @return the owning {@link Game}.
   */
  default Game getPlayGame() {

    return getFxGame().getPlayGame();
  }

  /**
   * @return the owning {@link GameUiFx} (root object).
   */
  default GameUiFx getPlayUiFx() {

    return getFxParent().getPlayUiFx();
  }

  /**
   * @return the {@link GameUiFxDataCache}.
   */
  default GameUiFxDataCache getFxDataCache() {

    return getFxGame().getFxDataCache();
  }

}
