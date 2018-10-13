package shapes;

/**
 * This class represents Square objects.
 * Users of a Square can also move the Square and get its area, vertices, height, and width.
 *
 * @author Aylee Andersen
 * @version 1.0
 */
public class Square extends Rectangle {
    /**
     * Constructor with a point for one vertex and a side length to determine size
     *
     * @param topLeftVertex The location of a vertex - must be a valid point
     * @param sideLength The length of the sides of the Square - must be positive
     * @throws ShapeException The exception thrown if the topLeftVertex, height, or width are not valid.
     */
    public Square(Point topLeftVertex, double sideLength) throws ShapeException {
        super(topLeftVertex, sideLength, sideLength);
    }

    /**
     * @return length of a side of the Square
     */
    public double getSideLength() {
        return getHeight();
    }

    /**
     * Override toString method
     * @return square parameters as a string
     */
    public String toString() {
        String result = "";
        try {
            result = "Square," + getVertices()[0].getX() + "," + getVertices()[0].getY() + "," + getSideLength();
        } catch (Exception e) {}
        return result;
    }
}
