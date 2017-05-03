/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.player;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import io.github.ghosthopper.choice.PlayChoice;
import io.github.ghosthopper.choice.PlayChoiceGroup;
import io.github.ghosthopper.choice.PlayChoiceGroupBase;
import io.github.ghosthopper.choice.PlayChoiceOptionsBase;
import io.github.ghosthopper.choice.PlayChoiceSingleBase;
import io.github.ghosthopper.choice.PlayValidator;
import io.github.ghosthopper.game.PlayGame;

/**
 * Implementation of {@link PlayerConfigBase}.
 */
public class PlayerConfigChoiceGroup extends PlayerConfigBase {

  private final List<Player> selectedPlayers;

  private final Set<Player> selectedPlayersSet;

  /**
   * The constructor.
   *
   * @param game - see {@link #getGame()}.
   */
  public PlayerConfigChoiceGroup(PlayGame game) {
    this(game, 1);
  }

  /**
   * The constructor.
   *
   * @param game - see {@link #getGame()}.
   * @param minHumans - see {@link #getMinHumans()}.
   */
  public PlayerConfigChoiceGroup(PlayGame game, int minHumans) {
    super(game, minHumans);
    this.selectedPlayers = new ArrayList<>();
    this.selectedPlayersSet = new HashSet<>();
  }

  @Override
  public PlayChoiceGroupBase<Player, Player> getChoice() {

    PlayGame game = getGame();
    List<PlayChoice<Player>> choices = createChoices();
    PlayChoiceGroupBase<Player, Player> group = new PlayChoiceGroupBase<>(game, "Players", "ChoosePlayers", PlayerType.DEFAULT, choices);
    group.setSelectionCallback(this::onSelectChoices);
    group.addValidator(this::onValidateChoices);
    return group;
  }

  /**
   * @return the {@link List} of {@link PlayChoice}s to {@link PlayChoice#select(List) select} the {@link Player}s.
   */
  protected List<PlayChoice<Player>> createChoices() {

    List<PlayChoice<Player>> choices = new ArrayList<>();
    PlayChoiceOptionsBase<Player> playerChoice = new PlayChoiceOptionsBase<>(getGame(), "Players", null, this.playerOptions, getMinPlayers(), getMaxPlayers());
    playerChoice.addValidator(this::onValidateSelection);
    choices.add(playerChoice);
    return choices;
  }

  /**
   * @param choices the {@link List} of {@link PlayChoice}s that have been selected.
   */
  protected void onSelectChoices(List<? extends PlayChoice<Player>> choices) {

    this.players.clear();
    this.players.addAll(this.selectedPlayers);
    getGame().start();
  }

  /**
   * @param choices the {@link PlayChoice}s to validate.
   * @return the validation error message or {@code null} if valid.
   */
  protected String onValidateChoices(List<? extends PlayChoice<Player>> choices) {

    this.selectedPlayers.clear();
    this.selectedPlayersSet.clear();
    collectSelectedPlayers(choices);
    return onValidateSelection(this.selectedPlayers);
  }

  /**
   * @param selection the selected {@link Player}s.
   * @return the validation error message or {@code null} if valid.
   */
  protected String onValidateSelection(List<? extends Player> selection) {

    String error = PlayValidator.validateRange(selection.size(), getMinPlayers(), getMaxPlayers(), "Players");
    if (error != null) {
      return error;
    }
    int humans = 0;
    int bots = 0;
    for (Player player : selection) {
      if (player.isHuman()) {
        humans++;
      } else {
        bots++;
      }
    }
    error = PlayValidator.aggregateError(error, PlayValidator.validateRange(humans, getMinHumans(), getMaxHumans(), "Humans"));
    error = PlayValidator.aggregateError(error, PlayValidator.validateRange(bots, getMinBots(), getMaxBots(), "Bots"));
    return error;
  }

  @SuppressWarnings("unchecked")
  private void collectSelectedPlayers(List<? extends PlayChoice<Player>> choices) {

    for (PlayChoice<Player> choice : choices) {
      if (choice instanceof PlayChoiceSingleBase) {
        List<Player> selection = ((PlayChoiceSingleBase<Player>) choice).getSelection();
        for (Player player : selection) {
          boolean unique = this.selectedPlayersSet.add(player);
          if (unique) {
            this.selectedPlayers.add(player);
          }
        }
      } else if (choice instanceof PlayChoiceGroup) {
        collectSelectedPlayers(((PlayChoiceGroup<Player, Player>) choice).getChoices());
      } else {
        throw new IllegalArgumentException(choice.getClass().getName());
      }
    }
  }

}
