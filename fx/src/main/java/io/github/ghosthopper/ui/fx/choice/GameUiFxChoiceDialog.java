/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.ui.fx.choice;

import io.github.ghosthopper.choice.GameChoice;
import io.github.ghosthopper.choice.GameChoiceGroup;
import io.github.ghosthopper.game.Game;
import io.github.ghosthopper.ui.fx.GameUiFxGame;
import io.github.ghosthopper.ui.fx.GameUiFxObject;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * JavaFx view for {@link GameChoice} or most likely {@link GameChoiceGroup}.
 */
public class GameUiFxChoiceDialog extends Stage implements GameUiFxObject {

  private final GameUiFxGame game;

  private final GridPane gridPane;

  private int rowIndex;

  private GameUiFxChoiceGroup<?> fxChoice;

  /**
   * The constructor.
   *
   * @param game the {@link Game}.
   */
  public GameUiFxChoiceDialog(GameUiFxGame game) {
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
  public GameUiFxObject getFxParent() {

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
   * Will clear this dialog an (re)initialize it for the given {@link GameChoice} and then {@link #show() show} this
   * dialog. On Submission the {@link GameChoice} will be {@link GameChoice#select(java.util.List) selected}.
   *
   * @param choice the {@link GameChoice} to perform.
   */
  public void selectChoice(GameChoice<?> choice) {

    this.rowIndex = 0;
    this.gridPane.getChildren().clear();
    GameUiFxChoice<?> fxChoiceAny = GameUiFxChoice.of(this, choice);
    if (fxChoiceAny instanceof GameUiFxChoiceGroup) {
      this.fxChoice = (GameUiFxChoiceGroup<?>) fxChoiceAny;
    } else {
      this.fxChoice = new GameUiFxChoiceGroup<>(this, (GameUiFxChoiceSingle<?>) fxChoiceAny);
    }
    this.fxChoice.attachView();
    sizeToScene();
    show();
  }

}
