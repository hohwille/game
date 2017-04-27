/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.ui.fx;

import io.github.ghosthopper.game.PlayGame;
import io.github.ghosthopper.game.ghosty.Ghosty;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * The main program of the game-engine for JavaFX UI.
 */
public class PlayUiFx extends Application implements PlayUiFxNode {

  private PlayUiFxPositionMapper positionMapper;

  /**
   * The constructor.
   */
  public PlayUiFx() {
    super();
  }

  @Override
  public void start(Stage stage) throws Exception {

    PlayGame game = new Ghosty();
    PlayUiFxGame fxGame = new PlayUiFxGame(game, this);
    stage.setTitle(game.getLocalizedName());
    stage.setScene(fxGame);
    stage.show();
  }

  /**
   * @return the positionMapper
   */
  public PlayUiFxPositionMapper getPositionMapper() {

    if (this.positionMapper == null) {
      this.positionMapper = createPositionMapper();
    }
    return this.positionMapper;
  }

  /**
   * @return a new instance of {@link PlayUiFxPositionMapper}. Override this method to provide your custom extension if
   *         required.
   */
  protected PlayUiFxPositionMapper createPositionMapper() {

    return new PlayUiFxPositionMapper();
  }

  @Override
  public PlayUiFxNode getFxParent() {

    return null;
  }

  @Override
  public PlayUiFx getPlayUiFx() {

    return this;
  }

  /**
   * @param args are the command-line arguments.
   */
  public static void main(String[] args) {

    Application.launch(PlayUiFx.class);
  }
}
