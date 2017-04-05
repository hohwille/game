/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.i18n;

import java.util.Locale;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import io.github.ghosthopper.PlayColor;
import io.github.ghosthopper.figure.PlayFigureType;
import io.github.ghosthopper.game.PlayGame;
import io.github.ghosthopper.item.PlayItemType;
import io.github.ghosthopper.player.Player;

/**
 * Test of {@link PlayTranslator} (indirect).
 */
public class PlayTranslatorTest extends Assertions {

  private static final PlayGame STRANGE_GAME = new PlayGame("Strange");

  private static final PlayFigureType MOUSE = new PlayFigureType("Mouse", 'M');

  private static final Player STRANGE_PLAYER = new Player(PlayColor.BLUE, MOUSE);

  static {
    STRANGE_PLAYER.setGame(STRANGE_GAME);
  }

  /** Tests with {@link Locale#ENGLISH}. */
  @Test
  public void testEnglish() {

    // given
    Locale locale = Locale.ENGLISH;

    // when + then
    assertThat(PlayItemType.KEY.getLocalizedName(locale)).isEqualTo("Key");
    assertThat(PlayItemType.GEM.getLocalizedName(locale)).isEqualTo("Gem");
    assertThat(PlayColor.RED.getLocalizedName(locale)).isEqualTo("Red");
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
    assertThat(PlayItemType.KEY.getLocalizedName(locale)).isEqualTo("Schlüssel");
    assertThat(PlayItemType.GEM.getLocalizedName(locale)).isEqualTo("Edelstein");
    assertThat(PlayColor.RED.getLocalizedName(locale)).isEqualTo("Rot");
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
    assertThat(PlayItemType.KEY.getLocalizedName(locale)).isEqualTo("Clé");
    assertThat(PlayItemType.GEM.getLocalizedName(locale)).isEqualTo("Gemme");
    assertThat(PlayColor.RED.getLocalizedName(locale)).isEqualTo("Rouge");
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
    assertThat(PlayItemType.KEY.getLocalizedName(locale)).isEqualTo("Llave");
    assertThat(PlayItemType.GEM.getLocalizedName(locale)).isEqualTo("Joya");
    assertThat(PlayColor.RED.getLocalizedName(locale)).isEqualTo("Rojo");
    assertThat(STRANGE_GAME.getLocalizedName(locale)).isEqualTo("Juego extraño");
    assertThat(STRANGE_PLAYER.getLocalizedName(locale)).isEqualTo("Jugador");
    assertThat(STRANGE_PLAYER.getFigures().get(0).getLocalizedName(locale)).isEqualTo("Extraño ratón");
  }

}
