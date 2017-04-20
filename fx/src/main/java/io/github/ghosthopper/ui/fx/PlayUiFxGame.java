/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.ui.fx;

import java.util.HashMap;
import java.util.Map;

import io.github.ghosthopper.PlayLevel;
import io.github.ghosthopper.event.PlayKeyEvent;
import io.github.ghosthopper.field.PlayField;
import io.github.ghosthopper.figure.PlayFigure;
import io.github.ghosthopper.game.PlayGame;
import io.github.ghosthopper.player.Player;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;

/**
 * JavaFx view for a {@link PlayLevel}.
 */
public class PlayUiFxGame extends Scene {

  private final PlayGame game;

  private final PlayUiFxDataCache dataCache;

  private PlayUiFxLevel level;

  private final Map<PlayFigure, PlayUiFxFigure> figureMap;

  /**
   * The constructor.
   *
   * @param game the {@link PlayGame} to visualize.
   */
  public PlayUiFxGame(PlayGame game) {
    super(new VBox());
    this.game = game;
    this.game.start();
    this.dataCache = new PlayUiFxDataCache(game.getId());
    this.figureMap = new HashMap<>();
    this.level = new PlayUiFxLevel(game.getCurrentLevel(), this);
    initPlayers();
    ((VBox) getRoot()).getChildren().add(this.level);
    setOnKeyPressed(this::handleKeyEvent);
    this.game.addListener(PlayFigure.class, this::onUpdateFigure);
  }

  private void initPlayers() {

    for (Player player : this.game.getPlayers()) {
      for (PlayFigure figure : player.getFigures()) {
        PlayUiFxFigure fxFigure = new PlayUiFxFigure(figure, this.dataCache);
        this.figureMap.put(figure, fxFigure);
        PlayField field = figure.getLocation();
        if (field != null) {
          PlayUiFxField playField = this.level.getFxField(field);
          if (playField != null) {
            fxFigure.setPlayField(playField);
          }
        }
      }
    }
  }

  /**
   * @param figure the {@link PlayFigure}.
   * @return the corresponding {@link PlayUiFxFigure}.
   */
  public PlayUiFxFigure getFxFigure(PlayFigure figure) {

    return this.figureMap.get(figure);
  }

  private void handleKeyEvent(KeyEvent keyEvent) {

    PlayKeyEvent event = PlayUiFxKeyEvent.convertEvent(keyEvent);
    if (event != null) {
      this.game.sendEvent(event);
    }
  }

  private void onUpdateFigure(PlayFigure figure) {

    PlayUiFxFigure fxFigure = getFxFigure(figure);
    if (fxFigure != null) {
      PlayUiFxField fxField = this.level.getFxField(figure.getLocation());
      fxFigure.setPlayField(fxField);
    }
  }

  /**
   * @return the dataCache
   */
  public PlayUiFxDataCache getDataCache() {

    return this.dataCache;
  }

  /**
   * @return the {@link PlayGame}.
   */
  public PlayGame getPlayGame() {

    return this.game;
  }

}
