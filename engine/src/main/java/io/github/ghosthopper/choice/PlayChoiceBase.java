/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.choice;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

import io.github.ghosthopper.game.PlayGame;
import io.github.ghosthopper.object.PlayObjectWithId;

/**
 * A choice for the user. Either to configure or initialize the game or potentially also for quiz games.
 *
 * @param <O> the type of the option.
 * @param <S> the type of the actual selection(s).
 */
public abstract class PlayChoiceBase<O, S> extends PlayObjectWithId implements PlayChoice<O> {

  private final PlayGame game;

  private final String question;

  private Consumer<List<? extends S>> selectionCallback;

  private List<Function<List<? extends S>, String>> validators;

  /**
   * The constructor.
   *
   * @param game - see {@link #getGame()}.
   * @param id - see {@link #getId()}.
   */
  public PlayChoiceBase(PlayGame game, String id) {
    this(game, id, null);
  }

  /**
   * The constructor.
   *
   * @param game - see {@link #getGame()}.
   * @param id - see {@link #getId()}.
   * @param description - see {@link #getDescription()}.
   */
  public PlayChoiceBase(PlayGame game, String id, String description) {
    super(id);
    this.game = game;
    this.question = description;
  }

  @Override
  public PlayGame getGame() {

    return this.game;
  }

  @Override
  public String getDescription() {

    return this.question;
  }

  /**
   * @return the {@link Consumer} that will be {@link Consumer#accept(Object) called} on successful {@link #select(List)
   *         selection}. May be {@code null}.
   */
  public Consumer<List<? extends S>> getSelectionCallback() {

    return this.selectionCallback;
  }

  /**
   * @param selectionCallback the new value of {@link #getSelectionCallback()}.
   */
  public void setSelectionCallback(Consumer<List<? extends S>> selectionCallback) {

    this.selectionCallback = selectionCallback;
  }

  /**
   * @param selection the {@link #select(List) selected option(s)}.
   * @return the converted selection.
   */
  @SuppressWarnings({ "rawtypes", "unchecked" })
  protected List<? extends S> convertSelection(List<O> selection) {

    return (List) selection;
  }

  @Override
  public String select(List<O> selection) {

    String error = PlayChoice.super.select(selection);
    if ((error == null) && (this.selectionCallback != null)) {
      this.selectionCallback.accept(convertSelection(selection));
    }
    return error;
  }

  /**
   * @param validator the validator {@link Function} to add. Will be called from {@link #validate(List)} in the order of
   *        registration. If the given {@link Function} returns a {@link String} that is not {@code null} it will be
   *        considered as validation error and is added to the aggregated error message.
   */
  public void addValidator(Function<List<? extends S>, String> validator) {

    if (this.validators == null) {
      this.validators = new ArrayList<>();
    }
    this.validators.add(validator);
  }

  @Override
  public String validate(List<O> selection) {

    String error = PlayChoice.super.validate(selection);
    if (this.validators != null) {
      List<? extends S> convertedSelection = convertSelection(selection);
      for (Function<List<? extends S>, String> validator : this.validators) {
        String validatorError = validator.apply(convertedSelection);
        error = PlayValidator.aggregateError(error, validatorError);
      }
    }
    return error;
  }

}