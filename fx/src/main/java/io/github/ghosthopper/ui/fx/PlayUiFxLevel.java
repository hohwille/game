/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.ui.fx;

import io.github.ghosthopper.border.PlayBorder;
import io.github.ghosthopper.data.PlayDataKey;
import io.github.ghosthopper.field.PlayField;
import io.github.ghosthopper.game.PlayGame;
import io.github.ghosthopper.level.PlayLevel;
import io.github.ghosthopper.move.PlayDirection;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

/**
 * JavaFx view for a {@link PlayLevel}.
 */
public class PlayUiFxLevel extends GridPane implements PlayUiFxNode {

  private final PlayUiFxGame game;

  private final PlayLevel level;

  private boolean initialized;

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
    getStyleClass().add("level");
  }

  void initialize() {

    if (this.initialized) {
      return;
    }
    this.initialized = true;
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

  @Override
  public PlayUiFxGame getFxGame() {

    return this.game;
  }

  @Override
  public PlayUiFxGame getFxParent() {

    return this.game;
  }

  private void addEdge(int x, int y) {

    Image image = this.game.getFxDataCache().getImage(PlayDataKey.EDGE);
    ImageView imageView = new ImageView(image);
    add(imageView, x, y);
  }

  private void addField(PlayField field, int x, int y) {

    PlayUiFxField fxField = this.game.getFxField(field);
    add(fxField, x, y);
  }

  private void addBorder(PlayBorder border, int x, int y) {

    PlayUiFxBorder fxBorder = this.game.getFxBorder(border);
    add(fxBorder, x, y);
  }

  /**
   * @return the {@link PlayLevel}.
   */
  public PlayLevel getPlayLevel() {

    return this.level;
  }

}
