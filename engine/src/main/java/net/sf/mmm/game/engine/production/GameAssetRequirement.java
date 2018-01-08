/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.game.engine.production;

import java.util.Objects;

import net.sf.mmm.game.engine.asset.GameAsset;
import net.sf.mmm.game.engine.asset.GameAssetType;
import net.sf.mmm.game.engine.type.GameType;

/**
 * A requirement specifying that a {@link #getQuantity() quantity} of {@link #getAsset() asset(s)} are needed for
 * something and these {@link #getAsset() asset(s)} may be {@link #isConsuming() consumed} by that something.
 *
 * @author Joerg Hohwiller (hohwille at users.sourceforge.net)
 * @since 1.0.0
 */
public class GameAssetRequirement {

  private final GameAsset<?> asset;

  private final GameAssetType assetType;

  private final int quantity;

  private final boolean consuming;

  /**
   * The constructor.
   *
   * @param assetType - see {@link #getAssetType()}.
   */
  public GameAssetRequirement(GameAssetType assetType) {

    this(null, assetType, 1, true);
  }

  /**
   * The constructor.
   *
   * @param assetType - see {@link #getAssetType()}.
   * @param quantity - see {@link #getQuantity()}.
   */
  public GameAssetRequirement(GameAssetType assetType, int quantity) {

    this(null, assetType, quantity, true);
  }

  /**
   * The constructor.
   *
   * @param assetType - see {@link #getAssetType()}.
   * @param quantity - see {@link #getQuantity()}.
   * @param consuming - see {@link #isConsuming()}.
   */
  public GameAssetRequirement(GameAssetType assetType, int quantity, boolean consuming) {

    this(null, assetType, quantity, consuming);
  }

  /**
   * The constructor.
   *
   * @param asset the expected {@link GameAsset}.
   */
  public GameAssetRequirement(GameAsset<?> asset) {

    this(asset, asset.getType(), 1, true);
  }

  /**
   * The constructor.
   *
   * @param asset the expected {@link GameAsset}.
   * @param quantity - see {@link #getQuantity()}.
   */
  public GameAssetRequirement(GameAsset<?> asset, int quantity) {

    this(asset, asset.getType(), quantity, true);
  }

  /**
   * The constructor.
   *
   * @param asset the expected {@link GameAsset}.
   * @param quantity - see {@link #getQuantity()}.
   * @param consuming - see {@link #isConsuming()}.
   */
  public GameAssetRequirement(GameAsset<?> asset, int quantity, boolean consuming) {

    this(asset, asset.getType(), quantity, consuming);
  }

  /**
   * The constructor.
   *
   * @param asset - see {@link #getType()}.
   * @param quantity - see {@link #getQuantity()}.
   * @param consuming - see {@link #isConsuming()}.
   */
  private GameAssetRequirement(GameAsset<?> asset, GameAssetType assetType, int quantity, boolean consuming) {

    super();
    Objects.requireNonNull(assetType, "type");
    if (quantity <= 0) {
      throw new IllegalArgumentException(Integer.toString(quantity));
    }
    this.asset = asset;
    this.assetType = assetType;
    this.quantity = quantity;
    this.consuming = consuming;
  }

  /**
   * @return the {@link GameAssetType} of the required {@link #getAsset() asset}.
   */
  public GameAssetType getAssetType() {

    return this.assetType;
  }

  /**
   * @return the optional {@link GameAsset} that may be used as template to define the required quality of the
   *         {@link GameAsset}s to {@link #isAccepting(GameAsset) accept}. May be {@code null}.
   */
  public GameAsset<?> getAsset() {

    return this.asset;
  }

  /**
   * @return the quantity
   */
  public int getQuantity() {

    return this.quantity;
  }

  /**
   * @return {@code true} if the {@link #getAsset() required} offer{@link #getQuantity() (s)} will be consumed,
   *         {@code false} otherwise (they will only be used but remain). E.g. to create a pan-cake a number of eggs
   *         will be consumed and a pan is used.
   */
  public boolean isConsuming() {

    return this.consuming;
  }

  /**
   * This method may be overridden to extend and customize a requirement. E.g. it may also check the quality of the
   * object.
   *
   * @param gameAsset the {@link GameAsset} to check.
   * @return {@code true} if the given {@link GameAsset} is accepted for this requirement as {@link #getAsset() asset}
   *         in a single quantity.
   */
  public boolean isAccepting(GameAsset<?> gameAsset) {

    GameAssetType type = gameAsset.getType();
    if (type != this.assetType) {
      if (!GameType.ID_WILDCARD.equals(this.assetType.getId())) {
        return false;
      } else if (this.assetType.getClass() != type.getClass()) {
        return false;
      }
    }
    if (this.asset != null) {
      if (!isAcceptingAssetQuality(gameAsset)) {
        return false;
      }
    }
    return true;
  }

  /**
   * @param gameAsset the {@link GameAsset} that is supposed to already have the correct type.
   * @return {@code true} if the {@link GameAsset} {@link #isAccepting(GameAsset) is acceptable} also by its quality,
   *         {@code false} otherwise.
   */
  protected boolean isAcceptingAssetQuality(GameAsset<?> gameAsset) {

    return this.asset.equals(gameAsset);
  }

}
