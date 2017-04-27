/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.figure;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import io.github.ghosthopper.game.PlayGame;
import io.github.ghosthopper.object.PlayObjectWithId;
import io.github.ghosthopper.player.Player;

/**
 * A group of {@link PlayFigure}s that {@link PlayFigure#move()} together.
 */
public class PlayFigureGroup extends PlayObjectWithId implements PlayAttributeFigures {

  private final Player player;

  private List<PlayFigure> figures;

  private List<PlayFigure> figuresView;

  /**
   * The constructor.
   *
   * @param id the {@link #getId() ID}.
   * @param player the owning {@link Player}.
   */
  public PlayFigureGroup(String id, Player player) {
    super(id);
    this.player = player;
  }

  /**
   * @return the {@link Player} owning this {@link PlayFigureGroup}.
   */
  public Player getPlayer() {

    return this.player;
  }

  @Override
  public PlayGame getGame() {

    return this.player.getGame();
  }

  @Override
  public List<PlayFigure> getFigures() {

    if (this.figuresView == null) {
      return Collections.emptyList();
    }
    return this.figuresView;
  }

  private List<PlayFigure> getOrCreateFigures() {

    if (this.figures == null) {
      this.figures = new ArrayList<>();
      this.figuresView = Collections.unmodifiableList(this.figures);
    }
    return this.figures;
  }

  @Override
  public boolean addFigure(PlayFigure figure) {

    Objects.requireNonNull(figure, "figure");
    if (figure.getPlayer() != this.player) {
      throw new IllegalStateException("Only figures of the same player can be grouped!");
    }
    if (getOrCreateFigures().contains(figure)) {
      return true; // already contained...
    }
    this.figures.add(figure);
    figure.setGroup(this);
    return true;
  }

  @Override
  public boolean removeFigure(PlayFigure figure) {

    Objects.requireNonNull(figure, "figure");
    if (this.figures == null) {
      return false;
    }
    boolean success = this.figures.remove(figure);
    if (success) {
      figure.setGroup(null);
    }
    return success;
  }

  /**
   * {@link #removeFigure(PlayFigure) Removes} {@link #getFigures() all figures} from this group.
   */
  public void clear() {

    if (this.figures == null) {
      return;
    }
    Iterator<PlayFigure> iterator = this.figures.iterator();
    while (iterator.hasNext()) {
      iterator.next().setGroup(null);
      iterator.remove();
    }
  }

}
