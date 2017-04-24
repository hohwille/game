/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.object;

import io.github.ghosthopper.event.PlayEvent;
import io.github.ghosthopper.properties.PlayProperties;

/**
 * This is the abstract base class for an object that has a {@link #getType() type} and optionally a {@link #getColor()
 * color}.
 */
public abstract class AbstractPlayTypedObject extends AbstractPlayStateObject implements PlayTypedObject, PlayEvent {

  /**
   * The constructor.
   */
  public AbstractPlayTypedObject() {
    super();
  }

  @Override
  protected PlayProperties createProperties() {

    return new PlayProperties(getType().getProperties());
  }

  @Override
  public String getId() {

    return getType().getId();
  }

}
