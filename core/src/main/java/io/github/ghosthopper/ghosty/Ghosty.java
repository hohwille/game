package io.github.ghosthopper.ghosty;

import io.github.ghosthopper.PlayBorderTypeHole;
import io.github.ghosthopper.PlayFigureTypeHuman;

public class Ghosty {

  public static final PlayFigureTypeHuman MOUSE = new PlayFigureTypeHuman();

  public static final PlayFigureTypeHuman RABBIT = new PlayFigureTypeHuman();

  public static final PlayBorderTypeHole MOUSE_HOME = PlayBorderTypeHole.get(MOUSE);

  public static final PlayBorderTypeHole RABBIT_WINDOW = PlayBorderTypeHole.get(RABBIT);
}
