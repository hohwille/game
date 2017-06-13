/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.game.fx;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.geometry.Pos;
import net.sf.mmm.game.engine.position.GamePosition;

/**
 * {@link #getFxPosition(GamePosition) Mapper} from {@link GamePosition} to {@link Pos}.
 */
public class GameUiFxPositionMapper {

  private static final Logger LOG = LoggerFactory.getLogger(GameUiFxPositionMapper.class);

  private final Map<GamePosition, Pos> positionMap;

  /**
   * The constructor.
   */
  public GameUiFxPositionMapper() {
    super();
    this.positionMap = new HashMap<>();
    initDefaults();
  }

  /**
   * Initializes the {@link #add(GamePosition, Pos) mappings} for {@link GamePosition}.
   */
  protected void initDefaults() {

    add(GamePosition.NORTH_WEST, Pos.TOP_LEFT);
    add(GamePosition.NORTH, Pos.TOP_CENTER);
    add(GamePosition.NORTH_EAST, Pos.TOP_RIGHT);
    add(GamePosition.WEST, Pos.CENTER_LEFT);
    add(GamePosition.CENTER, Pos.CENTER);
    add(GamePosition.EAST, Pos.CENTER_RIGHT);
    add(GamePosition.SOUTH_WEST, Pos.BOTTOM_LEFT);
    add(GamePosition.SOUTH, Pos.BOTTOM_CENTER);
    add(GamePosition.SOUTH_EAST, Pos.BOTTOM_RIGHT);
  }

  /**
   * @param position the {@link GamePosition} to map.
   * @param pos the mapped {@link Pos}.
   */
  protected final void add(GamePosition position, Pos pos) {

    Pos duplicate = this.positionMap.put(position, pos);
    if ((duplicate != null) && (duplicate != pos)) {
      throw new IllegalStateException("Duplicate mapping for " + position + ": " + duplicate + " was overriden to " + pos);
    }
  }

  /**
   * @param position the {@link GamePosition} to map.
   * @return the mapped FX {@link Pos}.
   */
  public Pos getFxPosition(GamePosition position) {

    Pos pos = this.positionMap.get(position);
    if (pos == null) {
      LOG.info("Unmapped position: {}", position);
      pos = Pos.CENTER;
    }
    return pos;
  }

}
