/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper;

/**
 * This is the abstract base class for an object that optionally can have a {@link #getColor() color}.
 */
public abstract class PlayObjectWithColorAndType extends PlayObject implements PlayAttributeColor {

  private PlayColor color;

  /**
   * The constructor.
   */
  public PlayObjectWithColorAndType() {
    super();
  }

  /**
   * The constructor.
   *
   * @param color - see {@link #getColor()}.
   */
  public PlayObjectWithColorAndType(PlayColor color) {
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
