/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.properties;

import java.util.HashMap;
import java.util.Map;

/**
 * {@link PlayProperties} are a generic container for arbitrary property values. This class wraps a simple {@link Map}
 * but provides an API via {@link PlayProperty} and methods to {@link #get(PlayProperty) get} and
 * {@link #set(PlayProperty, Object) set} the values in a type-safe way. <br>
 * <b>Example:</b><br>
 * <pre>
 * {@link PlayProperties} properties = object.{@link PlayAttributeProperties#getProperties() getProperties()};
 * double score = properties.{@link #getDouble(PlayProperty) getDouble}({@link PlayPropertyKey#SCORE});
 *
 * </pre>
 */
public class PlayProperties {

  private PlayProperties parent;

  private final Map<String, Object> map;

  /**
   * The constructor.
   */
  public PlayProperties() {
    this(null);
  }

  /**
   * The constructor.
   *
   * @param parent the parent {@link PlayProperties} to inherit from.
   */
  public PlayProperties(PlayProperties parent) {
    super();
    this.parent = parent;
    this.map = new HashMap<>();
  }

  /**
   * @param property the {@link PlayProperty} to check.
   * @return {@code true} if the given {@link PlayProperty} is contained in this {@link PlayProperties}, {@code false}
   *         otherwise.
   */
  public boolean hasProperty(PlayProperty<?> property) {

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
   * @param property the requested {@link PlayProperty}.
   * @return the value of the requested {@link PlayProperty}. If the {@link PlayProperty} was undefined, the
   *         {@link PlayProperty#getDefaultValue() default value} is {@link #set(PlayProperty, Object) set} and
   *         returned.
   */
  @SuppressWarnings("unchecked")
  public <T> T get(PlayProperty<T> property) {

    return get(property, true);
  }

  /**
   * @param <V> the type of the value.
   * @param property the requested {@link PlayPropertyValue}.
   * @return the value of the requested {@link PlayProperty} or the {@code PlayPropertyValue#getValue() value} of the
   *         given {@link PlayPropertyValue} if undefined.
   */
  public <V> V getOrDefault(PlayPropertyValue<V, ?> property) {

    return get(property, property.getValue());
  }

  /**
   * @param <V> the type of the value.
   * @param property the requested {@link PlayPropertyValue}.
   * @param defaultValue the default {@link PlayPropertyValue#getValue() value}.
   * @return the value of the requested {@link PlayProperty} or the {@code default value} if undefined.
   */
  @SuppressWarnings("unchecked")
  public <V> V get(PlayPropertyValue<V, ?> property, V defaultValue) {

    PlayPropertyValue<V, ?> pv = getOrNull(property);
    if (pv != null) {
      return pv.getValue();
    } else {
      return defaultValue;
    }
  }

  /**
   * @param property the requested {@link PlayProperty}.
   * @param defaultValue the default {@link PlayPropertyValue#getValue() value}.
   * @return the value of the requested {@link PlayProperty} or the {@code default value} if undefined.
   */
  public double getDouble(PlayPropertyValueDouble property, double defaultValue) {

    PlayPropertyValueDouble pv = getOrNull(property);
    if (pv != null) {
      return pv.get();
    } else {
      return defaultValue;
    }
  }

  /**
   * @param property the requested {@link PlayProperty}.
   * @param defaultValue the default {@link PlayPropertyValue#getValue() value}.
   * @return the value of the requested {@link PlayProperty} or the {@code default value} if undefined.
   */
  public long getLong(PlayPropertyValueLong property, long defaultValue) {

    PlayPropertyValueLong pv = getOrNull(property);
    if (pv != null) {
      return pv.get();
    } else {
      return defaultValue;
    }
  }

  /**
   * @param property the requested {@link PlayProperty}.
   * @param defaultValue the default {@link PlayPropertyValue#getValue() value}.
   * @return the value of the requested {@link PlayProperty} or the {@code default value} if undefined.
   */
  public int getInteger(PlayPropertyValueInt property, int defaultValue) {

    PlayPropertyValueInt pv = getOrNull(property);
    if (pv != null) {
      return pv.get();
    } else {
      return defaultValue;
    }
  }

  /**
   * @param <T> the type of the property value.
   * @param property the requested {@link PlayProperty}.
   * @return the value of the requested {@link PlayProperty} or {@code null} if undefined (not
   *         {@link #set(PlayProperty, Object) set}).
   */
  @SuppressWarnings("unchecked")
  public <T> T getOrNull(PlayProperty<T> property) {

    return get(property, false);
  }

  /**
   * @param <T> the type of the property value.
   * @param property the requested {@link PlayProperty}.
   * @param useDefault - {@code true} if the {@link PlayProperty#getDefaultValue() default value} of the given
   *        {@link PlayProperty} will be {@link #set(PlayProperty, Object) set} and returned if the property was
   *        undefined (required for mutable {@link PlayPropertyContainer}s), {@code false} otherwise.
   * @return the value of the requested {@link PlayProperty}. If {@code useDefault} is {@code true} the
   *         {@link PlayProperty#getDefaultValue() default value} will be return if the requested property was
   *         undefined. Otherwise {@code null} is returned in such case.
   */
  private <T> T get(PlayProperty<T> property, boolean useDefault) {

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
   * @param property the requested {@link PlayProperty}.
   * @return the value of the requested {@link PlayProperty}.
   */
  public double getDouble(PlayProperty<Double> property) {

    Double value = get(property);
    if (value == null) {
      return 0;
    } else {
      return value.doubleValue();
    }
  }

  /**
   * @param property the requested {@link PlayProperty}.
   * @return the value of the requested {@link PlayProperty}.
   */
  public long getLong(PlayProperty<Long> property) {

    Long value = get(property);
    if (value == null) {
      return 0;
    } else {
      return value.longValue();
    }
  }

  /**
   * @param property the requested {@link PlayProperty}.
   * @return the value of the requested {@link PlayProperty}.
   */
  public int getInt(PlayProperty<Integer> property) {

    Integer value = get(property);
    if (value == null) {
      return 0;
    } else {
      return value.intValue();
    }
  }

  /**
   * @param <T> the type of the property value.
   * @param property the requested {@link PlayProperty}.
   * @param value the new value of the given {@link PlayProperty}.
   * @return this instance for fluent API calls.
   */
  public <T> PlayProperties set(PlayProperty<T> property, T value) {

    this.map.put(property.getName(), value);
    return this;
  }

  /**
   * @param property the {@link PlayPropertyValue} to set. Used both as {@code key} and as {@code value} to set.
   * @return this instance for fluent API calls.
   */
  public PlayProperties set(PlayPropertyContainer<?> property) {

    this.map.put(property.getName(), property);
    return this;
  }

  /**
   * @param property the requested {@link PlayProperty}.
   * @param value the new value of the given {@link PlayProperty}.
   * @return this instance for fluent API calls.
   */
  public PlayProperties setDouble(PlayProperty<Double> property, double value) {

    return set(property, Double.valueOf(value));
  }

  /**
   * @param property the requested {@link PlayProperty}.
   * @param value the new value of the given {@link PlayProperty}.
   * @return this instance for fluent API calls.
   */
  public PlayProperties setLong(PlayProperty<Long> property, long value) {

    return set(property, Long.valueOf(value));
  }

  /**
   * @param property the requested {@link PlayProperty}.
   * @param value the new value of the given {@link PlayProperty}.
   * @return this instance for fluent API calls.
   */
  public PlayProperties setInt(PlayProperty<Integer> property, int value) {

    return set(property, Integer.valueOf(value));
  }

}
