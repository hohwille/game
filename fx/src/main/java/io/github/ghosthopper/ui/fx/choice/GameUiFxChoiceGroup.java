/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.ui.fx.choice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.github.ghosthopper.choice.GameChoice;
import io.github.ghosthopper.choice.GameChoiceGroup;
import io.github.ghosthopper.type.GameType;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

/**
 * JavaFx view for {@link GameChoiceGroup}.
 *
 * @param <O> the type of the option.
 */
public class GameUiFxChoiceGroup<O> extends GameUiFxChoice<O> {

  private final GameChoiceGroup<O, ?> choice;

  private final List<GameUiFxChoice<?>> children;

  private final HBox topBox;

  private final Label label;

  /**
   * The constructor.
   *
   * @param dialog the {@link #getFxParent() parent}.
   * @param choice the {@link GameChoice}.
   */
  @SuppressWarnings("unchecked")
  public GameUiFxChoiceGroup(GameUiFxChoiceDialog dialog, GameChoiceGroup<O, ?> choice) {
    super(dialog);
    this.choice = choice;
    this.children = new ArrayList<>();
    this.topBox = new HBox(4);
    GameType imageKey = choice.getImageType();
    if (imageKey != null) {
      Image image = getFxDataCache().getImage(imageKey);
      if (image != null) {
        ImageView topImage = new ImageView(image);
        this.topBox.getChildren().add(topImage);
      }
    }
    String description = choice.getLocalizedDescription();
    if (description != null) {
      this.label = new Label(description);
      this.topBox.getChildren().add(this.label);
    } else {
      this.label = null;
    }
    for (GameChoice<?> child : choice.getChoices()) {
      GameUiFxChoice<?> fxChoice = GameUiFxChoice.of(dialog, child);
      this.children.add(fxChoice);
    }
  }

  /**
   * The constructor.
   *
   * @param dialog the {@link #getFxParent() parent}.
   * @param choice the {@link GameChoice}.
   */
  GameUiFxChoiceGroup(GameUiFxChoiceDialog dialog, GameUiFxChoiceSingle<?> choice) {
    super(dialog);
    this.choice = null;
    this.children = new ArrayList<>();
    this.topBox = null;
    this.label = null;
    this.children.add(choice);
  }

  @Override
  public GameChoiceGroup<O, ?> getGameChoice() {

    return this.choice;
  }

  @Override
  protected List<O> getSelectedOptions() {

    return Collections.emptyList();
  }

  @Override
  protected Label getFxLabel() {

    return this.label;
  }

  @Override
  protected Node getFxChoiceNode() {

    return this.topBox;
  }

  @Override
  public boolean submit() {

    boolean success = true;
    for (GameUiFxChoice<?> child : this.children) {
      boolean childSuccess = child.submit();
      if (!childSuccess) {
        success = false;
      }
    }
    if (success) {
      return super.submit();
    }
    return success;
  }

  @Override
  void attachView() {

    if (this.topBox != null) {
      GridPane grid = getFxParent().getGridPane();
      int rowIndex = getFxParent().nextRowIndex();
      grid.add(getFxNode(), 0, rowIndex, 2, 1);
    }
    for (GameUiFxChoice<?> child : this.children) {
      child.attachView();
    }
  }

}
