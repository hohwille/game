/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.game.games.chess;

import net.sf.mmm.game.engine.Game;
import net.sf.mmm.game.engine.color.GameColor;
import net.sf.mmm.game.engine.field.GameField;
import net.sf.mmm.game.engine.figure.GameFigure;
import net.sf.mmm.game.engine.figure.GameFigureType;
import net.sf.mmm.game.engine.level.GameLevel;
import net.sf.mmm.game.engine.player.GamePlayer;
import net.sf.mmm.game.engine.player.GamePlayerConfigBase;

/**
 * The game <em>chess</em>.
 */
public class Chess extends Game {

  private static final GameFigureType KING = new GameFigureType("ChessKing");

  private static final GameFigureType QUEEN = new GameFigureType("ChessQueen");

  private static final GameFigureType ROOK = new GameFigureType("ChessRook");

  private static final GameFigureType BISHOB = new GameFigureType("ChessBishob");

  private static final GameFigureType KNIGHT = new GameFigureType("ChessKnight");

  private static final GameFigureType PAWN = new GameFigureType("Pawn");

  private static final GameFigureType[] FIGURE_TYPES = new GameFigureType[] { //
      ROOK, KNIGHT, BISHOB, QUEEN, KING, BISHOB, KNIGHT, ROOK, //
      PAWN, PAWN, PAWN, PAWN, PAWN, PAWN, PAWN, PAWN, //
  };

  /**
   * The constructor.
   */
  public Chess() {
    super("Chess");
    GamePlayer white = new GamePlayer(this, GameColor.WHITE, FIGURE_TYPES);
    GamePlayer black = new GamePlayer(this, GameColor.BLACK, FIGURE_TYPES);
    GamePlayerConfigBase playerConfigBase = (GamePlayerConfigBase) getPlayerConfig();
    playerConfigBase.addPlayers(white, black);
    GameLevel level = getCurrentLevel();
    int figureIndex = 0;
    // place black figures
    GameColor fieldColor = GameColor.WHITE;
    for (int y = 1; y <= 8; y++) {
      for (int x = 1; x <= 8; x++) {
        GameField field = level.getField(x, y);
        field.setColor(fieldColor);
        fieldColor = toggleColor(fieldColor);
        if (y <= 2) {
          GameFigure playFigure = black.getFigures().get(figureIndex++);
          playFigure.setLocation(field);
        }
      }
    }
    // place white figures
    for (int y = 7; y <= 8; y++) {
      for (int x = 1; x <= 8; x++) {
        GameFigure playFigure = white.getFigures().get(getWhiteFigureIndex(figureIndex));
        figureIndex--;
        playFigure.setLocation(level.getField(x, y));
      }
    }
  }

  @Override
  protected GameLevel createFirstLevel() {

    GameLevel firstLevel = super.createFirstLevel();
    initLevelAsRectangular(firstLevel, 8, 8);
    return firstLevel;
  }

  private GameColor toggleColor(GameColor color) {

    if (color == GameColor.WHITE) {
      return GameColor.BLACK;
    }
    return GameColor.WHITE;
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
