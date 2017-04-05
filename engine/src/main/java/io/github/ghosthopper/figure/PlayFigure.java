package io.github.ghosthopper.figure;

import io.github.ghosthopper.PlayObjectWithColorAndItems;
import io.github.ghosthopper.Player;
import io.github.ghosthopper.field.PlayField;
import io.github.ghosthopper.game.PlayGame;

/**
 * Any figure of an {@link Player#getFigures() owning} {@link Player}. Each {@link PlayFigure} has a {@link #getType()
 * type} that represents its characteristics and influences its visualization in the UI of the game. For the current
 * moment in time of the {@link PlayGame} each {@link PlayFigure} is {@link #getField() located on} a specific
 * {@link PlayField}.
 */
public class PlayFigure extends PlayObjectWithColorAndItems {

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

  /**
   * @return the type of this {@link PlayField}.
   */
  public PlayFigureType getType() {

    return this.type;
  }

  /**
   * @return the {@link Player} owning this {@link PlayFigure}.
   */
  public Player getPlayer() {

    return this.player;
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
