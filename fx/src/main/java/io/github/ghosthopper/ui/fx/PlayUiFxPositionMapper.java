/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.ui.fx;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.github.ghosthopper.position.PlayPosition;
import javafx.geometry.Pos;

/**
 * {@link #getFxPosition(PlayPosition) Mapper} from {@link PlayPosition} to {@link Pos}.
 */
public class PlayUiFxPositionMapper {

  private static final Logger LOG = LoggerFactory.getLogger(PlayUiFxPositionMapper.class);

  private final Map<PlayPosition, Pos> positionMap;

  /**
   * The constructor.
   */
  public PlayUiFxPositionMapper() {
    super();
    this.positionMap = new HashMap<>();
    initDefaults();
  }

  /**
   * Initializes the {@link #add(PlayPosition, Pos) mappings} for {@link PlayPosition}.
   */
  protected void initDefaults() {

    add(PlayPosition.NORTH_WEST, Pos.TOP_LEFT);
    add(PlayPosition.NORTH, Pos.TOP_CENTER);
    add(PlayPosition.NORTH_EAST, Pos.TOP_RIGHT);
    add(PlayPosition.WEST, Pos.CENTER_LEFT);
    add(PlayPosition.CENTER, Pos.CENTER);
    add(PlayPosition.EAST, Pos.CENTER_RIGHT);
    add(PlayPosition.SOUTH_WEST, Pos.BOTTOM_LEFT);
    add(PlayPosition.SOUTH, Pos.BOTTOM_CENTER);
    add(PlayPosition.SOUTH_EAST, Pos.BASELINE_RIGHT);
  }

  /**
   * @param position the {@link PlayPosition} to map.
   * @param pos the mapped {@link Pos}.
   */
  protected final void add(PlayPosition position, Pos pos) {

    Pos duplicate = this.positionMap.put(position, pos);
    if ((duplicate != null) && (duplicate != pos)) {
      throw new IllegalStateException("Duplicate mapping for " + position + ": " + duplicate + " was overriden to " + pos);
    }
  }

  /**
   * @param position the {@link PlayPosition} to map.
   * @return the mapped FX {@link Pos}.
   */
  public Pos getFxPosition(PlayPosition position) {

    Pos pos = this.positionMap.get(position);
    if (pos == null) {
      LOG.info("Unmapped position: {}", position);
      pos = Pos.CENTER;
    }
    return pos;
  }

}
