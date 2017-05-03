/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.game.chess;

import io.github.ghosthopper.color.PlayColor;
import io.github.ghosthopper.field.PlayField;
import io.github.ghosthopper.figure.PlayFigure;
import io.github.ghosthopper.figure.PlayFigureType;
import io.github.ghosthopper.game.PlayGame;
import io.github.ghosthopper.level.PlayLevel;
import io.github.ghosthopper.player.Player;
import io.github.ghosthopper.player.PlayerConfigBase;

/**
 * The game <em>chess</em>.
 */
public class Chess extends PlayGame {

  private static final PlayFigureType KING = new PlayFigureType("ChessKing");

  private static final PlayFigureType QUEEN = new PlayFigureType("ChessQueen");

  private static final PlayFigureType ROOK = new PlayFigureType("ChessRook");

  private static final PlayFigureType BISHOB = new PlayFigureType("ChessBishob");

  private static final PlayFigureType KNIGHT = new PlayFigureType("ChessKnight");

  private static final PlayFigureType PAWN = new PlayFigureType("Pawn");

  private static final PlayFigureType[] FIGURE_TYPES = new PlayFigureType[] { //
      ROOK, KNIGHT, BISHOB, QUEEN, KING, BISHOB, KNIGHT, ROOK, //
      PAWN, PAWN, PAWN, PAWN, PAWN, PAWN, PAWN, PAWN, //
  };

  /**
   * The constructor.
   */
  public Chess() {
    super("Chess");
    Player white = new Player(this, PlayColor.WHITE, FIGURE_TYPES);
    Player black = new Player(this, PlayColor.BLACK, FIGURE_TYPES);
    PlayerConfigBase playerConfigBase = (PlayerConfigBase) getPlayerConfig();
    playerConfigBase.addPlayers(white, black);
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
          PlayFigure playFigure = black.getFigures().get(figureIndex++);
          playFigure.setLocation(field);
        }
      }
    }
    // place white figures
    for (int y = 7; y <= 8; y++) {
      for (int x = 1; x <= 8; x++) {
        PlayFigure playFigure = white.getFigures().get(getWhiteFigureIndex(figureIndex));
        figureIndex--;
        playFigure.setLocation(level.getField(x, y));
      }
    }
  }

  @Override
  protected PlayLevel createFirstLevel() {

    PlayLevel firstLevel = super.createFirstLevel();
    initLevelAsRectangular(firstLevel, 8, 8);
    return firstLevel;
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
