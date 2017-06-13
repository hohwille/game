/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.game.fx.choice;

import java.util.Collections;
import java.util.List;

import javafx.scene.control.ComboBox;
import net.sf.mmm.game.engine.choice.GameChoice;
import net.sf.mmm.game.engine.choice.GameChoiceOptions;

/**
 * JavaFx view for {@link GameChoiceOptions}.
 *
 * @param <O> the type of the option.
 */
public class GameUiFxChoiceOptionsComboBox<O> extends GameUiFxChoiceSingle<O> {

  private final GameChoiceOptions<O> choice;

  private final ComboBox<O> comboBox;

  /**
   * The constructor.
   *
   * @param dialog the {@link #getFxParent() parent}.
   * @param choice the {@link GameChoice}.
   */
  public GameUiFxChoiceOptionsComboBox(GameUiFxChoiceDialog dialog, GameChoiceOptions<O> choice) {
    super(dialog, choice);
    this.choice = choice;
    this.comboBox = new ComboBox<>();
  }

  @Override
  public ComboBox<O> getFxControl() {

    return this.comboBox;
  }

  @Override
  public GameChoiceOptions<O> getGameChoice() {

    return this.choice;
  }

  @Override
  protected List<O> getSelectedOptions() {

    O selection = this.comboBox.getSelectionModel().getSelectedItem();
    return Collections.singletonList(selection);
  }

}
