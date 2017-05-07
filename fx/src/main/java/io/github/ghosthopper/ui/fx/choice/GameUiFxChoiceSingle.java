/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.ui.fx.choice;

import io.github.ghosthopper.choice.GameChoice;
import io.github.ghosthopper.choice.GameChoiceSingle;
import javafx.scene.Node;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.GridPane;

/**
 * JavaFx view for {@link GameChoice}.
 *
 * @param <O> the type of the option.
 */
public abstract class GameUiFxChoiceSingle<O> extends GameUiFxChoice<O> {

  private final Label label;

  private final Tooltip tooltip;

  /**
   * The constructor.
   *
   * @param dialog the {@link #getFxParent() parent}.
   * @param choice the {@link GameChoice}.
   */
  public GameUiFxChoiceSingle(GameUiFxChoiceDialog dialog, GameChoiceSingle<O> choice) {
    super(dialog);
    this.label = new Label(choice.getLocalizedName() + ":");
    String description = choice.getLocalizedDescription();
    if (description == null) {
      this.tooltip = null;
    } else {
      this.tooltip = new Tooltip(description);
      this.label.setTooltip(this.tooltip);
    }
  }

  @Override
  protected Label getFxLabel() {

    return this.label;
  }

  /**
   * @return the {@link Tooltip} or {@code null} if no description was available.
   */
  protected Tooltip getFxTooltip() {

    return this.tooltip;
  }

  @Override
  protected Node getFxChoiceNode() {

    return getFxControl();
  }

  /**
   * @return the JavaFx {@link Control} used to view and edit the choice.
   */
  protected abstract Control getFxControl();

  /**
   * @return the represented {@link GameChoice}.
   */
  @Override
  public abstract GameChoiceSingle<O> getGameChoice();

  @Override
  protected void handleError(String error) {

    // TODO Auto-generated method stub
    super.handleError(error);
  }

  @Override
  void attachView() {

    GridPane grid = getFxParent().getGridPane();
    int rowIndex = getFxParent().nextRowIndex();
    grid.add(this.label, 0, rowIndex);
    Control control = getFxControl();
    if ((this.tooltip != null) && (control.getTooltip() == null)) {
      control.setTooltip(this.tooltip);
    }
    grid.add(getFxNode(), 1, rowIndex);
  }

}
