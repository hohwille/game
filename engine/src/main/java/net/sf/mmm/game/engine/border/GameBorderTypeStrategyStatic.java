/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.game.engine.border;

import net.sf.mmm.game.engine.direction.GameDirection;
import net.sf.mmm.game.engine.field.GameField;

/**
 * An implementation of {@link GameBorderTypeStrategy} that always returns the same result given at construction.
 */
public class GameBorderTypeStrategyStatic implements GameBorderTypeStrategy {

  /** An instance always returning {@code null}. */
  public static final GameBorderTypeStrategyStatic UNINITIALIZED = new GameBorderTypeStrategyStatic(null);

  /** An instance always returning {@link GameBorderTypeOpen}. */
  public static final GameBorderTypeStrategyStatic OPEN = new GameBorderTypeStrategyStatic(GameBorderTypeOpen.get());

  private final GameBorderType type;

  /**
   * The constructor.
   *
   * @param type the fixed result of {@link #getType(GameField, int, int, int, int, GameDirection)}.
   */
  public GameBorderTypeStrategyStatic(GameBorderType type) {
    super();
    this.type = type;
  }

  @Override
  public GameBorderType getType(GameField field, int x, int y, int width, int height, GameDirection direction) {

    return this.type;
  }

}
