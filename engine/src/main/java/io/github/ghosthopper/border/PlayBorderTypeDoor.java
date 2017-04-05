package io.github.ghosthopper.border;

import io.github.ghosthopper.PlayDirection;
import io.github.ghosthopper.figure.PlayFigure;
import io.github.ghosthopper.item.PlayItem;

/**
 * A {@link PlayBorderType} that is a magic door so every {@link PlayFigure} {@link #canPass(PlayFigure, PlayBorder) can
 * always pass through}.
 */
public class PlayBorderTypeDoor extends PlayBorderType {

  private final PlayItem key;

  private PlayBorderTypeDoor(PlayItem key) {
    super("Door");
    this.key = key;
  }

  @Override
  public boolean canPass(PlayFigure figure, PlayBorder border) {

    return figure.getItems().contains(this.key);
  }

  @Override
  protected char getAsciiArt(PlayDirection direction) {

    return 'D';
  }

  /**
   * @param key the key required to open this door.
   * @return an instance of this border type.
   */
  public static PlayBorderTypeDoor get(PlayItem key) {

    return new PlayBorderTypeDoor(key);
  }

}
