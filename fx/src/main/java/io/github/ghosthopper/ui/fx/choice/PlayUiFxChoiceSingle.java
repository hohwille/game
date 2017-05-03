/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.ui.fx.choice;

import io.github.ghosthopper.choice.PlayChoice;
import io.github.ghosthopper.choice.PlayChoiceSingle;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.GridPane;

/**
 * JavaFx view for {@link PlayChoice}.
 *
 * @param <O> the type of the option.
 */
public abstract class PlayUiFxChoiceSingle<O> extends PlayUiFxChoice<O> {

  private final Label label;

  private final Tooltip tooltip;

  /**
   * The constructor.
   *
   * @param dialog the {@link #getFxParent() parent}.
   * @param choice the {@link PlayChoice}.
   */
  public PlayUiFxChoiceSingle(PlayUiFxChoiceDialog dialog, PlayChoiceSingle<O> choice) {
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

  /**
   * @return the {@link Label}.
   */
  protected Label getLabel() {

    return this.label;
  }

  /**
   * @return the {@link Tooltip} or {@code null} if no description was available.
   */
  protected Tooltip getTooltip() {

    return this.tooltip;
  }

  /**
   * @return the JavaFx {@link Control} used to
   */
  public abstract Control getControl();

  /**
   * @return the represented {@link PlayChoice}.
   */
  @Override
  public abstract PlayChoiceSingle<O> getChoice();

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
    Control control = getControl();
    if ((this.tooltip != null) && (control.getTooltip() == null)) {
      control.setTooltip(this.tooltip);
    }
    grid.add(control, 1, rowIndex);
  }

}
