package examples.shapes;

import org.junit.Test;

import static org.junit.Assert.*;

public class EllipseTest {

    @Test
    public void testValidConstruction() throws Exception {
        Point center = new Point(1,2);
        Ellipse myEllipse = new Ellipse(center, 2, 3);
        assertSame(center, myEllipse.getCenter());
        assertEquals(2, myEllipse.getHeight(), 0);
        assertEquals(3, myEllipse.getWidth(), 0);
    }

    @Test
    public void testInvalidConstruction() {

        try {
            new Ellipse(null, 2.5, 3);
            fail("Expected exception not thrown");
        } catch (Exception e) {
            // ignore
        }

        try {
            new Ellipse(new Point(1, 2), 0, 1);
            fail("Expected exception not thrown");
        } catch (Exception e) {
            // ignore
        }

        try {
            new Ellipse( new Point(1, 2), Double.POSITIVE_INFINITY, 5);
            fail("Expected exception not thrown");
        } catch (Exception e) {
            // ignore
        }

        try {
            new Ellipse(new Point(1, 2), Double.NEGATIVE_INFINITY, 5);
            fail("Expected exception not thrown");
        } catch (Exception e) {
            // ignore
        }

        try {
            new Ellipse(new Point(1, 2), Double.NaN, 5);
            fail("Expected exception not thrown");
        } catch (Exception e) {
            // ignore
        }

        try {
            new Ellipse( new Point(1, 2), 5, Double.POSITIVE_INFINITY);
            fail("Expected exception not thrown");
        } catch (Exception e) {
            // ignore
        }

        try {
            new Ellipse(new Point(1, 2), 5, Double.NEGATIVE_INFINITY);
            fail("Expected exception not thrown");
        } catch (Exception e) {
            // ignore
        }

        try {
            new Ellipse(new Point(1, 2), 5, Double.NaN);
            fail("Expected exception not thrown");
        } catch (Exception e) {
            // ignore
        }
    }

    @Test
    public void testMove() throws ShapeException {
        Ellipse myEllipse = new Ellipse(new Point(1, 2), 5, 4);
        assertEquals(1, myEllipse.getCenter().getX(), 0);
        assertEquals(2, myEllipse.getCenter().getY(), 0);
        assertEquals(5, myEllipse.getHeight(), 0);
        assertEquals(4, myEllipse.getWidth(), 0);

        myEllipse.move(3,  4);
        assertEquals(4, myEllipse.getCenter().getX(), 0);
        assertEquals(6, myEllipse.getCenter().getY(), 0);
        assertEquals(5, myEllipse.getHeight(), 0);
        assertEquals(4, myEllipse.getWidth(), 0);

        myEllipse.move(0.123,  0.456);
        assertEquals(4.123, myEllipse.getCenter().getX(), 0);
        assertEquals(6.456, myEllipse.getCenter().getY(), 0);
        assertEquals(5, myEllipse.getHeight(), 0);
        assertEquals(4, myEllipse.getWidth(), 0);

        myEllipse.move(-0.123,  -0.456);
        assertEquals(4, myEllipse.getCenter().getX(), 0);
        assertEquals(6, myEllipse.getCenter().getY(), 0);
        assertEquals(5, myEllipse.getHeight(), 0);
        assertEquals(4, myEllipse.getWidth(), 0);

        myEllipse.move(-12,  -26);
        assertEquals(-8, myEllipse.getCenter().getX(), 0);
        assertEquals(-20, myEllipse.getCenter().getY(), 0);
        assertEquals(5, myEllipse.getHeight(), 0);
        assertEquals(4, myEllipse.getWidth(), 0);

        myEllipse.move(0,  0);
        assertEquals(-8, myEllipse.getCenter().getX(), 0);
        assertEquals(-20, myEllipse.getCenter().getY(), 0);
        assertEquals(5, myEllipse.getHeight(), 0);
        assertEquals(4, myEllipse.getWidth(), 0);

        try {
            myEllipse.move(Double.POSITIVE_INFINITY, 1);
            fail("Expected exception not thrown");
        } catch (Exception e) {
            // ignore
        }

        try {
            myEllipse.move(Double.NEGATIVE_INFINITY, 1);
            fail("Expected exception not thrown");
        } catch (Exception e) {
            // ignore
        }

        try {
            myEllipse.move(Double.NaN, 1);
            fail("Expected exception not thrown");
        } catch (Exception e) {
            // ignore
        }

        try {
            myEllipse.move(1, Double.POSITIVE_INFINITY);
            fail("Expected exception not thrown");
        } catch (Exception e) {
            // ignore
        }

        try {
            myEllipse.move(1, Double.NEGATIVE_INFINITY);
            fail("Expected exception not thrown");
        } catch (Exception e) {
            // ignore
        }

        try {
            myEllipse.move(1, Double.NaN);
            fail("Expected exception not thrown");
        } catch (Exception e) {
            // ignore
        }
    }

    @Test
    public void testComputeArea() throws ShapeException {
        Ellipse myEllipse = new Ellipse(new Point(1, 2), 5, 4);
        assertEquals(15.70796, myEllipse.getArea(), 0.0001);

        myEllipse = new Ellipse(new Point(1, 2), 4.234, 1.234);
        assertEquals(4.10351, myEllipse.getArea(), 0.0001);
    }

    @Test
    public void testGetFoci() throws ShapeException {
        Ellipse myEllipse = new Ellipse(new Point(1, 2), 5, 4);
        assertEquals(-0.5, myEllipse.getFoci()[0].getX(), 0.0001);
        assertEquals(2, myEllipse.getFoci()[0].getY(), 0.0001);
        assertEquals(2.5, myEllipse.getFoci()[1].getX(), 0.0001);
        assertEquals(2, myEllipse.getFoci()[1].getY(), 0.0001);

        myEllipse = new Ellipse(new Point(-1, 2), 5, 2);
        assertEquals(-3.29129, myEllipse.getFoci()[0].getX(), 0.0001);
        assertEquals(2, myEllipse.getFoci()[0].getY(), 0.0001);
        assertEquals(1.29129, myEllipse.getFoci()[1].getX(), 0.0001);
        assertEquals(2, myEllipse.getFoci()[1].getY(), 0.0001);
    }
}