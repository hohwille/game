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

import io.github.ghosthopper.PlayLevel;
import io.github.ghosthopper.border.PlayBorder;
import io.github.ghosthopper.border.PlayBorderType;
import io.github.ghosthopper.event.PlayEvent;
import io.github.ghosthopper.event.PlayEventDispatcher;
import io.github.ghosthopper.event.PlayEventListener;
import io.github.ghosthopper.event.PlayEventSender;
import io.github.ghosthopper.event.PlayKeyEvent;
import io.github.ghosthopper.event.PlayKeys;
import io.github.ghosthopper.field.PlayField;
import io.github.ghosthopper.field.PlayFieldType;
import io.github.ghosthopper.figure.PlayFigure;
import io.github.ghosthopper.figure.PlayFigureGroup;
import io.github.ghosthopper.figure.PlayFigureTurnEvent;
import io.github.ghosthopper.figure.PlayFigureType;
import io.github.ghosthopper.i18n.PlayTranslator;
import io.github.ghosthopper.item.PlayItem;
import io.github.ghosthopper.item.PlayItemType;
import io.github.ghosthopper.item.PlayPickItem;
import io.github.ghosthopper.move.PlayDirection;
import io.github.ghosthopper.object.PlayStateObjectWithId;
import io.github.ghosthopper.player.Player;
import io.github.ghosthopper.player.PlayerTurnEvent;
import io.github.ghosthopper.position.PlayPosition;

/**
 * This is the main object and represents an actual game with its rules. To implement your own game, simply extend this
 * class and implement/override according methods. You can e.g.
 * <ul>
 * <li>Decide for {@link #isTurnGame() turn game} (default) or action game.</li>
 * <li>Define custom {@link PlayFigureType}s and reuse predefined ones.</li>
 * <li>Define custom {@link PlayItemType}s and reuse predefined ones.</li>
 * <li>Define custom {@link PlayFieldType}s and reuse predefined ones.</li>
 * <li>{@link #getPlayers() Predefine} the {@link Player}s with their {@link PlayFigure}s based on the above types.</li>
 * <li>{@link #createDirections() Define} custom {@link PlayDirection}s or use the predefined ones (see
 * {@link #isSupportingDiagonalDirections()}).</li>
 * <li>{@link #createFirstLevel() create} {@link PlayLevel}s with all the {@link PlayField}s typically as
 * {@link #initLevelAsRectangular(PlayLevel, int, int, PlayBorderTypeStrategy) rectangular field}.</li>
 * <li>Customize the {@link PlayBorder}s to create complex puzzle/riddle games.</li>
 * <li>Place {@link PlayItem}s and {@link PlayFigure}s on the {@link PlayField}s.</li>
 * <li>Implement a strategy to {@link #moveBotPlayer(Player) move bot players}.</li>
 * </ul>
 */
public class PlayGame extends PlayStateObjectWithId implements PlayEventSender<PlayEvent> {

  /** @see #getCurrentGame() */
  private static PlayGame currentGame = PlayGameNone.INSTANCE;

  private final PlayTranslator translator;

  private final List<Player> players;

  private final List<Player> playersView;

  private final Map<Class<?>, PlayEventDispatcher<?>> dispatcherMap;

  private int currentPlayer;

  private int currentFigure;

  private PlayLevel currentLevel;

  private List<PlayDirection> directions;

  private List<PlayPosition> position;

  private boolean paused;

  /**
   * The constructor.
   *
   * @param id - see {@link #getId()}.
   */
  public PlayGame(String id) {
    super(id);
    this.translator = new PlayTranslator(this);
    this.players = new ArrayList<>();
    this.playersView = Collections.unmodifiableList(this.players);
    this.dispatcherMap = new HashMap<>();
    addListener(PlayKeyEvent.class, this::onKeyPressed);
  }

  /**
   * @param event the {@link PlayKeyEvent} to handle.
   */
  protected void onKeyPressed(PlayKeyEvent event) {

    if (event.getCode() == PlayKeys.KEY_LEFT) {
      move(PlayDirection.WEST);
    } else if (event.getCode() == PlayKeys.KEY_RIGHT) {
      move(PlayDirection.EAST);
    } else if (event.getCode() == PlayKeys.KEY_UP) {
      move(PlayDirection.NORTH);
    } else if (event.getCode() == PlayKeys.KEY_DOWN) {
      move(PlayDirection.SOUTH);
    } else if (event.getCode() == PlayKeys.KEY_ENTER) {
      nextFigure(true);
    } else if (event.getCode() == PlayKeys.KEY_D) {
      PlayFigure figure = getCurrentFigure();
      if (figure != null) {
        figure.dropItem();
      }
    } else if (event.getCode() == PlayKeys.KEY_P) {
      PlayFigure figure = getCurrentFigure();
      if (figure != null) {
        figure.pickItem();
      }
    } else if (event.getCode() == PlayKeys.KEY_G) {
      PlayFigure figure = getCurrentFigure();
      if (figure != null) {
        PlayField field = figure.getLocation();
        if (field != null) {
          PlayFigureGroup group = figure.getGroup();
          Player player = figure.getPlayer();
          for (PlayFigure fieldFigure : field.getFigures()) {
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
   * Moves the {@link #getCurrentFigure() current figure} in the given {@link PlayDirection}.
   *
   * @param direction the {@link PlayDirection} to move.
   */
  protected void move(PlayDirection direction) {

    PlayFigure figure = getCurrentFigure();
    if (figure != null) {
      figure.move(direction);
    }
  }

  @SuppressWarnings({ "rawtypes", "unchecked" })
  @Override
  public void sendEvent(PlayEvent event) {

    Objects.requireNonNull(event, "event");
    Class<? extends PlayEvent> eventType = event.getClass();
    PlayEventDispatcher eventDispatcher = getEventDispatcherOptional(eventType);
    if (eventDispatcher != null) {
      eventDispatcher.sendEvent(event);
    }
  }

  @SuppressWarnings("rawtypes")
  private PlayEventDispatcher getEventDispatcher(Class<?> eventType, boolean createIfNotExists) {

    PlayEventDispatcher<?> dispatcher = this.dispatcherMap.get(eventType);
    if (dispatcher == null) {
      Class<?> parentType = eventType.getSuperclass();
      if (createIfNotExists) {
        PlayEventDispatcher<?> parent;
        if ((parentType != null) && PlayEvent.class.isAssignableFrom(parentType)) {
          parent = getEventDispatcher(parentType, true);
        } else {
          parent = null;
        }
        dispatcher = new PlayEventDispatcher<>(parent);
        this.dispatcherMap.put(eventType, dispatcher);
      } else {
        if ((parentType != null) && PlayEvent.class.isAssignableFrom(parentType)) {
          dispatcher = getEventDispatcher(parentType, false);
        } else {
          dispatcher = null;
        }
      }
    }
    return dispatcher;
  }

  /**
   * @param <E> the type of the {@link PlayEvent}.
   * @param eventType the {@link Class} reflecting the {@link PlayEvent}.
   * @return the according {@link PlayEventDispatcher}. Will be created if not already exists.
   */
  @SuppressWarnings("unchecked")
  protected <E extends PlayEvent> PlayEventDispatcher<E> getEventDispatcherRequired(Class<E> eventType) {

    return getEventDispatcher(eventType, true);
  }

  /**
   * @param <E> the type of the {@link PlayEvent}.
   * @param eventType the {@link Class} reflecting the {@link PlayEvent}.
   * @return the according {@link PlayEventDispatcher} or {@code null} if not exists.
   */
  @SuppressWarnings("unchecked")
  protected <E extends PlayEvent> PlayEventDispatcher<E> getEventDispatcherOptional(Class<E> eventType) {

    return getEventDispatcher(eventType, false);
  }

  @Override
  public <E extends PlayEvent> void addListener(Class<E> eventType, PlayEventListener<E> listener) {

    Objects.requireNonNull(eventType, "eventType");
    Objects.requireNonNull(listener, "listener");
    if (eventType.isInterface()) {
      throw new IllegalArgumentException(eventType.getName());
    }
    getEventDispatcherRequired(eventType).addListener(eventType, listener);
  }

  @Override
  public boolean removeListener(PlayEventListener<? extends PlayEvent> listener) {

    for (PlayEventDispatcher<?> dispatcher : this.dispatcherMap.values()) {
      @SuppressWarnings({ "rawtypes", "unchecked" })
      boolean removed = dispatcher.removeListener((PlayEventListener) listener);
      if (removed) {
        return true;
      }
    }
    return false;
  }

  /**
   * @return the currentGame
   */
  public static PlayGame getCurrentGame() {

    return currentGame;
  }

  @Override
  public PlayGame getGame() {

    return this;
  }

  /**
   * Starts this {@link PlayGame}.
   */
  public final void start() {

    currentGame = this;
    sendEvent(PlayState.START);
  }

  /**
   * @return {@code true} if this game is currently active (it is the {@link #getCurrentGame() current game} and is not
   *         {@link #isPaused() paused} or over).
   */
  public boolean isActive() {

    if (currentGame != this) {
      return false;
    }
    if (this.paused) {
      return false;
    }
    return true;
  }

  /**
   * @return {@code true} if {@link #pause() paused}, {@code false} otherwise.
   */
  public boolean isPaused() {

    return this.paused;
  }

  /**
   * Pauses this {@link PlayGame}.
   */
  public void pause() {

    if (this.paused) {
      return;
    }
    this.paused = true;
    sendEvent(PlayState.PAUSE);
  }

  /**
   * Resumes this {@link PlayGame} after it has been {@link #pause() paused}.
   */
  public void resume() {

    if (!this.paused) {
      return;
    }
    this.paused = false;
    sendEvent(PlayState.RESUME);
  }

  /**
   * Ends this {@link PlayGame}.
   */
  public final void end() {

    sendEvent(PlayState.END);
    currentGame = PlayGameNone.INSTANCE;
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
   * @return the {@link PlayTranslator} used for localization.
   */
  public PlayTranslator getTranslator() {

    return this.translator;
  }

  /**
   * @return {@code true} if this game is played in turns (rounds), where only one {@link Player} can act at a time (for
   *         strategic games like chess, Ludo, etc.), {@code false} if all {@link Player}s can play in parallel (for
   *         action games where speed matters).
   */
  public boolean isTurnGame() {

    return true;
  }

  /**
   * @return {@code true} if a {@link PlayFigure} should automatically {@link PlayFigure#pickItem() pick} the
   *         {@link PlayPickItem}(s) when he moves on a {@link PlayField}, {@code false} otherwise.
   */
  public boolean isAutoPickItem() {

    return false;
  }

  /**
   * @return {@code true} if this game should show its {@link PlayBorder}s in the UI, {@code false} otherwise (borders
   *         are hidden).
   */
  public boolean isShowBorder() {

    return true;
  }

  /**
   * @return an {@link Collections#unmodifiableList(List) unmodifiable list} of the {@link Player}s.
   *
   * @see #addPlayer(Player)
   */
  public List<Player> getPlayers() {

    return this.playersView;
  }

  /**
   * @param player the {@link Player} to add.
   */
  protected void addPlayer(Player player) {

    player.setGame(this);
    this.players.add(player);
  }

  /**
   * @return the {@link Player} who is currently on turn.
   */
  public Player getCurrentPlayer() {

    return this.players.get(this.currentPlayer);
  }

  /**
   * Ends the turn of the {@link #getCurrentPlayer() current} {@link Player#isHuman() human} {@link Player} and moves
   * all following computer {@link Player}s (bots).
   *
   * @return the next {@link Player#isHuman() human} {@link Player} who is now on turn.
   */
  public Player nextPlayer() {

    if (!isTurnGame()) {
      return null;
    }
    PlayFigure oldFigure = getCurrentFigure();
    Player oldPlayer = null;
    if (oldFigure != null) {
      oldPlayer = oldFigure.getPlayer();
    }
    this.currentPlayer++;
    if (this.currentPlayer >= this.players.size()) {
      this.currentPlayer = 0;
    }
    this.currentFigure = 0;
    PlayFigure newFigure = getCurrentFigure();
    Player newPlayer;
    if (newFigure == null) {
      newPlayer = getCurrentPlayer();
    } else {
      newPlayer = newFigure.getPlayer();
    }
    sendEvent(new PlayerTurnEvent(oldPlayer, newPlayer));
    sendEvent(new PlayFigureTurnEvent(oldFigure, newFigure));
    if ((newPlayer != null) && !newPlayer.isHuman()) {
      moveBotPlayer(newPlayer);
      newPlayer = nextPlayer();
    }
    return newPlayer;
  }

  /**
   * @return the current {@link PlayFigure} of the {@link #getCurrentPlayer() current player} or {@code null} if the
   *         turn of the current player is over.
   */
  public PlayFigure getCurrentFigure() {

    Player player = getCurrentPlayer();
    List<PlayFigure> figures = player.getFigures();
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
   * @return the next {@link PlayFigure} or {@code null} if turn is over.
   */
  public PlayFigure nextFigure(boolean mayChangePlayer) {

    if (!isTurnGame()) {
      return null;
    }
    Player player = getCurrentPlayer();
    PlayFigure oldFigure = null;
    List<PlayFigure> figures = player.getFigures();
    PlayFigure newFigure = null;
    int size = figures.size();
    while (this.currentFigure < size) {
      if (oldFigure == null) {
        oldFigure = figures.get(this.currentFigure);
        assert (oldFigure != null);
      }
      this.currentFigure++;
      if (this.currentFigure < size) {
        PlayFigure nextFigure = player.getFigures().get(this.currentFigure);
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
    sendEvent(new PlayFigureTurnEvent(oldFigure, newFigure));
    return newFigure;
  }

  /**
   * @param player the non-{@link Player#isHuman() human} {@link Player} to move.
   */
  protected void moveBotPlayer(Player player) {

    // should be overridden if bot players are supported by game.
  }

  /**
   * @return the current {@link PlayLevel}.
   */
  public PlayLevel getCurrentLevel() {

    if (this.currentLevel == null) {
      this.currentLevel = initLevels();
    }
    return this.currentLevel;
  }

  /**
   * @return the initial {@link PlayLevel}.
   */
  private PlayLevel initLevels() {

    PlayLevel level = createFirstLevel();
    return level;
  }

  /**
   * @return creates the first (and potentially single) {@link PlayLevel}.
   */
  protected PlayLevel createFirstLevel() {

    return new PlayLevel("Level 1", this);
  }

  /**
   * @return the {@link PlayBorderTypeStrategy}.
   */
  protected PlayBorderTypeStrategy getBorderStrategy() {

    return PlayBorderTypeStrategyStatic.OPEN;
  }

  /**
   * @param level the {@link PlayLevel} to initialize.
   * @param width the number of {@link PlayField}s in {@link #getDirectionX() X-direction}.
   * @param height the number of {@link PlayField}s in {@link #getDirectionY() Y-direction}.
   * @return the given {@link PlayLevel} after initialization.
   */
  protected PlayLevel initLevelAsRectangular(PlayLevel level, int width, int height) {

    return initLevelAsRectangular(level, width, height, getBorderStrategy());
  }

  /**
   * @param level the {@link PlayLevel} to initialize.
   * @param width the number of {@link PlayField}s in {@link #getDirectionX() X-direction}.
   * @param height the number of {@link PlayField}s in {@link #getDirectionY() Y-direction}.
   * @param borderStrategy the {@link PlayBorderTypeStrategy}.
   * @return the given {@link PlayLevel} after initialization.
   */
  protected PlayLevel initLevelAsRectangular(PlayLevel level, int width, int height, PlayBorderTypeStrategy borderStrategy) {

    assert level.getGame() == this;
    PlayDirection xDir = getDirectionX();
    PlayDirection yDir = getDirectionY();
    PlayDirection xDirInverse = xDir.getInverse();
    PlayDirection yDirInverse = yDir.getInverse();
    PlayField startField = level.getStartField();
    startField.initEdge();
    PlayField xStartField = startField;
    for (int y = 1; y <= height; y++) {
      PlayField field = xStartField;
      field.createWall(xDirInverse);
      for (int x = 1; x <= width; x++) {
        if (y == 1) {
          field.createWall(yDirInverse);
        }
        if (y == height) {
          field.createWall(yDir);
        } else {
          PlayBorderType type = borderStrategy.getType(field, x, y, width, height, yDir);
          field.createBorder(type, yDir);
        }
        if (x == width) {
          field.createWall(xDir);
        } else {
          PlayBorderType type = borderStrategy.getType(field, x, y, width, height, xDir);
          if (y == 1) {
            field = field.createBorder(type, xDir);
          } else {
            PlayField targetField = field.getField(yDirInverse).getField(xDir).getField(yDir);
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
   * @return the {@link PlayDirection}s supported by this {@link PlayGame}.
   */
  public List<PlayDirection> getDirections() {

    if (this.directions == null) {
      List<PlayDirection> newDirections = createDirections();
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
  protected List<PlayDirection> createDirections() {

    List<PlayDirection> list = new ArrayList<>();
    list.add(PlayDirection.WEST);
    list.add(getDirectionX());
    list.add(PlayDirection.NORTH);
    list.add(getDirectionY());
    if (isSupportingDiagonalDirections()) {
      list.add(PlayDirection.NORTH_WEST);
      list.add(PlayDirection.NORTH_EAST);
      list.add(PlayDirection.SOUTH_WEST);
      list.add(PlayDirection.SOUTH_EAST);
    }
    return list;
  }

  /**
   * @return the {@link PlayDirection} on X-Axis.
   */
  public PlayDirection getDirectionX() {

    return PlayDirection.EAST;
  }

  /**
   * @return the {@link PlayDirection} on Y-Axis.
   */
  public PlayDirection getDirectionY() {

    return PlayDirection.SOUTH;
  }

  /**
   * @return the {@link PlayDirection} on Z-Axis (not yet supported).
   */
  public PlayDirection getDirectionZ() {

    return null;
  }

  /**
   * @return the {@link PlayPosition}s supported by this {@link PlayGame}.
   */
  public List<PlayPosition> getPositions() {

    if (this.position == null) {
      List<PlayPosition> newPositions = createPositions();
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
  protected List<PlayPosition> createPositions() {

    List<PlayPosition> list = new ArrayList<>();
    list.add(PlayPosition.NORTH_WEST);
    list.add(PlayPosition.NORTH);
    list.add(PlayPosition.NORTH_EAST);
    list.add(PlayPosition.WEST);
    list.add(PlayPosition.CENTER);
    list.add(PlayPosition.EAST);
    list.add(PlayPosition.SOUTH_WEST);
    list.add(PlayPosition.SOUTH);
    list.add(PlayPosition.SOUTH_EAST);
    return list;
  }

  /**
   * @return {@code true} if also the four diagonal {@link PlayDirection}s such as {@link PlayDirection#NORTH_WEST} are
   *         supported (e.g. for chess).
   */
  protected boolean isSupportingDiagonalDirections() {

    return false;
  }

}
