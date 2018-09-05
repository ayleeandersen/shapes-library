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
        Validator.validatePositiveDouble(height, "Must have a positive height");
        Validator.validatePositiveDouble(width, "Must have a positive width");

        this.topLeftVertex = topLeftVertex;
        this.height = height;
        this.width = width;
    }

    // TODO: do I have to comment this implementation?
    public Point[] getVertices() throws ShapeException {
        Point[] vertices = {topLeftVertex};
        vertices[1] = new Point(topLeftVertex.getX() + width, topLeftVertex.getY());
        vertices[2] = new Point(topLeftVertex.getX(), topLeftVertex.getY() + height);
        vertices[3] = new Point(topLeftVertex.getX() + width, topLeftVertex.getY() + height);
        return vertices;
    }

    // TODO: do I have to comment this implementation?
    public void move(double deltaX, double deltaY) throws ShapeException {
        // TODO: do I have to have validation here since point.move has validation?
        // TODO: do I have to move more than one vertex? USE testing to figure out.
        topLeftVertex.move(deltaX, deltaY);
    }

    // TODO: do I have to comment this implementation?
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
