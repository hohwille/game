/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.ui.fx.choice;

import java.util.Collections;
import java.util.List;

import io.github.ghosthopper.choice.PlayChoice;
import io.github.ghosthopper.choice.PlayChoiceOptions;
import javafx.scene.control.ComboBox;

/**
 * JavaFx view for {@link PlayChoiceOptions}.
 *
 * @param <O> the type of the option.
 */
public class PlayUiFxChoiceOptionsComboBox<O> extends PlayUiFxChoiceSingle<O> {

  private final PlayChoiceOptions<O> choice;

  private final ComboBox<O> comboBox;

  /**
   * The constructor.
   *
   * @param dialog the {@link #getFxParent() parent}.
   * @param choice the {@link PlayChoice}.
   */
  public PlayUiFxChoiceOptionsComboBox(PlayUiFxChoiceDialog dialog, PlayChoiceOptions<O> choice) {
    super(dialog, choice);
    this.choice = choice;
    this.comboBox = new ComboBox<>();
  }

  @Override
  public ComboBox<O> getControl() {

    return this.comboBox;
  }

  @Override
  public PlayChoiceOptions<O> getChoice() {

    return this.choice;
  }

  @Override
  protected List<O> getSelection() {

    O selection = this.comboBox.getSelectionModel().getSelectedItem();
    return Collections.singletonList(selection);
  }

}
