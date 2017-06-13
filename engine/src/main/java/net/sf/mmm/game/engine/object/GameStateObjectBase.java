/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.game.engine.object;

import net.sf.mmm.game.engine.properties.GameProperties;

/**
 * A {@link GameObjectBase} that has a state such as {@link #getProperties() properties}.
 */
public abstract class GameStateObjectBase extends GameObjectBase implements GameStateObject {

  private GameProperties properties;

  /**
   * The constructor.
   */
  public GameStateObjectBase() {
    super();
  }

  @Override
  public GameProperties getProperties() {

    if (this.properties == null) {
      this.properties = createProperties();
    }
    return this.properties;
  }

  /**
   * @return the new {@link #getProperties() properties} instance for lazy initialization.
   */
  protected GameProperties createProperties() {

    return new GameProperties();
  }

}
