/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.properties;

/**
 * Extends {@link PlayProperty} with the ability to also be the value itself (acting as a container for the wrapped
 * value(s)). Therefore this acts as {@link PlayProperty} (key) as well as (a container for) the actual value.
 *
 * @see PlayProperties#set(PlayPropertyContainer)
 *
 * @param <T> the type of this {@link PlayPropertyContainer} itself.
 */
public interface PlayPropertyContainer<T extends PlayPropertyContainer<T>> extends PlayProperty<T> {

  // nothing to add...

}
