/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.ui.fx.choice;

import io.github.ghosthopper.choice.GameChoiceOptions;
import io.github.ghosthopper.object.GameAttributeName;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;

/**
 * JavaFx {@link ListCell} implementation as view for a single {@link GameChoiceOptions#getOptions() option}.
 *
 * @param <O> the type of the option.
 */
class GameUiFxListCell<O> extends ListCell<O> {

  protected final GameUiFxChoiceOptionsList<O> parent;

  private final TextField textField;

  private final CheckBox checkBox;

  protected final HBox hBox;

  private boolean validateOnSelection;

  /**
   * The constructor.
   *
   * @param parent the parent object owning this cell.
   * @param editable {@code true} if the {@link ListView} is {@link ListView#isEditable() editable} and this cell may be
   *        edited, {@code false} otherwise.
   */
  public GameUiFxListCell(GameUiFxChoiceOptionsList<O> parent, boolean editable) {
    super();
    this.parent = parent;
    this.textField = new TextField();
    this.textField.addEventFilter(KeyEvent.KEY_PRESSED, e -> {
      if (e.getCode() == KeyCode.ESCAPE) {
        cancelEdit();
      }
    });
    this.textField.setOnAction(e -> {
      GameAttributeName option = (GameAttributeName) getItem();
      option.setName(this.textField.getText());
      setText(this.textField.getText());
    });
    this.checkBox = new CheckBox();
    this.checkBox.selectedProperty().addListener(x -> onSelection());
    this.hBox = new HBox();
    ObservableList<Node> children = this.hBox.getChildren();
    children.add(this.checkBox);
    children.add(this.textField);
    setGraphic(this.hBox);
    updateEditing(editable);
  }

  private void onSelection() {

    O item = getItem();
    if (this.checkBox.isSelected()) {
      this.parent.addSelectedOption(item, this.validateOnSelection);
    } else {
      this.parent.removeSelectedOption(item, this.validateOnSelection);
    }
    this.validateOnSelection = true;
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
      boolean selected = this.parent.defaultSelection.contains(option);
      if (this.checkBox.isSelected() != selected) {
        this.validateOnSelection = false;
        this.checkBox.setSelected(selected);
      } else {
        this.validateOnSelection = true;
      }
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
