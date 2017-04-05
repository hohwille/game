/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.player;

import java.util.ArrayList;
import java.util.List;

import io.github.ghosthopper.PlayColor;
import io.github.ghosthopper.PlayObjectType;
import io.github.ghosthopper.PlayObjectWithColorAndTypeAndItems;
import io.github.ghosthopper.figure.PlayFigure;
import io.github.ghosthopper.figure.PlayFigureType;
import io.github.ghosthopper.game.PlayGame;

/**
 * A {@link Player} of the {@link PlayGame}.
 */
public class Player extends PlayObjectWithColorAndTypeAndItems {

  private final List<PlayFigure> figures;

  private final PlayerType type;

  private PlayGame game;

  private String name;

  private boolean human;

  /**
   * The constructor.
   *
   * @param color - see {@link #getColor()}.
   * @param figureTypes - see {@link #getFigures()}.
   */
  public Player(PlayColor color, PlayFigureType... figureTypes) {
    this("Player " + color.getId(), true, PlayerType.DEFAULT, figureTypes);
    setColor(color);
  }

  /**
   * The constructor.
   *
   * @param name - see {@link #getName()}.
   * @param figureTypes - see {@link #getFigures()}.
   */
  public Player(String name, PlayFigureType... figureTypes) {
    this(name, true, PlayerType.DEFAULT, figureTypes);
  }

  /**
   * The constructor.
   *
   * @param name - see {@link #getName()}.
   * @param human - see {@link #isHuman()}.
   * @param type - see {@link #getType()}.
   * @param figureTypes - see {@link #getFigures()}.
   */
  public Player(String name, boolean human, PlayerType type, PlayFigureType... figureTypes) {
    super();
    this.name = name;
    this.human = human;
    this.type = type;
    this.figures = new ArrayList<>();
    for (PlayFigureType figureType : figureTypes) {
      this.figures.add(new PlayFigure(this, figureType));
    }
  }

  @Override
  public PlayGame getGame() {

    if (this.game != null) {
      return this.game;
    }
    return super.getGame();
  }

  /**
   * @param game the new value of {@link #getGame()}.
   */
  public void setGame(PlayGame game) {

    if ((this.game != null) && (this.game != game)) {
      throw new IllegalStateException("Player can not switch games (from '" + this.game + "' to '" + game + "')");
    }
    this.game = game;
  }

  @Override
  public PlayObjectType getType() {

    return this.type;
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
