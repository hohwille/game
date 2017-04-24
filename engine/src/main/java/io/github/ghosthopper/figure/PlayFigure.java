package io.github.ghosthopper.figure;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.github.ghosthopper.asset.PlayAsset;
import io.github.ghosthopper.border.PlayBorder;
import io.github.ghosthopper.field.PlayField;
import io.github.ghosthopper.game.PlayGame;
import io.github.ghosthopper.item.PlayAttributePickItems;
import io.github.ghosthopper.item.PlayPickItem;
import io.github.ghosthopper.item.PlayPickItemMoveEvent;
import io.github.ghosthopper.item.PlayPushItem;
import io.github.ghosthopper.move.PlayAttributeDirection;
import io.github.ghosthopper.move.PlayDirection;
import io.github.ghosthopper.object.PlayTypedObjectWithItems;
import io.github.ghosthopper.player.Player;
import io.github.ghosthopper.properties.PlayPropertyValueDouble;
import io.github.ghosthopper.properties.PlayPropertyValueInt;

/**
 * Any figure of an {@link Player#getFigures() owning} {@link Player}. Each {@link PlayFigure} has a {@link #getType()
 * type} that represents its characteristics and influences its visualization in the UI of the game. For the current
 * moment in time of the {@link PlayGame} each {@link PlayFigure} is {@link #getLocation() located on} a specific
 * {@link PlayField}.
 */
public class PlayFigure extends PlayTypedObjectWithItems implements PlayAsset<PlayField>, PlayAttributeDirection {

  private final Player player;

  private final PlayFigureType type;

  private final List<PlayPickItem> items;

  private final List<PlayPickItem> itemsView;

  private PlayField field;

  private PlayDirection direction;

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
    this.items = new ArrayList<>();
    this.itemsView = Collections.unmodifiableList(this.items);
    if (player != null) {
      setColor(player.getColor());
    }
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

  @Override
  public List<PlayPickItem> getItems() {

    return this.itemsView;
  }

  @Override
  public boolean canAddItem(PlayPickItem item) {

    return this.type.canAddItem(this, item);
  }

  @Override
  public boolean addItem(PlayPickItem item) {

    if (!canAddItem(item)) {
      return false;
    }
    PlayAttributePickItems oldLocation = item.getLocation();
    if (oldLocation != null) {
      boolean success = oldLocation.removeItem(item, false);
      if (!success) {
        return false;
      }
    }
    this.items.add(item);
    item.setLocation(this);
    getGame().sendEvent(new PlayPickItemMoveEvent(oldLocation, item, this));
    return true;
  }

  @Override
  public boolean removeItem(PlayPickItem item, boolean sendEvent) {

    boolean success = this.items.remove(item);
    if (success && sendEvent) {
      getGame().sendEvent(new PlayPickItemMoveEvent(this, item, null));
    }
    return success;
  }

  @Override
  public PlayField getLocation() {

    return this.field;
  }

  @Override
  public void setLocation(PlayField field) {

    this.field = field;
  }

  /**
   * @return {@code true} if this is the {@link PlayGame#getCurrentFigure() current figure}, {@code false} otherwise.
   */
  public boolean isCurrentFigure() {

    return getGame().getCurrentFigure() == this;
  }

  /**
   * @return the current {@link PlayDirection} this {@link PlayFigure} is pointing to. May be {@code null} if
   *         unspecified.
   */
  @Override
  public PlayDirection getDirection() {

    return this.direction;
  }

  /**
   * @param direction the new value of {@link #getDirection()}.
   */
  public void setDirection(PlayDirection direction) {

    this.direction = direction;
  }

  /**
   * @return the new {@link #getLocation()} after this move or {@code null} if the move was not possible and therefore
   *         failed.
   */
  public PlayField move() {

    return move(this.direction);
  }

  /**
   * @param clockwise {@code true} if figure should turn clockwise, {@code false} otherwise (opposite direction).
   * @return the new {@link PlayDirection}. May be {@code null} if {@link #getDirection() current direction} was already
   *         {@code null}.
   */
  public PlayDirection turn(boolean clockwise) {

    if (this.direction == null) {
      return null;
    }
    this.direction = this.direction.turn(clockwise);
    return this.direction;
  }

  /**
   * @param dir the {@link PlayDirection} to move to.
   * @return the new {@link #getLocation()} after this move or {@code null} if the move was not possible and therefore
   *         failed.
   */
  public PlayField move(PlayDirection dir) {

    if (this.field == null) {
      return null;
    }
    PlayField oldField = this.field;
    PlayBorder border = this.field.getBorder(dir);
    if (!border.canPass(this)) {
      return null;
    }
    PlayField targetField = border.getField(dir);
    if (targetField == null) {
      return null;
    }
    if (!push(targetField, dir)) {
      return null;
    }
    if (!border.pass(this)) {
      return null;
    }
    this.field = targetField;
    getGame().sendEvent(new PlayFigureMoveEvent(oldField, this, targetField));
    return this.field;
  }

  /**
   * @param sourceField the {@link PlayField} that may have an {@link PlayField#getPushItem() item} to push.
   * @param dir the {@link PlayDirection} to push to.
   * @param weight the current {@link PlayPropertyValueDouble#WEIGHT} to push.
   * @return {@code true} if the push was successful (no item to push or item successfully pushed), {@code false}
   *         otherwise.
   */
  private boolean push(PlayField sourceField, PlayDirection dir) {

    PlayPushItem item = sourceField.getPushItem();
    if (item == null) {
      return true;
    }
    return push(item, sourceField, dir, 0);
  }

  /**
   * @param sourceField the {@link PlayField} that may have an {@link PlayField#getPushItem() item} to push.
   * @param dir the {@link PlayDirection} to push to.
   * @param weight the current {@link PlayPropertyValueDouble#WEIGHT} to push.
   * @return {@code true} if the push was successful, {@code false} otherwise.
   */
  private boolean push(PlayPushItem item, PlayField sourceField, PlayDirection dir, double weight) {

    PlayBorder border = sourceField.getBorder(dir);
    if (!border.canPass(item)) {
      return false;
    }
    PlayField targetField = border.getField(dir);
    if ((targetField == null) || (targetField.canAddAsset(item))) {
      return false;
    }
    double itemWeight = item.getWeight();
    double totalWeight = weight + itemWeight;
    PlayPushItem nextItem = targetField.getPushItem();
    boolean success;
    if (nextItem == null) {
      double maxWeight = PlayPropertyValueDouble.MAX_WEIGHT.get(this);
      success = (totalWeight < maxWeight);
    } else {
      success = push(nextItem, targetField, dir, totalWeight);
    }
    if (success) {
      targetField.setPushItem(item);
      sourceField.setPushItem(null);
    }
    return success;
  }

  /**
   * @return the result of {@link #pickItem(PlayPickItem)} invoked for the top-most item of the {@link #getLocation()
   *         current field}.
   */
  public PlayPickItem pickItem() {

    if (this.field == null) {
      return null;
    }
    if (this.field.getItems().isEmpty()) {
      return null;
    }
    int itemIndex = this.field.getItems().size() - 1;
    PlayPickItem item = this.field.getItems().get(itemIndex);
    return pickItem(item, itemIndex);
  }

  /**
   * @param item the {@link PlayPickItem} to pick up.
   * @return the {@link PlayPickItem} that has been picked up from the {@link #getLocation() field} this
   *         {@link PlayFigure} is on, {@code null} otherwise (no {@link #getLocation() field}, no
   *         {@link PlayField#getItems() item}, {@link PlayPropertyValueInt#MAX_ITEMS full}, or
   *         {@link PlayPropertyValueDouble#MAX_WEIGHT overloaded}).
   */
  public PlayPickItem pickItem(PlayPickItem item) {

    if (this.field == null) {
      return null;
    }
    int itemIndex = this.field.getItems().indexOf(item);
    return pickItem(item, itemIndex);
  }

  private PlayPickItem pickItem(PlayPickItem item, int itemIndex) {

    if ((itemIndex >= 0) && item.getType().isPickable(this)) {
      if (addItem(item)) {
        return item;
      }
    }
    return null;
  }

  /**
   * @return the {@link PlayPickItem} that has been dropped from the {@link #getItems() inventory} to the
   *         {@link #getLocation() field} this {@link PlayFigure} is on, {@code null} otherwise (no
   *         {@link #getLocation() field}, no {@link #getItems() item in inventory}, etc.).
   */
  public PlayPickItem dropItem() {

    if (this.field == null) {
      return null;
    }
    if (this.items.isEmpty()) {
      return null;
    }
    int itemIndex = this.items.size() - 1;
    PlayPickItem item = this.items.get(itemIndex);
    return dropItem(item, itemIndex);
  }

  /**
   * @param item the {@link PlayPickItem} to pick up.
   * @return the {@link PlayPickItem} that has been dropped from the {@link #getItems() inventory} to the
   *         {@link #getLocation() field} this {@link PlayFigure} is on, {@code null} otherwise (no
   *         {@link #getLocation() field}, item not in {@link #getItems() inventory}, etc.).
   */
  public PlayPickItem dropItem(PlayPickItem item) {

    if (this.field == null) {
      return null;
    }
    int itemIndex = this.items.indexOf(item);
    return dropItem(item, itemIndex);
  }

  private PlayPickItem dropItem(PlayPickItem item, int itemIndex) {

    if ((itemIndex >= 0) && item.getType().isDroppable(this.field)) {
      if (this.field.addItem(item)) {
        return item;
      }
    }
    return null;
  }

}
