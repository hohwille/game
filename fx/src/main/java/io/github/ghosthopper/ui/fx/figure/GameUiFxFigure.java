/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.ui.fx.figure;

import io.github.ghosthopper.direction.GameDirection;
import io.github.ghosthopper.figure.GameFigure;
import io.github.ghosthopper.figure.GameFigureGroup;
import io.github.ghosthopper.ui.fx.asset.GameUiFxAsset;
import io.github.ghosthopper.ui.fx.player.GameUiFxPlayer;
import javafx.scene.effect.Effect;
import javafx.scene.effect.Glow;

/**
 * JavaFx view for a {@link GameFigure}.
 */
public class GameUiFxFigure extends GameUiFxAsset {

  private final GameFigure figure;

  private final Glow glow;

  private GameUiFxPlayer fxPlayer;

  private GameDirection direction;

  /**
   * The constructor.
   *
   * @param figure the {@link GameFigure}.
   * @param fxPlayer the {@link #getFxParent() parent} {@link GameUiFxPlayer player}.
   */
  public GameUiFxFigure(GameFigure figure, GameUiFxPlayer fxPlayer) {
    super(figure, fxPlayer);
    this.figure = figure;
    this.fxPlayer = fxPlayer;
    this.glow = new Glow();
    Effect effect = getEffect();
    if (effect != null) {
      this.glow.setInput(effect);
    }
    setEffect(this.glow);
  }

  /**
   * @return the {@link GameFigure}.
   */
  public GameFigure getGameFigure() {

    return this.figure;
  }

  @Override
  public GameUiFxPlayer getFxParent() {

    return this.fxPlayer;
  }

  @Override
  public GameFigure getGameAsset() {

    return this.figure;
  }

  /**
   * Updates the {@link GameFigure#isActiveFigure() activity} of this figure.
   */
  public void updateActivitySingle() {

    boolean selected = this.figure.isActiveFigure();
    if (selected) {
      this.glow.setLevel(0.8);
    } else {
      this.glow.setLevel(0);
    }
  }

  /**
   * Updates the {@link GameFigure#isActiveFigure() activity} of this figure.
   */
  public void updateActivity() {

    GameFigureGroup group = this.figure.getGroup();
    if (group != null) {
      for (GameFigure groupFigure : group.getFigures()) {
        getFxGame().getFxFigure(groupFigure).updateActivitySingle();
      }
    } else {
      updateActivitySingle();
    }
  }

  /**
   * Updates the {@link GameFigure#getDirection() direction} of this figure.
   */
  public void updateGameDirectionSingle() {

    GameDirection dir = this.figure.getDirection();
    if (dir == this.direction) {
      return;
    }
    double rotate = 0;
    if (dir != null) {
      rotate = dir.getRotationZ();
    }
    setRotate(rotate);
    this.direction = dir;
  }

  /**
   * Updates the {@link GameFigure#getDirection() direction} of this figure.
   */
  public void updateGameDirection() {

    GameFigureGroup group = this.figure.getGroup();
    if (group != null) {
      for (GameFigure groupFigure : group.getFigures()) {
        getFxGame().getFxFigure(groupFigure).updateGameDirectionSingle();
      }
    } else {
      updateGameDirectionSingle();
    }
  }

  /**
   * Performs all updates only on this figure (single means not considering {@link GameFigure#getGroup() groups}).
   */
  public void updateAllSingle() {

    updateActivitySingle();
    updateGameDirectionSingle();
  }

}
