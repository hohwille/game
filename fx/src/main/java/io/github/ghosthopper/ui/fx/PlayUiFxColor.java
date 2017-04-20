/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.ui.fx;

import java.util.HashMap;
import java.util.Map;

import io.github.ghosthopper.color.PlayColor;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.Effect;
import javafx.scene.paint.Color;

/**
 * JavaFx object for a {@link PlayColor}.
 */
public class PlayUiFxColor {

  private static final Map<PlayColor, PlayUiFxColor> COLOR_MAP = new HashMap<>();

  private final PlayColor playColor;

  private final Color color;

  private final ColorAdjust effect;

  /**
   * The constructor.
   *
   * @param color the {@link PlayColor}.
   */
  private PlayUiFxColor(PlayColor color) {
    super();
    this.playColor = color;
    this.color = new Color(color.getRed(), color.getGreen(), color.getBlue(), 0);
    this.effect = new ColorAdjust();
    if (this.color.getSaturation() < 0.1) { // grey color?
      this.effect.setSaturation(-1);
      double brightness = this.color.getBrightness() - 0.5;
      this.effect.setBrightness(brightness);
    } else {
      double hue = (this.color.getHue() - 180) / 180;
      this.effect.setHue(hue);
    }
  }

  // private static Map<PlayColor, Effect> createColorMap() {
  //
  // // Base color of images is magenta!
  // Map<PlayColor, Effect> map = new HashMap<>();
  //
  // map.put(PlayColor.MAGENTA, createColorAdjust(0));
  // map.put(PlayColor.GREEN, createColorAdjust(-1));
  // map.put(PlayColor.CYAN, createColorAdjust(-0.625));
  // map.put(PlayColor.BLUE, createColorAdjust(-0.375));
  // map.put(PlayColor.RED, createColorAdjust(0.35));
  // map.put(PlayColor.YELLOW, createColorAdjust(0.6));
  // map.put(PlayColor.GREY, new ColorAdjust(0, -1, 0, 0));
  // map.put(PlayColor.WHITE, new ColorAdjust(0, -1, 0.5, 0));
  // map.put(PlayColor.BLACK, new ColorAdjust(0, -1, -0.5, 0));
  // return map;
  // }

  /**
   * @return the {@link PlayColor}.
   */
  public PlayColor getPlayColor() {

    return this.playColor;
  }

  /**
   * @return the {@link Color}.
   */
  public Color getColor() {

    return this.color;
  }

  /**
   * @return the {@link Effect} to transform an image from the base-color (mangeta) to this color.
   */
  public ColorAdjust getEffect() {

    return this.effect;
  }

  /**
   * @param color the {@link PlayColor}.
   * @return the corresponding {@link PlayUiFxColor}.
   */
  public static PlayUiFxColor getFxColor(PlayColor color) {

    if (color == null) {
      return null;
    }
    return COLOR_MAP.computeIfAbsent(color, c -> new PlayUiFxColor(color));
  }

  /**
   * @param color the {@link PlayColor}.
   * @return the corresponding {@link #getEffect() effect}.
   */
  public static ColorAdjust getEffect(PlayColor color) {

    if (color == null) {
      return null;
    }
    return getFxColor(color).getEffect();
  }

}
