/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.choice;

import java.util.List;

/**
 * A {@link PlayChoice} that represents a group of {@link #getChoices() choices}. These choices should logically belong
 * together and can be {@link PlayChoice#select(List) selected} independent of each other and in any order. The view can
 * then ask for all options together in one dialog. However, if the game runs on a limited device with a small screen
 * resolution, the view may decide to split the choices into several dialogs.
 *
 * @param <O> the type of the option. Typically {@link Void} as {@link #select(List)} will be called with an empty
 *        {@link List}.
 */
public interface PlayChoiceGroup<O> extends PlayChoice<O> {

  /**
   * @return the grouped {@link PlayChoice}s. Has to contain at least one element and to actually make sense at least
   *         two.
   */
  List<PlayChoice<?>> getChoices();

}
