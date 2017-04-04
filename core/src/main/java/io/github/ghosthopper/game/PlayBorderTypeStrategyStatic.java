/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.game;

import io.github.ghosthopper.PlayDirection;
import io.github.ghosthopper.border.PlayBorderType;
import io.github.ghosthopper.border.PlayBorderTypeOpen;
import io.github.ghosthopper.field.PlayField;

/**
 * An implementation of {@link PlayBorderTypeStrategy} that always returns the same result given at construction.
 */
public class PlayBorderTypeStrategyStatic implements PlayBorderTypeStrategy {

  /** An instance always returning {@code null}. */
  public static final PlayBorderTypeStrategyStatic UNINITIALIZED = new PlayBorderTypeStrategyStatic(null);

  /** An instance always returning {@link PlayBorderTypeOpen}. */
  public static final PlayBorderTypeStrategyStatic OPEN = new PlayBorderTypeStrategyStatic(PlayBorderTypeOpen.get());

  private final PlayBorderType type;

  /**
   * The constructor.
   *
   * @param type the fixed result of {@link #getType(PlayField, int, int, int, int, PlayDirection)}.
   */
  public PlayBorderTypeStrategyStatic(PlayBorderType type) {
    super();
    this.type = type;
  }

  @Override
  public PlayBorderType getType(PlayField field, int x, int y, int width, int height, PlayDirection direction) {

    return this.type;
  }

}
