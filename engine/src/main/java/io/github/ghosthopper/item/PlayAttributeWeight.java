/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.item;

import io.github.ghosthopper.properties.PlayAttributeProperties;
import io.github.ghosthopper.properties.PlayPropertyValueDouble;

/**
 * This is the interface for an item that may have a {@link #getWeight() weight}.
 *
 * @param <S> this {@link PlayAttributeWeight} itself.
 */
public interface PlayAttributeWeight<S extends PlayAttributeWeight<S>> extends PlayAttributeProperties {

  /**
   * @return the weight of this item. Will be {@code 0} if the weight does not matter.
   */
  default double getWeight() {

    return getProperties().get(PlayPropertyValueDouble.WEIGHT).get();
  }

  /**
   * @param weight the new value of {@link #getWeight()}.
   * @return this instance itself for fluent API calls.
   */
  @SuppressWarnings("unchecked")
  default S setWeight(double weight) {

    getProperties().get(PlayPropertyValueDouble.WEIGHT).set(weight);
    return (S) this;
  }

}
