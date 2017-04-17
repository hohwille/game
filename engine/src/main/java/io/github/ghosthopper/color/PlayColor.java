/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.color;

import io.github.ghosthopper.figure.PlayFigureType;
import io.github.ghosthopper.object.PlayObjectWithId;
import io.github.ghosthopper.player.Player;

/**
 * A color (e.g. of a {@link Player} or {@link PlayFigureType}).
 */
public class PlayColor extends PlayObjectWithId {

  /** Red. */
  public static final PlayColor RED = new PlayColor("Red", 1, 0, 0);

  /** Green. */
  public static final PlayColor GREEN = new PlayColor("Green", 0, 1, 0);

  /** Blue. */
  public static final PlayColor BLUE = new PlayColor("Blue", 0, 0, 1);

  /** Yellow. */
  public static final PlayColor YELLOW = new PlayColor("Yellow", 1, 1, 0);

  /** Cyan. */
  public static final PlayColor CYAN = new PlayColor("Cyan", 0, 1, 1);

  /** Magenta. */
  public static final PlayColor MAGENTA = new PlayColor("Magenta", 1, 0, 1);

  /** White. */
  public static final PlayColor WHITE = new PlayColor("White", 1, 1, 1);

  /** Grey. */
  public static final PlayColor GREY = new PlayColor("Grey", 0.5, 0.5, 0.5);

  /** Black. */
  public static final PlayColor BLACK = new PlayColor("Black", 0, 0, 0);

  private final double red;

  private final double green;

  private final double blue;

  /**
   * The constructor.
   *
   * @param id - see {@link #getId()}.
   * @param red - see {@link #getRed()}.
   * @param green - see {@link #getGreen()}.
   * @param blue - see {@link #getBlue()}.
   */
  public PlayColor(String id, double red, double green, double blue) {
    super(id);
    checkValue(red, "red");
    checkValue(green, "green");
    checkValue(blue, "blue");
    this.red = red;
    this.green = green;
    this.blue = blue;
  }

  private static void checkValue(double v, String name) {

    if ((v < 0) || (v > 1)) {
      throw new IllegalArgumentException(name + " value of color is " + v + " but has to be in the range from 0 to 1");
    }
  }

  /**
   * @return the red part of the color.
   */
  public double getRed() {

    return this.red;
  }

  /**
   * @return the green part of the color.
   */
  public double getGreen() {

    return this.green;
  }

  /**
   * @return the blue part of the color.
   */
  public double getBlue() {

    return this.blue;
  }

}
