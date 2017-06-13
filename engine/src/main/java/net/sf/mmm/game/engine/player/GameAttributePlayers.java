/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.game.engine.player;

import java.util.Collections;
import java.util.List;

import net.sf.mmm.game.engine.Game;

/**
 * This is the interface for an object that {@link #getPlayers() has} a {@link GamePlayer}s.
 */
public interface GameAttributePlayers {

  /**
   * @return an {@link Collections#unmodifiableList(List) unmodifiable list} of the {@link GamePlayer}s. May be
   *         {@link List#isEmpty() empty} before the {@link Game} has {@link Game#start() started}.
   */
  List<GamePlayer> getPlayers();

}
