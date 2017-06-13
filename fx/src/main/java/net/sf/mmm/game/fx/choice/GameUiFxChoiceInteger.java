/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.game.fx.choice;

import java.util.Collections;
import java.util.List;

import javafx.scene.control.Slider;
import net.sf.mmm.game.engine.choice.GameChoice;
import net.sf.mmm.game.engine.choice.GameChoiceInteger;
import net.sf.mmm.game.engine.choice.GameChoiceOptions;

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
  public Slider getFxControl() {

    return this.slider;
  }

  @Override
  public GameChoiceInteger getGameChoice() {

    return this.choice;
  }

  @Override
  protected List<Integer> getSelectedOptions() {

    double value = this.slider.getValue();
    return Collections.singletonList(Integer.valueOf((int) value));
  }

}
