package sprites;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import biuoop.DrawSurface;
import game.GameLevel;
import geometry.Point;
import geometry.Rectangle;
import geometry.Velocity;
import interfaces.Collidable;
import interfaces.HitNotifier;
import interfaces.Sprite;
import listeners.HitListener;

/**
 * The type Block.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private final Rectangle rectangle;
    private final Color color;
    private final List<HitListener> hitListeners;

    /**
     * Initializes a new Block.
     *
     * @param rectangle (Rectangle)
     * @param color     (Color)
     */
    public Block(Rectangle rectangle, Color color) {
        this.rectangle = rectangle;
        this.color = color;
        this.hitListeners = new ArrayList<HitListener>();
    }

    /**
     * Return the rectangle.
     *
     * @return rectangle
     */
    public Rectangle getCollisionRectangle() {
        return rectangle;
    }

    /**
     * Notify the object that we collided with it at collisionPoint with.
     *
     * @param collisionPoint  (Point)
     * @param currentVelocity (Velocity)
     * @param hitter (ball)
     * @return newVelocity (new velocity expected after the hit based on the force the object inflicted on us).
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        // if touch in width of rectangle
        if (collisionPoint.getX() == rectangle.getUpperLeft().getX() || collisionPoint.getX()
                == rectangle.getUpperLeft().getX() + rectangle.getWidth()) {
            this.notifyHit(hitter);
            return new Velocity(-currentVelocity.getDx(), currentVelocity.getDy());
        }
        // if touch in height of rectangle.
        if (collisionPoint.getY() == rectangle.getUpperLeft().getY() || collisionPoint.getY()
                == rectangle.getUpperLeft().getY() + rectangle.getHeight()) {
            this.notifyHit(hitter);
            return new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
        }
        // if no hit
        return currentVelocity;
    }

    /**
     * Draw on the rectangle.
     *
     * @param surface (DrawSurface)
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(color);
        surface.fillRectangle((int) rectangle.getUpperLeft().getX(), (int) rectangle.getUpperLeft().getY(),
                (int) rectangle.getWidth(), (int) rectangle.getHeight());
        surface.setColor(Color.black);
        surface.drawRectangle((int) rectangle.getUpperLeft().getX(), (int) rectangle.getUpperLeft().getY(),
                (int) rectangle.getWidth(), (int) rectangle.getHeight());
    }

    /**
     * Time passed.
     */
    public void timePassed() {
    }

    /**
     * Add to game.
     *
     * @param gameLevel (GameLevel)
     */
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addSprite(this);
        gameLevel.addCollidable(this);
    }

    /**
     * Remove from game.
     *
     * @param gameLevel (GameLevel)
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeSprite(this);
        gameLevel.removeCollidable(this);
    }

    /**
     * Notify Hit.
     *
     * @param hitter (Ball)
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }

    }

    /**
     * Add hl as a listener to hit events.
     *
     * @param hl (HitListener)
     */
    public void addHitListener(HitListener hl) {
        if (hl != null) {
            hitListeners.add(hl);
        }
    }

    /**
     * Remove hl from the list of listeners to hit events.
     *
     * @param hl (HitListener)
     */
    public void removeHitListener(HitListener hl) {
        if (hl != null) {
            hitListeners.remove(hl);
        }
    }
}
