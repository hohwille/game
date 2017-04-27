/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.data;

import io.github.ghosthopper.object.PlayObjectWithId;

/**
 * Represents a view mode of a {@link io.github.ghosthopper.game.PlayGame}. See the predefined constants for additional
 * details.
 */
public class PlayView extends PlayObjectWithId {

  /** {@link PlayView} from the Sky in 2D from the sky perspective. */
  public static final PlayView VIEW_2D_SKY = new PlayView("View2dSky");

  /**
   * {@link PlayView} from the Sky in 3D from the perspective of the
   * {@link io.github.ghosthopper.game.PlayGame#getCurrentFigure() current}
   * {@link io.github.ghosthopper.figure.PlayFigure}.
   */
  public static final PlayView VIEW_3D_FLOOR = new PlayView("View3dFigure");

  /**
   * The constructor.
   *
   * @param id the {@link #getId() id}.
   */
  public PlayView(String id) {
    super(id);
  }

}
