/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.game.engine.item;

import net.sf.mmm.game.engine.properties.GameAttributeProperties;
import net.sf.mmm.game.engine.properties.GamePropertyValueDouble;

/**
 * This is the interface for an item that may have a {@link #getWeight() weight}.
 *
 * @param <S> this {@link GameAttributeWeight} itself.
 */
public interface GameAttributeWeight<S extends GameAttributeWeight<S>> extends GameAttributeProperties {

  /**
   * @return the weight of this item. Will be {@code 0} if the weight does not matter.
   */
  default double getWeight() {

    return getProperties().get(GamePropertyValueDouble.WEIGHT).get();
  }

  /**
   * @param weight the new value of {@link #getWeight()}.
   * @return this instance itself for fluent API calls.
   */
  @SuppressWarnings("unchecked")
  default S setWeight(double weight) {

    getProperties().get(GamePropertyValueDouble.WEIGHT).set(weight);
    return (S) this;
  }

}
