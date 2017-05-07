/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.ui.fx.choice;

import java.util.List;

import io.github.ghosthopper.choice.GameChoice;
import io.github.ghosthopper.choice.GameChoiceGroup;
import io.github.ghosthopper.choice.GameChoiceInteger;
import io.github.ghosthopper.choice.GameChoiceOptions;
import io.github.ghosthopper.choice.GameChoiceSingle;
import io.github.ghosthopper.data.GameSeverity;
import io.github.ghosthopper.ui.fx.GameUiFxObject;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

/**
 * JavaFx view for {@link GameChoiceSingle}.
 *
 * @param <O> the type of the option.
 */
public abstract class GameUiFxChoice<O> implements GameUiFxObject {

  private static final String STYLE_INVALID = "invalid";

  private final GameUiFxChoiceDialog dialog;

  private GameUiFxSeverityDecoration decoration;

  private StackPane errorPane;

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
  public abstract GameChoice<O> getGameChoice();

  /**
   * @return the {@link StackPane} used to decorate the actual choice view with potential error messages.
   */
  @Override
  public StackPane getFxNode() {

    if (this.errorPane == null) {
      this.errorPane = new StackPane();
      this.errorPane.getChildren().addAll(getFxChoiceNode(), getFxDecoration());
      StackPane.setAlignment(this.decoration, Pos.CENTER_RIGHT);
    }
    return this.errorPane;
  }

  /**
   * @return the {@link GameUiFxSeverityDecoration}.
   */
  private GameUiFxSeverityDecoration getFxDecoration() {

    if (this.decoration == null) {
      this.decoration = new GameUiFxSeverityDecoration(GameSeverity.ERROR, this);
    }
    return this.decoration;
  }

  /**
   * @return the {@link Label} or {@code null} if none exists.
   */
  protected abstract Label getFxLabel();

  /**
   * @return the main {@link Node} to decorate via {@link #getFxNode()}.
   */
  protected abstract Node getFxChoiceNode();

  /**
   * Submits this {@link GameChoice} and performs the {@link GameChoice#select(java.util.List) selection}.
   *
   * @return {@code true} on success, {@code false} otherwise (validation error).
   */
  public boolean submit() {

    String error = getGameChoice().select(getSelectedOptions());
    handleError(error);
    return (error == null);
  }

  /**
   * Called from {@link #submit()} to handle potential error(s). Will clear or show error feedback to the user.
   *
   * @param error the error message to show or {@code null} to clear the error message.
   */
  protected void handleError(String error) {

    getFxDecoration().setMessage(error);
    Label fxLabel = getFxLabel();
    if (fxLabel != null) {
      if (error == null) {
        fxLabel.getStyleClass().remove(STYLE_INVALID);
      } else {
        fxLabel.getStyleClass().add(STYLE_INVALID);
      }
    }
  }

  /**
   * @return the currently selected options.
   */
  protected abstract List<O> getSelectedOptions();

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
