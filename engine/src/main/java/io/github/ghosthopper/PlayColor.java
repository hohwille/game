/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper;

import io.github.ghosthopper.figure.PlayFigureType;
import io.github.ghosthopper.player.Player;

/**
 * A color (e.g. of a {@link Player} or {@link PlayFigureType}).
 */
public class PlayColor extends PlayObjectWithId {

  /** Red. */
  public static final PlayColor RED = new PlayColor("Red", 255, 0, 0);

  /** Green. */
  public static final PlayColor GREEN = new PlayColor("Green", 0, 255, 0);

  /** Blue. */
  public static final PlayColor BLUE = new PlayColor("Blue", 0, 0, 255);

  /** Yellow. */
  public static final PlayColor YELLOW = new PlayColor("Yellow", 255, 255, 0);

  /** Cyan. */
  public static final PlayColor CYAN = new PlayColor("Cyan", 0, 255, 255);

  /** Magenta. */
  public static final PlayColor MAGENTA = new PlayColor("Magenta", 255, 0, 255);

  /** White. */
  public static final PlayColor WHITE = new PlayColor("White", 255, 255, 255);

  /** Black. */
  public static final PlayColor BLACK = new PlayColor("Black", 0, 0, 0);

  private final int red;

  private final int green;

  private final int blue;

  /**
   * The constructor.
   *
   * @param id - see {@link #getId()}.
   * @param red - see {@link #getRed()}.
   * @param green - see {@link #getGreen()}.
   * @param blue - see {@link #getBlue()}.
   */
  public PlayColor(String id, int red, int green, int blue) {
    super(id);
    this.red = red;
    this.green = green;
    this.blue = blue;
  }

  /**
   * @return the red part of the color.
   */
  public int getRed() {

    return this.red;
  }

  /**
   * @return the green part of the color.
   */
  public int getGreen() {

    return this.green;
  }

  /**
   * @return the blue part of the color.
   */
  public int getBlue() {

    return this.blue;
  }

}
