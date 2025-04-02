package sprites;
import java.awt.Color;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import game.GameLevel;
import geometry.Point;
import geometry.Rectangle;
import geometry.Velocity;
import interfaces.Collidable;
import interfaces.Sprite;

/**
 * The type Paddle.
 */
public class Paddle implements Sprite, Collidable {
    private static final int WIDTH_OF_BOUNDS = 25;
    private final biuoop.KeyboardSensor keyboard;
    private Rectangle rectangle;
    private final int speed;
    private final int size;

    /**
     * Initializes gui, rectangle and keyboard.
     *
     * @param keyboardSensor (KeyboardSensor)
     * @param speed (int)
     * @param size (int)
     */
    public Paddle(KeyboardSensor keyboardSensor, int speed, int size) {
        this.speed = speed;
        this.size = size;
        rectangle = new Rectangle(new Point((800 - size) / 2.0, 570), size, 20);
        keyboard = keyboardSensor;
    }

    /**
     * Move left (until the left limit).
     */
    public void moveLeft() {
        double x = rectangle.getUpperLeft().getX() - speed;
        if (x >= WIDTH_OF_BOUNDS) {
            rectangle = new Rectangle(new Point(x, rectangle.getUpperLeft().getY()), rectangle.getWidth(),
                    rectangle.getHeight());
        } else {
            rectangle = new Rectangle(new Point(WIDTH_OF_BOUNDS, rectangle.getUpperLeft().getY()),
                    size, 20);
        }
    }

    /**
     * Move right (until the right limit).
     */
    public void moveRight() {
        double x = rectangle.getUpperLeft().getX() + speed;
        if (x + rectangle.getWidth() <= 800 - WIDTH_OF_BOUNDS) {
            rectangle = new Rectangle(new Point(x, rectangle.getUpperLeft().getY()), rectangle.getWidth(),
                    rectangle.getHeight());
        } else {
            rectangle = new Rectangle(new Point(800 - WIDTH_OF_BOUNDS - rectangle.getWidth(),
                    rectangle.getUpperLeft().getY()), size, 20);
        }
    }

    /**
     * Time passed.
     */
    public void timePassed() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        } else if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }
    }

    /**
     * Draw on.
     *
     * @param d (DrawSurface)
     */
    public void drawOn(DrawSurface d) {
        d.setColor(Color.YELLOW);
        d.fillRectangle((int) rectangle.getUpperLeft().getX(), (int) rectangle.getUpperLeft().getY(),
                (int) rectangle.getWidth(), (int) rectangle.getHeight());
    }

    /**
     * Return collision rectangle.
     *
     * @return rectangle (Rectangle)
     */
    public Rectangle getCollisionRectangle() {
        return rectangle;
    }

    /**
     * Hit velocity.
     *
     * @param collisionPoint  (Point)
     * @param currentVelocity (Velocity)
     * @param hitter (ball)
     * @return velocity (Velocity)
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        Velocity velocity = getAngleFromHit(collisionPoint, currentVelocity);
        // if touch in width of rectangle
        if (collisionPoint.getX() == rectangle.getUpperLeft().getX() || collisionPoint.getX()
                == rectangle.getUpperLeft().getX() + rectangle.getWidth()) {
            return new Velocity(-currentVelocity.getDx(), currentVelocity.getDy());
        }
        // if touch in height of rectangle.
        if (collisionPoint.getY() == rectangle.getUpperLeft().getY() || collisionPoint.getY()
                == rectangle.getUpperLeft().getY() + rectangle.getHeight()) {
            return velocity;
        }
        // if no hit
        return currentVelocity;
    }

    /**
     * Add paddle to the game.
     *
     * @param g (GameLevel)
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * The behavior of the ball's bounce depends on where it hits the paddle. Let's denote the left-most region as 1
     * and the rightmost as 5 (so the middle region is 3). If the ball hits the middle region (region 3), it keeps its
     * horizontal direction and only changes its vertical one (like when hitting a block). However, if we hit region 1,
     * the ball bounces back with an angle of 300 degrees (-60), regardless of where it came from. Similarly, for
     * region 2 it should bounce back 330 degrees, for region 4 it should bounce in 30 degrees, and for region 5 in 60
     * degrees.
     *
     * @param collosionPoint (Point)
     * @param velocity       (Velocity)
     * @return newVelocity (Velocity)
     */
    public Velocity getAngleFromHit(Point collosionPoint, Velocity velocity) {
        double dis = rectangle.getWidth() / 5;
        double x = rectangle.getUpperLeft().getX();
        double regin = collosionPoint.getX();
        double speed = Math.sqrt(Math.pow(velocity.getDx(), 2) + Math.pow(velocity.getDy(), 2));
        if (regin <= x + dis) {
            return Velocity.fromAngleAndSpeed(300, speed);
        }
        if (regin < x + dis * 2) {
            return Velocity.fromAngleAndSpeed(330, speed);
        }
        if (regin < x + dis * 3) {
            return new Velocity(velocity.getDx(), -velocity.getDy());
        }
        if (regin < x + dis * 4) {
            return Velocity.fromAngleAndSpeed(30, speed);
        }
        if (regin < x + dis * 5) {
            return Velocity.fromAngleAndSpeed(60, speed);
        }
        return velocity;
    }

}