/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.i18n;

import java.util.Locale;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import io.github.ghosthopper.color.PlayColor;
import io.github.ghosthopper.figure.PlayFigureType;
import io.github.ghosthopper.game.PlayGame;
import io.github.ghosthopper.item.PlayPickItemType;
import io.github.ghosthopper.move.PlayDirection;
import io.github.ghosthopper.player.Player;

/**
 * Test of {@link PlayTranslator} (indirect).
 */
public class PlayTranslatorTest extends Assertions {

  private static final PlayGame STRANGE_GAME = new PlayGame("Strange");

  private static final PlayFigureType MOUSE = new PlayFigureType("Mouse");

  private static final Player STRANGE_PLAYER = new Player(STRANGE_GAME, PlayColor.BLUE, MOUSE);

  /** Tests with {@link Locale#ENGLISH}. */
  @Test
  public void testEnglish() {

    // given
    Locale locale = Locale.ENGLISH;

    // when + then
    assertThat(PlayPickItemType.KEY.getLocalizedName(locale)).isEqualTo("Key");
    assertThat(PlayPickItemType.GEM.getLocalizedName(locale)).isEqualTo("Gem");
    assertThat(PlayColor.RED.getLocalizedName(locale)).isEqualTo("Red");
    assertThat(PlayDirection.NORTH.getLocalizedName(locale)).isEqualTo("North");
    assertThat(PlayDirection.SOUTH.getLocalizedName(locale)).isEqualTo("South");
    assertThat(PlayDirection.EAST.getLocalizedName(locale)).isEqualTo("East");
    assertThat(PlayDirection.WEST.getLocalizedName(locale)).isEqualTo("West");
    assertThat(PlayDirection.UP.getLocalizedName(locale)).isEqualTo("Up");
    assertThat(PlayDirection.DOWN.getLocalizedName(locale)).isEqualTo("Down");
    assertThat(PlayDirection.SOUTH_WEST.getLocalizedName(locale)).isEqualTo("South-West");
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
    assertThat(PlayPickItemType.KEY.getLocalizedName(locale)).isEqualTo("Schlüssel");
    assertThat(PlayPickItemType.GEM.getLocalizedName(locale)).isEqualTo("Edelstein");
    assertThat(PlayColor.RED.getLocalizedName(locale)).isEqualTo("Rot");
    assertThat(PlayDirection.NORTH.getLocalizedName(locale)).isEqualTo("Nord");
    assertThat(PlayDirection.SOUTH.getLocalizedName(locale)).isEqualTo("Süd");
    assertThat(PlayDirection.EAST.getLocalizedName(locale)).isEqualTo("Ost");
    assertThat(PlayDirection.WEST.getLocalizedName(locale)).isEqualTo("West");
    assertThat(PlayDirection.UP.getLocalizedName(locale)).isEqualTo("Hoch");
    assertThat(PlayDirection.DOWN.getLocalizedName(locale)).isEqualTo("Runter");
    assertThat(PlayDirection.SOUTH_WEST.getLocalizedName(locale)).isEqualTo("Süd-West");
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
    assertThat(PlayPickItemType.KEY.getLocalizedName(locale)).isEqualTo("Clé");
    assertThat(PlayPickItemType.GEM.getLocalizedName(locale)).isEqualTo("Gemme");
    assertThat(PlayColor.RED.getLocalizedName(locale)).isEqualTo("Rouge");
    assertThat(PlayDirection.NORTH.getLocalizedName(locale)).isEqualTo("Nord");
    assertThat(PlayDirection.SOUTH.getLocalizedName(locale)).isEqualTo("Sud");
    assertThat(PlayDirection.EAST.getLocalizedName(locale)).isEqualTo("Est");
    assertThat(PlayDirection.WEST.getLocalizedName(locale)).isEqualTo("Ouest");
    assertThat(PlayDirection.UP.getLocalizedName(locale)).isEqualTo("En haut");
    assertThat(PlayDirection.DOWN.getLocalizedName(locale)).isEqualTo("Vers le bas");
    assertThat(PlayDirection.SOUTH_WEST.getLocalizedName(locale)).isEqualTo("Sud-Ouest");
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
    assertThat(PlayPickItemType.KEY.getLocalizedName(locale)).isEqualTo("Llave");
    assertThat(PlayPickItemType.GEM.getLocalizedName(locale)).isEqualTo("Joya");
    assertThat(PlayColor.RED.getLocalizedName(locale)).isEqualTo("Rojo");
    assertThat(PlayDirection.NORTH.getLocalizedName(locale)).isEqualTo("Norte");
    assertThat(PlayDirection.SOUTH.getLocalizedName(locale)).isEqualTo("Sur");
    assertThat(PlayDirection.EAST.getLocalizedName(locale)).isEqualTo("Este");
    assertThat(PlayDirection.WEST.getLocalizedName(locale)).isEqualTo("Oeste");
    assertThat(PlayDirection.UP.getLocalizedName(locale)).isEqualTo("Hasta");
    assertThat(PlayDirection.DOWN.getLocalizedName(locale)).isEqualTo("Abajo");
    assertThat(PlayDirection.SOUTH_WEST.getLocalizedName(locale)).isEqualTo("Sur-Oeste");
    assertThat(STRANGE_GAME.getLocalizedName(locale)).isEqualTo("Juego extraño");
    assertThat(STRANGE_PLAYER.getLocalizedName(locale)).isEqualTo("Jugador");
    assertThat(STRANGE_PLAYER.getFigures().get(0).getLocalizedName(locale)).isEqualTo("Extraño ratón");
  }

}
