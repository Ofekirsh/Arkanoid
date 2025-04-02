package listeners;
// a BlockRemover is in charge of removing blocks from the game, as well as keeping count
// of the number of blocks that remain.

import game.GameLevel;
import sprites.Ball;
import sprites.Block;
import utils.Counter;

/**
 * The block remover type.
 */
public class BlockRemover implements HitListener {
    private final GameLevel gameLevel;
    private final Counter remainingBlocks;

    /**
     * Initialize game and remainingBlocks.
     *
     * @param gameLevel       (Game)
     * @param remainingBlocks (Counter)
     */
    public BlockRemover(GameLevel gameLevel, Counter remainingBlocks) {
        this.gameLevel = gameLevel;
        this.remainingBlocks = remainingBlocks;
    }

    /**
     * Hit event.
     * Blocks that are hit are removed from the game.
     *
     * @param beingHit (Block)
     * @param hitter   (Ball)
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        remainingBlocks.decrease(1);
        beingHit.removeFromGame(gameLevel);
    }
}
