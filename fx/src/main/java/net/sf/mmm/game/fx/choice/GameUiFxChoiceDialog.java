/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.game.fx.choice;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import net.sf.mmm.game.engine.Game;
import net.sf.mmm.game.engine.choice.GameChoice;
import net.sf.mmm.game.engine.choice.GameChoiceGroup;
import net.sf.mmm.game.fx.GameUiFxGame;
import net.sf.mmm.game.fx.GameUiFxObject;

/**
 * JavaFx view for {@link GameChoice} or most likely {@link GameChoiceGroup}.
 */
public class GameUiFxChoiceDialog extends Stage implements GameUiFxObject {

  private final GameUiFxGame game;

  private final GridPane gridPane;

  private int rowIndex;

  private GameUiFxChoiceGroup<?> fxChoice;

  private boolean success;

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
    submitButton.setOnAction(x -> onSubmit());
    Button cancelButton = new Button("Cancel");
    cancelButton.setOnAction(x -> onCancel());
    buttonPanel.getChildren().add(submitButton);
    buttonPanel.getChildren().add(cancelButton);
    vBox.getChildren().addAll(this.gridPane, buttonPanel);
    Scene scene = new Scene(vBox);
    scene.getStylesheets().add("Game.css");
    setScene(scene);
    this.game = game;
  }

  /**
   * @return {@code true} if this dialog and its {@link #selectChoice(GameChoice) choice} has been
   *         {@link GameChoice#select(java.util.List) submitted} successfully.
   */
  public boolean isSuccess() {

    return this.success;
  }

  private void onCancel() {

    this.success = false;
    hide();
  }

  private void onSubmit() {

    this.success = this.fxChoice.submit();
    if (this.success) {
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
