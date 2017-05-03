/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.ui.fx.choice;

import java.util.List;

import io.github.ghosthopper.choice.PlayChoice;
import io.github.ghosthopper.choice.PlayChoiceGroup;
import io.github.ghosthopper.choice.PlayChoiceInteger;
import io.github.ghosthopper.choice.PlayChoiceOptions;
import io.github.ghosthopper.choice.PlayChoiceSingle;
import io.github.ghosthopper.ui.fx.PlayUiFxNode;

/**
 * JavaFx view for {@link PlayChoiceSingle}.
 *
 * @param <O> the type of the option.
 */
public abstract class PlayUiFxChoice<O> implements PlayUiFxNode {

  private final PlayUiFxChoiceDialog dialog;

  /**
   * The constructor.
   *
   * @param dialog the {@link #getFxParent() parent}.
   */
  PlayUiFxChoice(PlayUiFxChoiceDialog dialog) {
    super();
    this.dialog = dialog;
  }

  @Override
  public PlayUiFxChoiceDialog getFxParent() {

    return this.dialog;
  }

  /**
   * @return the represented {@link PlayChoice}.
   */
  public abstract PlayChoice<O> getChoice();

  /**
   * Submits this {@link PlayChoice} and performs the {@link PlayChoice#select(java.util.List) selection}.
   *
   * @return {@code true} on success, {@code false} otherwise (validation error).
   */
  public boolean submit() {

    String error = getChoice().select(getSelection());
    handleError(error);
    return (error == null);
  }

  protected void handleError(String error) {

    if (error == null) {

    } else {

    }
  }

  /**
   * @return the currently selected options.
   */
  protected abstract List<O> getSelection();

  abstract void attachView();

  @SuppressWarnings("unchecked")
  static <O> PlayUiFxChoice<O> of(PlayUiFxChoiceDialog dialog, PlayChoice<O> choice) {

    PlayUiFxChoice<?> fxChoice;
    if (choice instanceof PlayChoiceInteger) {
      fxChoice = new PlayUiFxChoiceInteger(dialog, (PlayChoiceInteger) choice);
    } else if (choice instanceof PlayChoiceOptions) {
      fxChoice = new PlayUiFxChoiceOptionsList<>(dialog, (PlayChoiceOptions<?>) choice);
    } else if (choice instanceof PlayChoiceGroup) {
      fxChoice = new PlayUiFxChoiceGroup<>(dialog, (PlayChoiceGroup<?, ?>) choice);
    } else {
      throw new IllegalStateException(choice.getClass().getName());
    }
    return (PlayUiFxChoice<O>) fxChoice;
  }

}
