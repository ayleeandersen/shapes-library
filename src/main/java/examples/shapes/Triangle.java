package examples.shapes;

/**
 * This class represents Triangle objects.
 * Users of a Triangle can also move the Triangle and get its area and vertices.
 *
 * @author Aylee Andersen
 * @version 1.0
 */
public class Triangle extends TwoDShape implements IGetVertices {
    private Point point1;
    private Point point2;
    private Point point3;

    /**
     * Constructor with three points for the vertices
     *
     * @param point1 The location of a vertex - must be a valid point
     * @param point2 The location of a vertex - must be a valid point
     * @param point3 The location of a vertex - must be a valid point
     * @throws ShapeException The exception thrown if any of the points are not valid.
     */
    public void Triangle(Point point1, Point point2, Point point3) throws ShapeException {
        Validator.validatePoint(point1, "Must have valid points");
        Validator.validatePoint(point2, "Must have valid points");
        Validator.validatePoint(point3, "Must have valid points");

        this.point1 = point1;
        this.point2 = point2;
        this.point3 = point3;
    }

    // TODO: do I have to comment this implementation?
    public Point[] getVertices() {
        Point[] vertices = {point1, point2, point3};
        return vertices;
    }

    // TODO: do I have to comment this implementation?
    public void move(double deltaX, double deltaY) throws ShapeException {
        // TODO: do I have to have validation here since point.move has validation?
        point1.move(deltaX, deltaY);
        point2.move(deltaX, deltaY);
        point3.move(deltaX, deltaY);
    }

    // TODO: do I have to comment this implementation?
    public double getArea() {
        return Math.abs((point1.getX() - point3.getX())*(point2.getY() - point1.getY()) - (point1.getX() - point2.getX())*(point3.getY() - point1.getY())) / 2;
    }
}
