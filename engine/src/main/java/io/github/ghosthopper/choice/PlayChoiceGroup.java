/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.choice;

import java.util.List;

import io.github.ghosthopper.type.PlayType;

/**
 * A {@link PlayChoice} that represents a group of {@link #getChoices() choices}. These choices should logically belong
 * together and can be {@link PlayChoice#select(List) selected} independent of each other and in any order. The view can
 * then ask for all options together in one dialog. However, if the game runs on a limited device with a small screen
 * resolution, the view may decide to split the choices into several dialogs.
 *
 * @param <O> the type of the option. May also be {@link Void} as {@link #select(List)} will be called with an empty
 *        {@link List}.
 * @param <C> the type of the {@link #getChoices() choices} options.
 */
public interface PlayChoiceGroup<O, C> extends PlayChoice<O> {

  /**
   * @return the grouped {@link PlayChoice}s. Has to contain at least one element and to actually make sense at least
   *         two.
   */
  List<? extends PlayChoice<C>> getChoices();

  @Override
  default int getMinOptions() {

    return 0;
  }

  @Override
  default int getMaxOptions() {

    return 0;
  }

  /**
   * @see PlayChoiceType
   * @return the {@link PlayType} used to
   *         {@link io.github.ghosthopper.data.PlayDataUtil#getImageUrl(io.github.ghosthopper.data.PlayView, String, PlayType)
   *         locate} an image to be displayed on top of this {@link PlayChoiceGroup} (together with the
   *         {@link #getDescription() description}) or {@code null} for no image.
   */
  default PlayType getImageType() {

    return null;
  }

}
