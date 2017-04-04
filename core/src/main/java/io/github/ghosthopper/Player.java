/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper;

import java.util.ArrayList;
import java.util.List;

import io.github.ghosthopper.figure.PlayFigure;
import io.github.ghosthopper.figure.PlayFigureType;
import io.github.ghosthopper.game.PlayGame;

/**
 * A {@link Player} of the {@link PlayGame}.
 */
public class Player extends PlayObjectWithColorAndItems {

  private final List<PlayFigure> figures;

  private String name;

  private boolean human;

  /**
   * The constructor.
   *
   * @param color - see {@link #getColor()}.
   * @param figureTypes - see {@link #getFigures()}.
   */
  public Player(PlayColor color, PlayFigureType... figureTypes) {
    this("Player " + color.getId(), true, figureTypes);
    setColor(color);
  }

  /**
   * The constructor.
   *
   * @param name - see {@link #getName()}.
   * @param figureTypes - see {@link #getFigures()}.
   */
  public Player(String name, PlayFigureType... figureTypes) {
    this(name, true, figureTypes);
  }

  /**
   * The constructor.
   *
   * @param name - see {@link #getName()}.
   * @param human - see {@link #isHuman()}.
   * @param figureTypes - see {@link #getFigures()}.
   */
  public Player(String name, boolean human, PlayFigureType... figureTypes) {
    super();
    this.name = name;
    this.human = human;
    this.figures = new ArrayList<>();
    for (PlayFigureType type : figureTypes) {
      this.figures.add(new PlayFigure(this, type));
    }
  }

  /**
   * @return {@code true} if this {@link Player} is human, {@code false} otherwise ({@link Player} is controlled by the
   *         computer).
   */
  public boolean isHuman() {

    return this.human;
  }

  /**
   * @return the name of this {@link Player}.
   */
  public String getName() {

    return this.name;
  }

  /**
   * @param name the new value of {@link #getName()}.
   */
  public void setName(String name) {

    this.name = name;
  }

  /**
   * @return the figures
   */
  public List<PlayFigure> getFigures() {

    return this.figures;
  }

}
