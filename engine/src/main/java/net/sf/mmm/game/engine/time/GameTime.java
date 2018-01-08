/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.game.engine.time;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

/**
 * A {@link GameTime} represents a snapshot of the time information {@link GameAttributeTime#getCurrentTime() received}
 * from a {@link net.sf.mmm.game.engine.Game}.
 */
public interface GameTime {

  /**
   * @param unit the requested {@link GameTimeUnit}.
   * @return the duration in the given {@link GameTimeUnit unit}.
   */
  default int get(GameTimeUnit unit) {

    switch (unit) {
      case TURNS:
        return getTurnCount();
      case ROUNDS:
        return getRoundCount();
      case SECONDS:
        return (int) getDuration().getSeconds();
      case MINUTES:
        return (int) getDuration().get(ChronoUnit.MINUTES);
      case HOURS:
        return (int) getDuration().get(ChronoUnit.HOURS);
      case DAYS:
        return (int) getDuration().get(ChronoUnit.DAYS);
      default :
        throw new IllegalArgumentException("" + unit);
    }
  }

  /**
   * @return the number of turns that have been completed in case of a {@link net.sf.mmm.game.engine.Game#isTurnGame()
   *         turn game}, otherwise {@code 0}.
   */
  int getTurnCount();

  /**
   * @return the number of rounds that have been completed in case of a {@link net.sf.mmm.game.engine.Game#isTurnGame()
   *         turn game}, otherwise {@code 0}.
   */
  int getRoundCount();

  /**
   * @return the total {@link Duration} the {@link net.sf.mmm.game.engine.Game} has been running until this
   *         {@link GameTime} was {@link GameAttributeTime#getCurrentTime() received}. Whenever the
   *         {@link net.sf.mmm.game.engine.Game} is {@link net.sf.mmm.game.engine.Game#pause() paused} the according
   *         clock is stopped so this {@link Duration} only reflects the time when the
   *         {@link net.sf.mmm.game.engine.Game} was {@link net.sf.mmm.game.engine.Game#isActive() active}.
   */
  Duration getDuration();

  /**
   * @return the {@link Instant} when the {@link net.sf.mmm.game.engine.Game} was
   *         {@link net.sf.mmm.game.engine.Game#start() started}.
   */
  Instant getStart();

  /**
   * @return the {@link Instant} when this {@link GameTime} was {@link GameAttributeTime#getCurrentTime() created}.
   */
  Instant getTime();

  /**
   * @param time the {@link GameTime} to subtract from this {@link GameTime}.
   * @return the difference.
   */
  default GameTime subtract(GameTime time) {

    int turnCount = getTurnCount() - time.getTurnCount();
    int roundCount = getRoundCount() - time.getRoundCount();
    Duration duration = getDuration().minus(time.getDuration());
    return new GameTimeType(turnCount, roundCount, duration, getStart(), getTime());
  }

}
