/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.color;

import io.github.ghosthopper.figure.GameFigureType;
import io.github.ghosthopper.object.GameObjectWithId;
import io.github.ghosthopper.player.GamePlayer;

/**
 * A color (e.g. of a {@link GamePlayer} or {@link GameFigureType}).
 */
public class GameColor extends GameObjectWithId {

  /** Red. */
  public static final GameColor RED = new GameColor("Red", 1, 0, 0);

  /** Green. */
  public static final GameColor GREEN = new GameColor("Green", 0, 1, 0);

  /** Blue. */
  public static final GameColor BLUE = new GameColor("Blue", 0, 0, 1);

  /** Yellow. */
  public static final GameColor YELLOW = new GameColor("Yellow", 1, 1, 0);

  /** Cyan. */
  public static final GameColor CYAN = new GameColor("Cyan", 0, 1, 1);

  /** Magenta. */
  public static final GameColor MAGENTA = new GameColor("Magenta", 1, 0, 1);

  /** White. */
  public static final GameColor WHITE = new GameColor("White", 1, 1, 1);

  /** Grey. */
  public static final GameColor GREY = new GameColor("Grey", 0.5, 0.5, 0.5);

  /** Black. */
  public static final GameColor BLACK = new GameColor("Black", 0, 0, 0);

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
  public GameColor(String id, double red, double green, double blue) {
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
