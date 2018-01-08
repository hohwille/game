package net.sf.mmm.game.engine.asset;

import net.sf.mmm.game.engine.Game;
import net.sf.mmm.game.engine.color.GameColor;
import net.sf.mmm.game.engine.field.GameField;
import net.sf.mmm.game.engine.object.GameLocation;
import net.sf.mmm.game.engine.object.GameTypedObjectWithItems;
import net.sf.mmm.game.engine.player.GamePlayer;
import net.sf.mmm.game.engine.position.GamePosition;

/**
 * Any figure of an {@link GamePlayer#getFigures() owning} {@link GamePlayer}. Each {@link GameAssetBase} has a
 * {@link #getType() type} that represents its characteristics and influences its visualization in the UI of the game.
 * For the current moment in time of the {@link Game} each {@link GameAssetBase} is {@link #getLocation() located on} a
 * specific {@link GameField}.
 *
 * @param <T> generic type of {@link #getType()}.
 * @param <L> the generic type of the {@link #getLocation() location}.
 */
public abstract class GameAssetBase<T extends GameAssetType, L extends GameLocation> extends GameTypedObjectWithItems<T> implements GameAsset<L> {

  private GamePosition position;

  private L location;

  /**
   * The constructor.
   *
   * @param type the {@link #getType() type} of this object.
   * @param color the optional {@link #getColor() color}. May be {@code null} for default.
   */
  public GameAssetBase(T type, GameColor color) {

    super(type);
    this.position = GamePosition.CENTER;
    if (color != null) {
      setColor(color);
    }
  }

  @Override
  public GamePosition getPosition() {

    return this.position;
  }

  @Override
  public void setPosition(GamePosition position) {

    setLocation(this.location, position);
  }

  @Override
  public L getLocation() {

    return this.location;
  }

  @Override
  public boolean setLocation(L location, GamePosition position, boolean addOrRemove) {

    L oldLocation = this.location;
    GamePosition oldPosition = this.position;
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
      GameAssetMoveEvent<?, ?> moveEvent = createMoveEvent(oldLocation, oldPosition, location, position);
      getGame().sendEvent(moveEvent);
    }
    return success;
  }

  /**
   * @param targetLocation the {@link GameLocation} to {@link GameAttributeAsset#removeAsset(GameAsset, boolean) remove}
   *        this asset from.
   * @return {@code true} if successful, {@code false} otherwise.
   */
  protected boolean removeAssetFromLocation(L targetLocation) {

    if (targetLocation instanceof GameAttributeAsset) {
      return ((GameAttributeAsset) targetLocation).removeAsset(this, false);
    }
    return true;
  }

  /**
   * @param targetLocation the {@link GameLocation} to {@link GameAttributeAsset#addAsset(GameAsset) add} this asset to.
   * @return {@code true} if successful, {@code false} otherwise.
   */
  protected boolean addAssetToLocation(L targetLocation) {

    if (targetLocation instanceof GameAttributeAsset) {
      return ((GameAttributeAsset) targetLocation).addAsset(this);
    }
    return true;
  }

  /**
   * @param oldLocation - see {@link GameAssetMoveEvent#getOldLocation()}.
   * @param oldPosition - see {@link GameAssetMoveEvent#getOldPosition()}.
   * @param newLocation - see {@link GameAssetMoveEvent#getNewLocation()}.
   * @param newPosition - see {@link GameAssetMoveEvent#getNewPosition()}.
   * @return the created {@link GameAssetMoveEvent}.
   */
  protected abstract GameAssetMoveEvent<?, ?> createMoveEvent(L oldLocation, GamePosition oldPosition, L newLocation, GamePosition newPosition);

}
