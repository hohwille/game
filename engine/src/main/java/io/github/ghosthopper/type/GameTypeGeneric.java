/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.type;

import java.util.Objects;

import io.github.ghosthopper.object.GameObjectWithId;
import io.github.ghosthopper.properties.GameProperties;

/**
 * A generic implementation of {@link GameType} that is guaranteed to be immutable (<b>not</b> {@link #isMutable()
 * mutable}) and can act as hash key.
 */
public final class GameTypeGeneric extends GameObjectWithId implements GameType {

  private final String typeName;

  /**
   * The constructor.
   *
   * @param type the {@link GameType} to copy.
   */
  public GameTypeGeneric(GameType type) {
    this(type.getId(), type.getTypeName());
  }

  /**
   * The constructor.
   *
   * @param id - see {@link #getId()}.
   * @param typeName - see {@link #getTypeName()}.
   */
  public GameTypeGeneric(String id, String typeName) {
    super(id);
    this.typeName = typeName;
  }

  @Override
  public String getTypeName() {

    return this.typeName;
  }

  @Override
  public GameProperties getProperties() {

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
    GameTypeGeneric other = (GameTypeGeneric) obj;
    if (!Objects.equals(this.typeName, other.typeName)) {
      return false;
    }
    if (!Objects.equals(getId(), other.getId())) {
      return false;
    }
    return true;
  }

}
