/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.ui.fx.choice;

import java.util.List;

import io.github.ghosthopper.figure.GameFigure;
import io.github.ghosthopper.object.GameTypedObject;
import io.github.ghosthopper.player.GamePlayer;
import io.github.ghosthopper.ui.fx.GameUiFxColor;
import javafx.scene.control.ListView;
import javafx.scene.effect.Effect;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Extends {@code GameUiFxListCell} for an option that implements {@link GameTypedObject}.
 *
 * @param <O> the type of the option.
 */
class GameUiFxListCellTypedObject<O> extends GameUiFxListCell<O> {

  private ImageView imageView;

  /**
   * The constructor.
   *
   * @param editable {@code true} if the {@link ListView} is {@link ListView#isEditable() editable} and this cell may be
   *        edited, {@code false} otherwise.
   * @param parent the parent object owning this cell.
   */
  public GameUiFxListCellTypedObject(GameUiFxChoiceOptionsList<O> parent, boolean editable) {
    super(parent, editable);
  }

  @Override
  protected void updateItem(O option, boolean empty) {

    super.updateItem(option, empty);
    if (empty) {
      if (this.imageView != null) {
        this.imageView.setVisible(false);
      }
    } else {
      GameTypedObject typedObject = unwrap(option);
      Image image = this.parent.getFxDataCache().getImage(typedObject);
      if (this.imageView == null) {
        this.imageView = new ImageView(image);
        int size = 16;
        this.imageView.setFitHeight(size);
        this.imageView.setFitWidth(size);
        Effect effect = GameUiFxColor.getEffect(typedObject.getColor());
        if (effect != null) {
          this.imageView.setEffect(effect);
        }
        this.hBox.getChildren().add(1, this.imageView);
      } else {
        this.imageView.setVisible(true);
        this.imageView.setImage(image);
      }
    }
  }

  private GameTypedObject unwrap(O option) {

    if (option instanceof GamePlayer) {
      List<GameFigure> figures = ((GamePlayer) option).getFigures();
      if (figures.size() == 1) {
        return figures.get(0);
      }
    }
    return (GameTypedObject) option;
  }
}
