/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.game.engine.figure;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import net.sf.mmm.game.engine.Game;
import net.sf.mmm.game.engine.object.GameObjectWithId;
import net.sf.mmm.game.engine.player.GamePlayer;

/**
 * A group of {@link GameFigure}s that {@link GameFigure#move()} together.
 */
public class GameFigureGroup extends GameObjectWithId implements GameAttributeFigures {

  private final GamePlayer player;

  private List<GameFigure> figures;

  private List<GameFigure> figuresView;

  /**
   * The constructor.
   *
   * @param id the {@link #getId() ID}.
   * @param player the owning {@link GamePlayer}.
   */
  public GameFigureGroup(String id, GamePlayer player) {
    super(id);
    this.player = player;
  }

  /**
   * @return the {@link GamePlayer} owning this {@link GameFigureGroup}.
   */
  public GamePlayer getPlayer() {

    return this.player;
  }

  @Override
  public Game getGame() {

    return this.player.getGame();
  }

  @Override
  public List<GameFigure> getFigures() {

    if (this.figuresView == null) {
      return Collections.emptyList();
    }
    return this.figuresView;
  }

  private List<GameFigure> getOrCreateFigures() {

    if (this.figures == null) {
      this.figures = new ArrayList<>();
      this.figuresView = Collections.unmodifiableList(this.figures);
    }
    return this.figures;
  }

  @Override
  public boolean addFigure(GameFigure figure) {

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
  public boolean removeFigure(GameFigure figure) {

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
   * {@link #removeFigure(GameFigure) Removes} {@link #getFigures() all figures} from this group.
   */
  public void clear() {

    if (this.figures == null) {
      return;
    }
    Iterator<GameFigure> iterator = this.figures.iterator();
    while (iterator.hasNext()) {
      iterator.next().setGroup(null);
      iterator.remove();
    }
  }

}
