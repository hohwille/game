/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.object;

import io.github.ghosthopper.color.PlayAttributeColor;
import io.github.ghosthopper.color.PlayColor;
import io.github.ghosthopper.properties.PlayProperties;

/**
 * This is the abstract base class for an object that has a {@link #getType() type} and optionally a {@link #getColor()
 * color}.
 */
public abstract class PlayTypedObject extends PlayStateObject implements PlayAttributeColor {

  private PlayColor color;

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
   * The constructor.
   *
   * @param color - see {@link #getColor()}.
   */
  public PlayTypedObject(PlayColor color) {
    super();
    this.color = color;
  }

  @Override
  public PlayColor getColor() {

    return this.color;
  }

  /**
   * @param color the new value of {@link #getColor()}.
   */
  public void setColor(PlayColor color) {

    this.color = color;
  }

  /**
   * @return the type that classifies this object.
   */
  public abstract PlayObjectType getType();

  @Override
  protected String getId() {

    return getType().getId();
  }

}
