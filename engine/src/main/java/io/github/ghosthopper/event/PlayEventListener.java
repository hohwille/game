/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.event;

import io.github.ghosthopper.PlayLevel;
import io.github.ghosthopper.field.PlayField;
import io.github.ghosthopper.figure.PlayFigure;
import io.github.ghosthopper.game.PlayGame;
import io.github.ghosthopper.game.PlayState;
import io.github.ghosthopper.item.PlayItem;
import io.github.ghosthopper.item.PlayPickItem;
import io.github.ghosthopper.player.Player;
import io.github.ghosthopper.properties.PlayPropertyKey;
import io.github.ghosthopper.properties.PlayPropertyLifeValue;

/**
 * This is the interface for a listener that gets notified about events of a {@link PlayGame}.
 */
public interface PlayEventListener {

  /**
   * @param game the {@link PlayGame} that changed its state.
   * @param state the new {@link PlayState}.
   */
  default void onChangeState(PlayGame game, PlayState state) {
    // override to handle event...
  }

  /**
   * @param previousLevel the previous {@link PlayLevel} that has been left.
   * @param newLevel the new {@link PlayLevel} that has been entered.
   * @see PlayGame#getCurrentLevel()
   */
  default void onChangeLevel(PlayLevel previousLevel, PlayLevel newLevel) {
    // override to handle event...
  }

  /**
   * @param previousPlayer the previous {@link Player} who has completed his turn.
   * @param newPlayer the new {@link Player} who is now on turn.
   * @see PlayGame#getCurrentPlayer()
   */
  default void onChangePlayer(Player previousPlayer, Player newPlayer) {
    // override to handle event...
  }

  /**
   * @param sourceField the {@link PlayField} that was left by the given {@link PlayFigure}.
   * @param figure the moved {@link PlayFigure}.
   * @param targetField the new {@link PlayField} the given {@link PlayFigure} just moved to.
   */
  default void onMoveFigure(PlayField sourceField, PlayFigure figure, PlayField targetField) {
    // override to handle event...
  }

  /**
   * @param sourceField the {@link PlayField} the given {@link PlayItem} was located before.
   * @param item the moved {@link PlayItem}.
   * @param targetField the new {@link PlayField} the given {@link PlayItem} just moved to.
   */
  default void onMoveItem(PlayField sourceField, PlayItem<?> item, PlayField targetField) {
    // override to handle event...
  }

  /**
   * Called if the {@link PlayFigure} changed (e.g. {@link PlayFigure#getDirection() direction} changed,
   * {@link PlayPickItem} {@link PlayFigure#pickItem() picked} or {@link PlayFigure#dropItem() dropped},
   * {@link PlayPropertyLifeValue life value} updated, etc.).
   *
   * @param figure the {@link PlayFigure} that changed.
   */
  default void onChangeFigure(PlayFigure figure) {
    // override to handle event...
  }

  /**
   * Called if a {@link Player} changed (e.g. {@link PlayPropertyKey#SCORE score} changed, lost/game over, etc.)
   *
   * @param player the {@link Player} that changed.
   */
  default void onChangePlayer(Player player) {
    // override to handle event...
  }

}
