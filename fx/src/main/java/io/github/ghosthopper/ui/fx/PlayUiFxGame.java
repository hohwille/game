/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.ui.fx;

import java.util.HashMap;
import java.util.Map;

import io.github.ghosthopper.PlayLevel;
import io.github.ghosthopper.border.PlayBorder;
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

  private final Map<PlayField, PlayUiFxField> fieldMap;

  private final Map<PlayBorder, PlayUiFxBorder> borderMap;

  /**
   * The constructor.
   *
   * @param game the {@link PlayGame} to visualize.
   */
  public PlayUiFxGame(PlayGame game) {
    super(new VBox());
    this.game = game;
    this.fieldMap = new HashMap<>();
    this.borderMap = new HashMap<>();
    this.dataCache = new PlayUiFxDataCache(game.getId());
    this.figureMap = new HashMap<>();
    this.game.start();
    this.level = new PlayUiFxLevel(game.getCurrentLevel(), this);
    initPlayers();
    ((VBox) getRoot()).getChildren().add(this.level);
    setOnKeyPressed(this::handleKeyEvent);
    this.game.addListener(PlayFigure.class, this::onUpdateFigure);
    this.game.addListener(PlayBorder.class, this::onUpdateBorder);
  }

  private void initPlayers() {

    for (Player player : this.game.getPlayers()) {
      for (PlayFigure figure : player.getFigures()) {
        PlayUiFxFigure fxFigure = new PlayUiFxFigure(figure, this.dataCache);
        this.figureMap.put(figure, fxFigure);
        PlayField field = figure.getLocation();
        if (field != null) {
          PlayUiFxField playField = getFxField(field);
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
      PlayUiFxField fxField = getFxField(figure.getLocation());
      fxFigure.setPlayField(fxField);
      fxFigure.update();
    }
  }

  private void onUpdateBorder(PlayBorder border) {

    PlayUiFxBorder fxBorder = getFxBorder(border);
    if (fxBorder != null) {
      fxBorder.update();
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

  /**
   * @param fxField the {@link PlayUiFxField} to add.
   */
  void addFxField(PlayUiFxField fxField) {

    this.fieldMap.put(fxField.getPlayField(), fxField);
  }

  /**
   * @param field the {@link PlayField}.
   * @return the corresponding {@link PlayUiFxField} or {@code null} if undefined.
   */
  public PlayUiFxField getFxField(PlayField field) {

    return this.fieldMap.get(field);
  }

  /**
   * @param fxField the {@link PlayUiFxBorder} to add.
   */
  void addFxBorder(PlayUiFxBorder fxBorder) {

    this.borderMap.put(fxBorder.getPlayBorder(), fxBorder);
  }

  /**
   * @param border the {@link PlayBorder}.
   * @return the corresponding {@link PlayUiFxBorder} or {@code null} if undefined.
   */
  public PlayUiFxBorder getFxBorder(PlayBorder border) {

    return this.borderMap.get(border);
  }

}
