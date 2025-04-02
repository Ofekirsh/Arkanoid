package sprites;

import biuoop.DrawSurface;
import game.GameEnvironment;
import game.GameLevel;
import geometry.ArithmeticOnDouble;
import geometry.CollisionInfo;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import geometry.Velocity;
import interfaces.Collidable;
import interfaces.Sprite;

/**
 * The type ball.
 */
public class Ball implements Sprite {
    private Point center;
    private final int r;
    private final java.awt.Color color;
    private Velocity v;
    private int startCoordinate;
    private int endCoordinate;
    private GameEnvironment gameEnvironment;

    /**
     * Initialize center,r and color of this ball.
     *
     * @param center (Point)
     * @param r      (int)
     * @param color  (color)
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.center = center;
        this.r = Math.abs(r);
        this.color = color;
    }

    /**
     * Initialize gameEnvironment.
     *
     * @param gameEnvironment (GameEnvironment)
     */
    public void setGameEnvironment(GameEnvironment gameEnvironment) {
        this.gameEnvironment = gameEnvironment;
    }

    /**
     * Return gameEnvironment.
     *
     * @return gameEnvironment (GameEnvironment)
     */
    public GameEnvironment getGameEnvironment() {
        return gameEnvironment;
    }

    /**
     * Return x value of the center ball in casting to int.
     *
     * @return int
     */
    public int getX() {
        return (int) Math.round(center.getX());
    }

    /**
     * Return y value of the center ball in casting to int.
     *
     * @return int
     */
    public int getY() {
        return (int) Math.round(center.getY());
    }

    /**
     * Return the radius.
     *
     * @return int
     */
    public int getSize() {
        return r;
    }

    /**
     * Return color of this color.
     *
     * @return color (color)
     */
    public java.awt.Color getColor() {
        return color;
    }

    /**
     * Return center.
     *
     * @return center (Point)
     */
    public Point getCenter() {
        return new Point(center.getX(), center.getY());
    }

    /**
     * Draw the ball on the given DrawSurface.
     *
     * @param surface (DrawSurface)
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(getColor());
        surface.fillCircle(getX(), getY(), getSize());
    }

    /**
     * Initialize the velocity.
     *
     * @param v (Velocity)
     */
    public void setVelocity(Velocity v) {
        this.v = v;
    }

    /**
     * Initialize the velocity.
     *
     * @param dx (double)
     * @param dy (double)
     */
    public void setVelocity(double dx, double dy) {
        v = new Velocity(dx, dy);
    }

    /**
     * Return velocity of this velocity.
     *
     * @return v (Velocity)
     */
    public Velocity getVelocity() {
        return v;
    }

    /**
     * Initialize the startCoordinate.
     *
     * @param startCoordinate (int)
     */
    public void setStartCoordinate(int startCoordinate) {
        this.startCoordinate = startCoordinate;
    }

    /**
     * Initialize the endCoordinate.
     *
     * @param endCoordinate (int)
     */
    public void setEndCoordinate(int endCoordinate) {
        this.endCoordinate = endCoordinate;
    }

    /**
     * Move one step without collision of the ball.
     */
    public void moveOneStepWithoutCollision() {
        Point newCenter;
        Velocity temp = new Velocity(v.getDx(), v.getDy());
        // in the first time center is out of range the board in right X-axis
        if (center.getX() + r > endCoordinate) {
            newCenter = new Point(endCoordinate - r, center.getY());
            center = newCenter;
        }
        // in the first time center is out of range the board in down Y-axis
        if (center.getY() + r > endCoordinate) {
            newCenter = new Point(center.getX(), endCoordinate - r);
            center = newCenter;
        }
        // in the first time center is out of range the board in left X-axis
        if (center.getX() - r < startCoordinate) {
            newCenter = new Point(startCoordinate + r, center.getY());
            center = newCenter;
        }
        // in the first time center is out of range the board in top Y-axis
        if (center.getY() - r < startCoordinate) {
            newCenter = new Point(center.getX(), endCoordinate - r);
            center = newCenter;
        }
        // check if the ball out of range in right X-axis
        if (center.getX() + r + v.getDx() >= endCoordinate) {
            double dx1 = center.getX() + r + v.getDx() - endCoordinate;
            setVelocity(dx1, v.getDy());
            center = getVelocity().applyToPoint(center);
            setVelocity(-temp.getDx(), temp.getDy());
        }
        // check if the ball out of range in left X-axis
        if (center.getX() - r + v.getDx() <= startCoordinate) {
            double dx1 = center.getX() - r - startCoordinate;
            setVelocity(dx1, v.getDy());
            center = getVelocity().applyToPoint(center);
            setVelocity(-temp.getDx(), temp.getDy());
        }
        // check if the ball out of range in down Y-axis.
        if (center.getY() + r + v.getDy() >= endCoordinate) {
            double dy1 = center.getY() + r + v.getDy() - endCoordinate;
            setVelocity(v.getDx(), dy1);
            center = getVelocity().applyToPoint(center);
            setVelocity(temp.getDx(), -(temp.getDy()));
        }
        // check if the ball out of range in top Y-axis
        if (center.getY() - r + v.getDy() <= startCoordinate) {
            double dy1 = center.getY() - r - startCoordinate;
            setVelocity(v.getDx(), dy1);
            center = getVelocity().applyToPoint(center);
            setVelocity(temp.getDx(), -(temp.getDy()));
        }
        center = getVelocity().applyToPoint(center);
    }

    /**
     * Move one step.
     */
    public void moveOneStep() {
        CollisionInfo collisionInfo = gameEnvironment.getClosestCollision(calculatePath());
        if (collisionInfo == null) {
            moveOneStepWithoutCollision();
        } else {
            Point upperLeft = collisionInfo.collisionObject().getCollisionRectangle().getUpperLeft();
            Point collisionPoint = collisionInfo.collisionPoint();
            setVelocity(collisionInfo.collisionObject().hit(this, collisionPoint, getVelocity()));
            if (upperLeft.getX() == collisionPoint.getX()) {
                center = this.getVelocity().applyToPoint(new Point(collisionPoint.getX() - getSize(),
                        collisionPoint.getY()));
            } else if (upperLeft.getY() == collisionPoint.getY()) {
                center = this.getVelocity().applyToPoint(new Point(collisionPoint.getX(),
                        collisionPoint.getY() - getSize()));
            } else if (upperLeft.getX() + collisionInfo.collisionObject().getCollisionRectangle().getWidth()
                    == collisionPoint.getX()) {
                center = this.getVelocity().applyToPoint(new Point(collisionPoint.getX() + getSize(),
                        collisionPoint.getY()));
            } else if (upperLeft.getY() + collisionInfo.collisionObject().getCollisionRectangle().getHeight()
                    == collisionPoint.getY()) {
                center = this.getVelocity().applyToPoint(new Point(collisionPoint.getX(),
                        collisionPoint.getY() + getSize()));
            }
            // in case came to the corner
            if (gameEnvironment.isInCollidable(center)) {
                setVelocity(-v.getDx(), -v.getDy());
                this.center = this.getVelocity().applyToPoint(this.center);
            }
        }

    }

    /**
     * Calculate trajectory.
     *
     * @return trajectory (Line)
     */
    public Line calculatePath() {
        return new Line(center, new Point(center.getX() + v.getDx(), center.getY() + v.getDy()));
    }

    /**
     * Time passed.
     */
    public void timePassed() {
        getBallOutOfThePaddle();
        moveOneStep();
    }

    /**
     * Add to game.
     *
     * @param game (Game)
     */
    public void addToGame(GameLevel game) {
        game.addSprite(this);
    }

    /**
     * Get out the ball from the paddle.
     */
    private void getBallOutOfThePaddle() {
        // the only option here is if the ball is inside the paddle
        Collidable collidable = gameEnvironment.isPointInsideCollidable(getCenter());
        if (collidable == null) {
            return;
        }
        Rectangle rec = collidable.getCollisionRectangle();
        final int startFrameX = 50;
        final int endFrameX = 750;
        final int endFrameY = 550;
        //check if the paddle is closed the ball in the leftBound and fix him.
        if (ArithmeticOnDouble.equal(startFrameX, rec.getUpperLeft().getX())) {
            center = new Point(center.getX() + getSize(), endFrameY - rec.getHeight() - getSize());
            return;
        }
        //check if the paddle is closed the ball in the rightBound and fix him.
        if (ArithmeticOnDouble.equal(endFrameX, collidable.getCollisionRectangle().getBottomRight().getX())) {
            center = new Point(center.getX() - getSize(), endFrameY - rec.getHeight() - getSize());
            return;
        }
        double distanceLeft = center.getX() - rec.getUpperLeft().getX();
        double distanceRight = rec.getBottomRight().getX() - center.getX();
        double minDistance = Math.min(distanceLeft, distanceRight);
        //if the ball got inside from the left side of the paddle
        if (ArithmeticOnDouble.equal(distanceLeft, minDistance)) {
            setVelocity(-Math.abs(getVelocity().getDx()), getVelocity().getDy());
            center = new Point(center.getX() - minDistance, center.getY());
        } else {
            //if the ball got inside from the right side of the paddle
            setVelocity(Math.abs(getVelocity().getDx()), getVelocity().getDy());
            center = new Point(center.getX() + minDistance, center.getY());
        }
    }

    /**
     * Remove from game.
     *
     * @param gameLevel (GameLevel)
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeSprite(this);
    }
}
