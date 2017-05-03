/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.ui.fx.choice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.github.ghosthopper.choice.PlayChoice;
import io.github.ghosthopper.choice.PlayChoiceGroup;
import io.github.ghosthopper.type.PlayType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

/**
 * JavaFx view for {@link PlayChoiceGroup}.
 *
 * @param <O> the type of the option.
 */
public class PlayUiFxChoiceGroup<O> extends PlayUiFxChoice<O> {

  private final PlayChoiceGroup<O, ?> choice;

  private final List<PlayUiFxChoice<?>> children;

  private final HBox topBox;

  /**
   * The constructor.
   *
   * @param dialog the {@link #getFxParent() parent}.
   * @param choice the {@link PlayChoice}.
   */
  @SuppressWarnings("unchecked")
  public PlayUiFxChoiceGroup(PlayUiFxChoiceDialog dialog, PlayChoiceGroup<O, ?> choice) {
    super(dialog);
    this.choice = choice;
    this.children = new ArrayList<>();
    this.topBox = new HBox(4);
    PlayType imageKey = choice.getImageType();
    if (imageKey != null) {
      Image image = getFxDataCache().getImage(imageKey);
      if (image != null) {
        ImageView topImage = new ImageView(image);
        this.topBox.getChildren().add(topImage);
      }
    }
    String description = choice.getLocalizedDescription();
    if (description != null) {
      this.topBox.getChildren().add(new Label(description));
    }
    for (PlayChoice<?> child : choice.getChoices()) {
      PlayUiFxChoice<?> fxChoice = PlayUiFxChoice.of(dialog, child);
      this.children.add(fxChoice);
    }
  }

  /**
   * The constructor.
   *
   * @param dialog the {@link #getFxParent() parent}.
   * @param choice the {@link PlayChoice}.
   */
  PlayUiFxChoiceGroup(PlayUiFxChoiceDialog dialog, PlayUiFxChoiceSingle<?> choice) {
    super(dialog);
    this.choice = null;
    this.children = new ArrayList<>();
    this.topBox = null;
    this.children.add(choice);
  }

  @Override
  public PlayChoiceGroup<O, ?> getChoice() {

    return this.choice;
  }

  @Override
  protected List<O> getSelection() {

    return Collections.emptyList();
  }

  @Override
  public boolean submit() {

    boolean success = true;
    for (PlayUiFxChoice<?> child : this.children) {
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
      grid.add(this.topBox, 0, rowIndex);
    }
    for (PlayUiFxChoice<?> child : this.children) {
      child.attachView();
    }
  }

}
