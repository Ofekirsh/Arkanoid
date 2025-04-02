package game;
import java.awt.Color;

import biuoop.DrawSurface;
import interfaces.Animation;
import sprites.SpriteCollection;

/**
 * The type Countdown animation.
 */
public class CountdownAnimation implements Animation {
    private final SpriteCollection gameScreen;
    private double numSeconds;
    private int countFrom;
    private double counter;
    private boolean stop = false;
    private final int numFrames;

    /**
     * Instantiates a new Countdown animation.
     *
     * @param gameScreen the game screen
     * @param numSeconds the num seconds
     * @param countFrom  the count from
     */
    public CountdownAnimation(SpriteCollection gameScreen, double numSeconds, int countFrom) {
        this.gameScreen = gameScreen;
        this.numSeconds = numSeconds;
        this.countFrom = countFrom;
        counter = 0;
        numFrames = (int) this.numSeconds * 60 / countFrom;
    }

    /**
     * Do one frame.
     * @param d the d
     */
    public void doOneFrame(DrawSurface d) {
        gameScreen.drawAllOn(d);
        d.setColor(Color.black);
        d.drawText(d.getWidth() / 2, d.getHeight() / 2, String.valueOf(countFrom), 40);
        numSeconds /= 2;
        counter += 1;
        if (counter == numFrames) {
            countFrom -= 1;
            counter = 0;
        }
        if (countFrom == 0) {
            stop = true;
        }
    }

    @Override
    public boolean shouldStop() {
        return stop;
    }


}
