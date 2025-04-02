package listeners;
import sprites.Ball;
import sprites.Block;
import utils.Counter;

/**
 * The score tracking listener.
 */
public class ScoreTrackingListener implements HitListener {
    private final Counter currentScore;

    /**
     * Initialize a score counter.
     *
     * @param scoreCounter (Counter)
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * Hit event.
     *
     * @param beingHit (Block)
     * @param hitter   (Ball)
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        currentScore.increase(5);
    }
}