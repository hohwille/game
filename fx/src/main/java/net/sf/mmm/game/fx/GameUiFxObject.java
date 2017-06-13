/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.game.fx;

import javafx.scene.Node;
import net.sf.mmm.game.engine.Game;
import net.sf.mmm.game.fx.data.GameUiFxDataCache;

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
