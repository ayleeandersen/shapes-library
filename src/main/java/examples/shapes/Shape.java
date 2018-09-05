package examples.shapes;

/**
 * This class acts as a parent for all of the different shapes (Points and Lines included).
 * Also includes a move method for each child class to implement.
 *
 * @author Aylee Andersen
 * @version 1.0
 */
public abstract class Shape {
    /**
     * Abstract move method for each of the children Shapes to implement.
     * Moves the Shape along the x and/or y axes.
     *
     * @param x The distance the shape should move along the x-axis
     * @param y The distance the shape should move along the y-axis
     * @throws ShapeException Exception throw if any parameter is invalid
     */
    abstract void move(double x, double y) throws ShapeException;
}
