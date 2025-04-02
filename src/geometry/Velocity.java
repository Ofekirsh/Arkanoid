package geometry;

/**
 * The type velocity.
 */
public class Velocity {
    private final double dx;
    private final double dy;

    /**
     * Initializes the variables.
     *
     * @param dx (double) how much to move on 'x' axes.
     * @param dy (double) how much to move on 'y' axes.
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * return dx of this velocity.
     *
     * @return dx
     */
    public double getDx() {
        return dx;
    }

    /**
     * return dy of this velocity.
     *
     * @return dy
     */
    public double getDy() {
        return dy;
    }

    /**
     * Take a point with position (x,y) and return a new point with position (x+dx, y+dy).
     *
     * @param p (how much to move)
     * @return Point
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + dx, p.getY() + dy);
    }

    /**
     * Change the position from angle and speed.
     *
     * @param angle (angle of ball)
     * @param speed (speed of ball)
     * @return Velocity
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double radians = Math.toRadians(angle - 90);
        double dx = Math.cos(radians) * speed;
        double dy = Math.sin(radians) * speed;
        return new Velocity(dx, dy);
    }
}
