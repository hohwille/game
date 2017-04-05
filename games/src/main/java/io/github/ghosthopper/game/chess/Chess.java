/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.game.chess;

import io.github.ghosthopper.PlayColor;
import io.github.ghosthopper.PlayLevel;
import io.github.ghosthopper.Player;
import io.github.ghosthopper.field.PlayField;
import io.github.ghosthopper.figure.PlayFigure;
import io.github.ghosthopper.figure.PlayFigureType;
import io.github.ghosthopper.game.PlayGameSimple;

/**
 * The game <em>chess</em>.
 */
public class Chess extends PlayGameSimple {

  private static final PlayFigureType KING = new PlayFigureType("King", '♔');

  private static final PlayFigureType QUEEN = new PlayFigureType("Queen", '♕');

  private static final PlayFigureType ROOK = new PlayFigureType("Rook", '♖');

  private static final PlayFigureType BISHOB = new PlayFigureType("Bishob", '♗');

  private static final PlayFigureType KNIGHT = new PlayFigureType("Knight", '♘');

  private static final PlayFigureType PAWN = new PlayFigureType("Pawn", '♙');

  private static final PlayFigureType[] FIGURE_TYPES = new PlayFigureType[] { //
      ROOK, KNIGHT, BISHOB, QUEEN, KING, BISHOB, KNIGHT, ROOK, //
      PAWN, PAWN, PAWN, PAWN, PAWN, PAWN, PAWN, PAWN, //
  };

  private static final Player WHITE = new Player(PlayColor.WHITE, FIGURE_TYPES);

  private static final Player BLACK = new Player(PlayColor.BLACK, FIGURE_TYPES);

  /**
   * The constructor.
   */
  public Chess() {
    super("Chess", 8, 8);
    getPlayers().add(WHITE);
    getPlayers().add(BLACK);
    PlayLevel level = getCurrentLevel();
    int figureIndex = 0;
    // place black figures
    PlayColor fieldColor = PlayColor.WHITE;
    for (int y = 1; y <= 8; y++) {
      for (int x = 1; x <= 8; x++) {
        PlayField field = level.getField(x, y);
        field.setColor(fieldColor);
        fieldColor = toggleColor(fieldColor);
        if (y <= 2) {
          PlayFigure playFigure = BLACK.getFigures().get(figureIndex++);
          playFigure.setField(field);
        }
      }
    }
    // place white figures
    for (int y = 7; y <= 8; y++) {
      for (int x = 1; x <= 8; x++) {
        PlayFigure playFigure = WHITE.getFigures().get(getWhiteFigureIndex(figureIndex));
        figureIndex--;
        playFigure.setField(level.getField(x, y));
      }
    }
  }

  private PlayColor toggleColor(PlayColor color) {

    if (color == PlayColor.WHITE) {
      return PlayColor.BLACK;
    }
    return PlayColor.WHITE;
  }

  private int getWhiteFigureIndex(int figureIndex) {

    // toggle queen and king
    if (figureIndex == 3) {
      return 4;
    } else if (figureIndex == 4) {
      return 3;
    }
    return figureIndex;
  }

}
