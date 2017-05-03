/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import io.github.ghosthopper.color.PlayColor;
import io.github.ghosthopper.figure.PlayAttributeFigures;
import io.github.ghosthopper.figure.PlayFigure;
import io.github.ghosthopper.figure.PlayFigureGroup;
import io.github.ghosthopper.figure.PlayFigureType;
import io.github.ghosthopper.game.PlayGame;
import io.github.ghosthopper.object.PlayAttributeName;
import io.github.ghosthopper.object.PlayTypedObjectWithItems;
import io.github.ghosthopper.type.PlayTypeBase;

/**
 * A {@link Player} of the {@link PlayGame}.
 */
public class Player extends PlayTypedObjectWithItems implements PlayAttributeFigures, PlayAttributeName {

  private final PlayGame game;

  private final List<PlayFigure> figures;

  private final List<PlayFigure> figuresView;

  private final List<PlayFigureGroup> groups;

  private final List<PlayFigureGroup> groupsView;

  private final PlayerType type;

  private String name;

  private boolean human;

  /**
   * The constructor.
   *
   * @param game - see {@link #getGame()}.
   * @param color - see {@link #getColor()}.
   * @param figureTypes - see {@link #getFigures()}.
   */
  public Player(PlayGame game, PlayColor color, PlayFigureType... figureTypes) {
    this(game, color, createName(color), true, PlayerType.DEFAULT, figureTypes);
  }

  /**
   * The constructor.
   *
   * @param game - see {@link #getGame()}.
   * @param name - see {@link #getName()}.
   * @param figureTypes - see {@link #getFigures()}.
   */
  public Player(PlayGame game, String name, PlayFigureType... figureTypes) {
    this(game, null, name, true, PlayerType.DEFAULT, figureTypes);
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
  public Player(PlayGame game, PlayColor color, String name, boolean human, PlayerType type, PlayFigureType... figureTypes) {
    super();
    this.game = game;
    this.name = name;
    this.human = human;
    this.type = type;
    this.figures = new ArrayList<>();
    this.figuresView = Collections.unmodifiableList(this.figures);
    this.groups = new ArrayList<>();
    this.groupsView = Collections.unmodifiableList(this.groups);
    if (color != null) {
      setColor(color);
    }
    for (PlayFigureType figureType : figureTypes) {
      PlayFigure figure = new PlayFigure(this, figureType);
      if (color != null) {
        figure.setColor(color);
      }
      this.figures.add(figure);
    }
  }

  private static String createName(PlayColor color) {

    return "Player " + color.getId();
  }

  @Override
  public PlayGame getGame() {

    if (this.game != null) {
      return this.game;
    }
    return super.getGame();
  }

  @Override
  public PlayTypeBase getType() {

    return this.type;
  }

  /**
   * @return {@code true} if this {@link Player} is human, {@code false} otherwise ({@link Player} is controlled by the
   *         computer).
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
  public List<PlayFigure> getFigures() {

    return this.figuresView;
  }

  @Override
  public boolean addFigure(PlayFigure figure) {

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
  public boolean removeFigure(PlayFigure figure) {

    Objects.requireNonNull(figure, "figure");
    boolean success = this.figures.remove(figure);
    if (success) {
      figure.setLocation(null);
    }
    return success;
  }

  /**
   * @return {@code true} if this is the {@link PlayGame#getCurrentPlayer() current player}, {@code false} otherwise.
   */
  public boolean isCurrentPlayer() {

    return (this.game.getCurrentPlayer() == this);
  }

  /**
   * @return the groups
   */
  public List<PlayFigureGroup> getGroups() {

    return this.groupsView;
  }

  /**
   * @return the new {@link PlayFigureGroup}.
   */
  public PlayFigureGroup createGroup() {

    return createGroup("Group-" + (this.groups.size() + 1));
  }

  /**
   * @param groupName the {@link PlayFigureGroup#getId() group name}.
   * @return the new {@link PlayFigureGroup}.
   */
  public PlayFigureGroup createGroup(String groupName) {

    PlayFigureGroup group = new PlayFigureGroup(groupName, this);
    this.groups.add(group);
    return group;
  }

  /**
   * @param group destroys the given {@link PlayFigureGroup} (Calls {@link PlayFigureGroup#clear()} and removes it from
   *        the {@link #getGroups() groups}).
   */
  public void destroyGroup(PlayFigureGroup group) {

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
