package sprites;
import biuoop.DrawSurface;
import game.GameLevel;
import interfaces.Sprite;
import utils.Counter;

/**
 * The type Score indicator.
 */
public class ScoreIndicator implements Sprite {
    private final Counter score;

    /**
     * Instantiates a new Score indicator.
     *
     * @param score (Counter)
     */
    public ScoreIndicator(Counter score) {
        this.score = score;
    }

    /**
     * Draw on.
     * draw the sprite to the screen.
     *
     * @param d the draw surface
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.drawText(350, 20, "the score: " + score.getValue(), 15);
    }

    /**
     * Time passed.
     * notify the sprite that the time has passed.
     */
    public void timePassed() {
    }

    /**
     * Add the sprite to a game.
     *
     * @param g (GameLevel)
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}