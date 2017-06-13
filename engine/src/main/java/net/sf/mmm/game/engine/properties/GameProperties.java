/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.game.engine.properties;

import java.util.HashMap;
import java.util.Map;

/**
 * {@link GameProperties} are a generic container for arbitrary property values. This class wraps a simple {@link Map}
 * but provides an API via {@link GameProperty} and methods to {@link #get(GameProperty) get} and
 * {@link #set(GameProperty, Object) set} the values in a type-safe way. <br>
 * <b>Example:</b><br>
 * <pre>
 * {@link GameProperties} properties = object.{@link GameAttributeProperties#getProperties() getProperties()};
 * double score = properties.{@link #getDouble(GameProperty) getDouble}({@link GamePropertyKey#SCORE});
 *
 * </pre>
 */
public class GameProperties {

  private GameProperties parent;

  private final Map<String, Object> map;

  /**
   * The constructor.
   */
  public GameProperties() {
    this(null);
  }

  /**
   * The constructor.
   *
   * @param parent the parent {@link GameProperties} to inherit from.
   */
  public GameProperties(GameProperties parent) {
    super();
    this.parent = parent;
    this.map = new HashMap<>();
  }

  /**
   * @param property the {@link GameProperty} to check.
   * @return {@code true} if the given {@link GameProperty} is contained in this {@link GameProperties}, {@code false}
   *         otherwise.
   */
  public boolean hasProperty(GameProperty<?> property) {

    if (this.map.containsKey(property.getName())) {
      return true;
    }
    if (this.parent != null) {
      return this.parent.hasProperty(property);
    }
    return false;
  }

  /**
   * @param <T> the type of the property value.
   * @param property the requested {@link GameProperty}.
   * @return the value of the requested {@link GameProperty}. If the {@link GameProperty} was undefined, the
   *         {@link GameProperty#getDefaultValue() default value} is {@link #set(GameProperty, Object) set} and
   *         returned.
   */
  public <T> T get(GameProperty<T> property) {

    return get(property, true);
  }

  /**
   * @param <V> the type of the value.
   * @param property the requested {@link GamePropertyValue}.
   * @return the value of the requested {@link GameProperty} or the {@code PlayPropertyValue#getValue() value} of the
   *         given {@link GamePropertyValue} if undefined.
   */
  public <V> V getOrDefault(GamePropertyValue<V, ?> property) {

    return get(property, property.getValue());
  }

  /**
   * @param <V> the type of the value.
   * @param property the requested {@link GamePropertyValue}.
   * @param defaultValue the default {@link GamePropertyValue#getValue() value}.
   * @return the value of the requested {@link GameProperty} or the {@code default value} if undefined.
   */
  public <V> V get(GamePropertyValue<V, ?> property, V defaultValue) {

    GamePropertyValue<V, ?> pv = getOrNull(property);
    if (pv != null) {
      return pv.getValue();
    } else {
      return defaultValue;
    }
  }

  /**
   * @param property the requested {@link GameProperty}.
   * @param defaultValue the default {@link GamePropertyValue#getValue() value}.
   * @return the value of the requested {@link GameProperty} or the {@code default value} if undefined.
   */
  public double getDouble(GamePropertyValueDouble property, double defaultValue) {

    GamePropertyValueDouble pv = getOrNull(property);
    if (pv != null) {
      return pv.get();
    } else {
      return defaultValue;
    }
  }

  /**
   * @param property the requested {@link GameProperty}.
   * @param defaultValue the default {@link GamePropertyValue#getValue() value}.
   * @return the value of the requested {@link GameProperty} or the {@code default value} if undefined.
   */
  public long getLong(GamePropertyValueLong property, long defaultValue) {

    GamePropertyValueLong pv = getOrNull(property);
    if (pv != null) {
      return pv.get();
    } else {
      return defaultValue;
    }
  }

  /**
   * @param property the requested {@link GameProperty}.
   * @param defaultValue the default {@link GamePropertyValue#getValue() value}.
   * @return the value of the requested {@link GameProperty} or the {@code default value} if undefined.
   */
  public int getInteger(GamePropertyValueInt property, int defaultValue) {

    GamePropertyValueInt pv = getOrNull(property);
    if (pv != null) {
      return pv.get();
    } else {
      return defaultValue;
    }
  }

  /**
   * @param <T> the type of the property value.
   * @param property the requested {@link GameProperty}.
   * @return the value of the requested {@link GameProperty} or {@code null} if undefined (not
   *         {@link #set(GameProperty, Object) set}).
   */
  public <T> T getOrNull(GameProperty<T> property) {

    return get(property, false);
  }

  /**
   * @param <T> the type of the property value.
   * @param property the requested {@link GameProperty}.
   * @param useDefault - {@code true} if the {@link GameProperty#getDefaultValue() default value} of the given
   *        {@link GameProperty} will be {@link #set(GameProperty, Object) set} and returned if the property was
   *        undefined (required for mutable {@link GamePropertyContainer}s), {@code false} otherwise.
   * @return the value of the requested {@link GameProperty}. If {@code useDefault} is {@code true} the
   *         {@link GameProperty#getDefaultValue() default value} will be return if the requested property was
   *         undefined. Otherwise {@code null} is returned in such case.
   */
  private <T> T get(GameProperty<T> property, boolean useDefault) {

    String key = property.getName();
    T value = getPropertyInternal(key);
    if (value == null) {
      if (useDefault) {
        value = property.getDefaultValue();
        this.map.put(key, value); // required for mutable instances of PlayPropertyContainer
      }
    }
    return value;
  }

  @SuppressWarnings("unchecked")
  private <T> T getPropertyInternal(String key) {

    T value = (T) this.map.get(key);
    if ((value == null) && (this.parent != null)) {
      return this.parent.getPropertyInternal(key);
    }
    return value;
  }

  /**
   * @param property the requested {@link GameProperty}.
   * @return the value of the requested {@link GameProperty}.
   */
  public double getDouble(GameProperty<Double> property) {

    Double value = get(property);
    if (value == null) {
      return 0;
    } else {
      return value.doubleValue();
    }
  }

  /**
   * @param property the requested {@link GameProperty}.
   * @return the value of the requested {@link GameProperty}.
   */
  public long getLong(GameProperty<Long> property) {

    Long value = get(property);
    if (value == null) {
      return 0;
    } else {
      return value.longValue();
    }
  }

  /**
   * @param property the requested {@link GameProperty}.
   * @return the value of the requested {@link GameProperty}.
   */
  public int getInt(GameProperty<Integer> property) {

    Integer value = get(property);
    if (value == null) {
      return 0;
    } else {
      return value.intValue();
    }
  }

  /**
   * @param <T> the type of the property value.
   * @param property the requested {@link GameProperty}.
   * @param value the new value of the given {@link GameProperty}.
   * @return this instance for fluent API calls.
   */
  public <T> GameProperties set(GameProperty<T> property, T value) {

    this.map.put(property.getName(), value);
    return this;
  }

  /**
   * @param property the {@link GamePropertyValue} to set. Used both as {@code key} and as {@code value} to set.
   * @return this instance for fluent API calls.
   */
  public GameProperties set(GamePropertyContainer<?> property) {

    this.map.put(property.getName(), property);
    return this;
  }

  /**
   * @param property the requested {@link GameProperty}.
   * @param value the new value of the given {@link GameProperty}.
   * @return this instance for fluent API calls.
   */
  public GameProperties setDouble(GameProperty<Double> property, double value) {

    return set(property, Double.valueOf(value));
  }

  /**
   * @param property the requested {@link GameProperty}.
   * @param value the new value of the given {@link GameProperty}.
   * @return this instance for fluent API calls.
   */
  public GameProperties setLong(GameProperty<Long> property, long value) {

    return set(property, Long.valueOf(value));
  }

  /**
   * @param property the requested {@link GameProperty}.
   * @param value the new value of the given {@link GameProperty}.
   * @return this instance for fluent API calls.
   */
  public GameProperties setInt(GameProperty<Integer> property, int value) {

    return set(property, Integer.valueOf(value));
  }

}
