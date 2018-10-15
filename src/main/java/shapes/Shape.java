package shapes;

import java.awt.*;

/**
 * This class acts as a parent for all of the different shapes (Points and Lines included).
 * Also includes a move method for each child class to implement.
 *
 * @author Aylee Andersen
 * @version 1.0
 */
public abstract class Shape {
    /**
     * Abstract getArea method for each of the 2D Shapes to implement.
     * Gets the area of the shape.
     *
     * @return area of the shape
     */
    abstract double getArea();

    /**
     * Abstract move method for each of the children Shapes to implement.
     * Moves the Shape along the x and/or y axes.
     *
     * @param deltaX The distance the shape should move along the x-axis
     * @param deltaY The distance the shape should move along the y-axis
     * @throws ShapeException Exception throw if any parameter is invalid
     */
    abstract void move(double deltaX, double deltaY) throws ShapeException;

    abstract void render(Graphics2D graphics) throws ShapeException;
}
