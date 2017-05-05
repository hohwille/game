/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.i18n;

import java.util.Locale;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import io.github.ghosthopper.color.GameColor;
import io.github.ghosthopper.direction.GameDirection;
import io.github.ghosthopper.figure.GameFigureType;
import io.github.ghosthopper.game.Game;
import io.github.ghosthopper.item.GamePickItemType;
import io.github.ghosthopper.player.GamePlayer;

/**
 * Test of {@link GameTranslator} (indirect).
 */
public class GameTranslatorTest extends Assertions {

  private static final Game STRANGE_GAME = new Game("Strange");

  private static final GameFigureType MOUSE = new GameFigureType("Mouse");

  private static final GamePlayer STRANGE_PLAYER = new GamePlayer(STRANGE_GAME, GameColor.BLUE, MOUSE);

  /** Tests with {@link Locale#ENGLISH}. */
  @Test
  public void testEnglish() {

    // given
    Locale locale = Locale.ENGLISH;

    // when + then
    assertThat(GamePickItemType.KEY.getLocalizedName(locale)).isEqualTo("Key");
    assertThat(GamePickItemType.GEM.getLocalizedName(locale)).isEqualTo("Gem");
    assertThat(GameColor.RED.getLocalizedName(locale)).isEqualTo("Red");
    assertThat(GameDirection.NORTH.getLocalizedName(locale)).isEqualTo("North");
    assertThat(GameDirection.SOUTH.getLocalizedName(locale)).isEqualTo("South");
    assertThat(GameDirection.EAST.getLocalizedName(locale)).isEqualTo("East");
    assertThat(GameDirection.WEST.getLocalizedName(locale)).isEqualTo("West");
    assertThat(GameDirection.UP.getLocalizedName(locale)).isEqualTo("Up");
    assertThat(GameDirection.DOWN.getLocalizedName(locale)).isEqualTo("Down");
    assertThat(GameDirection.SOUTH_WEST.getLocalizedName(locale)).isEqualTo("South-West");
    assertThat(STRANGE_GAME.getLocalizedName(locale)).isEqualTo("Strange Game");
    assertThat(STRANGE_PLAYER.getLocalizedName(locale)).isEqualTo("Player");
    assertThat(STRANGE_PLAYER.getFigures().get(0).getLocalizedName(locale)).isEqualTo("Strange Mouse");
  }

  /** Tests with {@link Locale#GERMAN}. */
  @Test
  public void testGerman() {

    // given
    Locale locale = Locale.GERMAN;

    // when + then
    assertThat(GamePickItemType.KEY.getLocalizedName(locale)).isEqualTo("Schlüssel");
    assertThat(GamePickItemType.GEM.getLocalizedName(locale)).isEqualTo("Edelstein");
    assertThat(GameColor.RED.getLocalizedName(locale)).isEqualTo("Rot");
    assertThat(GameDirection.NORTH.getLocalizedName(locale)).isEqualTo("Nord");
    assertThat(GameDirection.SOUTH.getLocalizedName(locale)).isEqualTo("Süd");
    assertThat(GameDirection.EAST.getLocalizedName(locale)).isEqualTo("Ost");
    assertThat(GameDirection.WEST.getLocalizedName(locale)).isEqualTo("West");
    assertThat(GameDirection.UP.getLocalizedName(locale)).isEqualTo("Hoch");
    assertThat(GameDirection.DOWN.getLocalizedName(locale)).isEqualTo("Runter");
    assertThat(GameDirection.SOUTH_WEST.getLocalizedName(locale)).isEqualTo("Süd-West");
    assertThat(STRANGE_GAME.getLocalizedName(locale)).isEqualTo("Merkwürdiges Spiel");
    assertThat(STRANGE_PLAYER.getLocalizedName(locale)).isEqualTo("Spieler");
    assertThat(STRANGE_PLAYER.getFigures().get(0).getLocalizedName(locale)).isEqualTo("Merkwürdige Maus");
  }

  /** Tests with {@link Locale#FRENCH}. */
  @Test
  public void testFrench() {

    // given
    Locale locale = Locale.FRENCH;

    // when + then
    assertThat(GamePickItemType.KEY.getLocalizedName(locale)).isEqualTo("Clé");
    assertThat(GamePickItemType.GEM.getLocalizedName(locale)).isEqualTo("Gemme");
    assertThat(GameColor.RED.getLocalizedName(locale)).isEqualTo("Rouge");
    assertThat(GameDirection.NORTH.getLocalizedName(locale)).isEqualTo("Nord");
    assertThat(GameDirection.SOUTH.getLocalizedName(locale)).isEqualTo("Sud");
    assertThat(GameDirection.EAST.getLocalizedName(locale)).isEqualTo("Est");
    assertThat(GameDirection.WEST.getLocalizedName(locale)).isEqualTo("Ouest");
    assertThat(GameDirection.UP.getLocalizedName(locale)).isEqualTo("En haut");
    assertThat(GameDirection.DOWN.getLocalizedName(locale)).isEqualTo("Vers le bas");
    assertThat(GameDirection.SOUTH_WEST.getLocalizedName(locale)).isEqualTo("Sud-Ouest");
    assertThat(STRANGE_GAME.getLocalizedName(locale)).isEqualTo("Jeu étrange");
    assertThat(STRANGE_PLAYER.getLocalizedName(locale)).isEqualTo("Joueur");
    assertThat(STRANGE_PLAYER.getFigures().get(0).getLocalizedName(locale)).isEqualTo("Souris étrange");
  }

  /** Tests with Spanish {@link Locale}. */
  @Test
  public void testSpanish() {

    // given
    Locale locale = new Locale("es");

    // when + then
    assertThat(GamePickItemType.KEY.getLocalizedName(locale)).isEqualTo("Llave");
    assertThat(GamePickItemType.GEM.getLocalizedName(locale)).isEqualTo("Joya");
    assertThat(GameColor.RED.getLocalizedName(locale)).isEqualTo("Rojo");
    assertThat(GameDirection.NORTH.getLocalizedName(locale)).isEqualTo("Norte");
    assertThat(GameDirection.SOUTH.getLocalizedName(locale)).isEqualTo("Sur");
    assertThat(GameDirection.EAST.getLocalizedName(locale)).isEqualTo("Este");
    assertThat(GameDirection.WEST.getLocalizedName(locale)).isEqualTo("Oeste");
    assertThat(GameDirection.UP.getLocalizedName(locale)).isEqualTo("Hasta");
    assertThat(GameDirection.DOWN.getLocalizedName(locale)).isEqualTo("Abajo");
    assertThat(GameDirection.SOUTH_WEST.getLocalizedName(locale)).isEqualTo("Sur-Oeste");
    assertThat(STRANGE_GAME.getLocalizedName(locale)).isEqualTo("Juego extraño");
    assertThat(STRANGE_PLAYER.getLocalizedName(locale)).isEqualTo("Jugador");
    assertThat(STRANGE_PLAYER.getFigures().get(0).getLocalizedName(locale)).isEqualTo("Extraño ratón");
  }

}
