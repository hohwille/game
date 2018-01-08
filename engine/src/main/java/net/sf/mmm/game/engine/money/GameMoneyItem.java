/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.game.engine.money;

import java.math.BigDecimal;

import net.sf.mmm.game.engine.Game;
import net.sf.mmm.game.engine.color.GameColor;
import net.sf.mmm.game.engine.item.GamePickItem;
import net.sf.mmm.game.engine.time.GameTime;

/**
 * Simple implementation of {@link GameMoney}.
 */
public class GameMoneyItem extends GamePickItem implements GameMoney {

  private final BigDecimal value;

  private final GameTime time;

  /**
   * The constructor.
   *
   * @param game the owning {@link #getGame() game}.
   * @param type the {@link GameMoneyType} to use as currency.
   * @param value the {@link #getInitialValue() initial value}.
   */
  public GameMoneyItem(Game game, GameMoneyType type, BigDecimal value) {

    super(game, type);
    this.value = value;
    this.time = game.getCurrentTime();
  }

  /**
   * The constructor.
   *
   * @param game the owning {@link #getGame() game}.
   * @param type the {@link GameMoneyType} to use as currency.
   * @param value the {@link #getInitialValue() initial value}.
   * @param color the {@link GameColor}.
   */
  public GameMoneyItem(Game game, GameMoneyType type, BigDecimal value, GameColor color) {

    super(game, type, color);
    this.value = value;
    this.time = game.getCurrentTime();
  }

  @Override
  public GameMoneyType getType() {

    return (GameMoneyType) super.getType();
  }

  @Override
  public BigDecimal getInitialValue() {

    return this.value;
  }

  /**
   * @return the {@link GameTime} when this money was issued.
   */
  public GameTime getTime() {

    return this.time;
  }

}
