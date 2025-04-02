package geometry;
import java.util.List;

/**
 * The type Line.
 */
public class Line {
    private final Point start;
    private final Point end;

    /**
     * Initializes the variables that start is bigger than end in x value and if equal in x value then
     * check y value.
     *
     * @param start (Point)
     * @param end   (Point)
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * Initializes the variables that start is bigger than end in x value and if equal in x value then
     * check y value.
     *
     * @param x1 (double)
     * @param y1 (double)
     * @param x2 (double)
     * @param y2 (double)
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     * Return the length of the line.
     *
     * @return length (double)
     */
    public double length() {
        return start.distance(end);
    }

    /**
     * Returns the middle point of the line.
     *
     * @return midPoint (Point)
     */
    public Point middle() {
        double x1 = this.start.getX(), y1 = this.start.getY();
        double x2 = this.end.getX(), y2 = this.end.getY();
        double xMid = (x1 + x2) / 2;
        double yMid = (y1 + y2) / 2;
        return new Point(xMid, yMid);
    }

    /**
     * Returns the start point of the line.
     *
     * @return start (Point)
     */
    public Point start() {
        return start;
    }

    /**
     * Returns the end point of the line.
     *
     * @return end (Point)
     */
    public Point end() {
        return end;
    }

    /**
     * Returns true if the lines intersect (out of the lines are coincide), false otherwise.
     * Using a known algorithm to calculate if there is an intersection point of to lines link for the algorithm:
     * https://en.wikipedia.org/wiki/Line.
     *
     * @param other (Line)
     * @return true or false (boolean)
     */
    public boolean isIntersecting(Line other) {
        double dinominator = (this.start.getX() - this.end.getX()) * (other.start().getY() - other.end().getY())
                - (this.start.getY() - this.end.getY()) * (other.start().getX() - other.end().getX());
        if (dinominator == 0) {
            return false;
        }
        double t = (this.start.getX() - other.start().getX()) * (other.start().getY() - other.end().getY())
                - (this.start.getY() - other.start().getY()) * (other.start().getX() - other.end().getX());
        t = t / dinominator;
        if (t > 1 || t < 0) {
            return false;
        }
        double u = (this.start.getX() - other.start().getX()) * (this.start.getY() - this.end.getY())
                - (this.start.getY() - other.start().getY()) * (this.start.getX() - this.end.getX());
        u = u / dinominator;
        return !(u > 1) && !(u < 0);
    }

    /**
     * Returns the intersection point if the lines intersect, and null otherwise.
     * Using a known algorithm to calculate if there is an intersection point of to lines link for the algorithm:
     * https://en.wikipedia.org/wiki/Line.
     *
     * @param other (Line)
     * @return Point or null.
     */
    public Point intersectionWith(Line other) {
        if (!this.isIntersecting(other)) {
            return null;
        }
        double dinominator = (this.start.getX() - this.end.getX()) * (other.start().getY() - other.end().getY())
                - (this.start.getY() - this.end.getY()) * (other.start().getX() - other.end().getX());
        double t = (this.start.getX() - other.start().getX()) * (other.start().getY() - other.end().getY())
                - (this.start.getY() - other.start().getY()) * (other.start().getX() - other.end().getX());
        t = t / dinominator;
        double newPointX = this.start.getX() + t * (this.end.getX() - this.start.getX());
        double newPointY = this.start.getY() + t * (this.end.getY() - this.start.getY());
        return new Point(newPointX, newPointY);
    }

    /**
     * Return true is the lines are equal, false otherwise.
     *
     * @param other (Line)
     * @return true or false (boolean)
     */
    public boolean equals(Line other) {
        return start.equals(other.start) && end.equals(other.end);
    }

    /**
     * Calculate the slope of a line.
     *
     * @return slope (double)
     */
    public double slope() {
        double x1 = start.getX(), y1 = start.getY();
        double x2 = end.getX(), y2 = end.getY();
        return (y1 - y2) / (x1 - x2);
    }

    /**
     * Calculate the 'c' in formula line (y=mx+'c').
     *
     * @return c (double)
     */
    public double cInFormula() {
        double x = start.getX(), y = start.getY();
        return y - (x * slope());
    }

    /**
     * If this line does not intersect with the rectangle, return null. Otherwise, return the closest intersection
     * point to the start of the line.
     *
     * @param rect (Rectangle)
     * @return close (Point)
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        List<Point> listOfPoint;
        listOfPoint = rect.intersectionPoints(this);
        if (listOfPoint.isEmpty()) {
            return null;
        }
        double dis = start.distance(listOfPoint.get(0));
        Point close = listOfPoint.get(0);
        for (Point element : listOfPoint) {
            if (start.distance(element) < dis) {
                dis = start.distance(element);
                close = element;
            }
        }
        return close;
    }

    /**
     * Is on line.
     *
     * @param p (Point)
     * @return True or False (boolean)
     */
    public boolean isOnLine(Point p) {
        //check triangle inequality.
        return p.distance(this.start) + p.distance(this.end) - this.length() < 0.000000001;
    }
}
