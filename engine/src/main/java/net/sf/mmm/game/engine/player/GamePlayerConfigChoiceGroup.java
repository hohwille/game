/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.game.engine.player;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.sf.mmm.game.engine.Game;
import net.sf.mmm.game.engine.choice.GameChoice;
import net.sf.mmm.game.engine.choice.GameChoiceGroup;
import net.sf.mmm.game.engine.choice.GameChoiceGroupBase;
import net.sf.mmm.game.engine.choice.GameChoiceOptionsBase;
import net.sf.mmm.game.engine.choice.GameChoiceSingleBase;
import net.sf.mmm.game.engine.choice.GameValidator;

/**
 * Implementation of {@link GamePlayerConfigBase}.
 */
public class GamePlayerConfigChoiceGroup extends GamePlayerConfigBase {

  /** I18n key for a choice of the players. */
  public static final String I18N_CHOICE_PLAYERS = "Choice.Players";

  private final List<GamePlayer> selectedPlayers;

  private final Set<GamePlayer> selectedPlayersSet;

  /**
   * The constructor.
   *
   * @param game - see {@link #getGame()}.
   */
  public GamePlayerConfigChoiceGroup(Game game) {
    this(game, 1);
  }

  /**
   * The constructor.
   *
   * @param game - see {@link #getGame()}.
   * @param minHumans - see {@link #getMinHumans()}.
   */
  public GamePlayerConfigChoiceGroup(Game game, int minHumans) {
    super(game, minHumans);
    this.selectedPlayers = new ArrayList<>();
    this.selectedPlayersSet = new HashSet<>();
  }

  @Override
  public GameChoiceGroupBase<GamePlayer, GamePlayer> getChoice() {

    Game game = getGame();
    List<GameChoice<GamePlayer>> choices = createChoices();
    GameChoiceGroupBase<GamePlayer, GamePlayer> group = new GameChoiceGroupBase<>(game, GamePlayer.I18N_PLAYERS, I18N_CHOICE_PLAYERS, GamePlayerType.DEFAULT,
        choices);
    group.setSelectionCallback(this::onSelectChoices);
    group.addValidator(this::onValidateChoices);
    return group;
  }

  /**
   * @return the {@link List} of {@link GameChoice}s to {@link GameChoice#select(List) select} the {@link GamePlayer}s.
   */
  protected List<GameChoice<GamePlayer>> createChoices() {

    List<GameChoice<GamePlayer>> choices = new ArrayList<>();
    GameChoiceOptionsBase<GamePlayer> playerChoice = new GameChoiceOptionsBase<>(getGame(), "Players", null, this.playerOptions, getMinPlayers(),
        getMaxPlayers());
    playerChoice.addValidator(this::onValidateSelection);
    choices.add(playerChoice);
    return choices;
  }

  /**
   * @param choices the {@link List} of {@link GameChoice}s that have been selected.
   */
  protected void onSelectChoices(List<? extends GameChoice<GamePlayer>> choices) {

    this.players.clear();
    this.players.addAll(this.selectedPlayers);
    getGame().start();
  }

  /**
   * @param choices the {@link GameChoice}s to validate.
   * @return the validation error message or {@code null} if valid.
   */
  protected String onValidateChoices(List<? extends GameChoice<GamePlayer>> choices) {

    this.selectedPlayers.clear();
    this.selectedPlayersSet.clear();
    collectSelectedPlayers(choices);
    return onValidateSelection(this.selectedPlayers);
  }

  /**
   * @param selection the selected {@link GamePlayer}s.
   * @return the validation error message or {@code null} if valid.
   */
  protected String onValidateSelection(List<? extends GamePlayer> selection) {

    String error = null;
    // error = GameValidator.validateRange(selection.size(), getMinPlayers(), getMaxPlayers(), "Players");
    // if (error != null) {
    // return error;
    // }
    int humans = 0;
    int bots = 0;
    for (GamePlayer player : selection) {
      if (player.isHuman()) {
        humans++;
      } else {
        bots++;
      }
    }
    error = GameValidator.aggregateError(error, GameValidator.validateRange(humans, getMinHumans(), getMaxHumans(), "Humans"));
    error = GameValidator.aggregateError(error, GameValidator.validateRange(bots, getMinBots(), getMaxBots(), "Bots"));
    return error;
  }

  @SuppressWarnings("unchecked")
  private void collectSelectedPlayers(List<? extends GameChoice<GamePlayer>> choices) {

    for (GameChoice<GamePlayer> choice : choices) {
      if (choice instanceof GameChoiceSingleBase) {
        List<GamePlayer> selection = ((GameChoiceSingleBase<GamePlayer>) choice).getSelection();
        for (GamePlayer player : selection) {
          boolean unique = this.selectedPlayersSet.add(player);
          if (unique) {
            this.selectedPlayers.add(player);
          }
        }
      } else if (choice instanceof GameChoiceGroup) {
        collectSelectedPlayers(((GameChoiceGroup<GamePlayer, GamePlayer>) choice).getChoices());
      } else {
        throw new IllegalArgumentException(choice.getClass().getName());
      }
    }
  }

}
