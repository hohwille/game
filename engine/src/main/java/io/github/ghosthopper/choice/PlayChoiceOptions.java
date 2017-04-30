/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.choice;

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
public interface PlayChoiceOptions<O> extends PlayChoice<O> {

  /**
   * @return the predefined options to choose the {@link #select(List) selection} from.
   */
  List<O> getOptions();

}
