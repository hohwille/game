/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.game.fx.choice;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javafx.scene.control.ListView;
import net.sf.mmm.game.engine.choice.GameChoice;
import net.sf.mmm.game.engine.choice.GameChoiceOptions;
import net.sf.mmm.game.engine.object.GameAttributeName;
import net.sf.mmm.game.engine.object.GameTypedObject;

/**
 * JavaFx view for {@link GameChoiceOptions}.
 *
 * @param <O> the type of the option.
 */
public class GameUiFxChoiceOptionsList<O> extends GameUiFxChoiceSingle<O> {

  private final GameChoiceOptions<O> choice;

  private final ListView<O> listView;

  private final Set<O> selectedOptions;

  Collection<O> defaultSelection;

  /**
   * The constructor.
   *
   * @param dialog the {@link #getFxParent() parent}.
   * @param choice the {@link GameChoice}.
   */
  public GameUiFxChoiceOptionsList(GameUiFxChoiceDialog dialog, GameChoiceOptions<O> choice) {
    super(dialog, choice);
    this.choice = choice;
    this.selectedOptions = new HashSet<>();
    this.listView = new ListView<>();
    this.listView.setPrefHeight(150);
    int maxOptions = choice.getMaxOptions();
    if (maxOptions > 1) {
      // this.listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }
    this.defaultSelection = choice.getDefaultSelection();
    List<O> options = choice.getOptions();
    if (options.isEmpty()) {
      throw new IllegalStateException("No options available for choice: " + choice);
    } else {
      O option = options.get(0);
      boolean editable = (option instanceof GameAttributeName);
      this.listView.setEditable(editable);
      if (option instanceof GameTypedObject) {
        this.listView.setCellFactory(x -> new GameUiFxListCellTypedObject<>(this, editable));
      } else {
        this.listView.setCellFactory(x -> new GameUiFxListCell<>(this, editable));
      }
      this.listView.getItems().addAll(options);
    }
  }

  @Override
  public ListView<O> getFxControl() {

    return this.listView;
  }

  @Override
  public GameChoiceOptions<O> getGameChoice() {

    return this.choice;
  }

  @Override
  protected List<O> getSelectedOptions() {

    List<O> selectionList = new ArrayList<>(this.selectedOptions.size());
    for (O item : this.listView.getItems()) {
      if (this.selectedOptions.contains(item)) {
        selectionList.add(item);
      }
    }
    return selectionList;
  }

  void addSelectedOption(O option, boolean validate) {

    boolean updated = this.selectedOptions.add(option);
    if (updated && validate) {
      selectionChanged();
    }
  }

  void removeSelectedOption(O option, boolean validate) {

    boolean updated = this.selectedOptions.remove(option);
    if (updated && validate) {
      selectionChanged();
    }
  }

  private void selectionChanged() {

    String error = getGameChoice().validate(getSelectedOptions());
    handleError(error);
  }

}
