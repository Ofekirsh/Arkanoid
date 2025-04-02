package game;

import biuoop.DrawSurface;
import interfaces.Animation;

/**
 * The type Pause screen.
 */
public class PauseScreen implements Animation {
    /**
     * Do one frame.
     * @param d the d
     */
    public void doOneFrame(DrawSurface d) {
        d.drawText(150, d.getHeight() / 2, "paused -- press space to continue", 32);
    }

    /**
     * Should stop.
     * @return boolean
     */
    public boolean shouldStop() {
        return false;
    }
}