/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.game.games.polyopoly;

import net.sf.mmm.game.engine.Game;
import net.sf.mmm.game.engine.level.GameLevel;

/**
 * The game <em>polyopoly</em>.
 */
public class Polyopoly extends Game {

  /**
   * The constructor.
   */
  public Polyopoly() {

    super("Polyopoly");
  }

  @Override
  protected GameLevel createFirstLevel() {

    GameLevel firstLevel = super.createFirstLevel();
    initLevelAsRectangular(firstLevel, 8, 8);
    return firstLevel;
  }

}
