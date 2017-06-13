/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.game.engine.object;

import net.sf.mmm.game.engine.event.GameEvent;
import net.sf.mmm.game.engine.properties.GameProperties;

/**
 * This is the abstract base class for an object that has a {@link #getType() type} and optionally a {@link #getColor()
 * color}.
 */
public abstract class GameTypedObjectBase extends GameStateObjectBase implements GameTypedObject, GameEvent {

  /**
   * The constructor.
   */
  public GameTypedObjectBase() {
    super();
  }

  @Override
  protected GameProperties createProperties() {

    return new GameProperties(getType().getProperties());
  }

  @Override
  public String getId() {

    return getType().getId();
  }

}
