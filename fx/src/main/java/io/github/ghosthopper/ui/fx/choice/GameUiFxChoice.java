/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.ui.fx.choice;

import java.util.List;

import io.github.ghosthopper.choice.GameChoice;
import io.github.ghosthopper.choice.GameChoiceGroup;
import io.github.ghosthopper.choice.GameChoiceInteger;
import io.github.ghosthopper.choice.GameChoiceOptions;
import io.github.ghosthopper.choice.GameChoiceSingle;
import io.github.ghosthopper.ui.fx.GameUiFxObject;

/**
 * JavaFx view for {@link GameChoiceSingle}.
 *
 * @param <O> the type of the option.
 */
public abstract class GameUiFxChoice<O> implements GameUiFxObject {

  private final GameUiFxChoiceDialog dialog;

  /**
   * The constructor.
   *
   * @param dialog the {@link #getFxParent() parent}.
   */
  GameUiFxChoice(GameUiFxChoiceDialog dialog) {
    super();
    this.dialog = dialog;
  }

  @Override
  public GameUiFxChoiceDialog getFxParent() {

    return this.dialog;
  }

  /**
   * @return the represented {@link GameChoice}.
   */
  public abstract GameChoice<O> getChoice();

  /**
   * Submits this {@link GameChoice} and performs the {@link GameChoice#select(java.util.List) selection}.
   *
   * @return {@code true} on success, {@code false} otherwise (validation error).
   */
  public boolean submit() {

    String error = getChoice().select(getSelection());
    handleError(error);
    return (error == null);
  }

  /**
   * Called from {@link #submit()} to handle potential error(s). Will clear or show error feedback to the user.
   *
   * @param error the error message to show or {@code null} to clear the error message.
   */
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
  static <O> GameUiFxChoice<O> of(GameUiFxChoiceDialog dialog, GameChoice<O> choice) {

    GameUiFxChoice<?> fxChoice;
    if (choice instanceof GameChoiceInteger) {
      fxChoice = new GameUiFxChoiceInteger(dialog, (GameChoiceInteger) choice);
    } else if (choice instanceof GameChoiceOptions) {
      fxChoice = new GameUiFxChoiceOptionsList<>(dialog, (GameChoiceOptions<?>) choice);
    } else if (choice instanceof GameChoiceGroup) {
      fxChoice = new GameUiFxChoiceGroup<>(dialog, (GameChoiceGroup<?, ?>) choice);
    } else {
      throw new IllegalStateException(choice.getClass().getName());
    }
    return (GameUiFxChoice<O>) fxChoice;
  }

}
