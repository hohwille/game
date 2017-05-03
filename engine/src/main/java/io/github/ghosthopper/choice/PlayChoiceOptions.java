/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.choice;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import io.github.ghosthopper.object.PlayObject;

/**
 * A {@link PlayObject} to {@link #select(List) select} from a given {@link List} of {@link #getOptions() options}.
 * Please note that a {@link #isUniqueSelection() unique} instance of {@link PlayChoiceOptions} is invalid if
 * {@link #getMaxOptions()} is greater than the {@link List#size() size} of the {@link #getOptions() options} or if both
 * are equal to {@link #getMinOptions()}.
 *
 * @param <O> the type of the option.
 */
public interface PlayChoiceOptions<O> extends PlayChoiceSingle<O> {

  /**
   * @return the predefined options to choose the {@link #select(List) selection} from.
   */
  List<O> getOptions();

  /**
   * @return the {@link List} of {@link #getOptions() options} to use as pre-selected default.
   */
  default Collection<O> getDefaultSelection() {

    int minOptions = getMinOptions();
    List<O> options = getOptions();
    int size = options.size();
    boolean unique = isUniqueSelection();
    if (unique && (minOptions > size)) {
      throw new IllegalStateException("minOptions is " + minOptions + " but only " + size + " option(s) available for unique selection");
    }
    Collection<O> defaults;
    if (unique) {
      defaults = new HashSet<>();
    } else {
      defaults = new ArrayList<>();
    }
    for (int i = 0; i < minOptions; i++) {
      int index = i % size;
      defaults.add(options.get(index));
    }
    return defaults;
  }

}
