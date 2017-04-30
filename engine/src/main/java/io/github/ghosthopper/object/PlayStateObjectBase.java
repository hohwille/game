/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.object;

import io.github.ghosthopper.properties.PlayProperties;

/**
 * A {@link PlayObjectBase} that has a state such as {@link #getProperties() properties}.
 */
public abstract class PlayStateObjectBase extends PlayObjectBase implements PlayStateObject {

  private PlayProperties properties;

  /**
   * The constructor.
   */
  public PlayStateObjectBase() {
    super();
  }

  @Override
  public PlayProperties getProperties() {

    if (this.properties == null) {
      this.properties = createProperties();
    }
    return this.properties;
  }

  /**
   * @return the new {@link #getProperties() properties} instance for lazy initialization.
   */
  protected PlayProperties createProperties() {

    return new PlayProperties();
  }

}
