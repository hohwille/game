/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.ui.fx;

import java.util.HashMap;
import java.util.Map;

import io.github.ghosthopper.color.GameColor;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.Effect;
import javafx.scene.paint.Color;

/**
 * JavaFx object for a {@link GameColor}.
 */
public class GameUiFxColor {

  private static final Map<GameColor, GameUiFxColor> COLOR_MAP = new HashMap<>();

  private final GameColor playColor;

  private final Color color;

  private final ColorAdjust effect;

  /**
   * The constructor.
   *
   * @param color the {@link GameColor}.
   */
  private GameUiFxColor(GameColor color) {
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

  /**
   * @return the {@link GameColor}.
   */
  public GameColor getGameColor() {

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
  public Effect getEffect() {

    return this.effect;
  }

  /**
   * @param color the {@link GameColor}.
   * @return the corresponding {@link GameUiFxColor}.
   */
  public static GameUiFxColor getFxColor(GameColor color) {

    if (color == null) {
      return null;
    }
    return COLOR_MAP.computeIfAbsent(color, c -> new GameUiFxColor(color));
  }

  /**
   * @param color the {@link GameColor}.
   * @return the corresponding {@link #getEffect() effect}.
   */
  public static Effect getEffect(GameColor color) {

    if (color == null) {
      return null;
    }
    return getFxColor(color).getEffect();
  }

}
