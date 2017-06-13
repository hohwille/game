/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.game.engine.field;

import net.sf.mmm.game.engine.asset.GameAsset;
import net.sf.mmm.game.engine.asset.GameAttributeAsset;
import net.sf.mmm.game.engine.figure.GameFigure;
import net.sf.mmm.game.engine.item.GamePickItem;
import net.sf.mmm.game.engine.type.GameTypeBase;

/**
 * The type of a {@link GameField}.
 */
public class GameFieldType extends GameTypeBase implements GameAttributeAsset {

  /** Type of a normal/regular {@link GameField}. */
  public static final GameFieldType NORMAL = new GameFieldType("Normal");

  /** Type of a {@link GameField} with a pit (player falls down or dies). */
  public static final GameFieldType PIT = new GameFieldType("Pit");

  /** Type of a {@link GameField} that is an exit (level completed). */
  public static final GameFieldType EXIT = new GameFieldType("Exit");

  /**
   * The constructor.
   *
   * @param id - see {@link #getId()}.
   */
  public GameFieldType(String id) {
    super(id);
  }

  @Override
  public String getTypeName() {

    return "Field";
  }

  /**
   * @param item the {@link GamePickItem} to check.
   * @return {@code true} if the given {@link GamePickItem} may be {@link GameFigure#dropItem() dropped} on this
   *         {@link GameField}.
   */
  public boolean isDroppable(GamePickItem item) {

    return true;
  }

  @Override
  public boolean canAddAsset(GameAsset<?> asset) {

    // by default a field can hold any asset(s). Override to change...
    return true;
  }

  @Override
  public boolean addAsset(GameAsset<?> asset) {

    return canAddAsset(asset);
  }

  @Override
  public boolean removeAsset(GameAsset<?> asset, boolean updateLocation) {

    return true;
  }

}
