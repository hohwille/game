/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.game.engine.event;

/**
 * The state of the modifier keys {@link #isControl() control}, {@link #isAlt() alt}, and {@link #isShift() shift}.
 * 
 * @see GameKeyEvent#getModifiers()
 */
public enum GameKeyModifiers {

  /** No modifier key is currently down. */
  NONE,

  /** Only the {@code [shift]} key {@link #isShift() is currently down}. */
  SHIFT,

  /** Only the {@code [alt]} key {@link #isAlt() is currently down}. */
  ALT,

  /** Only the {@code [control]} key {@link #isControl() is currently down}. */
  CONTROL,

  /** {@link #CONTROL} and {@link #SHIFT} are currently down. */
  CONTROL_SHIFT,

  /** {@link #CONTROL} and {@link #ALT} are currently down. */
  CONTROL_ALT,

  /** {@link #ALT} and {@link #SHIFT} are currently down. */
  ALT_SHIFT,

  /** {@link #CONTROL}, {@link #ALT}, and {@link #SHIFT} are currently down. */
  CONTROL_ALT_SHIFT;

  /**
   * @return {@code true} if the {@link #SHIFT [shift]} key is currently down, {@code false} otherwise.
   */
  public boolean isShift() {

    return (this == SHIFT) || (this == ALT_SHIFT) || (this == CONTROL_SHIFT) || (this == CONTROL_ALT_SHIFT);
  }

  /**
   * @return {@code true} if the {@link #CONTROL [control]} key is currently down, {@code false} otherwise.
   */
  public boolean isControl() {

    return (this == CONTROL) || (this == CONTROL_ALT) || (this == CONTROL_SHIFT) || (this == CONTROL_ALT_SHIFT);
  }

  /**
   * @return {@code true} if the {@link #ALT [alt]} key is currently down, {@code false} otherwise.
   */
  public boolean isAlt() {

    return (this == ALT) || (this == CONTROL_ALT) || (this == ALT_SHIFT) || (this == CONTROL_ALT_SHIFT);
  }

  /**
   * @param control - see {@link #isControl()}.
   * @param alt - see {@link #isAlt()}.
   * @param shift - see {@link #isShift()}.
   * @return the according {@link GameKeyModifiers} with the given combination of modifier flags.
   */
  public static GameKeyModifiers of(boolean control, boolean alt, boolean shift) {

    GameKeyModifiers modifiers;
    if (control) {
      if (alt) {
        if (shift) {
          modifiers = CONTROL_ALT_SHIFT;
        } else {
          modifiers = CONTROL_ALT;
        }
      } else {
        if (shift) {
          modifiers = CONTROL_SHIFT;
        } else {
          modifiers = CONTROL;
        }
      }
    } else {
      if (alt) {
        if (shift) {
          modifiers = ALT_SHIFT;
        } else {
          modifiers = ALT;
        }
      } else {
        if (shift) {
          modifiers = SHIFT;
        } else {
          modifiers = NONE;
        }
      }
    }
    assert (modifiers.isControl() == control);
    assert (modifiers.isAlt() == alt);
    assert (modifiers.isShift() == shift);
    return modifiers;
  }

}
