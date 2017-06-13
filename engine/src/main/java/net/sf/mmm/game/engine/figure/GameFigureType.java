package net.sf.mmm.game.engine.figure;

import java.util.List;

import net.sf.mmm.game.engine.asset.GameAssetType;
import net.sf.mmm.game.engine.asset.GameAttributeAsset;
import net.sf.mmm.game.engine.field.GameField;
import net.sf.mmm.game.engine.item.GamePickItem;
import net.sf.mmm.game.engine.properties.GameProperties;
import net.sf.mmm.game.engine.properties.GamePropertyValueDouble;
import net.sf.mmm.game.engine.properties.GamePropertyValueInt;
import net.sf.mmm.game.engine.type.GameTypeBase;

/**
 * The {@link GameFigure#getType() type} of a {@link GameField}.
 */
public class GameFigureType extends GameTypeBase implements GameAssetType {

  private final boolean invisible;

  /**
   * The constructor.
   *
   * @param id - see {@link #getId()}.
   */
  public GameFigureType(String id) {
    this(id, false);
  }

  /**
   * The constructor.
   *
   * @param id - see {@link #getId()}.
   * @param invisible - see {@link #isInvisible()}.
   */
  public GameFigureType(String id, boolean invisible) {
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
   * @param figure the {@link GameFigure} to check. Has to be of this {@link GameFigure#getType() type}.
   * @param item the {@link GamePickItem} to check (that shall potentially be added).
   * @return {@code true} if this {@link GameFigure} may {@link GameFigure#pickItem(GamePickItem) pick} and carry the
   *         {@link GamePickItem}, {@code false} otherwise.
   * @see GameAttributeAsset#canAddAsset(net.sf.mmm.game.engine.asset.GameAsset)
   */
  public boolean canAddItem(GameFigure figure, GamePickItem item) {

    assert (figure.getType() == this);
    GameProperties properties = figure.getProperties();
    int maxItems = properties.get(GamePropertyValueInt.MAX_ITEMS).get();
    List<GamePickItem> items = figure.getItems();
    if (items.size() >= maxItems) {
      return false;
    }
    double maxWeight = properties.get(GamePropertyValueDouble.MAX_WEIGHT).get();
    if (maxWeight < Double.MAX_VALUE) {
      double weight = items.stream().mapToDouble(i -> i.getWeight()).sum();
      if ((weight + item.getWeight()) > maxWeight) {
        return false;
      }
    }
    return true;
  }
}
