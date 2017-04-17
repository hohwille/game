package io.github.ghosthopper.figure;

import java.util.List;

import io.github.ghosthopper.field.PlayField;
import io.github.ghosthopper.item.PlayPickItem;
import io.github.ghosthopper.object.PlayObjectType;
import io.github.ghosthopper.properties.PlayProperties;
import io.github.ghosthopper.properties.PlayPropertyValueDouble;
import io.github.ghosthopper.properties.PlayPropertyValueInt;

/**
 * The {@link PlayFigure#getType() type} of a {@link PlayField}.
 */
public class PlayFigureType extends PlayObjectType {

  private final boolean invisible;

  /**
   * The constructor.
   *
   * @param id - see {@link #getId()}.
   */
  public PlayFigureType(String id) {
    this(id, false);
  }

  /**
   * The constructor.
   *
   * @param id - see {@link #getId()}.
   * @param invisible - see {@link #isInvisible()}.
   */
  public PlayFigureType(String id, boolean invisible) {
    super(id);
    this.invisible = invisible;
  }

  @Override
  public String getTypeName() {

    return "Figure";
  }

  /**
   * @return {@code true} if the figure is invisible, {@code false} otherwise.
   */
  public boolean isInvisible() {

    return this.invisible;
  }

  /**
   * @param figure the {@link PlayFigure} to check. Has to be of this {@link PlayFigure#getType() type}.
   * @param item the {@link PlayPickItem} to check (that shall potentially be added).
   * @return {@code true} if this {@link PlayFigure} may {@link PlayFigure#pickItem(PlayPickItem) pick} and carry the
   *         {@link PlayPickItem}, {@code false} otherwise.
   */
  public boolean isPickable(PlayFigure figure, PlayPickItem item) {

    assert (figure.getType() == this);
    PlayProperties properties = figure.getProperties();
    int maxItems = properties.get(PlayPropertyValueInt.MAX_ITEMS).get();
    List<PlayPickItem> items = figure.getItems();
    if (items.size() >= maxItems) {
      return false;
    }
    double maxWeight = properties.get(PlayPropertyValueDouble.MAX_WEIGHT).get();
    if (maxWeight < Double.MAX_VALUE) {
      double weight = items.stream().mapToDouble(i -> i.getWeight()).sum();
      if ((weight + item.getWeight()) > maxWeight) {
        return false;
      }
    }
    return true;
  }
}
