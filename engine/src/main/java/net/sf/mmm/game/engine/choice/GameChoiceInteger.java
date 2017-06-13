/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.game.engine.choice;

/**
 * A {@link GameChoice} to {@link #select(java.util.List) select} (an) {@link Integer} value(s).
 */
public interface GameChoiceInteger extends GameChoiceSingle<Integer> {

  /**
   * @return the minimum value to {@link #select(java.util.List) select}.
   */
  default int getMinValue() {

    return 1;
  }

  /**
   * @return the maximum value to {@link #select(java.util.List) select}.
   */
  int getMaxValue();

}
