package levels;

import biuoop.DrawSurface;
import interfaces.Animation;
import utils.Counter;

/**
 * The type End screen.
 */
public class EndScreen implements Animation {

    private final Counter score;
    private final Counter lives;


    /**
     * Instantiates a new End screen.
     *
     * @param score the score
     * @param lives the lives
     */
    public EndScreen(Counter score, Counter lives) {
        this.score = score;
        this.lives = lives;
    }

    /**
     * Do one frame.
     * @param d the d
     */
    public void doOneFrame(DrawSurface d) {
        if (lives.getValue() >= 0) {
            d.drawText(250, d.getHeight() / 2, "You Win! Your score is " + score.getValue(), 32);
        } else {
            d.drawText(190, d.getHeight() / 2, "Game Over. Your score is " + score.getValue(), 32);
        }
    }

    /**
     * Should stop.
     * @return false (boolean)
     */
    public boolean shouldStop() {
        return false;
    }
}
