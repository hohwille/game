/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.game.engine.time;

import java.util.Objects;

/**
 * A duration of a {@link net.sf.mmm.game.engine.Game} specified as an {@link #getAmount() amount} in a
 * {@link #getUnit() unit}.
 *
 * @author Joerg Hohwiller (hohwille at users.sourceforge.net)
 * @since 1.0.0
 */
public class GameDuration {

  private final int amount;

  private final GameTimeUnit unit;

  /**
   * The constructor.
   *
   * @param amount - see {@link #getAmount()}.
   * @param unit - see {@link #getUnit()}.
   */
  public GameDuration(int amount, GameTimeUnit unit) {

    super();
    if (amount < 0) {
      throw new IllegalArgumentException(Integer.toString(amount));
    }
    Objects.requireNonNull(unit, "unit");
    this.amount = amount;
    this.unit = unit;
  }

  /**
   * @return the required time in the {@link #getUnit() time unit}.
   */
  public int getAmount() {

    return this.amount;
  }

  /**
   * @return the {@link GameTimeUnit unit} of the {@link #getAmount() time amount}.
   */
  public GameTimeUnit getUnit() {

    return this.unit;
  }

  @Override
  public String toString() {

    return this.amount + " " + this.unit;
  }

}
