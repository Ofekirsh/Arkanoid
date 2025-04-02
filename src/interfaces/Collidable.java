package interfaces;

import biuoop.DrawSurface;
import geometry.Point;
import geometry.Rectangle;
import geometry.Velocity;
import sprites.Ball;

/**
 * The interface Collidable.
 */
public interface Collidable {
    /**
     * Return the "collision shape" of the object.
     *
     * @return collision shape
     */
    Rectangle getCollisionRectangle();

    /**
     * Notify the object that we collided with it at collisionPoint with.
     *
     * @param collisionPoint  (Point)
     * @param currentVelocity (Velocity)
     * @param hitter (ball)
     * @return newVelocity (new velocity expected after the hit based on the force the object inflicted on us).
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);

    /**
     * Draw the shape.
     *
     * @param surface (DrawSurface)
     */
    void drawOn(DrawSurface surface);
}
