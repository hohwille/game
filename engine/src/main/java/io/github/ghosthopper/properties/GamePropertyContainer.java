/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.properties;

/**
 * Extends {@link GameProperty} with the ability to also be the value itself (acting as a container for the wrapped
 * value(s)). Therefore this acts as {@link GameProperty} (key) as well as (a container for) the actual value.
 *
 * @see GameProperties#set(GamePropertyContainer)
 *
 * @param <T> the type of this {@link GamePropertyContainer} itself.
 */
public interface GamePropertyContainer<T extends GamePropertyContainer<T>> extends GameProperty<T> {

  // nothing to add...

}
