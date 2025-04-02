package geometry;

import interfaces.Collidable;

/**
 * The type CollisionInfo.
 */
public class CollisionInfo {
    private final Point pointCollidable;
    private final Collidable collidable;

    /**
     * Initializes the variables.
     *
     * @param pointCollidable (Point)
     * @param collidable      (Collidable)
     */
    public CollisionInfo(Point pointCollidable, Collidable collidable) {
        this.collidable = collidable;
        this.pointCollidable = pointCollidable;
    }

    /**
     * Return the point at which the collision occurs.
     *
     * @return pointCollidable (Point)
     */
    public Point collisionPoint() {
        return pointCollidable;
    }

    /**
     * Return the collidable object involved in the collision.
     *
     * @return collidable (Collidable)
     */
    public Collidable collisionObject() {
        return collidable;
    }
}