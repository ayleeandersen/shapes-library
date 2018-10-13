package shapes;

/**
 * This class represents Ellipse objects.
 * Users of an Ellipse can also move the Ellipse and get its area, foci, center, height, width.
 *
 * @author Aylee Andersen
 * @version 1.0
 */
public class Ellipse extends SimpleShape {
    private Point center;
    private double height;
    private double width;

    /**
     * Constructor with a Point for center
     *
     * @param center The location of the center of the Ellipse -- must be a valid point.
     * @param height The height of the Ellipse -- must be greater thank zero.
     * @param width  The width of the Ellipse -- must be greater than zero.
     * @throws ShapeException The exception thrown if the center, height, or width are not valid.
     */
    public Ellipse(Point center, double height, double width) throws ShapeException {
        Validator.validatePoint(center, "Must have a valid center point");
        Validator.validateLineLength(height, "Must have a positive height");
        Validator.validateLineLength(width, "Must have a positive width");

        this.center = center;
        this.height = height;
        this.width = width;
    }

    /**
     * @return array of the foci points
     */
    public Point[] getFoci() throws ShapeException {
        double a = height / 2;
        double b = width / 2;
        double distanceToFoci = Math.sqrt(Math.abs(a*a - b*b));
        Point[] foci = new Point[2];
        foci[0] = new Point(center.getX() - distanceToFoci, center.getY());
        foci[1] = new Point(center.getX() + distanceToFoci, center.getY());
        return foci;
    }

    /**
     * Move an Ellipse
     *
     * @param deltaX            The delta x-location by which the line should be moved -- must be a valid double
     * @param deltaY            The delta y-location by which the line should be moved -- must be a valid double
     * @throws ShapeException   Exception throw if any parameter is invalid
     */
    public void move(double deltaX, double deltaY) throws ShapeException {
        center.move(deltaX, deltaY);
    }

    /**
     * Gets the area of the shape.
     *
     * @return area of the shape
     */
    public double getArea() {
        return Math.PI * (height/2) * (width/2);
    }

    /**
     * @return center point of Ellipse
     */
    public Point getCenter() {
        return center;
    }

    /**
     * @return height of Ellipse
     */
    public double getHeight() {
        return height;
    }

    /**
     * @return width of Ellipse
     */
    public double getWidth() {
        return width;
    }

    /**
     * Override toString method
     * @return ellipse parameters as a string
     */
    public String toString() {
        return "Ellipse," + getCenter().getX() + "," + getCenter().getY() + "," + getHeight() + "," + getWidth();
    }
}
