package interfaces;

import biuoop.DrawSurface;
import game.GameLevel;

/**
 * The interface Sprite.
 */
public interface Sprite {
    /**
     * Draw the sprite to the screen.
     *
     * @param d (DrawSurface)
     */
    void drawOn(DrawSurface d);

    /**
     * Notify the sprite that time has passed.
     */
    void timePassed();

    /**
     * Add to game.
     * @param gameLevel (GameLevel)
     */
    void addToGame(GameLevel gameLevel);
}
