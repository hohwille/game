/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.player;

import java.util.Collections;
import java.util.List;

import io.github.ghosthopper.game.PlayGame;

/**
 * This is the interface for an object that {@link #getPlayers() has} a {@link Player}s.
 */
public interface PlayAttributePlayers {

  /**
   * @return an {@link Collections#unmodifiableList(List) unmodifiable list} of the {@link Player}s. May be
   *         {@link List#isEmpty() empty} before the {@link PlayGame} has {@link PlayGame#start() started}.
   */
  List<Player> getPlayers();

}
