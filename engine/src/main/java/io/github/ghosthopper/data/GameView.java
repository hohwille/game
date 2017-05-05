/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.data;

import io.github.ghosthopper.object.GameObjectWithId;

/**
 * Represents a view mode of a {@link io.github.ghosthopper.game.Game}. See the predefined constants for additional
 * details.
 */
public class GameView extends GameObjectWithId {

  /** {@link #getId() ID} of {@link #VIEW_DEFAULT}. */
  public static final String DEFAULT_ID = "ViewDefault";

  /** Default {@link GameView} view for independent graphics. */
  public static final GameView VIEW_DEFAULT = new GameView(DEFAULT_ID);

  /** {@link GameView} from the Sky in 2D from the sky perspective. */
  public static final GameView VIEW_2D_SKY = new GameView("View2dSky");

  /**
   * {@link GameView} from the Sky in 3D from the perspective of the
   * {@link io.github.ghosthopper.game.Game#getCurrentFigure() current} {@link io.github.ghosthopper.figure.GameFigure}.
   */
  public static final GameView VIEW_3D_FLOOR = new GameView("View3dFigure");

  /**
   * The constructor.
   *
   * @param id the {@link #getId() id}.
   */
  public GameView(String id) {
    super(id);
  }

}
