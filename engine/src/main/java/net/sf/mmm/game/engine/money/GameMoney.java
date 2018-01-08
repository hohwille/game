/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.game.engine.money;

import java.math.BigDecimal;

import net.sf.mmm.game.engine.object.GameTypedObject;

/**
 * Interface for money consisting of a {@link #getValue() value} (amount) with its {@link #getType() type} (currency).
 */
public interface GameMoney extends GameTypedObject {

  @Override
  GameMoneyType getType();

  /**
   * @return the current value of this {@link GameMoney}.
   */
  default BigDecimal getValue() {

    return getType().getValue(this);
  }

  /**
   * @return the initial value of this {@link GameMoney}.
   */
  BigDecimal getInitialValue();

}
