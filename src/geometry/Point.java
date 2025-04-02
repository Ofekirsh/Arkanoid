package geometry;

/**
 * The type Point.
 */
public class Point {
    private final double x;
    private final double y;

    /**
     * Initializes the variables.
     *
     * @param x (double)
     * @param y (double)
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Calculate distance of this point to the other point.
     *
     * @param other (Point) another point
     * @return distance (double)
     */
    public double distance(Point other) {
        double x1 = this.x, y1 = this.y;
        double x2 = other.getX(), y2 = other.getY();
        return Math.sqrt(Math.pow((x1 - x2), 2) + Math.pow((y1 - y2), 2));
    }

    /**
     * Return true is the points are equal, false otherwise.
     *
     * @param other (Point)
     * @return true or false (boolean)
     */
    public boolean equals(Point other) {
        return this.x == other.getX() && this.y == other.getY();
    }

    /**
     * Return the x value of this point.
     *
     * @return x (double)
     */
    public double getX() {
        return x;
    }

    /**
     * Return the y value of this point.
     *
     * @return y (double)
     */
    public double getY() {
        return y;
    }
}
