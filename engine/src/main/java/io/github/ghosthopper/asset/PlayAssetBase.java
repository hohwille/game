package io.github.ghosthopper.asset;

import io.github.ghosthopper.field.PlayField;
import io.github.ghosthopper.game.PlayGame;
import io.github.ghosthopper.object.PlayLocation;
import io.github.ghosthopper.object.PlayTypedObjectWithItems;
import io.github.ghosthopper.player.Player;
import io.github.ghosthopper.position.PlayPosition;

/**
 * Any figure of an {@link Player#getFigures() owning} {@link Player}. Each {@link PlayAssetBase} has a
 * {@link #getType() type} that represents its characteristics and influences its visualization in the UI of the game.
 * For the current moment in time of the {@link PlayGame} each {@link PlayAssetBase} is {@link #getLocation() located
 * on} a specific {@link PlayField}.
 *
 * @param <L> the generic type of the {@link #getLocation() location}.
 */
public abstract class PlayAssetBase<L extends PlayLocation> extends PlayTypedObjectWithItems implements PlayAsset<L> {

  private PlayPosition position;

  private L location;

  /**
   * The constructor.
   */
  public PlayAssetBase() {
    super();
    this.position = PlayPosition.CENTER;
  }

  @Override
  public PlayPosition getPosition() {

    return this.position;
  }

  @Override
  public void setPosition(PlayPosition position) {

    setLocation(this.location, position);
  }

  @Override
  public L getLocation() {

    return this.location;
  }

  @Override
  public boolean setLocation(L location, PlayPosition position, boolean addOrRemove) {

    L oldLocation = this.location;
    PlayPosition oldPosition = this.position;
    boolean success = true;
    if (oldLocation == location) {
      if (oldPosition == position) {
        return success;
      }
    } else {
      if (addOrRemove) {
        if (location == null) {
          this.location = null;
          success = removeAssetFromLocation(oldLocation);
          assert (success);
          success = true;
        } else {
          success = addAssetToLocation(location);
        }
      }
    }
    if (success) {
      this.location = location;
      this.position = position;
      PlayAssetMoveEvent<?, ?> moveEvent = createMoveEvent(oldLocation, oldPosition, location, position);
      getGame().sendEvent(moveEvent);
    }
    return success;
  }

  /**
   * @param targetLocation the {@link PlayLocation} to {@link PlayAttributeAsset#removeAsset(PlayAsset, boolean) remove}
   *        this asset from.
   * @return {@code true} if successful, {@code false} otherwise.
   */
  protected boolean removeAssetFromLocation(L targetLocation) {

    if (targetLocation instanceof PlayAttributeAsset) {
      return ((PlayAttributeAsset) targetLocation).removeAsset(this, false);
    }
    return true;
  }

  /**
   * @param targetLocation the {@link PlayLocation} to {@link PlayAttributeAsset#addAsset(PlayAsset) add} this asset to.
   * @return {@code true} if successful, {@code false} otherwise.
   */
  protected boolean addAssetToLocation(L targetLocation) {

    if (targetLocation instanceof PlayAttributeAsset) {
      return ((PlayAttributeAsset) targetLocation).addAsset(this);
    }
    return true;
  }

  /**
   * @param oldLocation - see {@link PlayAssetMoveEvent#getOldLocation()}.
   * @param oldPosition - see {@link PlayAssetMoveEvent#getOldPosition()}.
   * @param newLocation - see {@link PlayAssetMoveEvent#getNewLocation()}.
   * @param newPosition - see {@link PlayAssetMoveEvent#getNewPosition()}.
   * @return the created {@link PlayAssetMoveEvent}.
   */
  protected abstract PlayAssetMoveEvent<?, ?> createMoveEvent(L oldLocation, PlayPosition oldPosition, L newLocation, PlayPosition newPosition);

}
