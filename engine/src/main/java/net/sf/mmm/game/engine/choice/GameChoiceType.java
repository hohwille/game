/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.game.engine.choice;

import net.sf.mmm.game.engine.type.GameTypeBase;

/**
 * The type specific for a choice.
 * 
 * @see GameChoiceGroup#getImageType()
 */
public class GameChoiceType extends GameTypeBase {

  /**
   * The constructor.
   *
   * @param id - see {@link #getId()}.
   */
  public GameChoiceType(String id) {
    super(id);
  }

  @Override
  public String getTypeName() {

    return "Choice";
  }

}
