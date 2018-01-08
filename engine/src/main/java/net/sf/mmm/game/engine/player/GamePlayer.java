/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.game.engine.player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import net.sf.mmm.game.engine.Game;
import net.sf.mmm.game.engine.color.GameColor;
import net.sf.mmm.game.engine.figure.GameAttributeFigures;
import net.sf.mmm.game.engine.figure.GameFigure;
import net.sf.mmm.game.engine.figure.GameFigureGroup;
import net.sf.mmm.game.engine.figure.GameFigureType;
import net.sf.mmm.game.engine.object.GameAttributeName;
import net.sf.mmm.game.engine.object.GameTypedObjectWithItems;

/**
 * A {@link GamePlayer} of the {@link Game}.
 */
public class GamePlayer extends GameTypedObjectWithItems<GamePlayerType> implements GameAttributeFigures, GameAttributeName {

  /** I18N key for the plural term "players". */
  public static final String I18N_PLAYERS = "Players";

  private final Game game;

  private final List<GameFigure> figures;

  private final List<GameFigure> figuresView;

  private final List<GameFigureGroup> groups;

  private final List<GameFigureGroup> groupsView;

  private String name;

  private boolean human;

  /**
   * The constructor.
   *
   * @param game - see {@link #getGame()}.
   * @param color - see {@link #getColor()}.
   * @param figureTypes - see {@link #getFigures()}.
   */
  public GamePlayer(Game game, GameColor color, GameFigureType... figureTypes) {

    this(game, color, createName(game, color), true, GamePlayerType.DEFAULT, figureTypes);
  }

  /**
   * The constructor.
   *
   * @param game - see {@link #getGame()}.
   * @param name - see {@link #getName()}.
   * @param figureTypes - see {@link #getFigures()}.
   */
  public GamePlayer(Game game, String name, GameFigureType... figureTypes) {

    this(game, null, name, true, GamePlayerType.DEFAULT, figureTypes);
  }

  /**
   * The constructor.
   *
   * @param game - see {@link #getGame()}.
   * @param color - see {@link #getColor()}.
   * @param name - see {@link #getName()}.
   * @param human - see {@link #isHuman()}.
   * @param type - see {@link #getType()}.
   * @param figureTypes - see {@link #getFigures()}.
   */
  public GamePlayer(Game game, GameColor color, String name, boolean human, GamePlayerType type, GameFigureType... figureTypes) {

    super(type);
    this.game = game;
    this.name = name;
    this.human = human;
    this.figures = new ArrayList<>();
    this.figuresView = Collections.unmodifiableList(this.figures);
    this.groups = new ArrayList<>();
    this.groupsView = Collections.unmodifiableList(this.groups);
    if (color != null) {
      setColor(color);
    }
    for (GameFigureType figureType : figureTypes) {
      GameFigure figure = new GameFigure(this, figureType);
      if (color != null) {
        figure.setColor(color);
      }
      this.figures.add(figure);
    }
  }

  private static String createName(Game game, GameColor color) {

    return game.getTranslator().translate("Player " + color.getId());
  }

  @Override
  public Game getGame() {

    if (this.game != null) {
      return this.game;
    }
    return super.getGame();
  }

  /**
   * @return {@code true} if this {@link GamePlayer} is human, {@code false} otherwise ({@link GamePlayer} is controlled
   *         by the computer).
   */
  public boolean isHuman() {

    return this.human;
  }

  @Override
  public String getName() {

    return this.name;
  }

  @Override
  public void setName(String name) {

    this.name = name;
  }

  /**
   * @return the figures
   */
  @Override
  public List<GameFigure> getFigures() {

    return this.figuresView;
  }

  @Override
  public boolean addFigure(GameFigure figure) {

    Objects.requireNonNull(figure, "figure");
    if (figure.getPlayer() != this) {
      throw new IllegalStateException("Only figures of this player can be added!");
    }
    if (this.figures.contains(figure)) {
      return true; // already contained
    }
    this.figures.add(figure);
    return true;
  }

  @Override
  public boolean removeFigure(GameFigure figure) {

    Objects.requireNonNull(figure, "figure");
    boolean success = this.figures.remove(figure);
    if (success) {
      figure.setLocation(null);
    }
    return success;
  }

  /**
   * @return {@code true} if this is the {@link Game#getCurrentPlayer() current player}, {@code false} otherwise.
   */
  public boolean isCurrentPlayer() {

    return (this.game.getCurrentPlayer() == this);
  }

  /**
   * @return the groups
   */
  public List<GameFigureGroup> getGroups() {

    return this.groupsView;
  }

  /**
   * @return the new {@link GameFigureGroup}.
   */
  public GameFigureGroup createGroup() {

    return createGroup("Group-" + (this.groups.size() + 1));
  }

  /**
   * @param groupName the {@link GameFigureGroup#getId() group name}.
   * @return the new {@link GameFigureGroup}.
   */
  public GameFigureGroup createGroup(String groupName) {

    GameFigureGroup group = new GameFigureGroup(groupName, this);
    this.groups.add(group);
    return group;
  }

  /**
   * @param group destroys the given {@link GameFigureGroup} (Calls {@link GameFigureGroup#clear()} and removes it from
   *        the {@link #getGroups() groups}).
   */
  public void destroyGroup(GameFigureGroup group) {

    Objects.requireNonNull(group, "group");
    if (group.getPlayer() != this) {
      throw new IllegalArgumentException("Group belongs to different player!");
    }
    boolean removed = this.groups.remove(group);
    if (removed) {
      group.clear();
    }
  }

  @Override
  public String toString() {

    return this.name;
  }

}
