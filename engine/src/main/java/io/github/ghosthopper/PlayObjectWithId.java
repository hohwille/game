/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper;

import java.util.Locale;

/**
 * This is the interface for any item of this game that can be identified by and {@link #getId() ID}.
 */
public abstract class PlayObjectWithId extends PlayObject {

  private final String id;

  /**
   * The constructor.
   *
   * @param id - see {@link #getId()}.
   */
  public PlayObjectWithId(String id) {
    super();
    this.id = id;
  }

  /**
   * @return the ID of this item used to find corresponding graphics or audio information.
   */
  public String getId() {

    return this.id;
  }

  /**
   * @return the localized name of this object.
   */
  public String getLocalizedName() {

    return getGame().getTranslator().translate(getId());
  }

  /**
   * @param locale the explicit {@link Locale} to translate to.
   * @return the localized name of this object.
   */
  public String getLocalizedName(Locale locale) {

    return getGame().getTranslator().translate(getId(), locale);
  }

  @Override
  public String toString() {

    return this.id;
  }

}
