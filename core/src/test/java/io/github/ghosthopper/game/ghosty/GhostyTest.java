package io.github.ghosthopper.game.ghosty;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import io.github.ghosthopper.PlayColor;
import io.github.ghosthopper.PlayDirection;
import io.github.ghosthopper.Player;
import io.github.ghosthopper.border.PlayBorder;
import io.github.ghosthopper.border.PlayBorderTypeOpen;
import io.github.ghosthopper.border.PlayBorderTypeWall;
import io.github.ghosthopper.figure.PlayFigure;

public class GhostyTest extends Assertions {

  @Test
  public void testHole() {

    Player playerM1 = new Player("M1", Ghosty.MOUSE);
    playerM1.setColor(PlayColor.BLUE);
    PlayFigure mouse1 = playerM1.getFigures().get(0);
    Player playerR1 = new Player("R1", Ghosty.RABBIT);
    playerM1.setColor(PlayColor.BLUE);
    PlayFigure rabbit1 = playerR1.getFigures().get(0);
    PlayBorder mouseHole1 = new PlayBorder(null, PlayDirection.RIGHT, null, Ghosty.MOUSE_HOME);
    PlayBorder wall1 = new PlayBorder(null, PlayDirection.RIGHT, null, PlayBorderTypeWall.get());
    PlayBorder gate1 = new PlayBorder(null, PlayDirection.RIGHT, null, PlayBorderTypeOpen.get());

    assertThat(mouseHole1.canPass(mouse1)).isTrue();
    assertThat(mouseHole1.canPass(rabbit1)).isFalse();
    assertThat(wall1.canPass(mouse1)).isFalse();
    assertThat(wall1.canPass(rabbit1)).isFalse();
    assertThat(gate1.canPass(mouse1)).isTrue();
    assertThat(gate1.canPass(rabbit1)).isTrue();
  }

}
