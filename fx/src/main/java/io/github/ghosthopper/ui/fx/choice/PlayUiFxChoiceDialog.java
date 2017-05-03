/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.ui.fx.choice;

import io.github.ghosthopper.choice.PlayChoice;
import io.github.ghosthopper.choice.PlayChoiceGroup;
import io.github.ghosthopper.game.PlayGame;
import io.github.ghosthopper.i18n.PlayTranslator;
import io.github.ghosthopper.ui.fx.PlayUiFxGame;
import io.github.ghosthopper.ui.fx.PlayUiFxNode;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * JavaFx view for {@link PlayChoice} or most likely {@link PlayChoiceGroup}.
 */
public class PlayUiFxChoiceDialog extends Stage implements PlayUiFxNode {

  private final PlayUiFxGame game;

  private final GridPane gridPane;

  private final PlayTranslator translator;

  private int rowIndex;

  private PlayUiFxChoiceGroup<?> fxChoice;

  /**
   * The constructor.
   *
   * @param game the {@link PlayGame}.
   */
  public PlayUiFxChoiceDialog(PlayUiFxGame game) {
    super();
    this.gridPane = new GridPane();
    this.gridPane.setHgap(4);
    this.gridPane.setVgap(4);
    VBox vBox = new VBox(4);
    vBox.setPadding(new Insets(4, 4, 4, 4));
    HBox buttonPanel = new HBox(10);
    buttonPanel.setAlignment(Pos.CENTER);
    Button submitButton = new Button("Submit");
    submitButton.setOnAction(x -> submit());
    Button cancelButton = new Button("Cancel");
    buttonPanel.getChildren().add(submitButton);
    buttonPanel.getChildren().add(cancelButton);
    vBox.getChildren().addAll(this.gridPane, buttonPanel);
    Scene scene = new Scene(vBox);
    setScene(scene);
    this.game = game;
    this.translator = getFxGame().getPlayGame().getTranslator();
  }

  private void submit() {

    boolean success = this.fxChoice.submit();
    // boolean success = true;
    // for (PlayChoice<?> choice : this.choiceList) {
    // PlayUiFxChoice fxChoice = this.choiceMap.get(choice);
    // boolean choiceSuccess;
    // if (fxChoice == null) {
    // choiceSuccess = fxChoice.submit();
    // } else {
    // choiceSuccess = ;
    // }
    // if (!choiceSuccess) {
    // success = false;
    // }
    // }
    if (success) {
      hide();
    }
  }

  @Override
  public PlayUiFxNode getFxParent() {

    return this.game;
  }

  /**
   * @return the {@link GridPane}.
   */
  GridPane getGridPane() {

    return this.gridPane;
  }

  int nextRowIndex() {

    return this.rowIndex++;
  }

  /**
   * Will clear this dialog an (re)initialize it for the given {@link PlayChoice} and then {@link #show() show} this
   * dialog. On Submission the {@link PlayChoice} will be {@link PlayChoice#select(java.util.List) selected}.
   *
   * @param choice the {@link PlayChoice} to perform.
   */
  public void selectChoice(PlayChoice<?> choice) {

    this.rowIndex = 0;
    this.gridPane.getChildren().clear();
    PlayUiFxChoice<?> fxChoiceAny = PlayUiFxChoice.of(this, choice);
    if (fxChoiceAny instanceof PlayUiFxChoiceGroup) {
      this.fxChoice = (PlayUiFxChoiceGroup<?>) fxChoiceAny;
    } else {
      this.fxChoice = new PlayUiFxChoiceGroup<>(this, (PlayUiFxChoiceSingle<?>) fxChoiceAny);
    }
    this.fxChoice.attachView();
    sizeToScene();
    show();
  }

}
