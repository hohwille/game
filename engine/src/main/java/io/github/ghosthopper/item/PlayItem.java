/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.item;

import io.github.ghosthopper.game.PlayGame;
import io.github.ghosthopper.object.PlayTypedObject;

/**
 * An item of the {@link PlayGame} such as a key or a gem.
 *
 * @param <S> this {@link PlayItem} itself.
 */
public abstract class PlayItem<S extends PlayItem<S>> extends PlayTypedObject implements PlayAttributeWeight<S> {

  /**
   * The constructor.
   */
  public PlayItem() {
    super();
  }

}
