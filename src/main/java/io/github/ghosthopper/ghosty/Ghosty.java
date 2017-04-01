package io.github.ghosthopper.ghosty;

import io.github.ghosthopper.PlayPlayerType;
import io.github.ghosthopper.PlayWallTypeHole;

public class Ghosty {

  public static final PlayPlayerType MOUSE = new PlayPlayerType();
  public static final PlayPlayerType RABBIT = new PlayPlayerType();

  public static final PlayWallTypeHole MOUSE_HOME = new PlayWallTypeHole(MOUSE);
  public static final PlayWallTypeHole RABBIT_WINDOW = new PlayWallTypeHole(RABBIT);
}
