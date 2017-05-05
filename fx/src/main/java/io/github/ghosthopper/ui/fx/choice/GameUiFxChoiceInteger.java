/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.ui.fx.choice;

import java.util.Collections;
import java.util.List;

import io.github.ghosthopper.choice.GameChoice;
import io.github.ghosthopper.choice.GameChoiceInteger;
import io.github.ghosthopper.choice.GameChoiceOptions;
import javafx.scene.control.Slider;

/**
 * JavaFx view for {@link GameChoiceOptions}.
 */
public class GameUiFxChoiceInteger extends GameUiFxChoiceSingle<Integer> {

  private final GameChoiceInteger choice;

  private final Slider slider;

  /**
   * The constructor.
   *
   * @param dialog the {@link #getFxParent() parent}.
   * @param choice the {@link GameChoice}.
   */
  public GameUiFxChoiceInteger(GameUiFxChoiceDialog dialog, GameChoiceInteger choice) {
    super(dialog, choice);
    this.choice = choice;
    this.slider = new Slider();
    this.slider.setMin(choice.getMinValue());
    this.slider.setMax(choice.getMaxValue());
    this.slider.setShowTickLabels(true);
    this.slider.setShowTickMarks(true);
  }

  @Override
  public Slider getControl() {

    return this.slider;
  }

  @Override
  public GameChoiceInteger getChoice() {

    return this.choice;
  }

  @Override
  protected List<Integer> getSelection() {

    double value = this.slider.getValue();
    return Collections.singletonList(Integer.valueOf((int) value));
  }

}
