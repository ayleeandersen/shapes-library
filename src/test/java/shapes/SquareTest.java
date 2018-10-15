package shapes;

import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

import static org.junit.Assert.*;

public class SquareTest {

    @Test
    public void testValidConstruction() throws Exception {
        Point corner = new Point(1,2);
        Square mySquare = new Square(corner, 2);
        assertEquals(corner, mySquare.getVertices()[0]);
        assertEquals(2, mySquare.getHeight(), 0);
        assertEquals(2, mySquare.getWidth(), 0);
        assertEquals(2, mySquare.getSideLength(), 0);

        corner = new Point(-1,-2.4);
        mySquare = new Square(corner, 3);
        assertEquals(corner, mySquare.getVertices()[0]);
        assertEquals(3, mySquare.getHeight(), 0);
        assertEquals(3, mySquare.getWidth(), 0);
        assertEquals(3, mySquare.getWidth(), 0);
    }

    @Test
    public void testInvalidConstruction() {

        try {
            new Square(null, 2.5);
            fail("Expected exception not thrown");
        } catch (ShapeException e) {
            assertEquals("Must have a valid top, left vertex", e.getMessage());
        }

        try {
            new Square(new Point(1, 2), 0);
            fail("Expected exception not thrown");
        } catch (ShapeException e) {
            assertEquals("Must have a positive height", e.getMessage());
        }

        try {
            new Square( new Point(1, 2), Double.POSITIVE_INFINITY);
            fail("Expected exception not thrown");
        } catch (ShapeException e) {
            assertEquals("Must have a positive height", e.getMessage());
        }

        try {
            new Square(new Point(1, 2), Double.NEGATIVE_INFINITY);
            fail("Expected exception not thrown");
        } catch (ShapeException e) {
            assertEquals("Must have a positive height", e.getMessage());
        }

        try {
            new Square(new Point(1, 2), Double.NaN);
            fail("Expected exception not thrown");
        } catch (ShapeException e) {
            assertEquals("Must have a positive height", e.getMessage());
        }
    }

    @Test
    public void testMove() throws ShapeException {
        Square mySquare = new Square(new Point(1, 2), 5);
        assertEquals(1, mySquare.getVertices()[0].getX(), 0);
        assertEquals(2, mySquare.getVertices()[0].getY(), 0);
        assertEquals(6, mySquare.getVertices()[1].getX(), 0);
        assertEquals(2, mySquare.getVertices()[1].getY(), 0);
        assertEquals(1, mySquare.getVertices()[2].getX(), 0);
        assertEquals(7, mySquare.getVertices()[2].getY(), 0);
        assertEquals(6, mySquare.getVertices()[3].getX(), 0);
        assertEquals(7, mySquare.getVertices()[3].getY(), 0);
        assertEquals(5, mySquare.getSideLength(), 0);

        mySquare.move(3,  4);
        assertEquals(4, mySquare.getVertices()[0].getX(), 0);
        assertEquals(6, mySquare.getVertices()[0].getY(), 0);
        assertEquals(9, mySquare.getVertices()[1].getX(), 0);
        assertEquals(6, mySquare.getVertices()[1].getY(), 0);
        assertEquals(4, mySquare.getVertices()[2].getX(), 0);
        assertEquals(11, mySquare.getVertices()[2].getY(), 0);
        assertEquals(9, mySquare.getVertices()[3].getX(), 0);
        assertEquals(11, mySquare.getVertices()[3].getY(), 0);

        mySquare.move(0.123,  0.456);
        assertEquals(4.123, mySquare.getVertices()[0].getX(), 0);
        assertEquals(6.456, mySquare.getVertices()[0].getY(), 0);
        assertEquals(9.123, mySquare.getVertices()[1].getX(), 0.00001);
        assertEquals(6.456, mySquare.getVertices()[1].getY(), 0);
        assertEquals(4.123, mySquare.getVertices()[2].getX(), 0);
        assertEquals(11.456, mySquare.getVertices()[2].getY(), 0);
        assertEquals(9.123, mySquare.getVertices()[3].getX(), 0.00001);
        assertEquals(11.456, mySquare.getVertices()[3].getY(), 0);

        mySquare.move(-0.123,  -0.456);
        assertEquals(4, mySquare.getVertices()[0].getX(), 0);
        assertEquals(6, mySquare.getVertices()[0].getY(), 0);
        assertEquals(9, mySquare.getVertices()[1].getX(), 0);
        assertEquals(6, mySquare.getVertices()[1].getY(), 0);
        assertEquals(4, mySquare.getVertices()[2].getX(), 0);
        assertEquals(11, mySquare.getVertices()[2].getY(), 0);
        assertEquals(9, mySquare.getVertices()[3].getX(), 0);
        assertEquals(11, mySquare.getVertices()[3].getY(), 0);

        mySquare.move(-12,  -26);
        assertEquals(-8, mySquare.getVertices()[0].getX(), 0);
        assertEquals(-20, mySquare.getVertices()[0].getY(), 0);
        assertEquals(-3, mySquare.getVertices()[1].getX(), 0);
        assertEquals(-20, mySquare.getVertices()[1].getY(), 0);
        assertEquals(-8, mySquare.getVertices()[2].getX(), 0);
        assertEquals(-15, mySquare.getVertices()[2].getY(), 0);
        assertEquals(-3, mySquare.getVertices()[3].getX(), 0);
        assertEquals(-15, mySquare.getVertices()[3].getY(), 0);

        mySquare.move(0,  0);
        assertEquals(-8, mySquare.getVertices()[0].getX(), 0);
        assertEquals(-20, mySquare.getVertices()[0].getY(), 0);
        assertEquals(-3, mySquare.getVertices()[1].getX(), 0);
        assertEquals(-20, mySquare.getVertices()[1].getY(), 0);
        assertEquals(-8, mySquare.getVertices()[2].getX(), 0);
        assertEquals(-15, mySquare.getVertices()[2].getY(), 0);
        assertEquals(-3, mySquare.getVertices()[3].getX(), 0);
        assertEquals(-15, mySquare.getVertices()[3].getY(), 0);

        try {
            mySquare.move(Double.POSITIVE_INFINITY, 1);
            fail("Expected exception not thrown");
        } catch (ShapeException e) {
            assertEquals("Invalid delta-x value", e.getMessage());
        }

        try {
            mySquare.move(Double.NEGATIVE_INFINITY, 1);
            fail("Expected exception not thrown");
        } catch (ShapeException e) {
            assertEquals("Invalid delta-x value", e.getMessage());
        }

        try {
            mySquare.move(Double.NaN, 1);
            fail("Expected exception not thrown");
        } catch (ShapeException e) {
            assertEquals("Invalid delta-x value", e.getMessage());
        }

        try {
            mySquare.move(1, Double.POSITIVE_INFINITY);
            fail("Expected exception not thrown");
        } catch (ShapeException e) {
            assertEquals("Invalid delta-y value", e.getMessage());
        }

        try {
            mySquare.move(1, Double.NEGATIVE_INFINITY);
            fail("Expected exception not thrown");
        } catch (ShapeException e) {
            assertEquals("Invalid delta-y value", e.getMessage());
        }

        try {
            mySquare.move(1, Double.NaN);
            fail("Expected exception not thrown");
        } catch (ShapeException e) {
            assertEquals("Invalid delta-y value", e.getMessage());
        }
    }

    @Test
    public void testComputeArea() throws ShapeException {
        Square mySquare = new Square(new Point(1, 2), 5);
        assertEquals(25, mySquare.getArea(), 0.0001);

        mySquare = new Square(new Point(1, 2), 1.234);
        assertEquals(1.522756, mySquare.getArea(), 0.0001);
    }

    @Test
    public void testRender() throws Exception {
        ShapeManager shapeManager = new ShapeManager();
        Square square = new Square(new Point(50, 50), 30);

        BufferedImage bufferedImage = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = bufferedImage.createGraphics();
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, 100, 100);
        graphics.setColor(Color.BLUE);

        shapeManager.render(square, graphics);

        // Write observed results to a file so it can be manually compared
        assertTrue(ImageIO.write(bufferedImage, "png", new File("scripts/test/renderSquareToImage.png")));
        // To check predicted results against observed results, view renderSquareToImage.png in scripts/test
        // and see if it's centered on (65, 65) with a side length of 30.
    }

    @Test
    public void testGetVertices() throws ShapeException {
        Square mySquare = new Square(new Point(1, 2), 4);
        assertEquals(1, mySquare.getVertices()[0].getX(), 0.0001);
        assertEquals(2, mySquare.getVertices()[0].getY(), 0.0001);
        assertEquals(5, mySquare.getVertices()[1].getX(), 0.0001);
        assertEquals(2, mySquare.getVertices()[1].getY(), 0.0001);
        assertEquals(1, mySquare.getVertices()[2].getX(), 0.0001);
        assertEquals(6, mySquare.getVertices()[2].getY(), 0.0001);
        assertEquals(5, mySquare.getVertices()[3].getX(), 0.0001);
        assertEquals(6, mySquare.getVertices()[3].getY(), 0.0001);
    }

    @Test
    public void testToString() throws ShapeException {
        Square square = new Square(new Point(4, -2.4), 6);
        assertEquals("Square,4.0,-2.4,6.0", square.toString());
    }
}