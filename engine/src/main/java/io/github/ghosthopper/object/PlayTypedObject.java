/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.object;

import io.github.ghosthopper.color.PlayAttributeColor;
import io.github.ghosthopper.event.PlayEvent;
import io.github.ghosthopper.properties.PlayProperties;

/**
 * This is the abstract base class for an object that has a {@link #getType() type} and optionally a {@link #getColor()
 * color}.
 */
public abstract class PlayTypedObject extends PlayStateObject implements PlayAttributeColor, PlayEvent {

  /**
   * The constructor.
   */
  public PlayTypedObject() {
    super();
  }

  @Override
  protected PlayProperties createProperties() {

    return new PlayProperties(getType().getProperties());
  }

  /**
   * @return the type that classifies this object.
   */
  public abstract PlayObjectType getType();

  @Override
  public String getId() {

    return getType().getId();
  }

}
