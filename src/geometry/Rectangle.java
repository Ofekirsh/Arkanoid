package geometry;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Rectangle.
 */
public class Rectangle {
    private final Point upperLeft;
    private final double width;
    private final double height;

    /**
     * Create a new rectangle with location and width/height.
     *
     * @param upperLeft (Point)
     * @param width     (double)
     * @param height    (double)
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }

    /**
     * Return a (possibly empty) List of intersection points with the specified line.
     *
     * @param line (Line)
     * @return listOfPoints (Point list)
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        // define arrOfLines and 4 lines of rectangle
        Line[] lines = getLinesOfRectangle();
        List<Point> listOfPoints = new ArrayList<Point>();
        // check pointIntersection with rectangle.
        for (Line element : lines) {
            Point pointIntersection = element.intersectionWith(line);
            if (pointIntersection != null) {
                listOfPoints.add(pointIntersection);
            }
        }
        return listOfPoints;
    }

    /**
     * Return array of lines of rectangle.
     *
     * @return linesOfRectangle (Line[])
     */
    public Line[] getLinesOfRectangle() {
        Line top = new Line(upperLeft, new Point(upperLeft.getX() + getWidth(), upperLeft.getY()));
        Line right = new Line(new Point(upperLeft.getX() + getWidth(), upperLeft.getY()),
                new Point(upperLeft.getX() + getWidth(), upperLeft.getY() + getHeight()));
        Line button = new Line(new Point(upperLeft.getX() + getWidth(), upperLeft.getY() + getHeight()),
                new Point(upperLeft.getX(), upperLeft.getY() + getHeight()));
        Line left = new Line(new Point(upperLeft.getX(), upperLeft.getY() + getHeight()), upperLeft);
        return new Line[]{top, right, button, left};
    }

    /**
     * Return the width of the rectangle.
     *
     * @return width
     */
    public double getWidth() {
        return width;
    }

    /**
     * Return the height of the rectangle.
     *
     * @return height
     */
    public double getHeight() {
        return height;
    }

    /**
     * Returns the upper-left point of the rectangle.
     *
     * @return upperLeft (Point)
     */
    public Point getUpperLeft() {
        return upperLeft;
    }

    /**
     * Return the bottom right point of the rectangle.
     *
     * @return bottomRight (Point)
     */
    public Point getBottomRight() {
        return new Point(upperLeft.getX() + width, upperLeft.getY() + height);
    }
}