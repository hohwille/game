/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.choice;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * A choice for the user. Either to configure or initialize the game or potentially also for quiz games.
 */
public class PlayChoice {

  private final String title;

  private final String question;

  private final List<PlayOption> options;

  private final int minimum;

  private final int maximum;

  /**
   * The constructor.
   *
   * @param title - see {@link #getTitle()}.
   * @param question - see {@link #getQuestion()}.
   * @param min - see {@link #getMinimum()}.
   * @param max - see {@link #getMaximum()}.
   * @param options - see {@link #getOptions()}.
   */
  public PlayChoice(String title, String question, int min, int max, PlayOption... options) {
    super();
    assert (min >= 0);
    assert (max <= options.length);
    this.title = title;
    this.question = question;
    this.minimum = min;
    this.maximum = max;
    this.options = Collections.unmodifiableList(Arrays.asList(options));
  }

  /**
   * @return the minimum number of {@link #getOptions() options} the user has to choose.
   */
  public int getMinimum() {

    return this.minimum;
  }

  /**
   * @return the maximum number of {@link #getOptions() options} the user has to choose.
   */
  public int getMaximum() {

    return this.maximum;
  }

  /**
   * @return the title as a very short summary of the choice (e.g. "Players", "Difficulty" or "Level").
   */
  public String getTitle() {

    return this.title;
  }

  /**
   * @return an optional question that describes the choice more detailed than the {@link #getTitle() title}. Can be
   *         {@link String#isEmpty() empty} or {@code null} to omit the question (if {@link #getTitle() title} is
   *         sufficient).
   */
  public String getQuestion() {

    return this.question;
  }

  /**
   * @return the available {@link PlayOption}s.
   */
  public List<PlayOption> getOptions() {

    return this.options;
  }

}
