/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.player;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import io.github.ghosthopper.choice.PlayChoice;
import io.github.ghosthopper.game.PlayGame;

/**
 * The abstract base implementation of {@link PlayerConfig}.
 */
public abstract class PlayerConfigBase implements PlayerConfig {

  private final PlayGame game;

  /** Internal mutable {@link List} for {@link #getPlayers()}. */
  protected final List<Player> players;

  private final List<Player> playersView;

  /** Internal mutable {@link List} of potential {@link Player}s to {@link #getChoice() choose} from. */
  protected final List<Player> playerOptions;

  private int minPlayers;

  private int maxPlayers;

  private int minHumans;

  private int maxHumans;

  private int minBots;

  private int maxBots;

  /**
   * The constructor.
   *
   * @param game - see {@link #getGame()}.
   */
  public PlayerConfigBase(PlayGame game) {
    this(game, 1);
  }

  /**
   * The constructor.
   *
   * @param game - see {@link #getGame()}.
   * @param minHumans - see {@link #getMinHumans()}.
   */
  public PlayerConfigBase(PlayGame game, int minHumans) {
    super();
    this.game = game;
    this.minPlayers = -1;
    this.maxPlayers = -1;
    this.minHumans = minHumans;
    this.maxHumans = -1;
    this.players = new ArrayList<>();
    this.playersView = Collections.unmodifiableList(this.players);
    this.playerOptions = new ArrayList<>();
  }

  @Override
  public PlayGame getGame() {

    return this.game;
  }

  /**
   * @return an {@link Collections#unmodifiableList(List) unmodifiable list} of the {@link Player}s.
   *
   * @see #addPlayer(Player)
   */
  @Override
  public List<Player> getPlayers() {

    return this.playersView;
  }

  /**
   * Add the given {@link Player} to the internal {@link List} of {@link Player} options. These will be used for the
   * {@link #getChoice() choice} to select the actual {@link Player}s for the {@link PlayGame}. Only after the
   * {@link #getChoice() choice} has been {@link PlayChoice#select(List) selected} the actual {@link Player}s will be
   * added to the {@link List} returned by {@link #getPlayers()}.
   *
   * @param player the {@link Player} to add.
   */
  public void addPlayer(Player player) {

    assert (player.getGame() == this.game);
    this.playerOptions.add(player);
  }

  /**
   * @param playersToAdd the {@link Player}s to {@link #addPlayer(Player) add}.
   */
  public void addPlayers(Player... playersToAdd) {

    for (Player player : playersToAdd) {
      addPlayer(player);
    }
  }

  /**
   * @param playersToAdd the {@link Player}s to {@link #addPlayer(Player) add}.
   */
  public void addPlayers(Collection<Player> playersToAdd) {

    for (Player player : playersToAdd) {
      addPlayer(player);
    }
  }

  @Override
  public int getMinPlayers() {

    if (this.minPlayers <= 0) {
      this.minPlayers = getMinHumans() + getMinBots();
    }
    return this.minPlayers;
  }

  /**
   * @param minPlayers the new value of {@link #getMinPlayers()}.
   */
  public void setMinPlayers(int minPlayers) {

    this.minPlayers = minPlayers;
  }

  @Override
  public int getMaxPlayers() {

    if (this.maxPlayers <= 0) {
      this.maxPlayers = getMaxHumans() + getMaxBots();
    }
    return this.maxPlayers;
  }

  /**
   * @param maxPlayers the new value of {@link #getMaxPlayers()}.
   */
  public void setMaxPlayers(int maxPlayers) {

    if (this.maxPlayers < 0) {
      this.maxPlayers = this.players.size();
    }
    this.maxPlayers = maxPlayers;
  }

  @Override
  public int getMinHumans() {

    return this.minHumans;
  }

  /**
   * @param minHumans the new value of {@link #getMinHumans()}.
   */
  public void setMinHumans(int minHumans) {

    this.minHumans = minHumans;
  }

  @Override
  public int getMaxHumans() {

    if (this.maxHumans < 0) {
      this.maxHumans = countPlayers(true);
    }
    return this.maxHumans;
  }

  /**
   * @param maxHumans the new value of {@link #getMaxHumans()}.
   */
  public void setMaxHumans(int maxHumans) {

    this.maxHumans = maxHumans;
  }

  @Override
  public int getMinBots() {

    if (this.minBots < 0) {
      this.minBots = countPlayers(false);
    }
    return this.minBots;
  }

  /**
   * @param minBots the new value of {@link #getMinBots()}.
   */
  public void setMinBots(int minBots) {

    this.minBots = minBots;
  }

  @Override
  public int getMaxBots() {

    if (this.maxBots < 0) {
      this.maxBots = countPlayers(false);
    }
    return this.maxBots;
  }

  /**
   * @param maxBots the new value of {@link #getMaxBots()}.
   */
  public void setMaxBots(int maxBots) {

    assert (maxBots > 0);
    assert (maxBots > this.minBots);

    this.maxBots = maxBots;
  }

  private int countPlayers(boolean human) {

    return (int) this.playerOptions.stream().filter(x -> x.isHuman() == human).count();
  }

}
