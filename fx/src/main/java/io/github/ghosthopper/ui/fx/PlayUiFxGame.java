/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.ui.fx;

import java.util.HashMap;
import java.util.Map;

import io.github.ghosthopper.asset.PlayAsset;
import io.github.ghosthopper.asset.PlayAssetMoveEvent;
import io.github.ghosthopper.border.PlayBorder;
import io.github.ghosthopper.data.PlayView;
import io.github.ghosthopper.event.PlayKeyEvent;
import io.github.ghosthopper.field.PlayField;
import io.github.ghosthopper.figure.PlayFigure;
import io.github.ghosthopper.figure.PlayFigureDirectionEvent;
import io.github.ghosthopper.figure.PlayFigureGroupEvent;
import io.github.ghosthopper.figure.PlayFigureTurnEvent;
import io.github.ghosthopper.game.PlayGame;
import io.github.ghosthopper.game.PlayState;
import io.github.ghosthopper.item.PlayItem;
import io.github.ghosthopper.item.PlayPickItem;
import io.github.ghosthopper.item.PlayPushItem;
import io.github.ghosthopper.level.PlayLevel;
import io.github.ghosthopper.object.PlayLocation;
import io.github.ghosthopper.player.Player;
import io.github.ghosthopper.ui.fx.data.PlayUiFxDataCache;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;

/**
 * JavaFx view for a {@link PlayLevel}.
 */
public class PlayUiFxGame extends Scene implements PlayUiFxNode {

  private final PlayGame game;

  private final PlayUiFx fx;

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
   * @param fx the {@link PlayUiFx}.
   */
  public PlayUiFxGame(PlayGame game, PlayUiFx fx) {
    super(new VBox());
    this.game = game;
    this.fx = fx;
    this.dataCache = new PlayUiFxDataCache(game.getId(), PlayView.VIEW_2D_SKY); // TODO: view is currently hard-coded
    this.levelMap = new HashMap<>();
    this.fieldMap = new HashMap<>();
    this.borderMap = new HashMap<>();
    this.playerMap = new HashMap<>();
    this.figureMap = new HashMap<>();
    this.pickItemMap = new HashMap<>();
    this.pushItemMap = new HashMap<>();
    this.level = getFxLevel(game.getCurrentLevel());
    ((VBox) getRoot()).getChildren().add(this.level);
    setOnKeyPressed(this::handleKeyEvent);
    this.game.addListener(PlayState.class, this::onPlayState);
    this.game.addListener(PlayAssetMoveEvent.class, this::onAssetMove);
    this.game.addListener(PlayFigureTurnEvent.class, this::onFigureTurn);
    this.game.addListener(PlayBorder.class, this::onBorderUpdate);
    this.game.addListener(PlayFigureGroupEvent.class, this::onGroupFigure);
    this.game.addListener(PlayFigureDirectionEvent.class, this::onFigureDirection);
  }

  private void initPlayers() {

    for (Player player : this.game.getPlayers()) {
      getFxPlayer(player);
      for (PlayFigure figure : player.getFigures()) {
        PlayUiFxFigure fxFigure = getFxFigure(figure);
        PlayField field = figure.getLocation();
        if (field != null) {
          PlayUiFxField playField = getFxField(field);
          if (playField != null) {
            fxFigure.setPlayField(playField);
          }
        }
        fxFigure.updateAllSingle();
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

    return this.fx;
  }

  private void handleKeyEvent(KeyEvent keyEvent) {

    PlayKeyEvent event = PlayUiFxKeyEventMapper.convertEvent(keyEvent);
    if (event != null) {
      this.game.sendEvent(event);
    }
  }

  private void onPlayState(PlayState state) {

    if (state == PlayState.START) {
      initPlayers();
    }
  }

  private void onAssetMove(PlayAssetMoveEvent<?, ?> moveAsset) {

    PlayUiFxAsset fxAsset = getFxAsset(moveAsset.getAsset());
    if (moveAsset.isPositionChange()) {
      fxAsset.updatePosition();
    }
    if (moveAsset.isLocationChange()) {
      PlayLocation newLocation = moveAsset.getNewLocation();
      if (newLocation instanceof PlayField) {
        PlayUiFxField fxField = getFxField((PlayField) newLocation);
        fxAsset.setPlayField(fxField);
        return;
      }
      PlayLocation oldLocation = moveAsset.getOldLocation();
      if (oldLocation instanceof PlayField) {
        PlayUiFxField fxField = getFxField((PlayField) oldLocation);
        fxField.removeFxAsset(fxAsset);
      }
    }
  }

  private void onFigureDirection(PlayFigureDirectionEvent figureDirection) {

    PlayUiFxFigure fxFigure = getFxFigure(figureDirection.getFigure());
    fxFigure.updateDirectionSingle();
  }

  private void onFigureTurn(PlayFigureTurnEvent turnFigure) {

    updateActivity(turnFigure.getOldFigure());
    updateActivity(turnFigure.getNewFigure());
  }

  private void onGroupFigure(PlayFigureGroupEvent groupFigure) {

    updateActivity(groupFigure.getFigure());
  }

  private void updateActivity(PlayFigure figure) {

    PlayUiFxFigure fxFigure = getFxFigure(figure);
    if (fxFigure != null) {
      fxFigure.updateActivity();
    }
  }

  private void onBorderUpdate(PlayBorder border) {

    PlayUiFxBorder fxBorder = getFxBorder(border);
    if (fxBorder != null) {
      fxBorder.update();
    }
  }

  /**
   * @param playLevel the {@link PlayLevel}.
   * @return the corresponding {@link PlayUiFxLevel} or {@code null} if undefined.
   */
  public PlayUiFxLevel getFxLevel(PlayLevel playLevel) {

    if (playLevel == null) {
      return null;
    }
    PlayUiFxLevel fxLevel = this.levelMap.computeIfAbsent(playLevel, x -> new PlayUiFxLevel(playLevel, this));
    fxLevel.initialize();
    return fxLevel;
  }

  /**
   * @param field the {@link PlayField}.
   * @return the corresponding {@link PlayUiFxField} or {@code null} if undefined.
   */
  public PlayUiFxField getFxField(PlayField field) {

    if (field == null) {
      return null;
    }
    return this.fieldMap.computeIfAbsent(field, x -> new PlayUiFxField(field, getFxLevel(field.getLevel())));
  }

  /**
   * @param border the {@link PlayBorder}.
   * @return the corresponding {@link PlayUiFxBorder} or {@code null} if undefined.
   */
  public PlayUiFxBorder getFxBorder(PlayBorder border) {

    if (border == null) {
      return null;
    }
    return this.borderMap.computeIfAbsent(border, x -> new PlayUiFxBorder(border, this));
  }

  /**
   * @param player the {@link Player}.
   * @return the corresponding {@link PlayUiFxPlayer} or {@code null} if undefined.
   */
  public PlayUiFxPlayer getFxPlayer(Player player) {

    if (player == null) {
      return null;
    }
    return this.playerMap.computeIfAbsent(player, x -> new PlayUiFxPlayer(player, this));
  }

  /**
   * @param figure the {@link PlayFigure}.
   * @return the corresponding {@link PlayUiFxFigure}.
   */
  public PlayUiFxFigure getFxFigure(PlayFigure figure) {

    if (figure == null) {
      return null;
    }
    return this.figureMap.computeIfAbsent(figure, x -> new PlayUiFxFigure(figure, getFxPlayer(figure.getPlayer())));
  }

  /**
   * @param item the {@link PlayPickItem}.
   * @return the corresponding {@link PlayUiFxPickItem} or {@code null} if undefined.
   */
  public PlayUiFxPickItem getFxPickItem(PlayPickItem item) {

    if (item == null) {
      return null;
    }
    return this.pickItemMap.computeIfAbsent(item, x -> new PlayUiFxPickItem(item, this));
  }

  /**
   * @param item the {@link PlayPushItem}.
   * @return the corresponding {@link PlayUiFxPushItem}.
   */
  public PlayUiFxPushItem getFxPushItem(PlayPushItem item) {

    if (item == null) {
      return null;
    }
    return this.pushItemMap.computeIfAbsent(item, x -> new PlayUiFxPushItem(item, this));
  }

  /**
   * @param item the {@link PlayPickItem}.
   * @return the corresponding {@link PlayUiFxPickItem}.
   */
  public PlayUiFxItem getFxItem(PlayItem<?, ?> item) {

    if (item == null) {
      return null;
    }
    if (item instanceof PlayPickItem) {
      return getFxPickItem((PlayPickItem) item);
    } else if (item instanceof PlayPushItem) {
      return getFxPushItem((PlayPushItem) item);
    } else {
      throw new IllegalStateException(item.getClass().getName());
    }
  }

  /**
   * @param asset the {@link PlayAsset}.
   * @return the corresponding {@link PlayUiFxAsset}.
   */
  public PlayUiFxAsset getFxAsset(PlayAsset<?> asset) {

    if (asset == null) {
      return null;
    }
    if (asset instanceof PlayItem) {
      return getFxItem((PlayItem<?, ?>) asset);
    } else if (asset instanceof PlayFigure) {
      return getFxFigure((PlayFigure) asset);
    } else {
      throw new IllegalStateException(asset.getClass().getName());
    }
  }

}
