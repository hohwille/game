/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.game.engine.money;

import java.math.BigDecimal;

import net.sf.mmm.game.engine.item.GamePickItemType;

/**
 * The type (currency) of {@link GameMoney}.
 *
 * @see GameMoney#getType()
 */
public class GameMoneyType extends GamePickItemType {

  /** The default {@link GameMoneyType}. */
  public static final GameMoneyType DEFAULT = new GameMoneyType("Money");

  /**
   * The constructor.
   *
   * @param id - see {@link #getId()}.
   */
  public GameMoneyType(String id) {

    super(id);
  }

  /**
   * @param money the {@link GameMoney} for which to determine the current value.
   * @return the {@link GameMoney#getValue() current value} of the given {@link GameMoney}.
   */
  public BigDecimal getValue(GameMoney money) {

    return money.getInitialValue();
  }

  @Override
  public String getTypeName() {

    return "Money";
  }

}
