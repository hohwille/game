/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.game.engine.time;

import java.time.Duration;
import java.time.Instant;

/**
 * Implementation of {@link GameTime} as immutable datatype.
 *
 * @author Joerg Hohwiller (hohwille at users.sourceforge.net)
 * @since 1.0.0
 */
public class GameTimeType implements GameTime {

  private final int turnCount;

  private final int roundCount;

  private final Duration duration;

  private final Instant start;

  private final Instant time;

  /**
   * The constructor.
   *
   * @param turnCount - see {@link #getTurnCount()}.
   * @param roundCount - see {@link #getRoundCount()}.
   * @param duration - see {@link #getDuration()}.
   * @param start - see {@link #getStart()}.
   */
  public GameTimeType(int turnCount, int roundCount, Duration duration, Instant start) {

    this(turnCount, roundCount, duration, start, Instant.now());
  }

  /**
   * The constructor.
   *
   * @param turnCount - see {@link #getTurnCount()}.
   * @param roundCount - see {@link #getRoundCount()}.
   * @param duration - see {@link #getDuration()}.
   * @param start - see {@link #getStart()}.
   * @param time - see {@link #getTime()}.
   */
  public GameTimeType(int turnCount, int roundCount, Duration duration, Instant start, Instant time) {

    super();
    this.turnCount = turnCount;
    this.roundCount = roundCount;
    this.duration = duration;
    this.start = start;
    this.time = time;
  }

  @Override
  public int getTurnCount() {

    return this.turnCount;
  }

  @Override
  public int getRoundCount() {

    return this.roundCount;
  }

  @Override
  public Duration getDuration() {

    return this.duration;
  }

  @Override
  public Instant getStart() {

    return this.start;
  }

  @Override
  public Instant getTime() {

    return this.time;
  }

}
