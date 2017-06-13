/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.game.fx.asset;

import javafx.scene.Node;
import javafx.scene.effect.Effect;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import net.sf.mmm.game.engine.asset.GameAsset;
import net.sf.mmm.game.engine.field.GameField;
import net.sf.mmm.game.engine.figure.GameFigure;
import net.sf.mmm.game.engine.object.GameLocation;
import net.sf.mmm.game.fx.GameUiFxColor;
import net.sf.mmm.game.fx.GameUiFxGame;
import net.sf.mmm.game.fx.GameUiFxObject;
import net.sf.mmm.game.fx.field.GameUiFxField;

/**
 * JavaFx view for a {@link GameFigure}.
 */
public abstract class GameUiFxAsset extends ImageView implements GameUiFxObject {

  private GameUiFxField field;

  /**
   * The constructor.
   *
   * @param asset the {@link GameAsset}.
   * @param parent the {@link #getParent() parent}.
   */
  public GameUiFxAsset(GameAsset<?> asset, GameUiFxObject parent) {
    super();
    Image image = parent.getFxDataCache().getImage(asset);
    setImage(image);
    Effect effect = GameUiFxColor.getEffect(asset.getColor());
    if (effect != null) {
      setEffect(effect);
    }
  }

  @Override
  public Node getFxNode() {

    return this;
  }

  /**
   * @return the {@link GameAsset}.
   */
  public abstract GameAsset<?> getGameAsset();

  /**
   * @return the {@link GameUiFxField} where the {@link GameAsset} is currently {@link GameAsset#getLocation() on}.
   */
  public GameUiFxField getGameField() {

    return this.field;
  }

  /**
   * @param newField the new {@link #getGameField() field} to move this figure to.
   */
  public void setGameField(GameUiFxField newField) {

    if (this.field == newField) {
      return;
    }
    if (this.field != null) {
      this.field.removeFxAsset(this);
    }
    this.field = newField;
    if (this.field != null) {
      assert (getGameAsset().getLocation() == this.field.getGameField());
      newField.addFxAsset(this);
    }
  }

  /**
   * Updates the {@link GameAsset#getPosition() position} of this figure.
   */
  public void updateGamePosition() {

    GameAsset<?> asset = getGameAsset();
    GameLocation location = asset.getLocation();
    if (location instanceof GameField) {
      GameUiFxGame fxGame = getFxGame();
      GameUiFxField fxField = fxGame.getFxField((GameField) location);
      GameUiFxAsset fxAsset = fxGame.getFxAsset(asset);
      fxField.updateGamePosition(fxAsset);
    }
  }

}
