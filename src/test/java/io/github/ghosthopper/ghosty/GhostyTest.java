package io.github.ghosthopper.ghosty;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import io.github.ghosthopper.PlayPlayer;
import io.github.ghosthopper.PlayWall;
import io.github.ghosthopper.PlayWallTypeGate;
import io.github.ghosthopper.PlayWallTypeWall;

public class GhostyTest extends Assertions {

  @Test
  public void testHole() {
    PlayPlayer mouse1 = new PlayPlayer(Ghosty.MOUSE);
    PlayPlayer rabbit1 = new PlayPlayer(Ghosty.RABBIT);
    PlayWall mouseHole1 = new PlayWall(null, Ghosty.MOUSE_HOME);
    PlayWall wall1 = new PlayWall(null, new PlayWallTypeWall());
    PlayWall gate1 = new PlayWall(null, new PlayWallTypeGate());

    assertThat(mouseHole1.getType().canPass(mouse1)).isTrue();
    assertThat(mouseHole1.getType().canPass(rabbit1)).isFalse();
    assertThat(wall1.getType().canPass(mouse1)).isFalse();
    assertThat(wall1.getType().canPass(rabbit1)).isFalse();
    assertThat(gate1.getType().canPass(mouse1)).isTrue();
    assertThat(gate1.getType().canPass(rabbit1)).isTrue();
  }

}
