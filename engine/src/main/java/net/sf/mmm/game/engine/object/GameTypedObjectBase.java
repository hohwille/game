/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.game.engine.object;

import net.sf.mmm.game.engine.event.GameEvent;
import net.sf.mmm.game.engine.properties.GameProperties;
import net.sf.mmm.game.engine.type.GameType;

/**
 * This is the abstract base class for an object that has a {@link #getType() type} and optionally a {@link #getColor()
 * color}.
 *
 * @param <T> generic type of {@link #getType()}.
 */
public abstract class GameTypedObjectBase<T extends GameType> extends GameStateObjectBase implements GameTypedObject, GameEvent {

  private T type;

  /**
   * The constructor.
   *
   * @param type the {@link #getType() type} of this object.
   */
  public GameTypedObjectBase(T type) {

    super();
    this.type = type;
  }

  @Override
  public T getType() {

    return this.type;
  }

  /**
   * @param type the new value of {@link #getType()}.
   */
  protected void setType(T type) {

    this.type = type;
  }

  @Override
  protected GameProperties createProperties() {

    return new GameProperties(this.type.getProperties());
  }

  @Override
  public String getId() {

    return getType().getId();
  }

}
