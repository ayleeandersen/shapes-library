package examples.shapes;

import org.junit.Test;

import static org.junit.Assert.*;

public class LineTest {

    @Test
    public void testValidConstruction() throws ShapeException {
        Point p1 = new Point(1,2);
        Point p2 = new Point(4, 10);

        Line myLine = new Line(p1, p2);
        assertSame(p1, myLine.getVertices()[0]);
        assertSame(p2, myLine.getVertices()[1]);

        p1 = new Point(1.4,2.5);
        p2 = new Point(4.6, 10.7);
        myLine = new Line(p1, p2);
        assertSame(p1, myLine.getVertices()[0]);
        assertSame(p2, myLine.getVertices()[1]);

        myLine = new Line(1, 3.33, 4.444, 5.5555);
        assertEquals(1, myLine.getVertices()[0].getX(), 0);
        assertEquals(3.33, myLine.getVertices()[0].getY(), 0);
        assertEquals(4.444, myLine.getVertices()[1].getX(), 0);
        assertEquals(5.5555, myLine.getVertices()[1].getY(), 0);
    }

    @Test
    public void testInvalidConstruction() throws Exception {
        Point p1 = new Point(1,2);
        Point p2 = new Point(4, 10);

        try {
            new Line(p1, null);
            fail("Expected exception not thrown for when the first parameter is null");
        } catch (ShapeException e) {
            assertEquals("Invalid Point", e.getMessage());
        }

        try {
            new Line(null, p2);
            fail("Expected exception not thrown for when the second parameter is null");
        } catch (ShapeException e) {
            assertEquals("Invalid Point", e.getMessage());
        }

        try {
            new Line(Double.POSITIVE_INFINITY, 2, 3, 4);
            fail("Expected exception not thrown");
        } catch (ShapeException e) {
            assertEquals("Invalid x-location", e.getMessage());
        }

        try {
            new Line(1, Double.POSITIVE_INFINITY, 3, 4);
            fail("Expected exception not thrown");
        } catch (ShapeException e) {
            assertEquals("Invalid y-location", e.getMessage());
        }

        try {
            new Line(1, 2, Double.POSITIVE_INFINITY, 4);
            fail("Expected exception not thrown");
        } catch (ShapeException e) {
            assertEquals("Invalid x-location", e.getMessage());
        }

        try {
            new Line(1, 2, 3, Double.POSITIVE_INFINITY);
            fail("Expected exception not thrown");
        } catch (ShapeException e) {
            assertEquals("Invalid y-location", e.getMessage());
        }

        try {
            new Line(Double.NEGATIVE_INFINITY, 2, 3, 4);
            fail("Expected exception not thrown");
        } catch (ShapeException e) {
            assertEquals("Invalid x-location", e.getMessage());
        }

        try {
            new Line(1, Double.NEGATIVE_INFINITY, 3, 4);
            fail("Expected exception not thrown");
        } catch (ShapeException e) {
            assertEquals("Invalid y-location", e.getMessage());
        }

        try {
            new Line(1, 2, Double.NEGATIVE_INFINITY, 4);
            fail("Expected exception not thrown");
        } catch (ShapeException e) {
            assertEquals("Invalid x-location", e.getMessage());
        }

        try {
            new Line(1, 2, 3, Double.NEGATIVE_INFINITY);
            fail("Expected exception not thrown");
        } catch (ShapeException e) {
            assertEquals("Invalid y-location", e.getMessage());
        }

        try {
            new Line(Double.NaN, 2, 3, 4);
            fail("Expected exception not thrown");
        } catch (ShapeException e) {
            assertEquals("Invalid x-location", e.getMessage());
        }

        try {
            new Line(1, Double.NaN, 3, 4);
            fail("Expected exception not thrown");
        } catch (ShapeException e) {
            assertEquals("Invalid y-location", e.getMessage());
        }

        try {
            new Line(1, 2, Double.NaN, 4);
            fail("Expected exception not thrown");
        } catch (ShapeException e) {
            assertEquals("Invalid x-location", e.getMessage());
        }

        try {
            new Line(1, 2, 3, Double.NaN);
            fail("Expected exception not thrown");
        } catch (ShapeException e) {
            assertEquals("Invalid y-location", e.getMessage());
        }

        try {
            new Line(3, 4, 3, 4);
            fail("Expected exception not thrown");
        } catch (ShapeException e) {
            assertEquals("A line must have a length > 0", e.getMessage());
        }

        try {
            new Line(p1, p1);
            fail("Expected exception not thrown");
        } catch (ShapeException e) {
            assertEquals("A line must have a length > 0", e.getMessage());
        }

        try {
            new Line(1,2, 1,2);
            fail("Expected exception not thrown");
        } catch (ShapeException e) {
            assertEquals("A line must have a length > 0", e.getMessage());
        }
    }

    @Test
    public void testMove() throws ShapeException {
        Line myLine = new Line(1, 2, 4, 10);

        myLine.move(3, 4);
        assertEquals(4, myLine.getVertices()[0].getX(), 0);
        assertEquals(6, myLine.getVertices()[0].getY(), 0);
        assertEquals(7, myLine.getVertices()[1].getX(), 0);
        assertEquals(14, myLine.getVertices()[1].getY(), 0);

        myLine.move(.4321, .7654);
        assertEquals(4.4321, myLine.getVertices()[0].getX(), 0);
        assertEquals(6.7654, myLine.getVertices()[0].getY(), 0);
        assertEquals(7.4321, myLine.getVertices()[1].getX(), 0);
        assertEquals(14.7654, myLine.getVertices()[1].getY(), 0);

        myLine.move(-0.4321, -0.7654);
        assertEquals(4, myLine.getVertices()[0].getX(), 0);
        assertEquals(6, myLine.getVertices()[0].getY(), 0);
        assertEquals(7, myLine.getVertices()[1].getX(), 0);
        assertEquals(14, myLine.getVertices()[1].getY(), 0);
    }

    @Test
    public void testComputeLength() throws ShapeException {
        Line myLine = new Line(1, 2, 4, 10);
        assertEquals(8.544, myLine.getLength(), 0.001);

        myLine = new Line(1, 2, 1, 3);
        assertEquals(Math.sqrt(1), myLine.getLength(), 0.001);

        myLine = new Line(3, -2, -4, 10);
        assertEquals(13.892, myLine.getLength(), 0.001);
    }

    @Test
    public void testComputeSlope() throws ShapeException {
        Line myLine = new Line(2, 2, 4, 10);
        assertEquals(4, myLine.getSlope(), 0.1);

        myLine = new Line(2, 2, 4, 10);
        assertEquals(4, myLine.getSlope(), 0.1);

        myLine = new Line(2, 2, 2, 4);
        assertEquals(Double.POSITIVE_INFINITY, myLine.getSlope(), 0.1);

        myLine = new Line(2, 2, 4, 2);
        assertEquals(0, myLine.getSlope(), 0.1);

        myLine = new Line(4, 2, 2, 2);
        assertEquals(0, myLine.getSlope(), 0.1);
    }

    @Test
    public void testGetVertices() throws ShapeException {
        Line myLine = new Line(2, 2, 4, 10);
        assertEquals(2, myLine.getVertices()[0].getX(), 0.1);
        assertEquals(2, myLine.getVertices()[0].getY(), 0.1);
        assertEquals(4, myLine.getVertices()[1].getX(), 0.1);
        assertEquals(10, myLine.getVertices()[1].getY(), 0.1);

        myLine = new Line(new Point(2, 3), new Point(-4, 10));
        assertEquals(2, myLine.getVertices()[0].getX(), 0.1);
        assertEquals(3, myLine.getVertices()[0].getY(), 0.1);
        assertEquals(-4, myLine.getVertices()[1].getX(), 0.1);
        assertEquals(10, myLine.getVertices()[1].getY(), 0.1);
    }
}