/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.ghosthopper.ui.fx.event;

import java.util.HashMap;
import java.util.Map;

import io.github.ghosthopper.event.GameKeyEvent;
import io.github.ghosthopper.event.GameKeyModifiers;
import io.github.ghosthopper.event.GameKeys;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * {@link #convertEvent(KeyEvent) Converter} from {@link KeyEvent} to {@link GameKeyEvent}.
 */
public class GameUiFxKeyEventMapper {

  private static final Map<KeyCode, Character> KEY_CODE_MAP = createKeyCodeMap();

  private GameUiFxKeyEventMapper() {}

  @SuppressWarnings("boxing")
  private static Map<KeyCode, Character> createKeyCodeMap() {

    Map<KeyCode, Character> map = new HashMap<>();
    map.put(KeyCode.ENTER, GameKeys.KEY_RETURN);
    map.put(KeyCode.BACK_SPACE, GameKeys.KEY_BACKSPACE);
    map.put(KeyCode.HOME, GameKeys.KEY_POS1);
    map.put(KeyCode.END, GameKeys.KEY_END);
    map.put(KeyCode.DELETE, GameKeys.KEY_DELETE);
    map.put(KeyCode.UP, GameKeys.KEY_UP);
    map.put(KeyCode.DOWN, GameKeys.KEY_DOWN);
    map.put(KeyCode.LEFT, GameKeys.KEY_LEFT);
    map.put(KeyCode.RIGHT, GameKeys.KEY_RIGHT);
    map.put(KeyCode.SCROLL_LOCK, GameKeys.KEY_SCROLL_LOCK);
    map.put(KeyCode.PRINTSCREEN, GameKeys.KEY_PRINT);
    map.put(KeyCode.PAUSE, GameKeys.KEY_PAUSE);
    map.put(KeyCode.ESCAPE, GameKeys.KEY_ESCAPE);
    map.put(KeyCode.TAB, GameKeys.KEY_TAB);
    map.put(KeyCode.F1, GameKeys.KEY_F1);
    map.put(KeyCode.F2, GameKeys.KEY_F2);
    map.put(KeyCode.F3, GameKeys.KEY_F3);
    map.put(KeyCode.F4, GameKeys.KEY_F4);
    map.put(KeyCode.F5, GameKeys.KEY_F5);
    map.put(KeyCode.F6, GameKeys.KEY_F6);
    map.put(KeyCode.F7, GameKeys.KEY_F7);
    map.put(KeyCode.F8, GameKeys.KEY_F8);
    map.put(KeyCode.F9, GameKeys.KEY_F9);
    map.put(KeyCode.F10, GameKeys.KEY_F10);
    map.put(KeyCode.F11, GameKeys.KEY_F11);
    map.put(KeyCode.F12, GameKeys.KEY_F12);
    return map;
  }

  /**
   * @param event the JavaFx {@link KeyEvent} to convert.
   * @return the converted {@link GameKeyEvent} or {@code null} to ignore the given {@link KeyEvent}.
   */
  public static GameKeyEvent convertEvent(KeyEvent event) {

    String text = event.getText();
    if ((text != null) && (text.length() == 1)) {
      return new GameKeyEvent(convertModifiers(event), text.charAt(0), true);
    } else {
      Character c = KEY_CODE_MAP.get(event.getCode());
      if (c != null) {
        return new GameKeyEvent(convertModifiers(event), c.charValue(), false);
      }
    }
    return null;
  }

  private static GameKeyModifiers convertModifiers(KeyEvent event) {

    return GameKeyModifiers.of(event.isControlDown(), event.isMetaDown(), event.isShiftDown());
  }

}
