package io.github.ghosthopper.border;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import io.github.ghosthopper.PlayDirection;
import io.github.ghosthopper.figure.PlayFigure;
import io.github.ghosthopper.figure.PlayFigureType;

/**
 * A {@link PlayBorderType} that is a hole where only {@link PlayFigure}s of specific {@link PlayFigure#getType() types}
 * {@link #canPass(PlayFigure, PlayBorder) can pass through}.
 */
public class PlayBorderTypeHole extends PlayBorderType {

  private final Set<PlayFigureType> figureTypes;

  private PlayBorderTypeHole(PlayFigureType... figureTypes) {
    super("Hole");
    this.figureTypes = Collections.unmodifiableSet(new HashSet<>(Arrays.asList(figureTypes)));
  }

  @Override
  public boolean canPass(PlayFigure player, PlayBorder border) {

    return this.figureTypes.contains(player.getType());
  }

  /**
   * @param figureTypes the {@link PlayFigureType}s that are allowed to {@link #canPass(PlayFigure, PlayBorder) pass
   *        through}.
   * @return an instance of this border type.
   */
  public static final PlayBorderTypeHole get(PlayFigureType... figureTypes) {

    return new PlayBorderTypeHole(figureTypes);
  }

  @Override
  protected char getAsciiArt(PlayDirection direction) {

    return Character.toLowerCase(this.figureTypes.iterator().next().getAsciiArt());
  }

}
