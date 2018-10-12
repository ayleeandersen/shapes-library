package examples.shapes;

import org.junit.Test;

import java.util.ArrayList;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.*;

public class CompositeShapeTest {
    @Test
    public void testValidConstruction() throws ShapeException {
        CompositeShape compositeShape = new CompositeShape();
        assertEquals(0, compositeShape.getShapes().size());
        assertTrue(0 == compositeShape.getArea());

        ArrayList<Shape> shapes = new ArrayList<Shape>();
        shapes.add(new Rectangle(new Point(1,2),1,1));
        shapes.add(new Circle(new Point(3, 2.3), 5));
        shapes.add(new Triangle(new Point(1,1), new Point(-4, 2), new Point(-5, -8)));
        CompositeShape compositeShape1 = new CompositeShape(shapes);
        assertEquals(shapes, compositeShape1.getShapes());
    }

    @Test
    public void testInvalidConstruction() throws ShapeException {
        try {
            new CompositeShape(null);
        } catch (Exception e) {
            fail("Expected exception not thrown");
        }
    }

    @Test
    public void testAdd() throws ShapeException {
        CompositeShape compositeShape = new CompositeShape();
        assertEquals(0, compositeShape.getShapes().size());
        Rectangle rect = new Rectangle(new Point(1,2),1,1);
        compositeShape.add(rect);
        assertEquals(rect, compositeShape.getShapes().get(0));
        compositeShape.add(new Triangle(new Point(1,1), new Point(-4, 2), new Point(-5, -8)));
        assertEquals(2, compositeShape.getShapes().size());
    }

    @Test
    public void testRemove() throws ShapeException {
        ArrayList<Shape> shapes = new ArrayList<Shape>();
        Rectangle rectangle = new Rectangle(new Point(1,2),1,1);
        shapes.add(rectangle);
        Circle circle = new Circle(new Point(3, 2.3), 5);
        shapes.add(circle);
        Triangle triangle = new Triangle(new Point(1,1), new Point(-4, 2), new Point(-5, -8));
        shapes.add(triangle);
        CompositeShape compositeShape1 = new CompositeShape(shapes);
        assertEquals(3, compositeShape1.getShapes().size());

        compositeShape1.remove(circle);
        assertEquals(2, compositeShape1.getShapes().size());
        shapes.remove(circle);
        assertEquals(shapes, compositeShape1.getShapes());

        compositeShape1.remove(triangle);
        assertEquals(1, compositeShape1.getShapes().size());
        shapes.remove(triangle);
        assertEquals(shapes, compositeShape1.getShapes());

        compositeShape1.remove(rectangle);
        assertEquals(0, compositeShape1.getShapes().size());
        shapes.remove(rectangle);
        assertEquals(shapes, compositeShape1.getShapes());
    }

    @Test
    public void testRemoveAll() throws ShapeException {
        ArrayList<Shape> shapes = new ArrayList<Shape>();
        shapes.add(new Rectangle(new Point(1,2),1,1));
        shapes.add(new Circle(new Point(3, 2.3), 5));
        shapes.add(new Triangle(new Point(1,1), new Point(-4, 2), new Point(-5, -8)));
        CompositeShape compositeShape1 = new CompositeShape(shapes);
        assertEquals(3, compositeShape1.getShapes().size());

        compositeShape1.removeAll();
        assertEquals(0, compositeShape1.getShapes().size());
        shapes.clear();
        assertEquals(shapes, compositeShape1.getShapes());
    }

    @Test
    public void testMove() throws ShapeException {
        ArrayList<Shape> shapes = new ArrayList<Shape>();
        Rectangle rectangle = new Rectangle(new Point(1,2),1,1);
        shapes.add(rectangle);
        Circle circle = new Circle(new Point(3, 2.3), 5);
        shapes.add(circle);
        Triangle triangle = new Triangle(new Point(1,1), new Point(-4, 2), new Point(-5, -8));
        shapes.add(triangle);
        CompositeShape compositeShape = new CompositeShape(shapes);

        compositeShape.move(1, 1);
        assertEquals(rectangle.getVertices()[0].getX(), 2, 0.001);
        assertEquals(rectangle.getVertices()[0].getY(), 3, 0.001);
        assertEquals(circle.getCenter().getX(), 4, 0.001);
        assertEquals(circle.getCenter().getY(), 3.3, 0.001);
        assertEquals(triangle.getVertices()[0].getX(), 2, 0.001);
        assertEquals(triangle.getVertices()[0].getY(), 2, 0.001);

        try {
            compositeShape.move(Double.POSITIVE_INFINITY, 0);
            fail("Should have thrown exception");
        } catch (Exception e) {
            assertEquals("Invalid delta-x value", e.getMessage());
        }

        try {
            compositeShape.move(Double.NEGATIVE_INFINITY, 0);
            fail("Should have thrown exception");
        } catch (Exception e) {
            assertEquals("Invalid delta-x value", e.getMessage());
        }

        try {
            compositeShape.move(-100, Double.NEGATIVE_INFINITY);
            fail("Should have thrown exception");
        } catch (Exception e) {
            assertEquals("Invalid delta-y value", e.getMessage());
        }

        try {
            compositeShape.move(-100, Double.POSITIVE_INFINITY);
            fail("Should have thrown exception");
        } catch (Exception e) {
            assertEquals("Invalid delta-y value", e.getMessage());
        }
    }

    @Test
    public void testComputeArea() throws ShapeException {
        CompositeShape compositeShape = new CompositeShape();
        assertTrue(0 == compositeShape.getArea());

        compositeShape.add(new Point(0.4, -100));
        compositeShape.add(new Line(new Point(1.2, 3.4), new Point(100, 20)));
        assertTrue(0 == compositeShape.getArea());

        Rectangle rectangle = new Rectangle(new Point(0, 2), 2, 2);
        compositeShape.add(rectangle);
        assertTrue(4 == compositeShape.getArea());

        compositeShape.add(new Triangle(new Point(0,0), new Point(2, 4), new Point(4, 0)));
        assertTrue(12 == compositeShape.getArea());;

        compositeShape.remove(rectangle);
        assertTrue(8 == compositeShape.getArea());
    }
}
