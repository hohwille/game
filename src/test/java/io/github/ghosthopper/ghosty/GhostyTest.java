package io.github.ghosthopper.ghosty;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import io.github.ghosthopper.PlayBorder;
import io.github.ghosthopper.PlayBorderTypeGate;
import io.github.ghosthopper.PlayBorderTypeWall;
import io.github.ghosthopper.PlayFigureHuman;

public class GhostyTest extends Assertions {

  @Test
  public void testHole() {
    PlayFigureHuman mouse1 = new PlayFigureHuman(Ghosty.MOUSE);
    PlayFigureHuman rabbit1 = new PlayFigureHuman(Ghosty.RABBIT);
    PlayBorder mouseHole1 = new PlayBorder(null, Ghosty.MOUSE_HOME, null);
    PlayBorder wall1 = new PlayBorder(null, PlayBorderTypeWall.INSTANCE, null);
    PlayBorder gate1 = new PlayBorder(null, PlayBorderTypeGate.INSTANCE, null);

    assertThat(mouseHole1.canPass(mouse1)).isTrue();
    assertThat(mouseHole1.canPass(rabbit1)).isFalse();
    assertThat(wall1.canPass(mouse1)).isFalse();
    assertThat(wall1.canPass(rabbit1)).isFalse();
    assertThat(gate1.canPass(mouse1)).isTrue();
    assertThat(gate1.canPass(rabbit1)).isTrue();
  }

}
