/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.ui.fx.choice;

import io.github.ghosthopper.data.GameSeverity;
import io.github.ghosthopper.i18n.GameTranslator;
import io.github.ghosthopper.ui.fx.GameUiFxObject;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * A JavaFx decorator for a message with a {@link GameSeverity}.
 */
public class GameUiFxSeverityDecoration extends Label implements GameUiFxObject {

  private final GameUiFxObject parent;

  private final ImageView imageView;

  private final Tooltip tooltip;

  private GameSeverity severity;

  private Image image;

  /**
   * The constructor.
   *
   * @param severity the {@link GameSeverity}.
   * @param parent the parent {@link GameUiFxObject}.
   */
  public GameUiFxSeverityDecoration(GameSeverity severity, GameUiFxObject parent) {
    super();
    this.parent = parent;
    this.severity = severity;
    this.tooltip = new Tooltip();
    this.image = parent.getFxDataCache().getImage(severity);
    this.imageView = new ImageView(this.image);
    // http://stackoverflow.com/questions/17405688/javafx-activate-a-tooltip-with-a-button
    // this.imageView.setOnMouseClicked(x -> showTooltip());
    setGraphic(this.imageView);
    setTooltip(this.tooltip);
    setVisible(false);
  }

  @Override
  public GameUiFxObject getFxParent() {

    return this.parent;
  }

  /**
   * @return the {@link GameSeverity}.
   */
  public GameSeverity getGameSeverity() {

    return this.severity;
  }

  /**
   * @param severity the new value of {@link #getGameSeverity()}.
   */
  public void setSeverity(GameSeverity severity) {

    if (this.severity == severity) {
      return;
    }
    this.image = getFxDataCache().getImage(severity);
    this.imageView.setImage(this.image);
    this.severity = severity;
  }

  /**
   * @param message the textual message to display (will be used as is so {@link GameTranslator#translate(String)
   *        translation} has to happen before).
   */
  public void setMessage(String message) {

    setMessage(message, this.severity);
  }

  /**
   * @param message the textual message to display (will be used as is so {@link GameTranslator#translate(String)
   *        translation} has to happen before).
   * @param severity the new value of {@link #getGameSeverity()}.
   */
  public void setMessage(String message, GameSeverity severity) {

    setSeverity(severity);
    if (message == null) {
      clear();
      return;
    }
    setVisible(true);
    this.tooltip.setText(message);
  }

  /**
   * Clears the {@link #setMessage(String) message} and hides this decoration.
   */
  public void clear() {

    setVisible(false);
    this.tooltip.setText(null);
  }

}
