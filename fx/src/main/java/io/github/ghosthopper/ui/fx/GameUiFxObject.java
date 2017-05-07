/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.ui.fx;

import io.github.ghosthopper.game.Game;
import io.github.ghosthopper.ui.fx.data.GameUiFxDataCache;
import io.github.ghosthopper.ui.fx.game.GameUiFxGame;
import javafx.scene.Node;

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
   * @return the {@link Node} or {@code null} if this is no JavaFx object.
   */
  default Node getFxNode() {

    if (this instanceof Node) {
      return (Node) this;
    }
    return null;
  }

  /**
   * @return the owning {@link GameUiFxGame}.
   */
  default GameUiFxGame getFxGame() {

    return getFxParent().getFxGame();
  }

  /**
   * @return the owning {@link Game}.
   */
  default Game getGame() {

    return getFxGame().getGame();
  }

  /**
   * @return the owning {@link GameUiFx} (root object).
   */
  default GameUiFx getFxUi() {

    return getFxParent().getFxUi();
  }

  /**
   * @return the {@link GameUiFxDataCache}.
   */
  default GameUiFxDataCache getFxDataCache() {

    return getFxGame().getFxDataCache();
  }

}
