package shapes;

import org.junit.Test;

import javax.imageio.ImageIO;

import static org.junit.Assert.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;

public class EmbeddedPictureTest {
    @Test
    public void testValidConstruction() throws Exception {
        BufferedImage bufferedImage = ImageIO.read(new FileInputStream(new File("images/cars.jpg")));
        EmbeddedPicture embeddedPicture = new EmbeddedPicture(bufferedImage,"images/cars.jpg", new Point(50, 50), 25, 50);
        assertEquals(1250, embeddedPicture.getArea(), 0.001);
        assertEquals(50, embeddedPicture.getLocation().getX(), 0.001);
        assertEquals(50, embeddedPicture.getLocation().getY(), 0.001);
        assertEquals(25, embeddedPicture.getWidth(), 0.001);
        assertEquals(50, embeddedPicture.getHeight(), 0.001);

        String url = "images/toystory.jpeg";
        bufferedImage = ImageIO.read(new FileInputStream(new File(url)));
        embeddedPicture = new EmbeddedPicture(bufferedImage, url, new Point(500, 100), 100, 50);
        assertEquals(5000, embeddedPicture.getArea(), 0.001);
        assertEquals(500, embeddedPicture.getLocation().getX(), 0.001);
        assertEquals(100, embeddedPicture.getLocation().getY(), 0.001);
        assertEquals(100, embeddedPicture.getWidth(), 0.001);
        assertEquals(50, embeddedPicture.getHeight(), 0.001);
    }

    @Test
    public void testInvalidConstruction() throws Exception {
        EmbeddedPicture embeddedPicture;
        try {
            embeddedPicture = new EmbeddedPicture(null, "images/cars.jpg", new Point(50,50), 25, 25);
            fail("Should have thrown exception");
        } catch (Exception e) {
            assertEquals("Must have a buffered image", e.getMessage());
        }

        BufferedImage bufferedImage = ImageIO.read(new FileInputStream(new File("images/cars.jpg")));
        try {
            embeddedPicture = new EmbeddedPicture(bufferedImage, null, new Point(50,50), 25, 25);
            fail("Should have thrown exception");
        } catch (Exception e) {
            assertEquals("Must have a resource", e.getMessage());
        }

        try {
            embeddedPicture = new EmbeddedPicture(bufferedImage,"images/cars.jpg", null, 25, 25);
            fail("Should have thrown exception");
        } catch (Exception e) {
            assertEquals("Must have a valid location", e.getMessage());
        }

        try {
            embeddedPicture = new EmbeddedPicture(bufferedImage,"images/cars.jpg", new Point(50,50), Double.NEGATIVE_INFINITY, 25);
            fail("Should have thrown exception");
        } catch (Exception e) {
            assertEquals("Must have a valid width", e.getMessage());
        }

        try {
            embeddedPicture = new EmbeddedPicture(bufferedImage,"images/cars.jpg", new Point(50,50), Double.POSITIVE_INFINITY, 25);
            fail("Should have thrown exception");
        } catch (Exception e) {
            assertEquals("Must have a valid width", e.getMessage());
        }

        try {
            embeddedPicture = new EmbeddedPicture(bufferedImage,"images/cars.jpg", new Point(50,50), Double.NaN, 25);
            fail("Should have thrown exception");
        } catch (Exception e) {
            assertEquals("Must have a valid width", e.getMessage());
        }

        try {
            embeddedPicture = new EmbeddedPicture(bufferedImage,"images/cars.jpg", new Point(50,50), -50, 25);
            fail("Should have thrown exception");
        } catch (Exception e) {
            assertEquals("Must have a valid width", e.getMessage());
        }

        try {
            embeddedPicture = new EmbeddedPicture(bufferedImage,"images/cars.jpg", new Point(50,50), 25, Double.NEGATIVE_INFINITY);
            fail("Should have thrown exception");
        } catch (Exception e) {
            assertEquals("Must have a valid height", e.getMessage());
        }

        try {
            embeddedPicture = new EmbeddedPicture(bufferedImage,"images/cars.jpg", new Point(50,50), 25, Double.POSITIVE_INFINITY);
            fail("Should have thrown exception");
        } catch (Exception e) {
            assertEquals("Must have a valid height", e.getMessage());
        }

        try {
            embeddedPicture = new EmbeddedPicture(bufferedImage,"images/cars.jpg", new Point(50,50), 25, Double.NaN);
            fail("Should have thrown exception");
        } catch (Exception e) {
            assertEquals("Must have a valid height", e.getMessage());
        }

        try {
            embeddedPicture = new EmbeddedPicture(bufferedImage,"images/cars.jpg", new Point(50,50), 50, -25);
            fail("Should have thrown exception");
        } catch (Exception e) {
            assertEquals("Must have a valid height", e.getMessage());
        }
    }

    @Test
    public void testMove() throws Exception {
        BufferedImage bufferedImage = ImageIO.read(new FileInputStream(new File("images/cars.jpg")));
        EmbeddedPicture embeddedPicture = new EmbeddedPicture(bufferedImage, "images/cars.jpg", new Point(50, 50), 25, 50);
        assertEquals(50, embeddedPicture.getLocation().getX(), 0.001);
        assertEquals(50, embeddedPicture.getLocation().getY(), 0.001);
        embeddedPicture.move(10, -10);
        assertEquals(60, embeddedPicture.getLocation().getX(), 0.001);
        assertEquals(40, embeddedPicture.getLocation().getY(), 0.001);

        try{
            embeddedPicture.move(Double.NEGATIVE_INFINITY, 5);
            fail("Should have thrown exception");
        } catch (Exception e) {
            assertEquals("Invalid delta-x value", e.getMessage());
        }

        try{
            embeddedPicture.move(Double.POSITIVE_INFINITY, 5);
            fail("Should have thrown exception");
        } catch (Exception e) {
            assertEquals("Invalid delta-x value", e.getMessage());
        }

        try{
            embeddedPicture.move(Double.NaN, 5);
            fail("Should have thrown exception");
        } catch (Exception e) {
            assertEquals("Invalid delta-x value", e.getMessage());
        }

        try{
            embeddedPicture.move(5, Double.POSITIVE_INFINITY);
            fail("Should have thrown exception");
        } catch (Exception e) {
            assertEquals("Invalid delta-y value", e.getMessage());
        }

        try{
            embeddedPicture.move(5, Double.NEGATIVE_INFINITY);
            fail("Should have thrown exception");
        } catch (Exception e) {
            assertEquals("Invalid delta-y value", e.getMessage());
        }

        try{
            embeddedPicture.move(5, Double.NaN);
            fail("Should have thrown exception");
        } catch (Exception e) {
            assertEquals("Invalid delta-y value", e.getMessage());
        }
    }

    @Test
    public void testGetArea() throws Exception {
        BufferedImage bufferedImage = ImageIO.read(new FileInputStream(new File("images/cars.jpg")));
        EmbeddedPicture embeddedPicture = new EmbeddedPicture(bufferedImage, "images/cars.jpg", new Point(50, 50), 25, 50);
        assertEquals(1250, embeddedPicture.getArea(), 0.001);
        embeddedPicture = new EmbeddedPicture(bufferedImage, "images/cars.jpg", new Point(0,0), 1, 1);
        assertEquals(1, embeddedPicture.getArea(), 0.001);
    }

    @Test
    public void testRendering() throws Exception {
        BufferedImage bufferedImage = ImageIO.read(new FileInputStream(new File("images/cars.jpg")));
        EmbeddedPicture embeddedPicture = new EmbeddedPicture(bufferedImage, "images/cars.jpg", new Point(50, 50), 25, 50);

        BufferedImage bufferedImage2 = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = bufferedImage.createGraphics();
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, 100, 100);
        graphics.setColor(Color.BLUE);

        embeddedPicture.render(graphics);

        // Write observed results to a file so it can be manually compared
        assertTrue(ImageIO.write(bufferedImage2, "png", new File("scripts/test/renderEmbeddedPictureToImage.png")));
        // To check predicted results against observed results, view renderEmbeddedPictureToImage.png in scripts/test
        // and see if it is the top 1/8 of the cars image

        bufferedImage = ImageIO.read(new FileInputStream(new File("images/toystory.jpeg")));
        embeddedPicture = new EmbeddedPicture(bufferedImage, "images/toystory.jpeg", new Point(500, 300), 100, 500);
        bufferedImage2 = new BufferedImage(1000, 1000, BufferedImage.TYPE_INT_RGB);
        graphics = bufferedImage.createGraphics();
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, 1000, 1000);
        graphics.setColor(Color.BLUE);
        try {
            embeddedPicture.render(graphics);
            fail("Should have thrown exception");
        } catch (Exception e) {
            assertEquals("Error creating sub-image", e.getMessage());
        }
    }

    @Test
    public void testToString() throws Exception {
        BufferedImage bufferedImage = ImageIO.read(new FileInputStream(new File("images/cars.jpg")));
        EmbeddedPicture embeddedPicture = new EmbeddedPicture(bufferedImage, "images/cars.jpg", new Point(50,50),25,50);
        assertEquals("EmbeddedPicture,images/cars.jpg,50.0,50.0,25.0,50.0", embeddedPicture.toString());
    }
}
