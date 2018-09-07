package examples.shapes;

/**
 * This class represents Rectangle objects.
 * Users of a Rectangle can also move the Rectangle and get its area, vertices, height, and width.
 *
 * @author Aylee Andersen
 * @version 1.0
 */
public class Rectangle extends TwoDShape implements IGetVertices {
    private Point topLeftVertex;
    private double height;
    private double width;

    /**
     * Constructor with a point for one vertex and a height and width to determine size
     *
     * @param topLeftVertex The location of a vertex - must be a valid point
     * @param height The height of the Rectangle - must be positive
     * @param width The width of the Rectangle - must be positive
     * @throws ShapeException The exception thrown if the topLeftVertex, height, or width are not valid.
     */
    public Rectangle(Point topLeftVertex, double height, double width) throws ShapeException {
        Validator.validatePoint(topLeftVertex, "Must have a valid top, left vertex");
        Validator.validateLineLength(height, "Must have a positive height");
        Validator.validateLineLength(width, "Must have a positive width");

        this.topLeftVertex = topLeftVertex;
        this.height = height;
        this.width = width;
    }

    /**
     * @return vertices of the Rectangle
     */
    public Point[] getVertices() throws ShapeException {
        Point[] vertices = new Point[4];
        vertices[0] = topLeftVertex;
        vertices[1] = new Point(topLeftVertex.getX() + width, topLeftVertex.getY());
        vertices[2] = new Point(topLeftVertex.getX(), topLeftVertex.getY() + height);
        vertices[3] = new Point(topLeftVertex.getX() + width, topLeftVertex.getY() + height);
        return vertices;
    }

    /**
     * Moves a Rectangle
     *
     * @param deltaX            The delta x-location by which the line should be moved -- must be a valid double
     * @param deltaY            The delta y-location by which the line should be moved -- must be a valid double
     * @throws ShapeException   Exception throw if any parameter is invalid
     */
    public void move(double deltaX, double deltaY) throws ShapeException {
        topLeftVertex.move(deltaX, deltaY);
    }

    /**
     * Gets the area of the shape.
     *
     * @return area of the shape
     */
    public double getArea() {
        return height * width;
    }

    /**
     * @return height of Rectangle
     */
    public double getHeight() {
        return height;
    }

    /**
     * @return width of Rectangle
     */
    public double getWidth() {
        return width;
    }
}
