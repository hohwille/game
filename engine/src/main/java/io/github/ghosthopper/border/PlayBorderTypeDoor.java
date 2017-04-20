package io.github.ghosthopper.border;

import io.github.ghosthopper.figure.PlayFigure;
import io.github.ghosthopper.item.PlayAttributePickItems;
import io.github.ghosthopper.item.PlayPickItem;
import io.github.ghosthopper.object.PlayAsset;
import io.github.ghosthopper.object.PlayStateObject;

/**
 * A {@link PlayBorderType} that is a door that {@link #canPass(PlayAsset, PlayBorder, boolean) can only be passed} by
 * {@link PlayFigure}s having the proper key.
 */
public class PlayBorderTypeDoor extends PlayBorderType {

  private final PlayPickItem key;

  private PlayBorderTypeDoor(PlayPickItem key) {
    super("Door");
    this.key = key;
  }

  @Override
  public boolean canPass(PlayAsset<?> asset, PlayBorder border, boolean move) {

    if (asset instanceof PlayAttributePickItems) {
      return ((PlayAttributePickItems) asset).getItems().contains(this.key);
    }
    return false;
  }

  @Override
  public PlayStateObject getOverlay() {

    return this.key;
  }

  /**
   * @param key the key required to open this door.
   * @return an instance of this border type.
   */
  public static PlayBorderTypeDoor get(PlayPickItem key) {

    return new PlayBorderTypeDoor(key);
  }

}
