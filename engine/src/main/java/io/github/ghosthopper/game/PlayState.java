/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.game;

import io.github.ghosthopper.event.PlayEventListener;

/**
 * The state of a {@link PlayGame}.
 *
 * @see PlayEventListener
 */
public class PlayState {

  /** The {@link PlayState} for {@link PlayGame#start()}. */
  public static final PlayState START = new PlayState("Start");

  /** The {@link PlayState} for {@link PlayGame#pause()}. */
  public static final PlayState PAUSE = new PlayState("Pause");

  /** The {@link PlayState} for {@link PlayGame#resume()}. */
  public static final PlayState RESUME = new PlayState("Resume");

  /** The {@link PlayState} for {@link PlayGame#end()}. */
  public static final PlayState END = new PlayState("End");

  private final String name;

  /**
   * The constructor.
   *
   * @param name see {@link #getName()}.
   */
  public PlayState(String name) {
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
