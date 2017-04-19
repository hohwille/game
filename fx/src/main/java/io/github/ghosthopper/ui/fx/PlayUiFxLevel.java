/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.ui.fx;

import java.util.HashMap;
import java.util.Map;

import io.github.ghosthopper.PlayLevel;
import io.github.ghosthopper.border.PlayBorder;
import io.github.ghosthopper.color.PlayColor;
import io.github.ghosthopper.data.PlayDataKey;
import io.github.ghosthopper.field.PlayField;
import io.github.ghosthopper.figure.PlayFigure;
import io.github.ghosthopper.figure.PlayFigureType;
import io.github.ghosthopper.game.PlayGame;
import io.github.ghosthopper.move.PlayDirection;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

/**
 * JavaFx view for a {@link PlayLevel}.
 */
public class PlayUiFxLevel extends GridPane {

  private final PlayUiFxGame game;

  private final PlayLevel level;

  private final Map<PlayField, PlayUiFxField> fieldMap;

  private final Map<PlayBorder, PlayUiFxBorder> borderMap;

  /**
   * The constructor.
   *
   * @param level the {@link PlayLevel}.
   * @param game the {@link PlayUiFxGame} owning this level.
   */
  public PlayUiFxLevel(PlayLevel level, PlayUiFxGame game) {
    super();
    this.level = level;
    this.game = game;
    this.fieldMap = new HashMap<>();
    this.borderMap = new HashMap<>();
    getStyleClass().add("level");
    initLevel();
    if (isDummy()) {
      placeDummyFigures();
    }
  }

  private boolean isDummy() {

    return false;
  }

  private void placeDummyFigures() {

    PlayColor[] colors = new PlayColor[] { PlayColor.MAGENTA, PlayColor.BLUE, PlayColor.CYAN, PlayColor.GREEN, PlayColor.RED, PlayColor.YELLOW, PlayColor.BLACK,
        PlayColor.GREY, PlayColor.WHITE };
    int colorIndex = 0;
    for (int y = 0; y < 4; y++) {
      for (int x = 0; x < 4; x++) {
        PlayField field = this.level.getField(x, y);
        PlayFigure figure = new PlayFigure(null, new PlayFigureType("Frog"));
        figure.setColor(colors[colorIndex++]);
        if (colorIndex == colors.length) {
          colorIndex = 0;
        }
        PlayUiFxFigure fxFigure = new PlayUiFxFigure(figure, this.game.getDataCache());
        fxFigure.setPlayField(getFxField(field));
      }
    }
  }

  private void initLevel() {

    PlayGame playGame = this.level.getGame();
    PlayField startField = this.level.getStartField();
    PlayDirection xDir = playGame.getDirectionX();
    PlayDirection yDir = playGame.getDirectionY();
    PlayDirection xDirInverse = xDir.getInverse();
    PlayDirection yDirInverse = yDir.getInverse();
    PlayField xStartField = startField;
    boolean showBorder = playGame.isShowBorder();
    int y = 0;
    while (xStartField != null) {
      PlayField field = xStartField;
      int x = 0;
      if (showBorder) {
        if (y == 0) {
          addEdge(x, y);
          y++;
        }
        addBorder(field.getBorder(xDirInverse), x, y);
        addEdge(x, y + 1);
        x++;
      }
      while (field != null) {
        addField(field, x, y);
        if (showBorder) {
          if (y == 1) {
            addBorder(field.getBorder(yDirInverse), x, y - 1);
          }
          addBorder(field.getBorder(yDir), x, y + 1);
        }
        x++;
        PlayBorder border = field.getBorder(xDir);
        if (showBorder) {
          if (y == 1) {
            addEdge(x, y - 1);
          }
          addBorder(border, x, y);
          addEdge(x, y + 1);
          x++;
        }
        field = border.getField(xDir);
      }
      PlayBorder border = xStartField.getBorder(yDir);
      xStartField = border.getField(yDir);
      y++;
      if (showBorder) {
        y++;
      }
    }
  }

  private void addEdge(int x, int y) {

    Image image = this.game.getDataCache().getImage(PlayDataKey.EDGE);
    ImageView imageView = new ImageView(image);
    add(imageView, x, y);
  }

  private void addField(PlayField field, int x, int y) {

    PlayUiFxField fxField = new PlayUiFxField(field, this.game.getDataCache());
    this.fieldMap.put(field, fxField);
    add(fxField, x, y);
  }

  private void addBorder(PlayBorder border, int x, int y) {

    PlayUiFxBorder fxBorder = new PlayUiFxBorder(border, this.game.getDataCache());
    this.borderMap.put(border, fxBorder);
    add(fxBorder, x, y);
  }

  /**
   * @param field the {@link PlayField}.
   * @return the corresponding {@link PlayUiFxField} or {@code null} if undefined.
   */
  public PlayUiFxField getFxField(PlayField field) {

    assert (field.getLevel() == this.level);
    return this.fieldMap.get(field);
  }

  /**
   * @param border the {@link PlayBorder}.
   * @return the corresponding {@link PlayUiFxBorder} or {@code null} if undefined.
   */
  public PlayUiFxBorder getFxBorder(PlayBorder border) {

    assert ((border.getSourceField() == null) || (border.getSourceField().getLevel() == this.level));
    assert ((border.getTargetField() == null) || (border.getTargetField().getLevel() == this.level));
    return this.borderMap.get(border);
  }

  /**
   * @return the {@link PlayLevel}.
   */
  public PlayLevel getPlayLevel() {

    return this.level;
  }

}
