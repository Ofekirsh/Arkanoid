package sprites;
import java.util.ArrayList;
import java.util.List;

import biuoop.DrawSurface;
import interfaces.Sprite;

/**
 * The type Sprite collection.
 */
public class SpriteCollection {
    private final List<Sprite> spriteList;

    /**
     * Initializes a new sprite.
     */
    public SpriteCollection() {
        spriteList = new ArrayList<>();
    }

    /**
     * Add sprite.
     *
     * @param s (Sprite)
     */
    public void addSprite(Sprite s) {
        if (s != null) {
            spriteList.add(s);
        }
    }

    /**
     * Notify all sprite that time has passed.
     */
    public void notifyAllTimePassed() {
        List<Sprite> temp = new ArrayList<Sprite>(spriteList);
        for (Sprite s : temp) {
            if (s != null) {
                s.timePassed();
            }
        }
    }

    /**
     * Draw on all sprites.
     *
     * @param d (DrawSurface)
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite element : spriteList) {
            element.drawOn(d);
        }
    }

    /**
     * Remove sprite.
     *
     * @param s (Sprite)
     */
    public void removeSprite(Sprite s) {
        if (s != null) {
            spriteList.remove(s);
        }
    }
}