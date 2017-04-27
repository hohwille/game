/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.ui.fx;

import java.util.HashMap;
import java.util.Map;

import io.github.ghosthopper.event.PlayKeyEvent;
import io.github.ghosthopper.event.PlayKeyModifiers;
import io.github.ghosthopper.event.PlayKeys;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * {@link #convertEvent(KeyEvent) Converter} from {@link KeyEvent} to {@link PlayKeyEvent}.
 */
public class PlayUiFxKeyEventMapper {

  private static final Map<KeyCode, Character> KEY_CODE_MAP = createKeyCodeMap();

  private PlayUiFxKeyEventMapper() {}

  @SuppressWarnings("boxing")
  private static Map<KeyCode, Character> createKeyCodeMap() {

    Map<KeyCode, Character> map = new HashMap<>();
    map.put(KeyCode.ENTER, PlayKeys.KEY_RETURN);
    map.put(KeyCode.BACK_SPACE, PlayKeys.KEY_BACKSPACE);
    map.put(KeyCode.HOME, PlayKeys.KEY_POS1);
    map.put(KeyCode.END, PlayKeys.KEY_END);
    map.put(KeyCode.DELETE, PlayKeys.KEY_DELETE);
    map.put(KeyCode.UP, PlayKeys.KEY_UP);
    map.put(KeyCode.DOWN, PlayKeys.KEY_DOWN);
    map.put(KeyCode.LEFT, PlayKeys.KEY_LEFT);
    map.put(KeyCode.RIGHT, PlayKeys.KEY_RIGHT);
    map.put(KeyCode.SCROLL_LOCK, PlayKeys.KEY_SCROLL_LOCK);
    map.put(KeyCode.PRINTSCREEN, PlayKeys.KEY_PRINT);
    map.put(KeyCode.PAUSE, PlayKeys.KEY_PAUSE);
    map.put(KeyCode.ESCAPE, PlayKeys.KEY_ESCAPE);
    map.put(KeyCode.TAB, PlayKeys.KEY_TAB);
    map.put(KeyCode.F1, PlayKeys.KEY_F1);
    map.put(KeyCode.F2, PlayKeys.KEY_F2);
    map.put(KeyCode.F3, PlayKeys.KEY_F3);
    map.put(KeyCode.F4, PlayKeys.KEY_F4);
    map.put(KeyCode.F5, PlayKeys.KEY_F5);
    map.put(KeyCode.F6, PlayKeys.KEY_F6);
    map.put(KeyCode.F7, PlayKeys.KEY_F7);
    map.put(KeyCode.F8, PlayKeys.KEY_F8);
    map.put(KeyCode.F9, PlayKeys.KEY_F9);
    map.put(KeyCode.F10, PlayKeys.KEY_F10);
    map.put(KeyCode.F11, PlayKeys.KEY_F11);
    map.put(KeyCode.F12, PlayKeys.KEY_F12);
    return map;
  }

  /**
   * @param event the JavaFx {@link KeyEvent} to convert.
   * @return the converted {@link PlayKeyEvent} or {@code null} to ignore the given {@link KeyEvent}.
   */
  public static PlayKeyEvent convertEvent(KeyEvent event) {

    String text = event.getText();
    if ((text != null) && (text.length() == 1)) {
      return new PlayKeyEvent(convertModifiers(event), text.charAt(0), true);
    } else {
      Character c = KEY_CODE_MAP.get(event.getCode());
      if (c != null) {
        return new PlayKeyEvent(convertModifiers(event), c.charValue(), false);
      }
    }
    return null;
  }

  private static PlayKeyModifiers convertModifiers(KeyEvent event) {

    return PlayKeyModifiers.of(event.isControlDown(), event.isMetaDown(), event.isShiftDown());
  }

}
