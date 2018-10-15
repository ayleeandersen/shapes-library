package shapes;

import org.junit.Test;

import javax.imageio.ImageIO;

import static org.junit.Assert.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;

public class ShapeManagerTest {
    @Test
    public void testLoadFileFromScript() throws Exception {
        ShapeManager shapeManager = new ShapeManager();
        String script = "Rectangle,0,5,5,10";
        InputStream inputStream = new ByteArrayInputStream(script.getBytes());
        Rectangle rectangle = (Rectangle)shapeManager.loadFromScript(inputStream);
        assertEquals(50, rectangle.getArea(), 0.001);
        assertEquals(5, rectangle.getHeight(), 0.001);

        inputStream = new FileInputStream(new File("scripts/composite1"));
        CompositeShape compositeShape = (CompositeShape)shapeManager.loadFromScript(inputStream);
        assertEquals(3, compositeShape.getShapes().size());
        assertEquals(50, compositeShape.getShapes().get(0).getArea(), 0.001);
        assertEquals(Circle.class.getTypeName(), compositeShape.getShapes().get(1).getClass().getTypeName());
        assertEquals(Triangle.class.getTypeName(), compositeShape.getShapes().get(2).getClass().getTypeName());

        try {
            inputStream = new FileInputStream(new File("scripts/bad"));
            fail("Should have thrown exception");
        } catch (Exception e) {
            assertEquals("scripts/bad (No such file or directory)", e.getMessage());
        }

        script = "";
        inputStream = new ByteArrayInputStream(script.getBytes());
        try {
            Shape shape = shapeManager.loadFromScript(inputStream);
            fail("Should have thrown exception");
        } catch (Exception e) {
            assertEquals("Error parsing shape ", e.getMessage());
        }
    }

    @Test
    public void testCreate() throws Exception {
        ShapeManager shapeManager = new ShapeManager();
        String script = "Rectangle,0,5,5,10";
        Rectangle rectangle = (Rectangle)shapeManager.create(script);
        assertEquals(50, rectangle.getArea(), 0.001);
        assertEquals(5, rectangle.getHeight(), 0.001);
        rectangle.move(1, 1);
        assertEquals(1, rectangle.getVertices()[0].getX(), 0.001);
        assertEquals(6, rectangle.getVertices()[0].getY(), 0.001);

        script = "Line,0,0,0,0";
        try {
            shapeManager.create(script);
            fail("Should have thrown exception");
        } catch (Exception e) {
            assertEquals("A line must have a length > 0", e.getMessage());
        }

        script = "Rectangle,null";
        try {
            shapeManager.create(script);
            fail("Should have thrown exception");
        } catch (Exception e) {
            assertEquals("Error parsing shape Rectangle,null", e.getMessage());
        }
    }

    @Test
    public void testSaveToScript() throws Exception {
        ShapeManager shapeManager = new ShapeManager();
        Ellipse ellipse = new Ellipse(new Point(5,10),2,3);
        ByteArrayOutputStream result = new ByteArrayOutputStream();
        shapeManager.saveToScript(ellipse, result);
        assertEquals("Ellipse,5.0,10.0,2.0,3.0", result.toString());

        ArrayList<Shape> shapes = new ArrayList<Shape>();
        shapes.add(new Rectangle(new Point(1,2),1,1));
        shapes.add(new Circle(new Point(3, 2.3), 5));
        shapes.add(new Triangle(new Point(1,1), new Point(-4, 2), new Point(-5, -8)));
        CompositeShape compositeShape1 = new CompositeShape(shapes);

        File file = new File("scripts/test/testSaveToScript.txt");
        shapeManager.saveToScript(compositeShape1, new FileOutputStream(file));
        assertTrue(file.exists());
        assertEquals("CompositeShape,3\n" +
                "Rectangle,1.0,2.0,1.0,1.0\n" +
                "Circle,3.0,2.3,5.0\n" +
                "Triangle,1.0,1.0,-4.0,2.0,-5.0,-8.0\n",
                compositeShape1.toString());

        file = new File("scripts/test/testSaveBadShape.txt");
        try {
            shapeManager.saveToScript(null, new FileOutputStream(file));
            fail("Should have thrown exception");
        } catch (Exception e) {
            assertEquals("Error writing shape null to script", e.getMessage());
        }
    }

    @Test
    public void testRender() throws Exception {
        ShapeManager shapeManager = new ShapeManager();

        Circle circle = new Circle(15, 15, 10);
        BufferedImage bufferedImage = new BufferedImage(50, 50, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = bufferedImage.createGraphics();
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, 50, 50);
        graphics.setColor(Color.BLUE);

        shapeManager.render(circle, graphics);

        // Write observed results to a file so it can be manually compared
        assertTrue(ImageIO.write(bufferedImage, "png", new File("scripts/test/shapeManagerTestToImage.png")));
        // To check predicted results against observed results, view shapeManagerTestToImage.png in scripts/test
        // as see if there is a circle centered on (15,15) and with a radius of 10.

        circle = null;
        try {
            shapeManager.render(circle, graphics);
            fail("Should have thrown exception");
        } catch (Exception e) {
            assertEquals("Error rendering shape", e.getMessage());
        }

        circle = new Circle(15,15,10);
        try {
            shapeManager.render(circle, null);
            fail("Should have thrown exception");
        } catch (Exception e) {
            assertEquals("Error rendering shape", e.getMessage());
        }
    }
}
