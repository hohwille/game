/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.game.engine.choice;

import java.text.MessageFormat;

import net.sf.mmm.game.engine.Game;
import net.sf.mmm.game.engine.i18n.GameTranslator;

/**
 * A utility for validation.
 */
public final class GameValidator {

  private GameValidator() {}

  /**
   * @param value the value to validate.
   * @param min the minimum value.
   * @param max the maximum value.
   * @return {@code null} if {@code min <= value <= max} applies, otherwise an according localized error message.
   */
  public static String validateRange(double value, double min, double max) {

    return validateRange(value, min, max, null);
  }

  /**
   * @param value the value to validate.
   * @param min the minimum value.
   * @param max the maximum value.
   * @param object an optional description of the object to validate used as contextual information in a potential error
   *        message. May be {@code null}.
   * @return {@code null} if {@code min <= value <= max} applies, otherwise an according localized error message.
   */
  public static String validateRange(double value, double min, double max, String object) {

    assert (min <= max);
    String message = null;
    Object[] args = null;
    if (value < min) {
      message = "Validation.Min";
      args = new Object[] { toString(min) };
    } else if (value > max) {
      message = "Validation.Max";
      args = new Object[] { toString(max) };
    }
    if (message != null) {
      GameTranslator translator = Game.getCurrentGame().getTranslator();
      message = translator.translate(message);
      if (args != null) {
        message = MessageFormat.format(message, args);
      }
      if (object != null) {
        message = translator.translate(object) + ": " + message;
      }
      return message;
    }
    return null;
  }

  private static String toString(double value) {

    int i = (int) value;
    if (i == value) {
      return Integer.toString(i);
    } else {
      return Double.toString(value);
    }
  }

  /**
   * @param error1 the first error or {@code null} for no error.
   * @param error2 the second error or {@code null} for no error.
   * @return the {@code null} if both errors are {@code null}, the error that is not {@code null} if only one of them is
   *         {@code null} and the concatenation of {@code error1} and {@code error2} separated with a newline.
   */
  public static String aggregateError(String error1, String error2) {

    if (error2 == null) {
      return error1;
    } else if (error1 == null) {
      return error2;
    } else {
      return error1 + "\n" + error2;
    }
  }

}
