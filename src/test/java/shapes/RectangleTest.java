package shapes;

import org.junit.Test;
import shapes.Point;
import shapes.Rectangle;
import shapes.ShapeException;

import static org.junit.Assert.*;

public class RectangleTest {

    @Test
    public void testValidConstruction() throws Exception {
        Point corner = new Point(1,2);
        Rectangle myRectangle = new Rectangle(corner, 2, 3);
        assertEquals(corner, myRectangle.getVertices()[0]);
        assertEquals(2, myRectangle.getHeight(), 0);
        assertEquals(3, myRectangle.getWidth(), 0);

        corner = new Point(-1,-2.4);
        myRectangle = new Rectangle(corner, 2, 3);
        assertEquals(corner, myRectangle.getVertices()[0]);
        assertEquals(2, myRectangle.getHeight(), 0);
        assertEquals(3, myRectangle.getWidth(), 0);
    }

    @Test
    public void testInvalidConstruction() {

        try {
            new Rectangle(null, 2.5, 3);
            fail("Expected exception not thrown");
        } catch (ShapeException e) {
            assertEquals("Must have a valid top, left vertex", e.getMessage());
        }

        try {
            new Rectangle(new Point(1, 2), 0, 1);
            fail("Expected exception not thrown");
        } catch (ShapeException e) {
            assertEquals("Must have a positive height", e.getMessage());
        }

        try {
            new Rectangle( new Point(1, 2), Double.POSITIVE_INFINITY, 5);
            fail("Expected exception not thrown");
        } catch (ShapeException e) {
            assertEquals("Must have a positive height", e.getMessage());
        }

        try {
            new Rectangle(new Point(1, 2), Double.NEGATIVE_INFINITY, 5);
            fail("Expected exception not thrown");
        } catch (ShapeException e) {
            assertEquals("Must have a positive height", e.getMessage());
        }

        try {
            new Rectangle(new Point(1, 2), Double.NaN, 5);
            fail("Expected exception not thrown");
        } catch (ShapeException e) {
            assertEquals("Must have a positive height", e.getMessage());
        }

        try {
            new Rectangle( new Point(1, 2), 5, Double.POSITIVE_INFINITY);
            fail("Expected exception not thrown");
        } catch (ShapeException e) {
            assertEquals("Must have a positive width", e.getMessage());
        }

        try {
            new Rectangle(new Point(1, 2), 5, Double.NEGATIVE_INFINITY);
            fail("Expected exception not thrown");
        } catch (ShapeException e) {
            assertEquals("Must have a positive width", e.getMessage());
        }

        try {
            new Rectangle(new Point(1, 2), 5, Double.NaN);
            fail("Expected exception not thrown");
        } catch (ShapeException e) {
            assertEquals("Must have a positive width", e.getMessage());
        }

        try {
            new Rectangle(new Point(1, 2), 5, 0);
            fail("Expected exception not thrown");
        } catch (ShapeException e) {
            assertEquals("Must have a positive width", e.getMessage());
        }

        try {
            new Rectangle(new Point(1, 2), 0, 3);
            fail("Expected exception not thrown");
        } catch (ShapeException e) {
            assertEquals("Must have a positive height", e.getMessage());
        }
    }

    @Test
    public void testMove() throws ShapeException {
        Rectangle myRectangle = new Rectangle(new Point(1, 2), 5, 4);
        assertEquals(1, myRectangle.getVertices()[0].getX(), 0);
        assertEquals(2, myRectangle.getVertices()[0].getY(), 0);
        assertEquals(5, myRectangle.getVertices()[1].getX(), 0);
        assertEquals(2, myRectangle.getVertices()[1].getY(), 0);
        assertEquals(1, myRectangle.getVertices()[2].getX(), 0);
        assertEquals(7, myRectangle.getVertices()[2].getY(), 0);
        assertEquals(5, myRectangle.getVertices()[3].getX(), 0);
        assertEquals(7, myRectangle.getVertices()[3].getY(), 0);
        assertEquals(5, myRectangle.getHeight(), 0);
        assertEquals(4, myRectangle.getWidth(), 0);

        myRectangle.move(3,  4);
        assertEquals(4, myRectangle.getVertices()[0].getX(), 0);
        assertEquals(6, myRectangle.getVertices()[0].getY(), 0);
        assertEquals(8, myRectangle.getVertices()[1].getX(), 0);
        assertEquals(6, myRectangle.getVertices()[1].getY(), 0);
        assertEquals(4, myRectangle.getVertices()[2].getX(), 0);
        assertEquals(11, myRectangle.getVertices()[2].getY(), 0);
        assertEquals(8, myRectangle.getVertices()[3].getX(), 0);
        assertEquals(11, myRectangle.getVertices()[3].getY(), 0);

        myRectangle.move(0.123,  0.456);
        assertEquals(4.123, myRectangle.getVertices()[0].getX(), 0);
        assertEquals(6.456, myRectangle.getVertices()[0].getY(), 0);
        assertEquals(8.123, myRectangle.getVertices()[1].getX(), 0.000001);
        assertEquals(6.456, myRectangle.getVertices()[1].getY(), 0);
        assertEquals(4.123, myRectangle.getVertices()[2].getX(), 0);
        assertEquals(11.456, myRectangle.getVertices()[2].getY(), 0);
        assertEquals(8.123, myRectangle.getVertices()[3].getX(), 0.000001);
        assertEquals(11.456, myRectangle.getVertices()[3].getY(), 0);

        myRectangle.move(-0.123,  -0.456);
        assertEquals(4, myRectangle.getVertices()[0].getX(), 0);
        assertEquals(6, myRectangle.getVertices()[0].getY(), 0);
        assertEquals(8, myRectangle.getVertices()[1].getX(), 0);
        assertEquals(6, myRectangle.getVertices()[1].getY(), 0);
        assertEquals(4, myRectangle.getVertices()[2].getX(), 0);
        assertEquals(11, myRectangle.getVertices()[2].getY(), 0);
        assertEquals(8, myRectangle.getVertices()[3].getX(), 0);
        assertEquals(11, myRectangle.getVertices()[3].getY(), 0);

        myRectangle.move(-12,  -26);
        assertEquals(-8, myRectangle.getVertices()[0].getX(), 0);
        assertEquals(-20, myRectangle.getVertices()[0].getY(), 0);
        assertEquals(-4, myRectangle.getVertices()[1].getX(), 0);
        assertEquals(-20, myRectangle.getVertices()[1].getY(), 0);
        assertEquals(-8, myRectangle.getVertices()[2].getX(), 0);
        assertEquals(-15, myRectangle.getVertices()[2].getY(), 0);
        assertEquals(-4, myRectangle.getVertices()[3].getX(), 0);
        assertEquals(-15, myRectangle.getVertices()[3].getY(), 0);

        myRectangle.move(0,  0);
        assertEquals(-8, myRectangle.getVertices()[0].getX(), 0);
        assertEquals(-20, myRectangle.getVertices()[0].getY(), 0);
        assertEquals(-4, myRectangle.getVertices()[1].getX(), 0);
        assertEquals(-20, myRectangle.getVertices()[1].getY(), 0);
        assertEquals(-8, myRectangle.getVertices()[2].getX(), 0);
        assertEquals(-15, myRectangle.getVertices()[2].getY(), 0);
        assertEquals(-4, myRectangle.getVertices()[3].getX(), 0);
        assertEquals(-15, myRectangle.getVertices()[3].getY(), 0);

        try {
            myRectangle.move(Double.POSITIVE_INFINITY, 1);
            fail("Expected exception not thrown");
        } catch (ShapeException e) {
            assertEquals("Invalid delta-x value", e.getMessage());
        }

        try {
            myRectangle.move(Double.NEGATIVE_INFINITY, 1);
            fail("Expected exception not thrown");
        } catch (ShapeException e) {
            assertEquals("Invalid delta-x value", e.getMessage());
        }

        try {
            myRectangle.move(Double.NaN, 1);
            fail("Expected exception not thrown");
        } catch (ShapeException e) {
            assertEquals("Invalid delta-x value", e.getMessage());
        }

        try {
            myRectangle.move(1, Double.POSITIVE_INFINITY);
            fail("Expected exception not thrown");
        } catch (ShapeException e) {
            assertEquals("Invalid delta-y value", e.getMessage());
        }

        try {
            myRectangle.move(1, Double.NEGATIVE_INFINITY);
            fail("Expected exception not thrown");
        } catch (ShapeException e) {
            assertEquals("Invalid delta-y value", e.getMessage());
        }

        try {
            myRectangle.move(1, Double.NaN);
            fail("Expected exception not thrown");
        } catch (ShapeException e) {
            assertEquals("Invalid delta-y value", e.getMessage());
        }
    }

    @Test
    public void testComputeArea() throws ShapeException {
        Rectangle myRectangle = new Rectangle(new Point(1, 2), 5, 4);
        assertEquals(20, myRectangle.getArea(), 0.0001);

        myRectangle = new Rectangle(new Point(1, 2), 4.234, 1.234);
        assertEquals(5.22476, myRectangle.getArea(), 0.0001);
    }

    @Test
    public void testGetVertices() throws ShapeException {
        Rectangle myRectangle = new Rectangle(new Point(1, 2), 5, 4);
        assertEquals(1, myRectangle.getVertices()[0].getX(), 0.0001);
        assertEquals(2, myRectangle.getVertices()[0].getY(), 0.0001);
        assertEquals(5, myRectangle.getVertices()[1].getX(), 0.0001);
        assertEquals(2, myRectangle.getVertices()[1].getY(), 0.0001);
        assertEquals(1, myRectangle.getVertices()[2].getX(), 0.0001);
        assertEquals(7, myRectangle.getVertices()[2].getY(), 0.0001);
        assertEquals(5, myRectangle.getVertices()[3].getX(), 0.0001);
        assertEquals(7, myRectangle.getVertices()[3].getY(), 0.0001);
    }

    @Test
    public void testToString() throws ShapeException {
        Rectangle rectangle = new Rectangle(new Point(1, -0.9), 5, 3.4);
        assertEquals("Rectangle,1.0,-0.9,5.0,3.4", rectangle.toString());
    }
}