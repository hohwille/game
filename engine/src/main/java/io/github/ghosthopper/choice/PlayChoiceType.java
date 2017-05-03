/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.choice;

import io.github.ghosthopper.type.PlayTypeBase;

/**
 * The type specific for a choice.
 * 
 * @see PlayChoiceGroup#getImageType()
 */
public class PlayChoiceType extends PlayTypeBase {

  /**
   * The constructor.
   *
   * @param id - see {@link #getId()}.
   */
  public PlayChoiceType(String id) {
    super(id);
  }

  @Override
  public String getTypeName() {

    return "Choice";
  }

}
