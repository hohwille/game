/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.ui.fx;

import io.github.ghosthopper.figure.PlayFigure;
import io.github.ghosthopper.figure.PlayFigureGroup;
import io.github.ghosthopper.move.PlayDirection;
import javafx.scene.effect.Effect;
import javafx.scene.effect.Glow;

/**
 * JavaFx view for a {@link PlayFigure}.
 */
public class PlayUiFxFigure extends PlayUiFxAsset {

  private final PlayFigure figure;

  private final Glow glow;

  private PlayUiFxPlayer fxPlayer;

  private PlayDirection direction;

  /**
   * The constructor.
   *
   * @param figure the {@link PlayFigure}.
   * @param fxPlayer the {@link #getFxParent() parent} {@link PlayUiFxPlayer player}.
   */
  public PlayUiFxFigure(PlayFigure figure, PlayUiFxPlayer fxPlayer) {
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
   * @return the {@link PlayFigure}.
   */
  public PlayFigure getPlayFigure() {

    return this.figure;
  }

  @Override
  public PlayUiFxPlayer getFxParent() {

    return this.fxPlayer;
  }

  @Override
  public PlayFigure getPlayAsset() {

    return this.figure;
  }

  /**
   * Updates the {@link PlayFigure#isActiveFigure() activity} of this figure.
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
   * Updates the {@link PlayFigure#isActiveFigure() activity} of this figure.
   */
  public void updateActivity() {

    PlayFigureGroup group = this.figure.getGroup();
    if (group != null) {
      for (PlayFigure groupFigure : group.getFigures()) {
        getFxGame().getFxFigure(groupFigure).updateActivitySingle();
      }
    } else {
      updateActivitySingle();
    }
  }

  /**
   * Updates the {@link PlayFigure#getDirection() direction} of this figure.
   */
  public void updateDirectionSingle() {

    PlayDirection dir = this.figure.getDirection();
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
   * Updates the {@link PlayFigure#getDirection() direction} of this figure.
   */
  public void updateDirection() {

    PlayFigureGroup group = this.figure.getGroup();
    if (group != null) {
      for (PlayFigure groupFigure : group.getFigures()) {
        getFxGame().getFxFigure(groupFigure).updateDirectionSingle();
      }
    } else {
      updateDirectionSingle();
    }
  }

  /**
   * Performs all updates only on this figure (single means not considering {@link PlayFigure#getGroup() groups}).
   */
  public void updateAllSingle() {

    updateActivitySingle();
    updateDirectionSingle();
  }

}
