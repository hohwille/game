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
import io.github.ghosthopper.figure.PlayFigureMoveEvent;
import io.github.ghosthopper.figure.PlayFigureTurnEvent;
import io.github.ghosthopper.game.PlayGame;
import io.github.ghosthopper.item.PlayItem;
import io.github.ghosthopper.item.PlayItemMoveEvent;
import io.github.ghosthopper.item.PlayPickItem;
import io.github.ghosthopper.item.PlayPushItem;
import io.github.ghosthopper.object.PlayLocation;
import io.github.ghosthopper.player.Player;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;

/**
 * JavaFx view for a {@link PlayLevel}.
 */
public class PlayUiFxGame extends Scene implements PlayUiFxNode {

  private final PlayGame game;

  private final PlayUiFxDataCache dataCache;

  private PlayUiFxLevel level;

  private final Map<PlayLevel, PlayUiFxLevel> levelMap;

  private final Map<PlayField, PlayUiFxField> fieldMap;

  private final Map<PlayBorder, PlayUiFxBorder> borderMap;

  private final Map<Player, PlayUiFxPlayer> playerMap;

  private final Map<PlayFigure, PlayUiFxFigure> figureMap;

  private final Map<PlayPickItem, PlayUiFxPickItem> pickItemMap;

  private final Map<PlayPushItem, PlayUiFxPushItem> pushItemMap;

  /**
   * The constructor.
   *
   * @param game the {@link PlayGame} to visualize.
   */
  public PlayUiFxGame(PlayGame game) {
    super(new VBox());
    this.game = game;
    this.dataCache = new PlayUiFxDataCache(game.getId());
    this.levelMap = new HashMap<>();
    this.fieldMap = new HashMap<>();
    this.borderMap = new HashMap<>();
    this.playerMap = new HashMap<>();
    this.figureMap = new HashMap<>();
    this.pickItemMap = new HashMap<>();
    this.pushItemMap = new HashMap<>();
    this.game.start();
    this.level = new PlayUiFxLevel(game.getCurrentLevel(), this);
    initPlayers();
    ((VBox) getRoot()).getChildren().add(this.level);
    setOnKeyPressed(this::handleKeyEvent);
    this.game.addListener(PlayFigureMoveEvent.class, this::onMoveFigure);
    this.game.addListener(PlayFigureTurnEvent.class, this::onTurnFigure);
    this.game.addListener(PlayBorder.class, this::onUpdateBorder);
    this.game.addListener(PlayItemMoveEvent.class, this::onMoveItem);
  }

  private void initPlayers() {

    for (Player player : this.game.getPlayers()) {
      PlayUiFxPlayer fxPlayer = new PlayUiFxPlayer(player, this);
      for (PlayFigure figure : player.getFigures()) {
        PlayUiFxFigure fxFigure = new PlayUiFxFigure(figure, fxPlayer);
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

  @Override
  public PlayUiFxGame getFxGame() {

    return this;
  }

  @Override
  public PlayGame getPlayGame() {

    return this.game;
  }

  @Override
  public PlayUiFxDataCache getFxDataCache() {

    return this.dataCache;
  }

  @Override
  public PlayUiFxNode getFxParent() {

    return null;
  }

  private void handleKeyEvent(KeyEvent keyEvent) {

    PlayKeyEvent event = PlayUiFxKeyEvent.convertEvent(keyEvent);
    if (event != null) {
      this.game.sendEvent(event);
    }
  }

  private void onMoveFigure(PlayFigureMoveEvent moveFigure) {

    PlayUiFxFigure fxFigure = getFxFigure(moveFigure.getAsset());
    if (fxFigure != null) {
      PlayUiFxField fxField = getFxField(moveFigure.getNewLocation());
      fxFigure.setPlayField(fxField);
    }
  }

  private void onMoveItem(PlayItemMoveEvent<?, ?> moveItem) {

    PlayUiFxItem fxItem = getFxItem(moveItem.getAsset());
    if (fxItem != null) {
      PlayLocation oldLocation = moveItem.getOldLocation();
      if (oldLocation instanceof PlayField) {
        PlayUiFxField fxField = getFxField((PlayField) oldLocation);
        fxField.removeFxAsset(fxItem);
      }
      PlayLocation newLocation = moveItem.getNewLocation();
      if (newLocation instanceof PlayField) {
        PlayUiFxField fxField = getFxField((PlayField) newLocation);
        fxField.addFxAsset(fxItem);
      }
    }
  }

  private void onTurnFigure(PlayFigureTurnEvent turnFigure) {

    PlayUiFxFigure fxFigure = getFxFigure(turnFigure.getOldFigure());
    if (fxFigure != null) {
      fxFigure.update();
    }
    fxFigure = getFxFigure(turnFigure.getNewFigure());
    if (fxFigure != null) {
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
   * @param fxField the {@link PlayUiFxLevel} to add.
   */
  void addFxLevel(PlayUiFxLevel fxLevel) {

    this.levelMap.put(fxLevel.getPlayLevel(), fxLevel);
  }

  /**
   * @param fxLevel the {@link PlayLevel}.
   * @return the corresponding {@link PlayUiFxLevel} or {@code null} if undefined.
   */
  public PlayUiFxLevel getFxLevel(PlayLevel fxLevel) {

    return this.levelMap.get(fxLevel);
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

  /**
   * @param fxPlayer the {@link PlayUiFxPlayer} to add.
   */
  void addFxPlayer(PlayUiFxPlayer fxPlayer) {

    this.playerMap.put(fxPlayer.getPlayer(), fxPlayer);
  }

  /**
   * @param player the {@link Player}.
   * @return the corresponding {@link PlayUiFxPlayer} or {@code null} if undefined.
   */
  public PlayUiFxPlayer getFxPlayer(Player player) {

    return this.playerMap.get(player);
  }

  /**
   * @param fxFigure the {@link PlayUiFxFigure} to add.
   */
  void addFxFigure(PlayUiFxFigure fxFigure) {

    this.figureMap.put(fxFigure.getPlayAsset(), fxFigure);
  }

  /**
   * @param figure the {@link PlayFigure}.
   * @return the corresponding {@link PlayUiFxFigure}.
   */
  public PlayUiFxFigure getFxFigure(PlayFigure figure) {

    return this.figureMap.get(figure);
  }

  /**
   * @param fxItem the {@link PlayUiFxPickItem} to add.
   */
  void addFxPickItem(PlayUiFxPickItem fxItem) {

    this.pickItemMap.put(fxItem.getPlayAsset(), fxItem);
  }

  /**
   * @param item the {@link PlayPickItem}.
   * @return the corresponding {@link PlayUiFxPickItem} or {@code null} if undefined.
   */
  public PlayUiFxPickItem getFxPickItem(PlayPickItem item) {

    return this.pickItemMap.get(item);
  }

  /**
   * @param fxItem the {@link PlayUiFxPushItem} to add.
   */
  void addFxPushItem(PlayUiFxPushItem fxItem) {

    this.pushItemMap.put(fxItem.getPlayAsset(), fxItem);
  }

  /**
   * @param item the {@link PlayPushItem}.
   * @return the corresponding {@link PlayUiFxPushItem} or {@code null} if undefined.
   */
  public PlayUiFxPushItem getFxPushItem(PlayPushItem item) {

    return this.pushItemMap.get(item);
  }

  /**
   * @param item the {@link PlayPickItem}.
   * @return the corresponding {@link PlayUiFxPickItem} or {@code null} if undefined.
   */
  public PlayUiFxItem getFxItem(PlayItem<?, ?> item) {

    if (item instanceof PlayPickItem) {
      return getFxPickItem((PlayPickItem) item);
    } else if (item instanceof PlayPushItem) {
      return getFxPushItem((PlayPushItem) item);
    } else {
      throw new IllegalStateException(item.getClass().getName());
    }
  }

}
