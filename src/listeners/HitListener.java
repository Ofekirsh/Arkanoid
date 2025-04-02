package listeners;
import sprites.Ball;
import sprites.Block;

/**
 * The Hit Listener interface.
 */
public interface HitListener {
    /**
     * Hit event.
     * This method is called whenever the beingHit object is hit.
     * The hitter parameter is the Ball that's doing the hitting.
     *
     * @param beingHit (Block)
     * @param hitter   (Ball)
     */
    void hitEvent(Block beingHit, Ball hitter);
}

