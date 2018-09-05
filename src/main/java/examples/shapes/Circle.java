package examples.shapes;

/**
 * Circle
 *
 * This class represents circle objects that can be moved and scales.  Users of a circle can also get its area.
 *
 */
@SuppressWarnings("WeakerAccess")
public class Circle extends Ellipse {
    /**
     * Constructor with x-y Location for center
     *
     * @param x                 The x-location of the center of the circle -- must be a valid double
     * @param y                 The y-location of the center of the circle -- must be a valid double
     * @param radius            The radius of the circle -- must be greater or equal to zero.
     * @throws ShapeException   The exception thrown if the x, y, or radius are not valid
     */
    public Circle(double x, double y, double radius) throws ShapeException {
        super(new Point(x, y), radius*2, radius*2);
    }

    /**
     * Constructor with a Point for center
     *
     * @param center            The location of the center of the circle -- must be a valid point
     * @param radius            The radius of the circle -- must be greater or equal to zero.
     * @throws ShapeException   The exception thrown if the center or the radius are not valid
     */
    public Circle(Point center, double radius) throws ShapeException {
        super(center, radius*2, radius*2);
    }

    /**
     * @return  The radius of the circle
     */
    public double getRadius() { return getHeight()/2; }
}
