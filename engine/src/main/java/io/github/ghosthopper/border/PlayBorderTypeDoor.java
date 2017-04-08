package io.github.ghosthopper.border;

import io.github.ghosthopper.figure.PlayFigure;
import io.github.ghosthopper.item.PlayPickItem;

/**
 * A {@link PlayBorderType} that is a door that {@link #canPass(PlayFigure, PlayBorder, boolean) can only be passed} by
 * {@link PlayFigure}s having the proper key.
 */
public class PlayBorderTypeDoor extends PlayBorderType {

  private final PlayPickItem key;

  private PlayBorderTypeDoor(PlayPickItem key) {
    super("Door");
    this.key = key;
  }

  @Override
  public boolean canPass(PlayFigure figure, PlayBorder border, boolean move) {

    if (figure == null) {
      return false;
    }
    return figure.getItems().contains(this.key);
  }

  /**
   * @param key the key required to open this door.
   * @return an instance of this border type.
   */
  public static PlayBorderTypeDoor get(PlayPickItem key) {

    return new PlayBorderTypeDoor(key);
  }

}
