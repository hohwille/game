/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.ui.fx;

import io.github.ghosthopper.game.Game;
import io.github.ghosthopper.game.ghosty.Ghosty;
import io.github.ghosthopper.ui.fx.choice.GameUiFxChoiceDialog;
import io.github.ghosthopper.ui.fx.game.GameUiFxGame;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * The main program of the game-engine for JavaFX UI.
 */
public class GameUiFx extends Application implements GameUiFxObject {

  private GameUiFxPositionMapper positionMapper;

  /**
   * The constructor.
   */
  public GameUiFx() {
    super();
  }

  @Override
  public void start(Stage stage) throws Exception {

    Game game = new Ghosty();
    game.begin();
    GameUiFxGame fxGame = new GameUiFxGame(game, this);
    GameUiFxChoiceDialog dialog = new GameUiFxChoiceDialog(fxGame);
    dialog.selectChoice(game.getPlayerConfig().getChoice());
    dialog.setOnHidden(e -> {
      if (dialog.isSuccess()) {
        game.start();
        stage.setTitle(game.getLocalizedName());
        stage.setScene(fxGame);
        stage.show();
      }
    });
  }

  /**
   * @return the positionMapper
   */
  public GameUiFxPositionMapper getFxPositionMapper() {

    if (this.positionMapper == null) {
      this.positionMapper = createPositionMapper();
    }
    return this.positionMapper;
  }

  /**
   * @return a new instance of {@link GameUiFxPositionMapper}. Override this method to provide your custom extension if
   *         required.
   */
  protected GameUiFxPositionMapper createPositionMapper() {

    return new GameUiFxPositionMapper();
  }

  @Override
  public GameUiFxObject getFxParent() {

    return null;
  }

  @Override
  public GameUiFx getFxUi() {

    return this;
  }

  /**
   * @param args are the command-line arguments.
   */
  public static void main(String[] args) {

    Application.launch(GameUiFx.class);
  }
}
