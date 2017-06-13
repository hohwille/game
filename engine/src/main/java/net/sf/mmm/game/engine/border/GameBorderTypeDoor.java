package net.sf.mmm.game.engine.border;

import net.sf.mmm.game.engine.asset.GameAsset;
import net.sf.mmm.game.engine.figure.GameFigure;
import net.sf.mmm.game.engine.item.GameAttributePickItems;
import net.sf.mmm.game.engine.item.GamePickItem;
import net.sf.mmm.game.engine.type.GameTypeAccess;

/**
 * A {@link GameBorderType} that is a door that {@link #canPass(GameAsset, GameBorder, boolean) can only be passed} by
 * {@link GameFigure}s having the proper key.
 */
public class GameBorderTypeDoor extends GameBorderType {

  private final GamePickItem key;

  private GameBorderTypeDoor(GamePickItem key) {
    super("Door");
    this.key = key;
  }

  @Override
  public boolean canPass(GameAsset<?> asset, GameBorder border, boolean move) {

    if (asset instanceof GameAttributePickItems) {
      return ((GameAttributePickItems) asset).getItems().contains(this.key);
    }
    return false;
  }

  @Override
  public GameTypeAccess getOverlay() {

    return this.key;
  }

  /**
   * @param key the key required to open this door.
   * @return an instance of this border type.
   */
  public static GameBorderTypeDoor get(GamePickItem key) {

    return new GameBorderTypeDoor(key);
  }

}
