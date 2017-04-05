package io.github.ghosthopper.figure;

import io.github.ghosthopper.PlayObjectWithColorAndTypeAndItems;
import io.github.ghosthopper.field.PlayField;
import io.github.ghosthopper.game.PlayGame;
import io.github.ghosthopper.player.Player;

/**
 * Any figure of an {@link Player#getFigures() owning} {@link Player}. Each {@link PlayFigure} has a {@link #getType()
 * type} that represents its characteristics and influences its visualization in the UI of the game. For the current
 * moment in time of the {@link PlayGame} each {@link PlayFigure} is {@link #getField() located on} a specific
 * {@link PlayField}.
 */
public class PlayFigure extends PlayObjectWithColorAndTypeAndItems {

  private final Player player;

  private final PlayFigureType type;

  private PlayField field;

  /**
   * The constructor.
   *
   * @param player - see {@link #getPlayer()}.
   * @param type - see {@link #getType()}.
   */
  public PlayFigure(Player player, PlayFigureType type) {
    super();
    this.player = player;
    this.type = type;
    setColor(player.getColor());
  }

  @Override
  public PlayFigureType getType() {

    return this.type;
  }

  /**
   * @return the {@link Player} owning this {@link PlayFigure}.
   */
  public Player getPlayer() {

    return this.player;
  }

  @Override
  public PlayGame getGame() {

    return this.player.getGame();
  }

  /**
   * @return the {@link PlayField} this {@link PlayFigure} is currently standing on.
   */
  public PlayField getField() {

    return this.field;
  }

  /**
   * @param field the new value of {@link #getField()}.
   */
  public void setField(PlayField field) {

    this.field = field;
  }

}
