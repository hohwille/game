package io.github.ghosthopper.figure;

import io.github.ghosthopper.PlayLevel;
import io.github.ghosthopper.PlayObjectType;
import io.github.ghosthopper.border.PlayBorderType;
import io.github.ghosthopper.field.PlayField;

/**
 * The {@link PlayFigure#getType() type} of a {@link PlayField}.
 */
public class PlayFigureType extends PlayObjectType {

  private final char asciiArt;

  private final boolean invisible;

  /**
   * The constructor.
   *
   * @param id - see {@link #getId()}.
   * @param asciiArt - see {@link #getAsciiArt()}.
   */
  public PlayFigureType(String id, char asciiArt) {
    this(id, asciiArt, false);
  }

  /**
   * The constructor.
   *
   * @param id - see {@link #getId()}.
   * @param asciiArt - see {@link #getAsciiArt()}.
   * @param invisible - see {@link #isInvisible()}.
   */
  public PlayFigureType(String id, char asciiArt, boolean invisible) {
    super(id);
    this.asciiArt = asciiArt;
    this.invisible = invisible;
  }

  /**
   * @return the character used to represent this {@link PlayBorderType} in ASCII-Art of the {@link PlayLevel}.
   */
  public char getAsciiArt() {

    return this.asciiArt;
  }

  /**
   * @return {@code true} if the figure is invisible, {@code false} otherwise.
   */
  public boolean isInvisible() {

    return this.invisible;
  }

}
