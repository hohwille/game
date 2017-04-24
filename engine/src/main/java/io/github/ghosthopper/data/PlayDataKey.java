/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.data;

import java.util.Objects;

import io.github.ghosthopper.color.PlayColor;
import io.github.ghosthopper.move.PlayAttributeDirection;
import io.github.ghosthopper.move.PlayDirection;
import io.github.ghosthopper.object.AbstractPlayStateObject;
import io.github.ghosthopper.object.AbstractPlayTypedObject;
import io.github.ghosthopper.object.PlayObject;
import io.github.ghosthopper.object.PlayObjectType;
import io.github.ghosthopper.object.PlayType;
import io.github.ghosthopper.object.PlayTypedObject;

/**
 * A key to lookup and cache game resources.
 */
public class PlayDataKey {

  /** An Edge between borders. */
  public static final PlayDataKey EDGE = new PlayDataKey("Edge", "Normal");

  private final String typeId;

  private final String typeName;

  private final String objectId;

  private final PlayColor color;

  private final PlayDirection direction;

  private final PlayDataKey overlay;

  /**
   * The constructor.
   *
   * @param object the {@link PlayTypedObject}.
   */
  public PlayDataKey(PlayTypedObject object) {
    super();
    PlayType type = object.getType();
    this.typeId = getId(type);
    this.typeName = type.getTypeName();
    this.overlay = convertOverlay(type.getOverlay());
    this.color = object.getColor();
    String oid = getId(object);
    if (this.typeId.equals(oid)) {
      oid = null;
    }
    this.objectId = oid;
    this.direction = getDirection(object);
  }

  private PlayDataKey(String typeName, String typeId) {

    super();
    this.typeName = typeName;
    this.typeId = typeId;
    this.overlay = null;
    this.objectId = null;
    this.color = null;
    this.direction = null;
  }

  private static PlayDataKey convertOverlay(AbstractPlayStateObject overlayObject) {

    if (overlayObject == null) {
      return null;
    } else if (overlayObject instanceof AbstractPlayTypedObject) {
      return new PlayDataKey((AbstractPlayTypedObject) overlayObject);
    } else if (overlayObject instanceof PlayObjectType) {
      return new PlayDataKey(((PlayObjectType) overlayObject).getTypeName(), overlayObject.getId());
    } else {
      throw new IllegalStateException(overlayObject.toString());
    }
  }

  private PlayDirection getDirection(PlayTypedObject object) {

    if (object instanceof PlayAttributeDirection) {
      return ((PlayAttributeDirection) object).getDirection();
    }
    return null;
  }

  private static String getId(PlayObject object) {

    return object.getId();
  }

  /**
   * @return the {@link PlayObjectType#getId() id} of the {@link AbstractPlayTypedObject#getType() type}.
   */
  public String getTypeId() {

    return this.typeId;
  }

  /**
   * @return the {@link PlayObjectType#getTypeName() type name}.
   */
  public String getTypeName() {

    return this.typeName;
  }

  /**
   * @return the {@link AbstractPlayTypedObject#getId() id} of the object or {@code null}.
   */
  public String getObjectId() {

    return this.objectId;
  }

  /**
   * @return the color
   */
  public PlayColor getColor() {

    return this.color;
  }

  /**
   * @return the {@link PlayDataKey} of the overlay object or {@code null} for none.
   */
  public PlayDataKey getOverlay() {

    return this.overlay;
  }

  /**
   * @return the direction
   */
  public PlayDirection getDirection() {

    return this.direction;
  }

  @Override
  public int hashCode() {

    return Objects.hash(this.typeName, this.typeId, this.objectId, this.color, this.direction);
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
    PlayDataKey other = (PlayDataKey) obj;
    if (!Objects.equals(this.typeName, other.typeName)) {
      return false;
    }
    if (!Objects.equals(this.typeId, other.typeId)) {
      return false;
    }
    if (!Objects.equals(this.objectId, other.objectId)) {
      return false;
    }
    if (!Objects.equals(this.color, other.color)) {
      return false;
    }
    if (!Objects.equals(this.overlay, other.overlay)) {
      return false;
    }
    if (!Objects.equals(this.direction, other.direction)) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {

    StringBuilder buffer = new StringBuilder(this.typeName);
    if (this.direction != null) {
      buffer.append('/');
      buffer.append(this.direction);
    }
    if (this.color != null) {
      buffer.append('/');
      buffer.append(this.color);
    }
    buffer.append('/');
    buffer.append(this.typeId);
    if (this.objectId != null) {
      buffer.append('/');
      buffer.append(this.objectId);
    }
    return buffer.toString();
  }

}
