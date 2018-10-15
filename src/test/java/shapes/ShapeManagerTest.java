package shapes;

import org.junit.Test;
import static org.junit.Assert.*;

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
    }
}
