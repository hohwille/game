/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.game.engine.production;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.function.Supplier;

import net.sf.mmm.game.engine.asset.GameAsset;
import net.sf.mmm.game.engine.asset.GameAssetType;
import net.sf.mmm.game.engine.figure.GameFigure;
import net.sf.mmm.game.engine.item.GameAssetProxy;
import net.sf.mmm.game.engine.time.GameDuration;
import net.sf.mmm.game.engine.type.GameType;

/**
 * A recipe takes some {@link #getRequirements() requirements} (ingredients and tools) and some {@link #getDuration()
 * time} (additionally maybe also some skills of the producer) to {@link #produce(GameAsset, Collection) produce} a new
 * {@link GameAsset}.<br>
 * E.g. with a stove, a pan and and some eggs you can produce scrambled eggs. The raw eggs will be
 * {@link GameAssetRequirement#isConsuming() consumed} but the stove and the pan will remain.
 *
 * @param <A> generic type of the {@link #produce(GameFigure) produced} {@link GameAsset}.
 * @author Joerg Hohwiller (hohwille at users.sourceforge.net)
 * @since 1.0.0
 */
public class GameRecipe<A extends GameAsset<?>> {

  private final Supplier<A> assetFactory;

  private final Predicate<GameAsset<?>> producerRequirement;

  private final List<GameAssetRequirement> requirements;

  private final GameDuration duration;

  /**
   * The constructor.
   *
   * @param assetFactory the {@link Supplier} to create the {@link GameAsset}.
   * @param duration the {@link GameDuration} required as effort.
   * @param requirements - see {@link #getRequirements()}.
   */
  public GameRecipe(Supplier<A> assetFactory, GameDuration duration, GameAssetRequirement... requirements) {

    this(assetFactory, duration, a -> true, Arrays.asList(requirements));
  }

  /**
   * The constructor.
   *
   * @param assetFactory the {@link Supplier} to create the {@link GameAsset}.
   * @param duration the {@link GameDuration} required as effort.
   * @param requirements - see {@link #getRequirements()}.
   */
  public GameRecipe(Supplier<A> assetFactory, GameDuration duration, List<GameAssetRequirement> requirements) {

    this(assetFactory, duration, a -> true, requirements);
  }

  /**
   * The constructor.
   *
   * @param assetFactory the {@link Supplier} to create the {@link GameAsset}.
   * @param duration the {@link GameDuration} required as effort.
   * @param producerRequirement the {@link Predicate} {@link Predicate#test(Object) deciding} if the producer can
   *        {@link #produce(GameAsset, Collection) produce} this recipe.
   * @param requirements - see {@link #getRequirements()}.
   */
  public GameRecipe(Supplier<A> assetFactory, GameDuration duration, Predicate<GameAsset<?>> producerRequirement, GameAssetRequirement... requirements) {

    this(assetFactory, duration, producerRequirement, Arrays.asList(requirements));
  }

  /**
   * The constructor.
   *
   * @param assetFactory the {@link Supplier} to create the {@link GameAsset}.
   * @param duration the {@link GameDuration} required as effort.
   * @param producerRequirement the {@link Predicate} {@link Predicate#test(Object) deciding} if the producer can
   *        {@link #produce(GameAsset, Collection) produce} this recipe.
   * @param requirements - see {@link #getRequirements()}.
   */
  public GameRecipe(Supplier<A> assetFactory, GameDuration duration, Predicate<GameAsset<?>> producerRequirement, List<GameAssetRequirement> requirements) {

    super();
    this.assetFactory = assetFactory;
    this.duration = duration;
    this.producerRequirement = producerRequirement;
    this.requirements = Collections.unmodifiableList(requirements);
  }

  /**
   * @return the {@link GameDuration} needed to app
   */
  public GameDuration getDuration() {

    return this.duration;
  }

  /**
   * @return the requirements
   */
  public List<GameAssetRequirement> getRequirements() {

    return this.requirements;
  }

  /**
   * @param producer the {@link GameFigure} that acts as producer of the {@link GameAsset} to produce. The production
   *        will use its {@link GameFigure#getItems() items} as potential input for the recipe.
   * @return the {@link GameAssetProxy} {@link GameAssetProxy#getAsset() holding} the produced {@link GameAsset} or
   *         {@code null} if the production failed. The {@link GameAssetProxy} will be {@link GameAssetProxy#unwrap()
   *         unwrapped} after the required {@link #getDuration() duration} has passed.
   */
  public GameAssetProxy<A> produce(GameFigure producer) {

    return produce(producer, producer.getItems());
  }

  /**
   * @param producer the {@link GameAsset} (typically a {@link GameFigure}) that acts as producer of the
   *        {@link GameAsset} to produce.
   * @param requiredObjects the {@link Collection} of {@link GameAsset} that can be used for this recipe. They may be
   *        {@link GameAssetRequirement#isConsuming() consumed} (disposed) when the recipe is successfully applied.
   * @return the {@link GameAssetProxy} {@link GameAssetProxy#getAsset() holding} the produced {@link GameAsset} or
   *         {@code null} if the production failed. The {@link GameAssetProxy} will be {@link GameAssetProxy#unwrap()
   *         unwrapped} after the required {@link #getDuration() duration} has passed.
   */
  public GameAssetProxy<A> produce(GameAsset<?> producer, GameAsset<?>... requiredObjects) {

    return produce(producer, Arrays.asList(requiredObjects));
  }

  /**
   * @param producer the {@link GameAsset} (typically a {@link GameFigure}) that acts as producer of the
   *        {@link GameAsset} to produce.
   * @param requiredObjects the {@link Collection} of {@link GameAsset} that can be used for this recipe. They may be
   *        {@link GameAssetRequirement#isConsuming() consumed} (disposed) when the recipe is successfully applied.
   * @return the {@link GameAssetProxy} {@link GameAssetProxy#getAsset() holding} the produced {@link GameAsset} or
   *         {@code null} if the production failed. The {@link GameAssetProxy} will be {@link GameAssetProxy#unwrap()
   *         unwrapped} after the required {@link #getDuration() duration} has passed.
   */
  public GameAssetProxy<A> produce(GameAsset<?> producer, Collection<? extends GameAsset<?>> requiredObjects) {

    if (!this.producerRequirement.test(producer)) {
      return null;
    }

    Map<Class<? extends GameAssetType>, GameAssetTypeFulfillmentMap> fulfillmentMap = new HashMap<>(this.requirements.size());
    for (GameAssetRequirement requirement : this.requirements) {
      GameAssetType type = requirement.getAssetType();
      Class<? extends GameAssetType> typeClass = type.getClass();
      GameAssetTypeFulfillmentMap map = fulfillmentMap.computeIfAbsent(typeClass, x -> new GameAssetTypeFulfillmentMap());
      map.add(requirement);
    }

    for (GameAsset<?> object : requiredObjects) {
      GameType type = object.getType();
      GameAssetTypeFulfillmentMap map = fulfillmentMap.get(type.getClass());
      map.accept(object);
    }

    for (GameAssetTypeFulfillmentMap fulfillment : fulfillmentMap.values()) {
      if (!fulfillment.isFulfilled()) {
        return null;
      }
    }

    for (GameAssetTypeFulfillmentMap fulfillment : fulfillmentMap.values()) {
      fulfillment.consume();
    }

    A asset = this.assetFactory.get();
    return new GameAssetProxy<>(asset, this.duration);
  }

  private static class GameAssetTypeFulfillmentMap {

    private final Map<String, GameAssetRequirementFulfillment> typeId2FulfillmentMap;

    /**
     * The constructor.
     */
    public GameAssetTypeFulfillmentMap() {

      super();
      this.typeId2FulfillmentMap = new HashMap<>();
    }

    public void consume() {

      for (GameAssetRequirementFulfillment fulfillment : this.typeId2FulfillmentMap.values()) {
        fulfillment.consume();
      }
    }

    private boolean isFulfilled() {

      for (GameAssetRequirementFulfillment fulfillment : this.typeId2FulfillmentMap.values()) {
        if (!fulfillment.isFulfilled()) {
          return false;
        }
      }
      return true;
    }

    private void add(GameAssetRequirement requirement) {

      String id = requirement.getAssetType().getId();
      GameAssetRequirementFulfillment newFulfillment = new GameAssetRequirementFulfillment(requirement);
      GameAssetRequirementFulfillment fulfillment = this.typeId2FulfillmentMap.computeIfAbsent(id, (x) -> newFulfillment);
      if (fulfillment != newFulfillment) {
        fulfillment.append(newFulfillment);
      }
    }

    private boolean accept(GameAsset<?> object) {

      String id = object.getType().getId();
      GameAssetRequirementFulfillment fulfillment = this.typeId2FulfillmentMap.get(id);
      if ((fulfillment != null) && (fulfillment.accept(object))) {
        return true;
      }
      fulfillment = this.typeId2FulfillmentMap.get(GameType.ID_WILDCARD);
      if ((fulfillment != null) && (fulfillment.accept(object))) {
        return true;
      }
      return false;
    }

  }

  private static class GameAssetRequirementFulfillment {

    private final GameAssetRequirement requirement;

    private final List<GameAsset<?>> fulfillment;

    private GameAssetRequirementFulfillment next;

    /**
     * The constructor.
     *
     * @param requirement the {@link GameAssetRequirement}.
     */
    public GameAssetRequirementFulfillment(GameAssetRequirement requirement) {

      super();
      this.requirement = requirement;
      this.fulfillment = new ArrayList<>();
    }

    private void append(GameAssetRequirementFulfillment nextFulfillment) {

      if (this.next == null) {
        assert (this.requirement.getAssetType().equals(nextFulfillment.requirement.getAssetType()));
        this.next = nextFulfillment;
      } else {
        this.next.append(nextFulfillment);
      }
    }

    private boolean accept(GameAsset<?> object) {

      if (this.requirement.getQuantity() < this.fulfillment.size()) {
        if (this.requirement.isAccepting(object)) {
          this.fulfillment.add(object);
          return true;
        }
      }
      if (this.next != null) {
        return this.next.accept(object);
      }
      return false;
    }

    private boolean isFulfilled() {

      if (this.requirement.getQuantity() < this.fulfillment.size()) {
        // TODO send message: Can not produce because requirement is not met with details
        return false;
      }
      if (this.next != null) {
        return this.next.isFulfilled();
      }
      return true;
    }

    private void consume() {

      if (this.requirement.isConsuming()) {
        for (GameAsset<?> asset : this.fulfillment) {
          asset.setLocation(null);
        }
      }
    }

  }

}
