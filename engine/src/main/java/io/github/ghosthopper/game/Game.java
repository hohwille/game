/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import io.github.ghosthopper.border.GameBorder;
import io.github.ghosthopper.border.GameBorderType;
import io.github.ghosthopper.direction.GameDirection;
import io.github.ghosthopper.event.GameEvent;
import io.github.ghosthopper.event.GameEventDispatcher;
import io.github.ghosthopper.event.GameEventListener;
import io.github.ghosthopper.event.GameEventSender;
import io.github.ghosthopper.event.GameKeyEvent;
import io.github.ghosthopper.event.GameKeys;
import io.github.ghosthopper.field.GameField;
import io.github.ghosthopper.field.GameFieldType;
import io.github.ghosthopper.figure.GameFigure;
import io.github.ghosthopper.figure.GameFigureGroup;
import io.github.ghosthopper.figure.GameFigureTurnEvent;
import io.github.ghosthopper.figure.GameFigureType;
import io.github.ghosthopper.i18n.GameTranslator;
import io.github.ghosthopper.item.GameItem;
import io.github.ghosthopper.item.GameItemType;
import io.github.ghosthopper.item.GamePickItem;
import io.github.ghosthopper.level.GameLevel;
import io.github.ghosthopper.object.GameStateObjectWithId;
import io.github.ghosthopper.player.GamePlayer;
import io.github.ghosthopper.player.GamePlayerConfig;
import io.github.ghosthopper.player.GamePlayerConfigBase;
import io.github.ghosthopper.player.GamePlayerConfigChoiceGroup;
import io.github.ghosthopper.player.GamePlayerTurnEvent;
import io.github.ghosthopper.position.GamePosition;

/**
 * This is the main object and represents an actual game with its rules. To implement your own game, simply extend this
 * class and implement/override according methods. You can e.g.
 * <ul>
 * <li>Decide for {@link #isTurnGame() turn game} (default) or action game.</li>
 * <li>Define custom {@link GameFigureType}s and reuse predefined ones.</li>
 * <li>Define custom {@link GameItemType}s and reuse predefined ones.</li>
 * <li>Define custom {@link GameFieldType}s and reuse predefined ones.</li>
 * <li>{@link #getPlayers() Predefine} the {@link GamePlayer}s with their {@link GameFigure}s based on the above
 * types.</li>
 * <li>{@link #createDirections() Define} custom {@link GameDirection}s or use the predefined ones (see
 * {@link #isSupportingDiagonalDirections()}).</li>
 * <li>{@link #createFirstLevel() create} {@link GameLevel}s with all the {@link GameField}s typically as
 * {@link #initLevelAsRectangular(GameLevel, int, int, GameBorderTypeStrategy) rectangular field}.</li>
 * <li>Customize the {@link GameBorder}s to create complex puzzle/riddle games.</li>
 * <li>Place {@link GameItem}s and {@link GameFigure}s on the {@link GameField}s.</li>
 * <li>Implement a strategy to {@link #moveBotPlayer(GamePlayer) move bot players}.</li>
 * </ul>
 */
public class Game extends GameStateObjectWithId implements GameEventSender<GameEvent> {

  /** @see #getCurrentGame() */
  private static Game currentGame = GameNone.INSTANCE;

  private final GameTranslator translator;

  private final Map<Class<?>, GameEventDispatcher<?>> dispatcherMap;

  private GamePlayerConfigBase playerConfig;

  private int currentPlayer;

  private int currentFigure;

  private GameLevel currentLevel;

  private List<GameDirection> directions;

  private List<GamePosition> position;

  private GameState state;

  /**
   * The constructor.
   *
   * @param id - see {@link #getId()}.
   */
  public Game(String id) {
    super(id);
    this.translator = new GameTranslator(this);
    this.dispatcherMap = new HashMap<>();
    addListener(GameKeyEvent.class, this::onKeyPressed);
  }

  /**
   * @param event the {@link GameKeyEvent} to handle.
   */
  protected void onKeyPressed(GameKeyEvent event) {

    if (event.getCode() == GameKeys.KEY_LEFT) {
      if (event.getModifiers().isControl()) {
        rotate(false);
      } else {
        move(GameDirection.WEST);
      }
    } else if (event.getCode() == GameKeys.KEY_RIGHT) {
      if (event.getModifiers().isControl()) {
        rotate(true);
      } else {
        move(GameDirection.EAST);
      }
    } else if (event.getCode() == GameKeys.KEY_UP) {
      if (event.getModifiers().isControl()) {
        move(true);
      } else {
        move(GameDirection.NORTH);
      }
    } else if (event.getCode() == GameKeys.KEY_DOWN) {
      if (event.getModifiers().isControl()) {
        move(false);
      } else {
        move(GameDirection.SOUTH);
      }
    } else if (event.getCode() == GameKeys.KEY_ENTER) {
      nextFigure(true);
    } else if (event.getCode() == GameKeys.KEY_D) {
      GameFigure figure = getCurrentFigure();
      if (figure != null) {
        figure.dropItem();
      }
    } else if (event.getCode() == GameKeys.KEY_P) {
      GameFigure figure = getCurrentFigure();
      if (figure != null) {
        figure.pickItem();
      }
    } else if (event.getCode() == GameKeys.KEY_G) {
      GameFigure figure = getCurrentFigure();
      if (figure != null) {
        GameField field = figure.getLocation();
        if (field != null) {
          GameFigureGroup group = figure.getGroup();
          GamePlayer player = figure.getPlayer();
          for (GameFigure fieldFigure : field.getFigures()) {
            if ((fieldFigure != figure) && (fieldFigure.getPlayer() == player)) {
              if (group == null) {
                group = player.createGroup();
                group.addFigure(figure);
              }
              group.addFigure(fieldFigure);
            }
          }
        }
      }
    }
  }

  /**
   * {@link GameFigure#move(GameDirection) Moves} the {@link #getCurrentFigure() current figure} in the given
   * {@link GameDirection}.
   *
   * @param direction the {@link GameDirection} to move.
   */
  protected void move(GameDirection direction) {

    GameFigure figure = getCurrentFigure();
    if (figure != null) {
      figure.move(direction);
    }
  }

  /**
   * {@link GameFigure#move() Moves} the {@link #getCurrentFigure() current figure} in its direction forwards or
   * backwards.
   *
   * @param forward - {@code true} to {@link GameFigure#move() move forwards} and {@code false} to move backwards (in
   *        the {@link GameDirection#getInverse() inverse direction}).
   */
  protected void move(boolean forward) {

    GameFigure figure = getCurrentFigure();
    if (figure != null) {
      if (forward) {
        figure.move();
      } else {
        figure.move(figure.getDirection().getInverse());
      }
    }
  }

  /**
   * {@link GameFigure#rotate(boolean) Rotates} the {@link #getCurrentFigure() current figure}.
   *
   * @param clockwise {@code true} if figure should rotate clockwise, {@code false} otherwise (opposite direction).
   */
  protected void rotate(boolean clockwise) {

    GameFigure figure = getCurrentFigure();
    if (figure != null) {
      figure.rotate(clockwise);
    }
  }

  @SuppressWarnings({ "rawtypes", "unchecked" })
  @Override
  public void sendEvent(GameEvent event) {

    Objects.requireNonNull(event, "event");
    if (!isStarted() && !(event instanceof GameState)) {
      return; // prevent infinity loops on initialization
    }
    Class<? extends GameEvent> eventType = event.getClass();
    GameEventDispatcher eventDispatcher = getEventDispatcherOptional(eventType);
    if (eventDispatcher != null) {
      eventDispatcher.sendEvent(event);
    }
  }

  @SuppressWarnings({ "rawtypes", "unchecked" })
  private GameEventDispatcher getEventDispatcher(Class eventType, boolean createIfNotExists) {

    GameEventDispatcher<?> dispatcher = this.dispatcherMap.get(eventType);
    if (dispatcher == null) {
      Class<?> parentType = eventType.getSuperclass();
      GameEventDispatcher<?> parent;
      if ((parentType != null) && GameEvent.class.isAssignableFrom(parentType)) {
        parent = getEventDispatcher(parentType, createIfNotExists);
      } else {
        parent = null;
      }
      if (createIfNotExists) {
        dispatcher = new GameEventDispatcher(parent);
        this.dispatcherMap.put(eventType, dispatcher);
      } else {
        dispatcher = parent;
      }
    }
    return dispatcher;
  }

  /**
   * @param <E> the type of the {@link GameEvent}.
   * @param eventType the {@link Class} reflecting the {@link GameEvent}.
   * @return the according {@link GameEventDispatcher}. Will be created if not already exists.
   */
  @SuppressWarnings("unchecked")
  protected <E extends GameEvent> GameEventDispatcher<E> getEventDispatcherRequired(Class<E> eventType) {

    return getEventDispatcher(eventType, true);
  }

  /**
   * @param <E> the type of the {@link GameEvent}.
   * @param eventType the {@link Class} reflecting the {@link GameEvent}.
   * @return the according {@link GameEventDispatcher} or {@code null} if not exists.
   */
  @SuppressWarnings("unchecked")
  protected <E extends GameEvent> GameEventDispatcher<E> getEventDispatcherOptional(Class<E> eventType) {

    return getEventDispatcher(eventType, false);
  }

  @Override
  public <E extends GameEvent> void addListener(Class<E> eventType, GameEventListener<E> listener) {

    Objects.requireNonNull(eventType, "eventType");
    Objects.requireNonNull(listener, "listener");
    if (eventType.isInterface()) {
      throw new IllegalArgumentException(eventType.getName());
    }
    getEventDispatcherRequired(eventType).addListener(eventType, listener);
  }

  @Override
  public boolean removeListener(GameEventListener<? extends GameEvent> listener) {

    for (GameEventDispatcher<?> dispatcher : this.dispatcherMap.values()) {
      @SuppressWarnings({ "rawtypes", "unchecked" })
      boolean removed = dispatcher.removeListener((GameEventListener) listener);
      if (removed) {
        return true;
      }
    }
    return false;
  }

  /**
   * @return the currentGame
   */
  public static Game getCurrentGame() {

    return currentGame;
  }

  @Override
  public Game getGame() {

    return this;
  }

  /**
   * Starts this {@link Game}.
   */
  public final void begin() {

    if (currentGame == this) {
      return;
    }
    if (currentGame != null) {
      currentGame.end();
    }
    currentGame = this;
    this.state = GameState.BEGIN;
    sendEvent(this.state);
  }

  /**
   * Starts this {@link Game}.
   */
  public final void start() {

    assert (currentGame == this);
    this.state = GameState.START;
    sendEvent(GameState.START);
  }

  /**
   * @return the current {@link GameState state} of this game.
   */
  public GameState getState() {

    return this.state;
  }

  /**
   * @return {@code true} if this game has been {@link #start() started} and has not yet {@link #end() ended}.
   */
  public boolean isStarted() {

    if (this.state == GameState.START) {
      return true;
    }
    if (this.state == GameState.PAUSE) {
      return true;
    }
    return false;
  }

  /**
   * @return {@code true} if this game is currently active (it is the {@link #getCurrentGame() current game} and is not
   *         {@link #isPaused() paused} or over).
   */
  public boolean isActive() {

    if (currentGame != this) {
      return false;
    }
    return (this.state == GameState.START);
  }

  /**
   * @return {@code true} if {@link #pause() paused}, {@code false} otherwise.
   */
  public boolean isPaused() {

    return (this.state == GameState.PAUSE);
  }

  /**
   * Pauses this {@link Game}.
   */
  public void pause() {

    if (isPaused()) {
      return;
    }
    this.state = GameState.PAUSE;
    sendEvent(this.state);
  }

  /**
   * Resumes this {@link Game} after it has been {@link #pause() paused}.
   */
  public void resume() {

    if (!isPaused()) {
      return;
    }
    this.state = GameState.START;
    sendEvent(GameState.RESUME);
  }

  /**
   * Ends this {@link Game}.
   */
  public final void end() {

    this.state = GameState.END;
    sendEvent(this.state);
    currentGame = GameNone.INSTANCE;
  }

  /**
   * Loads the current game from the disc.
   */
  public void load() {

    // TODO
  }

  /**
   * Save the current game to the disc.
   */
  public void save() {

    // TODO
  }

  /**
   * @return the {@link GameTranslator} used for localization.
   */
  public GameTranslator getTranslator() {

    return this.translator;
  }

  /**
   * @return {@code true} if this game is played in turns (rounds), where only one {@link GamePlayer} can act at a time
   *         (for strategic games like chess, Ludo, etc.), {@code false} if all {@link GamePlayer}s can play in parallel
   *         (for action games where speed matters).
   */
  public boolean isTurnGame() {

    return true;
  }

  /**
   * @return {@code true} if a {@link GameFigure} should automatically {@link GameFigure#pickItem() pick} the
   *         {@link GamePickItem}(s) when he moves on a {@link GameField}, {@code false} otherwise.
   */
  public boolean isAutoPickItem() {

    return false;
  }

  /**
   * @return {@code true} if this game should show its {@link GameBorder}s in the UI, {@code false} otherwise (borders
   *         are hidden).
   */
  public boolean isShowBorder() {

    return true;
  }

  /**
   * @return the playerConfig
   */
  public GamePlayerConfig getPlayerConfig() {

    if (this.playerConfig == null) {
      this.playerConfig = createPlayerConfig();
    }
    return this.playerConfig;
  }

  /**
   * @return the new {@link GamePlayerConfigBase}.
   */
  protected GamePlayerConfigBase createPlayerConfig() {

    return new GamePlayerConfigChoiceGroup(this);
  }

  /**
   * @return an {@link Collections#unmodifiableList(List) unmodifiable list} of the {@link GamePlayer}s.
   *
   * @see #getPlayerConfig()
   */
  public List<GamePlayer> getPlayers() {

    return getPlayerConfig().getPlayers();
  }

  /**
   * @return the {@link GamePlayer} who is currently on turn.
   */
  public GamePlayer getCurrentPlayer() {

    return getPlayers().get(this.currentPlayer);
  }

  /**
   * Ends the turn of the {@link #getCurrentPlayer() current} {@link GamePlayer#isHuman() human} {@link GamePlayer} and
   * moves all following computer {@link GamePlayer}s (bots).
   *
   * @return the next {@link GamePlayer#isHuman() human} {@link GamePlayer} who is now on turn.
   */
  public GamePlayer nextPlayer() {

    if (!isTurnGame()) {
      return null;
    }
    GameFigure oldFigure = getCurrentFigure();
    GamePlayer oldPlayer = null;
    if (oldFigure != null) {
      oldPlayer = oldFigure.getPlayer();
    }
    this.currentPlayer++;
    if (this.currentPlayer >= getPlayers().size()) {
      this.currentPlayer = 0;
    }
    this.currentFigure = 0;
    GameFigure newFigure = getCurrentFigure();
    GamePlayer newPlayer;
    if (newFigure == null) {
      newPlayer = getCurrentPlayer();
    } else {
      newPlayer = newFigure.getPlayer();
    }
    sendEvent(new GamePlayerTurnEvent(oldPlayer, newPlayer));
    sendEvent(new GameFigureTurnEvent(oldFigure, newFigure));
    if ((newPlayer != null) && !newPlayer.isHuman()) {
      moveBotPlayer(newPlayer);
      newPlayer = nextPlayer();
    }
    return newPlayer;
  }

  /**
   * @return the current {@link GameFigure} of the {@link #getCurrentPlayer() current player} or {@code null} if the
   *         turn of the current player is over.
   */
  public GameFigure getCurrentFigure() {

    GamePlayer player = getCurrentPlayer();
    List<GameFigure> figures = player.getFigures();
    if (this.currentFigure >= figures.size()) {
      return null;
    }
    return figures.get(this.currentFigure);
  }

  /**
   * @param mayChangePlayer {@code true} if the turn of the {@link #getCurrentPlayer() current player} should
   *        automatically be ended and the {@link #nextPlayer() next player shall be on turn} in case the
   *        {@link #getCurrentPlayer() current player} has no {@link #getCurrentFigure() figure} left, {@code false}
   *        otherwise (return {@code null} in that case).
   * @return the next {@link GameFigure} or {@code null} if turn is over.
   */
  public GameFigure nextFigure(boolean mayChangePlayer) {

    if (!isTurnGame()) {
      return null;
    }
    GamePlayer player = getCurrentPlayer();
    GameFigure oldFigure = null;
    List<GameFigure> figures = player.getFigures();
    GameFigure newFigure = null;
    int size = figures.size();
    while (this.currentFigure < size) {
      if (oldFigure == null) {
        oldFigure = figures.get(this.currentFigure);
        assert (oldFigure != null);
      }
      this.currentFigure++;
      if (this.currentFigure < size) {
        GameFigure nextFigure = player.getFigures().get(this.currentFigure);
        if (nextFigure.isGroupLead()) {
          newFigure = nextFigure;
          break;
        }
      }
    }
    if ((newFigure == null) && (mayChangePlayer)) {
      nextPlayer();
      newFigure = getCurrentFigure();
    }
    sendEvent(new GameFigureTurnEvent(oldFigure, newFigure));
    return newFigure;
  }

  /**
   * @param player the non-{@link GamePlayer#isHuman() human} {@link GamePlayer} to move.
   */
  protected void moveBotPlayer(GamePlayer player) {

    // should be overridden if bot players are supported by game.
  }

  /**
   * @return the current {@link GameLevel}.
   */
  public GameLevel getCurrentLevel() {

    if (this.currentLevel == null) {
      this.currentLevel = initLevels();
    }
    return this.currentLevel;
  }

  /**
   * @return the initial {@link GameLevel}.
   */
  private GameLevel initLevels() {

    GameLevel level = createFirstLevel();
    return level;
  }

  /**
   * @return creates the first (and potentially single) {@link GameLevel}.
   */
  protected GameLevel createFirstLevel() {

    return new GameLevel("Level 1", this);
  }

  /**
   * @return the {@link GameBorderTypeStrategy}.
   */
  protected GameBorderTypeStrategy getBorderStrategy() {

    return GameBorderTypeStrategyStatic.OPEN;
  }

  /**
   * @param level the {@link GameLevel} to initialize.
   * @param width the number of {@link GameField}s in {@link #getDirectionX() X-direction}.
   * @param height the number of {@link GameField}s in {@link #getDirectionY() Y-direction}.
   * @return the given {@link GameLevel} after initialization.
   */
  protected GameLevel initLevelAsRectangular(GameLevel level, int width, int height) {

    return initLevelAsRectangular(level, width, height, getBorderStrategy());
  }

  /**
   * @param level the {@link GameLevel} to initialize.
   * @param width the number of {@link GameField}s in {@link #getDirectionX() X-direction}.
   * @param height the number of {@link GameField}s in {@link #getDirectionY() Y-direction}.
   * @param borderStrategy the {@link GameBorderTypeStrategy}.
   * @return the given {@link GameLevel} after initialization.
   */
  protected GameLevel initLevelAsRectangular(GameLevel level, int width, int height, GameBorderTypeStrategy borderStrategy) {

    assert level.getGame() == this;
    GameDirection xDir = getDirectionX();
    GameDirection yDir = getDirectionY();
    GameDirection xDirInverse = xDir.getInverse();
    GameDirection yDirInverse = yDir.getInverse();
    GameField startField = level.getStartField();
    startField.initEdge();
    GameField xStartField = startField;
    for (int y = 1; y <= height; y++) {
      GameField field = xStartField;
      field.createWall(xDirInverse);
      for (int x = 1; x <= width; x++) {
        if (y == 1) {
          field.createWall(yDirInverse);
        }
        if (y == height) {
          field.createWall(yDir);
        } else {
          GameBorderType type = borderStrategy.getType(field, x, y, width, height, yDir);
          field.createBorder(type, yDir);
        }
        if (x == width) {
          field.createWall(xDir);
        } else {
          GameBorderType type = borderStrategy.getType(field, x, y, width, height, xDir);
          if (y == 1) {
            field = field.createBorder(type, xDir);
          } else {
            GameField targetField = field.getField(yDirInverse).getField(xDir).getField(yDir);
            field.createBorder(type, xDir, targetField);
            field = targetField;
          }
        }
      }
      xStartField = xStartField.getField(yDir);
    }
    return level;
  }

  /**
   * @return the {@link GameDirection}s supported by this {@link Game}.
   */
  public List<GameDirection> getDirections() {

    if (this.directions == null) {
      List<GameDirection> newDirections = createDirections();
      if (new HashSet<>(newDirections).size() != newDirections.size()) {
        throw new IllegalStateException("Duplicate direction(s)!");
      }
      this.directions = Collections.unmodifiableList(newDirections);
    }
    return this.directions;
  }

  /**
   * @return the {@link #getDirections() directions}.
   */
  protected List<GameDirection> createDirections() {

    List<GameDirection> list = new ArrayList<>();
    list.add(GameDirection.WEST);
    list.add(getDirectionX());
    list.add(GameDirection.NORTH);
    list.add(getDirectionY());
    if (isSupportingDiagonalDirections()) {
      list.add(GameDirection.NORTH_WEST);
      list.add(GameDirection.NORTH_EAST);
      list.add(GameDirection.SOUTH_WEST);
      list.add(GameDirection.SOUTH_EAST);
    }
    return list;
  }

  /**
   * @return the {@link GameDirection} on X-Axis.
   */
  public GameDirection getDirectionX() {

    return GameDirection.EAST;
  }

  /**
   * @return the {@link GameDirection} on Y-Axis.
   */
  public GameDirection getDirectionY() {

    return GameDirection.SOUTH;
  }

  /**
   * @return the {@link GameDirection} on Z-Axis (not yet supported).
   */
  public GameDirection getDirectionZ() {

    return null;
  }

  /**
   * @return the {@link GamePosition}s supported by this {@link Game}.
   */
  public List<GamePosition> getPositions() {

    if (this.position == null) {
      List<GamePosition> newPositions = createPositions();
      if (new HashSet<>(newPositions).size() != newPositions.size()) {
        throw new IllegalStateException("Duplicate position(s)!");
      }
      this.position = Collections.unmodifiableList(newPositions);
    }
    return this.position;
  }

  /**
   * @return the {@link #getPositions() positions}.
   */
  protected List<GamePosition> createPositions() {

    List<GamePosition> list = new ArrayList<>();
    list.add(GamePosition.NORTH_WEST);
    list.add(GamePosition.NORTH);
    list.add(GamePosition.NORTH_EAST);
    list.add(GamePosition.WEST);
    list.add(GamePosition.CENTER);
    list.add(GamePosition.EAST);
    list.add(GamePosition.SOUTH_WEST);
    list.add(GamePosition.SOUTH);
    list.add(GamePosition.SOUTH_EAST);
    return list;
  }

  /**
   * @return {@code true} if also the four diagonal {@link GameDirection}s such as {@link GameDirection#NORTH_WEST} are
   *         supported (e.g. for chess).
   */
  protected boolean isSupportingDiagonalDirections() {

    return false;
  }

}
