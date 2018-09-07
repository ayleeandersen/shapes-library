package examples.shapes;

/**
 * This class acts as a parent for all 2D shapes (Triangle, Circle, Ellipse, Square, and Rectangle).
 * Also includes Shape's abstract move method for each child class to implement,
 * as well as a getArea method for the children to implement.
 *
 * @author Aylee Andersen
 * @version 1.0
 */
public abstract class TwoDShape extends Shape {
    /**
     * Abstract getArea method for each of the 2D Shapes to implement.
     * Gets the area of the shape.
     *
     * @return area of the shape
     */
    abstract double getArea();

    /**
     * Abstract move method for each of the children TwoDShapes to implement.
     * Moves the Shape along the x and/or y axes.
     *
     * @param x The distance the shape should move along the x-axis
     * @param y The distance the shape should move along the y-axis
     * @throws ShapeException Exception throw if any parameter is invalid
     */
    abstract void move(double deltaX, double deltaY) throws ShapeException;
}
