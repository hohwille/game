/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.event;

/**
 * {@link GameEvent} for a key that has been pressed.
 */
public class GameKeyEvent implements GameEvent, GameKeys {

  private final GameKeyModifiers modifiers;

  private final char code;

  private final boolean text;

  /**
   * The constructor.
   *
   * @param modifiers - see {@link #getModifiers()}.
   * @param code - see {@link #getCode()}.
   * @param text - see {@link #isText()}.
   */
  public GameKeyEvent(GameKeyModifiers modifiers, char code, boolean text) {
    super();
    this.modifiers = modifiers;
    this.code = code;
    this.text = text;
  }

  /**
   * @return the {@link GameKeyModifiers}.
   */
  public GameKeyModifiers getModifiers() {

    return this.modifiers;
  }

  /**
   * @return the code of the key that has actually been pressed.
   */
  public char getCode() {

    return this.code;
  }

  /**
   * @return {@code true} if the {@link #getCode() code} is textual input, {@code false} otherwise (in case of a special
   *         key such as arrows, backspace, insert, etc.).
   */
  public boolean isText() {

    return this.text;
  }

}
