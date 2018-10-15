package shapes;

import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

import static org.junit.Assert.*;

public class CircleTest {

    @Test
    public void testValidConstruction() throws ShapeException {
        Point center = new Point(1,2);
        Circle myCircle = new Circle(center, 5);
        assertSame(center, myCircle.getCenter());
        assertEquals(5, myCircle.getRadius(), 0);

        myCircle = new Circle(1.3, 2.6, 2.5);
        assertEquals(1.3, myCircle.getCenter().getX(), 0);
        assertEquals(2.6, myCircle.getCenter().getY(), 0);
        assertEquals(2.5, myCircle.getRadius(), 0);
    }

    @Test
    public void testInvalidConstruction() {

        try {
            new Circle(null, 2.5);
            fail("Expected exception not thrown");
        } catch (ShapeException e) {
            assertEquals("Must have a valid center point", e.getMessage());
        }

        try {
            new Circle(new Point(1, 2), 0);
            fail("Expected exception not thrown");
        } catch (ShapeException e) {
            assertEquals("Must have a positive height", e.getMessage());
        }

        try {
            new Circle( new Point(1, 2), Double.POSITIVE_INFINITY);
            fail("Expected exception not thrown");
        } catch (ShapeException e) {
            assertEquals("Must have a positive height", e.getMessage());
        }

        try {
            new Circle(new Point(1, 2), Double.NEGATIVE_INFINITY);
            fail("Expected exception not thrown");
        } catch (ShapeException e) {
            assertEquals("Must have a positive height", e.getMessage());
        }

        try {
            new Circle(new Point(1, 2), Double.NaN);
            fail("Expected exception not thrown");
        } catch (ShapeException e) {
            assertEquals("Must have a positive height", e.getMessage());
        }

        try {
            new Circle(Double.POSITIVE_INFINITY, 2, 3);
            fail("Expected exception not thrown");
        } catch (ShapeException e) {
            assertEquals("Invalid x-location", e.getMessage());
        }

        try {
            new Circle(Double.NEGATIVE_INFINITY, 2, 3);
            fail("Expected exception not thrown");
        } catch (ShapeException e) {
            assertEquals("Invalid x-location", e.getMessage());
        }

        try {
            new Circle(Double.NaN, 2, 3);
            fail("Expected exception not thrown");
        } catch (ShapeException e) {
            assertEquals("Invalid x-location", e.getMessage());
        }

        try {
            new Circle(1, Double.POSITIVE_INFINITY, 3);
            fail("Expected exception not thrown");
        } catch (ShapeException e) {
            assertEquals("Invalid y-location", e.getMessage());
        }

        try {
            new Circle(1, Double.NEGATIVE_INFINITY, 3);
            fail("Expected exception not thrown");
        } catch (ShapeException e) {
            assertEquals("Invalid y-location", e.getMessage());
        }

        try {
            new Circle(1, Double.NaN, 3);
            fail("Expected exception not thrown");
        } catch (ShapeException e) {
            assertEquals("Invalid y-location", e.getMessage());
        }
    }

    @Test
    public void testMove() throws Exception {
        Circle myCircle = new Circle(1, 2, 5);
        assertEquals(1, myCircle.getCenter().getX(), 0);
        assertEquals(2, myCircle.getCenter().getY(), 0);
        assertEquals(5, myCircle.getRadius(), 0);

        myCircle.move(3,  4);
        assertEquals(4, myCircle.getCenter().getX(), 0);
        assertEquals(6, myCircle.getCenter().getY(), 0);
        assertEquals(5, myCircle.getRadius(), 0);

        myCircle.move(0.123,  0.456);
        assertEquals(4.123, myCircle.getCenter().getX(), 0);
        assertEquals(6.456, myCircle.getCenter().getY(), 0);
        assertEquals(5, myCircle.getRadius(), 0);

        myCircle.move(-0.123,  -0.456);
        assertEquals(4, myCircle.getCenter().getX(), 0);
        assertEquals(6, myCircle.getCenter().getY(), 0);
        assertEquals(5, myCircle.getRadius(), 0);

        myCircle.move(-12,  -26);
        assertEquals(-8, myCircle.getCenter().getX(), 0);
        assertEquals(-20, myCircle.getCenter().getY(), 0);
        assertEquals(5, myCircle.getRadius(), 0);

        myCircle.move(0,  0);
        assertEquals(-8, myCircle.getCenter().getX(), 0);
        assertEquals(-20, myCircle.getCenter().getY(), 0);
        assertEquals(5, myCircle.getRadius(), 0);

        try {
            myCircle.move(Double.POSITIVE_INFINITY, 1);
            fail("Expected exception not thrown");
        } catch (ShapeException e) {
            assertEquals("Invalid delta-x value", e.getMessage());
        }

        try {
            myCircle.move(Double.NEGATIVE_INFINITY, 1);
            fail("Expected exception not thrown");
        } catch (ShapeException e) {
            assertEquals("Invalid delta-x value", e.getMessage());
        }

        try {
            myCircle.move(Double.NaN, 1);
            fail("Expected exception not thrown");
        } catch (ShapeException e) {
            assertEquals("Invalid delta-x value", e.getMessage());
        }

        try {
            myCircle.move(1, Double.POSITIVE_INFINITY);
            fail("Expected exception not thrown");
        } catch (ShapeException e) {
            assertEquals("Invalid delta-y value", e.getMessage());
        }

        try {
            myCircle.move(1, Double.NEGATIVE_INFINITY);
            fail("Expected exception not thrown");
        } catch (ShapeException e) {
            assertEquals("Invalid delta-y value", e.getMessage());
        }

        try {
            myCircle.move(1, Double.NaN);
            fail("Expected exception not thrown");
        } catch (ShapeException e) {
            assertEquals("Invalid delta-y value", e.getMessage());
        }

    }

    @Test
    public void testComputeArea() throws ShapeException {
        Circle myCircle = new Circle(1, 2, 5);
        assertEquals(78.53975, myCircle.getArea(), 0.0001);

        myCircle = new Circle(1, 2, 4.234);
        assertEquals(56.3185174, myCircle.getArea(), 0.0001);
    }

    @Test
    public void testRender() throws Exception {
        ShapeManager shapeManager = new ShapeManager();
        Circle circle = new Circle(10, 15, 5);

        BufferedImage bufferedImage = new BufferedImage(50, 50, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = bufferedImage.createGraphics();
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, 50, 50);
        graphics.setColor(Color.BLUE);

        shapeManager.render(circle, graphics);

        // Write observed results to a file so it can be manually compared
        assertTrue(ImageIO.write(bufferedImage, "png", new File("scripts/test/renderCircleToImage.png")));
        // To check predicted results against observed results, view renderCircleToImage.png in scripts/test
        // as see if there is a circle centered on (10,15) and with a radius of 5.
    }

    @Test
    public void testGetFoci() throws ShapeException {
        Circle myCircle = new Circle(1, 2, 5);
        assertEquals(1, myCircle.getFoci()[0].getX(), 0.0001);
        assertEquals(2, myCircle.getFoci()[0].getY(), 0.0001);
        assertEquals(1, myCircle.getFoci()[1].getX(), 0.0001);
        assertEquals(2, myCircle.getFoci()[1].getY(), 0.0001);

        myCircle = new Circle(-1, 2, 2);
        assertEquals(-1, myCircle.getFoci()[0].getX(), 0.0001);
        assertEquals(2, myCircle.getFoci()[0].getY(), 0.0001);
        assertEquals(-1, myCircle.getFoci()[1].getX(), 0.0001);
        assertEquals(2, myCircle.getFoci()[1].getY(), 0.0001);
    }

    @Test
    public void testToString() throws ShapeException {
        Circle myCircle = new Circle(1,2,5);
        assertEquals("Circle,1.0,2.0,5.0", myCircle.toString());
    }
}