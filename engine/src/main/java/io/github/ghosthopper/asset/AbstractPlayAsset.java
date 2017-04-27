package io.github.ghosthopper.asset;

import io.github.ghosthopper.field.PlayField;
import io.github.ghosthopper.game.PlayGame;
import io.github.ghosthopper.object.PlayLocation;
import io.github.ghosthopper.object.PlayTypedObjectWithItems;
import io.github.ghosthopper.player.Player;
import io.github.ghosthopper.position.PlayPosition;

/**
 * Any figure of an {@link Player#getFigures() owning} {@link Player}. Each {@link AbstractPlayAsset} has a
 * {@link #getType() type} that represents its characteristics and influences its visualization in the UI of the game.
 * For the current moment in time of the {@link PlayGame} each {@link AbstractPlayAsset} is {@link #getLocation()
 * located on} a specific {@link PlayField}.
 *
 * @param <L> the generic type of the {@link #getLocation() location}.
 */
public abstract class AbstractPlayAsset<L extends PlayLocation> extends PlayTypedObjectWithItems implements PlayAsset<L> {

  private PlayPosition position;

  /**
   * The constructor.
   */
  public AbstractPlayAsset() {
    super();
    this.position = PlayPosition.CENTER;
  }

  @Override
  public PlayPosition getPosition() {

    return this.position;
  }

  @Override
  public void setPosition(PlayPosition position, boolean updateLocation) {

    PlayPosition pos = PlayPosition.nonNull(position);
    if (this.position == pos) {
      return;
    }
    PlayPosition old = this.position;
    this.position = pos;
    PlayAssetPositionEvent<?> event = createPositionEvent(old, pos);
    getGame().sendEvent(event);
    if (updateLocation) {
      L location = getLocation();
      if (location != null) {
        location.updatePosition(event);
      }
    }
  }

  /**
   * @param oldPosition - see {@link PlayAssetPositionEvent#getOldPosition()}.
   * @param newPosition - see {@link PlayAssetPositionEvent#getNewPosition()}.
   * @return the created {@link PlayAssetPositionEvent}.
   */
  protected abstract PlayAssetPositionEvent<?> createPositionEvent(PlayPosition oldPosition, PlayPosition newPosition);

}
