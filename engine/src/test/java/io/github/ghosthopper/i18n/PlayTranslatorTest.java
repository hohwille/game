/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.i18n;

import java.util.Locale;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import io.github.ghosthopper.item.PlayItemType;

/**
 * Test of {@link PlayTranslator} (indirect).
 */
public class PlayTranslatorTest extends Assertions {

  /**
   * Test of {@link PlayItemType#getLocalizedName()}.
   */
  @Test
  public void testItems() {

    Locale locale;
    locale = Locale.ENGLISH;
    assertThat(PlayItemType.KEY.getLocalizedName(locale)).isEqualTo("Key");

    locale = Locale.GERMAN;
    assertThat(PlayItemType.KEY.getLocalizedName(locale)).isEqualTo("Schlüssel");

    locale = Locale.FRENCH;
    assertThat(PlayItemType.KEY.getLocalizedName(locale)).isEqualTo("Clé");

    locale = new Locale("es");
    assertThat(PlayItemType.KEY.getLocalizedName(locale)).isEqualTo("Llave");
  }

}
