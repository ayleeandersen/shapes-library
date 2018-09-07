package examples.shapes;

import org.junit.Test;

import static org.junit.Assert.*;

public class TriangleTest {

    @Test
    public void testValidConstruction() throws Exception {
        Point pt1 = new Point(1,2);
        Point pt2 = new Point(-1, 5);
        Point pt3 = new Point(10, -3);
        Triangle myTriangle = new Triangle(pt1, pt2, pt3);
        assertEquals(pt1, myTriangle.getVertices()[0]);
        assertEquals(pt2, myTriangle.getVertices()[1]);
        assertEquals(pt3, myTriangle.getVertices()[2]);
    }

    @Test
    public void testInvalidConstruction() {

        try {
            new Triangle(null, new Point(2.5, 3), new Point(1, 2));
            fail("Expected exception not thrown");
        } catch (ShapeException e) {
            assertEquals("Must have valid points", e.getMessage());
        }

        try {
            new Triangle(new Point(2.5, 3), null, new Point(1, 2));
            fail("Expected exception not thrown");
        } catch (ShapeException e) {
            assertEquals("Must have valid points", e.getMessage());
        }

        try {
            new Triangle(new Point(2.5, 3), new Point(1, 2), null);
            fail("Expected exception not thrown");
        } catch (ShapeException e) {
            assertEquals("Must have valid points", e.getMessage());
        }

        try {
            new Triangle(new Point(2.5, 3), new Point(1, 2), new Point(2.5, 3));
            fail("Expected exception not thrown");
        } catch (ShapeException e) {
            assertEquals("A line must have a length > 0", e.getMessage());
        }

        try {
            new Triangle(new Point(2.5, 3), new Point(1, 2), new Point(1, 2));
            fail("Expected exception not thrown");
        } catch (ShapeException e) {
            assertEquals("A line must have a length > 0", e.getMessage());
        }

        try {
            new Triangle(new Point(1, 2), new Point(2, 3), new Point(3, 4));
            fail("Expected exception not thrown");
        } catch (ShapeException e) {
            assertEquals("Three points must form a triangle", e.getMessage());
        }

        try {
            new Triangle(new Point(1, 2), new Point(5, 2), new Point(7, 2));
            fail("Expected exception not thrown");
        } catch (ShapeException e) {
            assertEquals("Three points must form a triangle", e.getMessage());
        }

        try {
            new Triangle(new Point(1, 2), new Point(2, 2), new Point(8, 2));
            fail("Expected exception not thrown");
        } catch (ShapeException e) {
            assertEquals("Three points must form a triangle", e.getMessage());
        }
    }

    @Test
    public void testMove() throws ShapeException {
        Point pt1 = new Point(1,2);
        Point pt2 = new Point(-1, 5);
        Point pt3 = new Point(10, -3);
        Triangle myTriangle = new Triangle(pt1, pt2, pt3);
        assertEquals(1, myTriangle.getVertices()[0].getX(), 0);
        assertEquals(2, myTriangle.getVertices()[0].getY(), 0);
        assertEquals(-1, myTriangle.getVertices()[1].getX(), 0);
        assertEquals(5, myTriangle.getVertices()[1].getY(), 0);
        assertEquals(10, myTriangle.getVertices()[2].getX(), 0);
        assertEquals(-3, myTriangle.getVertices()[2].getY(), 0);

        myTriangle.move(1,  2);
        assertEquals(2, myTriangle.getVertices()[0].getX(), 0);
        assertEquals(4, myTriangle.getVertices()[0].getY(), 0);
        assertEquals(0, myTriangle.getVertices()[1].getX(), 0);
        assertEquals(7, myTriangle.getVertices()[1].getY(), 0);
        assertEquals(11, myTriangle.getVertices()[2].getX(), 0);
        assertEquals(-1, myTriangle.getVertices()[2].getY(), 0);

        myTriangle.move(0.123,  0.456);
        assertEquals(2.123, myTriangle.getVertices()[0].getX(), 0);
        assertEquals(4.456, myTriangle.getVertices()[0].getY(), 0);
        assertEquals(0.123, myTriangle.getVertices()[1].getX(), 0);
        assertEquals(7.456, myTriangle.getVertices()[1].getY(), 0);
        assertEquals(11.123, myTriangle.getVertices()[2].getX(), 0);
        assertEquals(-0.544, myTriangle.getVertices()[2].getY(), 0);

        myTriangle.move(-0.123,  -0.456);
        assertEquals(2, myTriangle.getVertices()[0].getX(), 0);
        assertEquals(4, myTriangle.getVertices()[0].getY(), 0);
        assertEquals(0, myTriangle.getVertices()[1].getX(), 0);
        assertEquals(7, myTriangle.getVertices()[1].getY(), 0);
        assertEquals(11, myTriangle.getVertices()[2].getX(), 0);
        assertEquals(-1, myTriangle.getVertices()[2].getY(), 0);

        myTriangle.move(-12,  -26);
        assertEquals(-10, myTriangle.getVertices()[0].getX(), 0);
        assertEquals(-22, myTriangle.getVertices()[0].getY(), 0);
        assertEquals(-12, myTriangle.getVertices()[1].getX(), 0);
        assertEquals(-19, myTriangle.getVertices()[1].getY(), 0);
        assertEquals(-1, myTriangle.getVertices()[2].getX(), 0);
        assertEquals(-27, myTriangle.getVertices()[2].getY(), 0);

        myTriangle.move(0,  0);
        assertEquals(-10, myTriangle.getVertices()[0].getX(), 0);
        assertEquals(-22, myTriangle.getVertices()[0].getY(), 0);
        assertEquals(-12, myTriangle.getVertices()[1].getX(), 0);
        assertEquals(-19, myTriangle.getVertices()[1].getY(), 0);
        assertEquals(-1, myTriangle.getVertices()[2].getX(), 0);
        assertEquals(-27, myTriangle.getVertices()[2].getY(), 0);

        try {
            myTriangle.move(Double.POSITIVE_INFINITY, 1);
            fail("Expected exception not thrown");
        } catch (ShapeException e) {
            assertEquals("Invalid delta-x value", e.getMessage());
        }

        try {
            myTriangle.move(Double.NEGATIVE_INFINITY, 1);
            fail("Expected exception not thrown");
        } catch (ShapeException e) {
            assertEquals("Invalid delta-x value", e.getMessage());
        }

        try {
            myTriangle.move(Double.NaN, 1);
            fail("Expected exception not thrown");
        } catch (ShapeException e) {
            assertEquals("Invalid delta-x value", e.getMessage());
        }

        try {
            myTriangle.move(1, Double.POSITIVE_INFINITY);
            fail("Expected exception not thrown");
        } catch (ShapeException e) {
            assertEquals("Invalid delta-y value", e.getMessage());
        }

        try {
            myTriangle.move(1, Double.NEGATIVE_INFINITY);
            fail("Expected exception not thrown");
        } catch (ShapeException e) {
            assertEquals("Invalid delta-y value", e.getMessage());
        }

        try {
            myTriangle.move(1, Double.NaN);
            fail("Expected exception not thrown");
        } catch (ShapeException e) {
            assertEquals("Invalid delta-y value", e.getMessage());
        }
    }

    @Test
    public void testComputeArea() throws ShapeException {
        Point pt1 = new Point(1,2);
        Point pt2 = new Point(-1, 5);
        Point pt3 = new Point(10, -3);
        Triangle myTriangle = new Triangle(pt1, pt2, pt3);
        assertEquals(8.5, myTriangle.getArea(), 0.0001);

        pt1 = new Point(10,2);
        pt2 = new Point(-1, 5);
        pt3 = new Point(-10, -3);
        myTriangle = new Triangle(pt1, pt2, pt3);
        assertEquals(57.5, myTriangle.getArea(), 0.0001);
    }

    @Test
    public void testGetVertices() throws ShapeException {
        Point pt1 = new Point(1,2);
        Point pt2 = new Point(-1, 5);
        Point pt3 = new Point(10, -3);
        Triangle myTriangle = new Triangle(pt1, pt2, pt3);
        assertEquals(1, myTriangle.getVertices()[0].getX(), 0.0001);
        assertEquals(2, myTriangle.getVertices()[0].getY(), 0.0001);
        assertEquals(-1, myTriangle.getVertices()[1].getX(), 0.0001);
        assertEquals(5, myTriangle.getVertices()[1].getY(), 0.0001);
        assertEquals(10, myTriangle.getVertices()[2].getX(), 0.0001);
        assertEquals(-3, myTriangle.getVertices()[2].getY(), 0.0001);
    }
}