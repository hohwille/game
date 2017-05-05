/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.game;

import io.github.ghosthopper.event.GameEvent;
import io.github.ghosthopper.event.GameEventListener;

/**
 * The state of a {@link Game}.
 *
 * @see GameEventListener
 */
public class GameState implements GameEvent {

  /** The {@link GameState} for {@link Game#begin()}. */
  public static final GameState BEGIN = new GameState("Begin");

  /** The {@link GameState} for {@link Game#start()}. */
  public static final GameState START = new GameState("Start");

  /** The {@link GameState} for {@link Game#pause()}. */
  public static final GameState PAUSE = new GameState("Pause");

  /** The {@link GameState} for {@link Game#resume()}. */
  public static final GameState RESUME = new GameState("Resume");

  /** The {@link GameState} for {@link Game#end()}. */
  public static final GameState END = new GameState("End");

  private final String name;

  /**
   * The constructor.
   *
   * @param name see {@link #getName()}.
   */
  public GameState(String name) {
    super();
    this.name = name;
  }

  /**
   * @return the name of this state.
   */
  public String getName() {

    return this.name;
  }

  @Override
  public String toString() {

    return this.name;
  }

}
