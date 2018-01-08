/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.game.engine.time;

import java.time.Duration;
import java.time.Instant;

/**
 * Implementation of {@link GameTime} as mutable Java bean.
 *
 * @author Joerg Hohwiller (hohwille at users.sourceforge.net)
 * @since 1.0.0
 */
public class GameTimeBean implements GameTime {

  private int turnCount;

  private int roundCount;

  private Duration duration;

  private Instant start;

  private Instant time;

  /**
   * The constructor.
   */
  public GameTimeBean() {

    super();
    this.start = Instant.now();
  }

  @Override
  public int getTurnCount() {

    return this.turnCount;
  }

  /**
   * Increments the {@link #getTurnCount() turn count}.
   */
  public void incrementTurnCount() {

    this.turnCount++;
  }

  @Override
  public int getRoundCount() {

    return this.roundCount;
  }

  /**
   * Increments the {@link #getRoundCount() round count}.
   */
  public void incrementRoundCount() {

    this.roundCount++;
  }

  @Override
  public Duration getDuration() {

    return this.duration;
  }

  /**
   * @param duration the new value of {@link #getDuration()}.
   */
  public void setDuration(Duration duration) {

    this.duration = duration;
  }

  /**
   * @param nanos the number of nanoseconds to add to the current {@link #getDuration() duration}.
   */
  public void addDurationNanosconds(long nanos) {

    if (this.duration == null) {
      this.duration = Duration.ofNanos(nanos);
    } else {
      this.duration = this.duration.plusNanos(nanos);
    }
  }

  @Override
  public Instant getStart() {

    return this.start;
  }

  /**
   * @param start the new value of {@link #getStart()}.
   */
  public void setStart(Instant start) {

    this.start = start;
  }

  @Override
  public Instant getTime() {

    return this.time;
  }

  /**
   * @param time the new value of {@link #getTime()}.
   */
  public void setTime(Instant time) {

    this.time = time;
  }

}
