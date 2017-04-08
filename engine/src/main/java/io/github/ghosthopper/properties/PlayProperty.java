/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.properties;

/**
 * The interface for a property from the {@link PlayProperties}.
 *
 * @param <T> the type of the value of this {@link PlayProperty}.
 */
public interface PlayProperty<T> {

  /**
   * @return the name of this {@link PlayProperty}.
   */
  String getName();

  /**
   * @return the default value used if the property is undefined.
   */
  default T getDefaultValue() {

    return null;
  }

}
