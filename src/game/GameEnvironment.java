package game;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import biuoop.DrawSurface;
import geometry.CollisionInfo;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import interfaces.Collidable;

/**
 * The type Game environment.
 */
public class GameEnvironment {
    private final List<Collidable> collidableList = new ArrayList<Collidable>();

    /**
     * Add the given collidable to the environment.
     *
     * @param c (Collidable)
     */
    public void addCollidable(Collidable c) {
        if (c != null) {
            collidableList.add(c);
        }

    }

    /**
     * object moving from line.start() to line.end(). If this object will not collide with any of the collidables in
     * this collection, return null. Else, return the information about the closest collision that is going to occur.
     *
     * @param trajectory (Line)
     * @return CollisionInfo or null
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        int flag = 1;
        double bestDistance = 0, restDistance;
        Collidable closeElement = null;
        Point bestCollidPoint = null;
        for (Collidable element : collidableList) {
            Point collidPoint = trajectory.closestIntersectionToStartOfLine(element.getCollisionRectangle());
            if (flag == 1 && collidPoint != null) {
                flag = 0;
                bestDistance = trajectory.start().distance(collidPoint);
                bestCollidPoint = collidPoint;
                closeElement = element;
            } else if (collidPoint != null) {
                restDistance = trajectory.start().distance(collidPoint);
                if (restDistance < bestDistance) {
                    bestDistance = restDistance;
                    bestCollidPoint = collidPoint;
                    closeElement = element;
                }
            }
        }
        if (flag == 1) {
            return null;
        } else {
            return new CollisionInfo(bestCollidPoint, closeElement);
        }
    }

    /**
     * Draw on.
     *
     * @param surface (DrawSurface)
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(Color.black);
        for (Collidable element : collidableList) {
            element.drawOn(surface);
        }
    }

    /**
     * Check if the point in the collidable.
     *
     * @param point (Point)
     * @return collidable or null
     */
    public Collidable isPointInsideCollidable(Point point) {
        for (Collidable collidable : collidableList) {
            Rectangle rectangle = collidable.getCollisionRectangle();
            if (rectangle.getUpperLeft().getX() < point.getX()
                    && rectangle.getUpperLeft().getY() < point.getY()
                    && rectangle.getBottomRight().getX() > point.getX()
                    && rectangle.getBottomRight().getY() > point.getY()) {
                return collidable;
            }
        }
        return null;
    }

    /**
     * Check if the ball in the collidable.
     *
     * @param point (Point)
     * @return true or false (boolean)
     */
    public boolean isInCollidable(Point point) {
        for (Collidable element : collidableList) {
            if (element.getCollisionRectangle().getUpperLeft().getX() < point.getX()
                    && element.getCollisionRectangle().getUpperLeft().getX()
                    + element.getCollisionRectangle().getWidth() > point.getX()) {
                if (element.getCollisionRectangle().getUpperLeft().getY() < point.getY()
                        && element.getCollisionRectangle().getUpperLeft().getY()
                        + element.getCollisionRectangle().getHeight() > point.getY()) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * remove Collidable.
     *
     * @param c (Collidable)
     */
    public void removeCollidable(Collidable c) {
        if (c != null) {
            collidableList.remove(c);
        }
    }
}
