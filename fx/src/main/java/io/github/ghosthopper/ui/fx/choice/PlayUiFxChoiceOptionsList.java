/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.ui.fx.choice;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import io.github.ghosthopper.choice.PlayChoice;
import io.github.ghosthopper.choice.PlayChoiceOptions;
import io.github.ghosthopper.figure.PlayFigure;
import io.github.ghosthopper.object.PlayAttributeName;
import io.github.ghosthopper.object.PlayTypedObject;
import io.github.ghosthopper.player.Player;
import io.github.ghosthopper.ui.fx.PlayUiFxColor;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.effect.Effect;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;

/**
 * JavaFx view for {@link PlayChoiceOptions}.
 *
 * @param <O> the type of the option.
 */
public class PlayUiFxChoiceOptionsList<O> extends PlayUiFxChoiceSingle<O> {

  private final PlayChoiceOptions<O> choice;

  private final ListView<O> listView;

  private final Set<O> selection;

  private Collection<O> defaultSelection;

  /**
   * The constructor.
   *
   * @param dialog the {@link #getFxParent() parent}.
   * @param choice the {@link PlayChoice}.
   */
  public PlayUiFxChoiceOptionsList(PlayUiFxChoiceDialog dialog, PlayChoiceOptions<O> choice) {
    super(dialog, choice);
    this.choice = choice;
    this.selection = new HashSet<>();
    this.listView = new ListView<>();
    this.listView.setPrefHeight(150);
    int maxOptions = choice.getMaxOptions();
    if (maxOptions > 1) {
      // this.listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }
    this.defaultSelection = choice.getDefaultSelection();
    List<O> options = choice.getOptions();
    if (options.isEmpty()) {
    } else {
      O option = options.get(0);
      boolean editable = (option instanceof PlayAttributeName);
      this.listView.setEditable(editable);
      if (option instanceof PlayTypedObject) {
        this.listView.setCellFactory(x -> new PlayUiFxListCellTypedObject(editable));
      } else {
        this.listView.setCellFactory(x -> new PlayUiFxListCell(editable));
      }
      this.listView.getItems().addAll(options);
    }
  }

  @Override
  public ListView<O> getControl() {

    return this.listView;
  }

  @Override
  public PlayChoiceOptions<O> getChoice() {

    return this.choice;
  }

  @Override
  protected List<O> getSelection() {

    List<O> selectionList = new ArrayList<>(this.selection.size());
    for (O item : this.listView.getItems()) {
      if (this.selection.contains(item)) {
        selectionList.add(item);
      }
    }
    return selectionList;
  }

  private class PlayUiFxListCell extends ListCell<O> {

    private final TextField textField;

    private final CheckBox checkBox;

    protected final HBox hBox;

    /**
     * The constructor.
     *
     * @param editable {@code true} if the {@link ListView} is {@link ListView#isEditable() editable} and this cell may
     *        be edited, {@code false} otherwise.
     */
    public PlayUiFxListCell(boolean editable) {
      super();
      this.textField = new TextField();
      this.textField.addEventFilter(KeyEvent.KEY_PRESSED, e -> {
        if (e.getCode() == KeyCode.ESCAPE) {
          cancelEdit();
        }
      });
      this.textField.setOnAction(e -> {
        PlayAttributeName option = (PlayAttributeName) getItem();
        option.setName(this.textField.getText());
        setText(this.textField.getText());
      });
      this.checkBox = new CheckBox();
      this.checkBox.selectedProperty().addListener(x -> updateSelection());
      this.hBox = new HBox();
      ObservableList<Node> children = this.hBox.getChildren();
      children.add(this.checkBox);
      children.add(this.textField);
      setGraphic(this.hBox);
      updateEditing(editable);
    }

    private void updateSelection() {

      O item = getItem();
      if (this.checkBox.isSelected()) {
        PlayUiFxChoiceOptionsList.this.selection.add(item);
      } else {
        PlayUiFxChoiceOptionsList.this.selection.remove(item);
      }
    }

    @Override
    public void commitEdit(O newValue) {

      // TODO Auto-generated method stub
      super.commitEdit(newValue);
    }

    @Override
    protected void updateItem(O option, boolean empty) {

      super.updateItem(option, empty);
      if (empty) {
        setContentDisplay(ContentDisplay.TEXT_ONLY);
      } else {
        setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        this.textField.setText(option.toString());
        this.checkBox.setSelected(PlayUiFxChoiceOptionsList.this.defaultSelection.contains(option));
      }
    }

    private void updateEditing(boolean editing) {

      this.textField.setEditable(editing);
    }

    @Override
    public void startEdit() {

      super.startEdit();
      this.textField.requestFocus();
      this.textField.selectAll();
    }

    @Override
    public void cancelEdit() {

      super.cancelEdit();
      this.textField.setText(getItem().toString());
    }
  }

  private class PlayUiFxListCellTypedObject extends PlayUiFxListCell {

    private ImageView imageView;

    /**
     * The constructor.
     *
     * @param editable {@code true} if the {@link ListView} is {@link ListView#isEditable() editable} and this cell may
     *        be edited, {@code false} otherwise.
     */
    public PlayUiFxListCellTypedObject(boolean editable) {
      super(editable);
    }

    @Override
    protected void updateItem(O option, boolean empty) {

      super.updateItem(option, empty);
      if (empty) {
        if (this.imageView != null) {
          this.imageView.setVisible(false);
        }
      } else {
        PlayTypedObject typedObject = unwrap(option);
        Image image = getFxDataCache().getImage(typedObject);
        if (this.imageView == null) {
          this.imageView = new ImageView(image);
          int size = 16;
          this.imageView.setFitHeight(size);
          this.imageView.setFitWidth(size);
          Effect effect = PlayUiFxColor.getEffect(typedObject.getColor());
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

    private PlayTypedObject unwrap(O option) {

      if (option instanceof Player) {
        List<PlayFigure> figures = ((Player) option).getFigures();
        if (figures.size() == 1) {
          return figures.get(0);
        }
      }
      return (PlayTypedObject) option;
    }
  }

}
