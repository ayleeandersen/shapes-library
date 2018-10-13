package shapes;

/**
 * This class represents Triangle objects.
 * Users of a Triangle can also move the Triangle and get its area and vertices.
 *
 * @author Aylee Andersen
 * @version 1.0
 */
public class Triangle extends SimpleShape implements IGetVertices {
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
    public Triangle(Point point1, Point point2, Point point3) throws ShapeException {
        Validator.validatePoint(point1, "Must have valid points");
        Validator.validatePoint(point2, "Must have valid points");
        Validator.validatePoint(point3, "Must have valid points");

        Line line1 = new Line(point1, point2);
        Line line2 = new Line(point2, point3);
        Line line3 = new Line(point3, point1);
        Validator.validateTriangle(line1, line2, line3, "Three points must form a triangle");

        this.point1 = point1;
        this.point2 = point2;
        this.point3 = point3;
    }

    /**
     * @return vertices of the Triangle
     */
    public Point[] getVertices() {
        Point[] vertices = {point1, point2, point3};
        return vertices;
    }

    /**
     * Move a Triangle
     *
     * @param deltaX            The delta x-location by which the line should be moved -- must be a valid double
     * @param deltaY            The delta y-location by which the line should be moved -- must be a valid double
     * @throws ShapeException   Exception throw if any parameter is invalid
     */
    public void move(double deltaX, double deltaY) throws ShapeException {
        point1.move(deltaX, deltaY);
        point2.move(deltaX, deltaY);
        point3.move(deltaX, deltaY);
    }

    /**
     * Gets the area of the shape.
     *
     * @return area of the shape
     */
    public double getArea() {
        return Math.abs((point1.getX() - point3.getX())*(point2.getY() - point1.getY()) - (point1.getX() - point2.getX())*(point3.getY() - point1.getY())) / 2;
    }

    /**
     * Override toString method
     * @return triangle parameters as a string
     */
    public String toString() {
        return "Triangle," + getVertices()[0].getX() + "," + getVertices()[0].getY() + "," + getVertices()[1].getX() + "," + getVertices()[1].getY() + "," + getVertices()[2].getX() + "," + getVertices()[2].getY();
    }
}
