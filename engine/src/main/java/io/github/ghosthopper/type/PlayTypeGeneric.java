/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.type;

import java.util.Objects;

import io.github.ghosthopper.object.PlayObjectWithId;
import io.github.ghosthopper.properties.PlayProperties;

/**
 * A generic implementation of {@link PlayType} that is guaranteed to be immutable (<b>not</b> {@link #isMutable()
 * mutable}) and can act as hash key.
 */
public final class PlayTypeGeneric extends PlayObjectWithId implements PlayType {

  private final String typeName;

  /**
   * The constructor.
   *
   * @param type the {@link PlayType} to copy.
   */
  public PlayTypeGeneric(PlayType type) {
    this(type.getId(), type.getTypeName());
  }

  /**
   * The constructor.
   *
   * @param id - see {@link #getId()}.
   * @param typeName - see {@link #getTypeName()}.
   */
  public PlayTypeGeneric(String id, String typeName) {
    super(id);
    this.typeName = typeName;
  }

  @Override
  public String getTypeName() {

    return this.typeName;
  }

  @Override
  public PlayProperties getProperties() {

    throw new IllegalStateException();
  }

  @Override
  public int hashCode() {

    return Objects.hash(getId(), this.typeName);
  }

  @Override
  public boolean equals(Object obj) {

    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    PlayTypeGeneric other = (PlayTypeGeneric) obj;
    if (!Objects.equals(this.typeName, other.typeName)) {
      return false;
    }
    if (!Objects.equals(getId(), other.getId())) {
      return false;
    }
    return true;
  }

}
