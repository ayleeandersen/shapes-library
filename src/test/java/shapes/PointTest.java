package shapes;

import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

import static org.junit.Assert.*;

public class PointTest {

    @Test
    public void testValidConstruction() throws Exception {
        Point p1 = new Point(1,2);
        assertEquals(1, p1.getX(), 0);
        assertEquals(2, p1.getY(), 0);

        p1 = new Point(1.111,2.222);
        assertEquals(1.111, p1.getX(), 0);
        assertEquals(2.222, p1.getY(), 0);
    }

    @Test
    public void testInvalidConstruction() {

        try {
            new Point(1,Double.POSITIVE_INFINITY);
            fail("Expected exception not thrown");
        } catch (Exception e) {
            assertEquals("Invalid y-location", e.getMessage());
        }

        try {
            new Point(1,Double.NEGATIVE_INFINITY);
            fail("Expected exception not thrown");
        } catch (Exception e) {
            assertEquals("Invalid y-location", e.getMessage());
        }

        try {
            new Point(1,Double.NaN);
            fail("Expected exception not thrown");
        } catch (Exception e) {
            assertEquals("Invalid y-location", e.getMessage());
        }

        try {
            new Point(Double.POSITIVE_INFINITY, 1);
            fail("Expected exception not thrown");
        } catch (Exception e) {
            assertEquals("Invalid x-location", e.getMessage());
        }

        try {
            new Point(Double.NEGATIVE_INFINITY, 1);
            fail("Expected exception not thrown");
        } catch (Exception e) {
            assertEquals("Invalid x-location", e.getMessage());
        }

        try {
            new Point(Double.NaN, 1);
            fail("Expected exception not thrown");
        } catch (Exception e) {
            assertEquals("Invalid x-location", e.getMessage());
        }

    }

    @Test
    public void testMove() throws Exception {
        Point p1 = new Point(1,2);

        p1.move(10, 20);
        assertEquals(11, p1.getX(), 0);
        assertEquals(22, p1.getY(), 0);

        p1.move(0.222, 0.333);
        assertEquals(11.222, p1.getX(), 0);
        assertEquals(22.333, p1.getY(), 0);

        p1.move(-0.222, -0.333);
        assertEquals(11, p1.getX(), 0);
        assertEquals(22, p1.getY(), 0);

        p1.move(-20, -30);
        assertEquals(-9, p1.getX(), 0);
        assertEquals(-8, p1.getY(), 0);

        p1.move(-2, 0);
        assertEquals(-11, p1.getX(), 0);
        assertEquals(-8, p1.getY(), 0);

        p1.move(0, -3);
        assertEquals(-11, p1.getX(), 0);
        assertEquals(-11, p1.getY(), 0);

        try {
            p1.move(1, Double.POSITIVE_INFINITY);
            fail("Expected exception not thrown");
        } catch (Exception e) {
            assertEquals("Invalid delta-y value", e.getMessage());
        }

        try {
            p1.move(1, Double.NEGATIVE_INFINITY);
            fail("Expected exception not thrown");
        } catch (Exception e) {
            assertEquals("Invalid delta-y value", e.getMessage());
        }

        try {
            p1.move(1, Double.NaN);
            fail("Expected exception not thrown");
        } catch (Exception e) {
            assertEquals("Invalid delta-y value", e.getMessage());
        }

        try {
            p1.move(Double.POSITIVE_INFINITY, 1);
            fail("Expected exception not thrown");
        } catch (Exception e) {
            assertEquals("Invalid delta-x value", e.getMessage());
        }

        try {
            p1.move(Double.NEGATIVE_INFINITY,1 );
            fail("Expected exception not thrown");
        } catch (Exception e) {
            assertEquals("Invalid delta-x value", e.getMessage());
        }

        try {
            p1.move(Double.NaN,1);
            fail("Expected exception not thrown");
        } catch (Exception e) {
            assertEquals("Invalid delta-x value", e.getMessage());
        }

    }

    @Test
    public void getArea() throws Exception {
        Point p1 = new Point(-123, 314512);
        assertTrue(p1.getArea() == 0);
    }

    @Test
    public void testRender() throws Exception {
        ShapeManager shapeManager = new ShapeManager();
        Point point = new Point(5, 5);

        BufferedImage bufferedImage = new BufferedImage(10, 10, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = bufferedImage.createGraphics();
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, 10, 10);
        graphics.setColor(Color.BLUE);

        shapeManager.render(point, graphics);

        // Write observed results to a file so it can be manually compared
        assertTrue(ImageIO.write(bufferedImage, "png", new File("scripts/test/renderPointToImage.png")));
        // To check predicted results against observed results, view renderPointToImage.png in scripts/test
        // as see if there is a point at (5,5)
    }

    @Test
    public void testClone() throws Exception {
        Point p1 = new Point(-123.45,-23.45);
        assertEquals(-123.45, p1.getX(), 0);
        assertEquals(-23.45, p1.getY(), 0);

        Point p2 = p1.duplicate();
        assertNotSame(p1, p2);
        assertEquals(p1.getX(), p2.getX(), 0);
        assertEquals(p1.getY(), p2.getY(), 0);
    }

    @Test
    public void testToString() throws Exception {
        Point point = new Point(0.25, -9.9999);
        assertEquals("Point,0.25,-9.9999", point.toString());
    }
}