package listeners;
import game.GameLevel;
import sprites.Ball;
import sprites.Block;
import utils.Counter;

/**
 * The type Ball remover.
 */
public class BallRemover implements HitListener {
    private final GameLevel gameLevel;
    private final Counter counter;

    /**
     * Instantiates a new Ball remover.
     *
     * @param gameLevel (GameLevel)
     * @param counter   (Counter)
     */
    public BallRemover(GameLevel gameLevel, Counter counter) {
        this.gameLevel = gameLevel;
        this.counter = counter;
    }

    /**
     * Hit event.
     * This method is called whenever the beingHit object is hit.
     * The hitter parameter is the Ball that's doing the hitting.
     *
     * @param beingHit (Block)
     * @param hitter   (Ball)
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        counter.decrease(1);
        hitter.removeFromGame(gameLevel);
    }
}