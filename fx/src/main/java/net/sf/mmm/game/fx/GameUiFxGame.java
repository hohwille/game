/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.game.fx;

import java.util.HashMap;
import java.util.Map;

import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import net.sf.mmm.game.engine.Game;
import net.sf.mmm.game.engine.GameState;
import net.sf.mmm.game.engine.asset.GameAsset;
import net.sf.mmm.game.engine.asset.GameAssetMoveEvent;
import net.sf.mmm.game.engine.border.GameBorder;
import net.sf.mmm.game.engine.data.GameView;
import net.sf.mmm.game.engine.event.GameKeyEvent;
import net.sf.mmm.game.engine.field.GameField;
import net.sf.mmm.game.engine.figure.GameFigure;
import net.sf.mmm.game.engine.figure.GameFigureDirectionEvent;
import net.sf.mmm.game.engine.figure.GameFigureGroupEvent;
import net.sf.mmm.game.engine.figure.GameFigureTurnEvent;
import net.sf.mmm.game.engine.item.GameFieldItem;
import net.sf.mmm.game.engine.item.GameItem;
import net.sf.mmm.game.engine.item.GamePickItem;
import net.sf.mmm.game.engine.level.GameLevel;
import net.sf.mmm.game.engine.object.GameLocation;
import net.sf.mmm.game.engine.player.GamePlayer;
import net.sf.mmm.game.fx.asset.GameUiFxAsset;
import net.sf.mmm.game.fx.border.GameUiFxBorder;
import net.sf.mmm.game.fx.data.GameUiFxDataCache;
import net.sf.mmm.game.fx.event.GameUiFxKeyEventMapper;
import net.sf.mmm.game.fx.field.GameUiFxField;
import net.sf.mmm.game.fx.figure.GameUiFxFigure;
import net.sf.mmm.game.fx.item.GameUiFxItem;
import net.sf.mmm.game.fx.item.GameUiFxPickItem;
import net.sf.mmm.game.fx.item.GameUiFxPushItem;
import net.sf.mmm.game.fx.level.GameUiFxLevel;
import net.sf.mmm.game.fx.player.GameUiFxPlayer;

/**
 * JavaFx view for a {@link GameLevel}.
 */
public class GameUiFxGame extends Scene implements GameUiFxObject {

  private final Game game;

  private final GameUiFx fx;

  private final GameUiFxDataCache dataCache;

  private GameUiFxLevel level;

  private final Map<GameLevel, GameUiFxLevel> levelMap;

  private final Map<GameField, GameUiFxField> fieldMap;

  private final Map<GameBorder, GameUiFxBorder> borderMap;

  private final Map<GamePlayer, GameUiFxPlayer> playerMap;

  private final Map<GameFigure, GameUiFxFigure> figureMap;

  private final Map<GamePickItem, GameUiFxPickItem> pickItemMap;

  private final Map<GameFieldItem, GameUiFxPushItem> pushItemMap;

  /**
   * The constructor.
   *
   * @param game the {@link Game} to visualize.
   * @param fx the {@link GameUiFx}.
   */
  public GameUiFxGame(Game game, GameUiFx fx) {

    super(new VBox());
    this.game = game;
    this.fx = fx;
    this.dataCache = new GameUiFxDataCache(game.getId(), GameView.VIEW_2D_SKY); // TODO: view is currently hard-coded
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
    this.game.addListener(GameState.class, this::onPlayState);
    this.game.addListener(GameAssetMoveEvent.class, this::onAssetMove);
    this.game.addListener(GameFigureTurnEvent.class, this::onFigureTurn);
    this.game.addListener(GameBorder.class, this::onBorderUpdate);
    this.game.addListener(GameFigureGroupEvent.class, this::onGroupFigure);
    this.game.addListener(GameFigureDirectionEvent.class, this::onFigureDirection);
  }

  private void initPlayers() {

    for (GamePlayer player : this.game.getPlayers()) {
      getFxPlayer(player);
      for (GameFigure figure : player.getFigures()) {
        GameUiFxFigure fxFigure = getFxFigure(figure);
        GameField field = figure.getLocation();
        if (field != null) {
          GameUiFxField playField = getFxField(field);
          if (playField != null) {
            fxFigure.setGameField(playField);
          }
        }
        fxFigure.updateAllSingle();
      }
    }
  }

  @Override
  public GameUiFxGame getFxGame() {

    return this;
  }

  @Override
  public Game getGame() {

    return this.game;
  }

  @Override
  public GameUiFxDataCache getFxDataCache() {

    return this.dataCache;
  }

  @Override
  public GameUiFxObject getFxParent() {

    return this.fx;
  }

  private void handleKeyEvent(KeyEvent keyEvent) {

    GameKeyEvent event = GameUiFxKeyEventMapper.convertEvent(keyEvent);
    if (event != null) {
      this.game.sendEvent(event);
    }
  }

  private void onPlayState(GameState state) {

    if (state == GameState.START) {
      initPlayers();
    }
  }

  private void onAssetMove(GameAssetMoveEvent<?, ?> moveAsset) {

    GameUiFxAsset fxAsset = getFxAsset(moveAsset.getAsset());
    if (moveAsset.isPositionChange()) {
      fxAsset.updateGamePosition();
    }
    if (moveAsset.isLocationChange()) {
      GameLocation newLocation = moveAsset.getNewLocation();
      if (newLocation instanceof GameField) {
        GameUiFxField fxField = getFxField((GameField) newLocation);
        fxAsset.setGameField(fxField);
        return;
      }
      GameLocation oldLocation = moveAsset.getOldLocation();
      if (oldLocation instanceof GameField) {
        GameUiFxField fxField = getFxField((GameField) oldLocation);
        fxField.removeFxAsset(fxAsset);
      }
    }
  }

  private void onFigureDirection(GameFigureDirectionEvent figureDirection) {

    GameUiFxFigure fxFigure = getFxFigure(figureDirection.getFigure());
    fxFigure.updateGameDirectionSingle();
  }

  private void onFigureTurn(GameFigureTurnEvent turnFigure) {

    updateActivity(turnFigure.getOldFigure());
    updateActivity(turnFigure.getNewFigure());
  }

  private void onGroupFigure(GameFigureGroupEvent groupFigure) {

    updateActivity(groupFigure.getFigure());
  }

  private void updateActivity(GameFigure figure) {

    GameUiFxFigure fxFigure = getFxFigure(figure);
    if (fxFigure != null) {
      fxFigure.updateActivity();
    }
  }

  private void onBorderUpdate(GameBorder border) {

    GameUiFxBorder fxBorder = getFxBorder(border);
    if (fxBorder != null) {
      fxBorder.update();
    }
  }

  /**
   * @param playLevel the {@link GameLevel}.
   * @return the corresponding {@link GameUiFxLevel} or {@code null} if undefined.
   */
  public GameUiFxLevel getFxLevel(GameLevel playLevel) {

    if (playLevel == null) {
      return null;
    }
    GameUiFxLevel fxLevel = this.levelMap.computeIfAbsent(playLevel, x -> new GameUiFxLevel(playLevel, this));
    fxLevel.initialize();
    return fxLevel;
  }

  /**
   * @param field the {@link GameField}.
   * @return the corresponding {@link GameUiFxField} or {@code null} if undefined.
   */
  public GameUiFxField getFxField(GameField field) {

    if (field == null) {
      return null;
    }
    return this.fieldMap.computeIfAbsent(field, x -> new GameUiFxField(field, getFxLevel(field.getLevel())));
  }

  /**
   * @param border the {@link GameBorder}.
   * @return the corresponding {@link GameUiFxBorder} or {@code null} if undefined.
   */
  public GameUiFxBorder getFxBorder(GameBorder border) {

    if (border == null) {
      return null;
    }
    return this.borderMap.computeIfAbsent(border, x -> new GameUiFxBorder(border, this));
  }

  /**
   * @param player the {@link GamePlayer}.
   * @return the corresponding {@link GameUiFxPlayer} or {@code null} if undefined.
   */
  public GameUiFxPlayer getFxPlayer(GamePlayer player) {

    if (player == null) {
      return null;
    }
    return this.playerMap.computeIfAbsent(player, x -> new GameUiFxPlayer(player, this));
  }

  /**
   * @param figure the {@link GameFigure}.
   * @return the corresponding {@link GameUiFxFigure}.
   */
  public GameUiFxFigure getFxFigure(GameFigure figure) {

    if (figure == null) {
      return null;
    }
    return this.figureMap.computeIfAbsent(figure, x -> new GameUiFxFigure(figure, getFxPlayer(figure.getPlayer())));
  }

  /**
   * @param item the {@link GamePickItem}.
   * @return the corresponding {@link GameUiFxPickItem} or {@code null} if undefined.
   */
  public GameUiFxPickItem getFxPickItem(GamePickItem item) {

    if (item == null) {
      return null;
    }
    return this.pickItemMap.computeIfAbsent(item, x -> new GameUiFxPickItem(item, this));
  }

  /**
   * @param item the {@link GameFieldItem}.
   * @return the corresponding {@link GameUiFxPushItem}.
   */
  public GameUiFxPushItem getFxPushItem(GameFieldItem item) {

    if (item == null) {
      return null;
    }
    return this.pushItemMap.computeIfAbsent(item, x -> new GameUiFxPushItem(item, this));
  }

  /**
   * @param item the {@link GamePickItem}.
   * @return the corresponding {@link GameUiFxPickItem}.
   */
  public GameUiFxItem getFxItem(GameItem<?, ?, ?> item) {

    if (item == null) {
      return null;
    }
    if (item instanceof GamePickItem) {
      return getFxPickItem((GamePickItem) item);
    } else if (item instanceof GameFieldItem) {
      return getFxPushItem((GameFieldItem) item);
    } else {
      throw new IllegalStateException(item.getClass().getName());
    }
  }

  /**
   * @param asset the {@link GameAsset}.
   * @return the corresponding {@link GameUiFxAsset}.
   */
  public GameUiFxAsset getFxAsset(GameAsset<?> asset) {

    if (asset == null) {
      return null;
    }
    if (asset instanceof GameItem) {
      return getFxItem((GameItem<?, ?, ?>) asset);
    } else if (asset instanceof GameFigure) {
      return getFxFigure((GameFigure) asset);
    } else {
      throw new IllegalStateException(asset.getClass().getName());
    }
  }

}
