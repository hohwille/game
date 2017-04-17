/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.ui.fx;

import io.github.ghosthopper.game.PlayGame;
import io.github.ghosthopper.game.ghosty.Ghosty;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * The main program of the game-engine for JavaFX UI.
 */
public class PlayUiFx extends Application {

  /**
   * The constructor.
   */
  public PlayUiFx() {
    super();
  }

  @Override
  public void start(Stage stage) throws Exception {

    PlayGame game = new Ghosty();
    PlayUiFxDataCache dataCache = new PlayUiFxDataCache(game.getId());
    PlayUiFxLevel level = new PlayUiFxLevel(game.getCurrentLevel(), dataCache);
    Scene scene = new Scene(level);
    stage.setTitle(game.getLocalizedName());
    stage.setScene(scene);
    stage.show();
  }

  /**
   * @param args are the command-line arguments.
   */
  public static void main(String[] args) {

    Application.launch(PlayUiFx.class);
  }
}
