/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.choice;

/**
 * A {@link PlayChoice} to {@link #select(java.util.List) select} (an) {@link Integer} value(s).
 */
public interface PlayChoiceInteger extends PlayChoiceSingle<Integer> {

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
