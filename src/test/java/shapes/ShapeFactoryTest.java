package shapes;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import static org.junit.Assert.*;

public class ShapeFactoryTest {
    @Test
    public void testCreateCompositeShapeType() throws Exception {
        ShapeManager shapeManager = new ShapeManager();
        InputStream inputStream = new FileInputStream(new File("scripts/composite1"));
        CompositeShape compositeShape = (CompositeShape)shapeManager.loadFromScript(inputStream);
        assertEquals(3, compositeShape.getShapes().size());
        assertEquals(50, compositeShape.getShapes().get(0).getArea(), 0.001);
        assertEquals(Circle.class.getTypeName(), compositeShape.getShapes().get(1).getClass().getTypeName());
        assertEquals(Triangle.class.getTypeName(), compositeShape.getShapes().get(2).getClass().getTypeName());

        inputStream = new FileInputStream(new File("scripts/composite2"));
        compositeShape = (CompositeShape)shapeManager.loadFromScript(inputStream);
        assertEquals(34, compositeShape.getArea(), 0.001);
        assertEquals(2, compositeShape.getShapes().size());
        assertEquals(9, compositeShape.getShapes().get(1).getArea(), 0.001);

        ShapeFactory shapeFactory = new ShapeFactory();
        String script = "CompositeShape,0";
        CompositeShape compositeShape1 = (CompositeShape)shapeFactory.createShapeType(script);
        assertEquals(0, compositeShape1.getShapes().size());
        assertEquals(0, compositeShape1.getArea(), 0.001);

        script = "CompositeShape,null";
        try {
            CompositeShape compositeShape2 = (CompositeShape)ShapeFactory.createShapeType(script);
            fail("Should have thrown exception");
        } catch(Exception e) {
            assertEquals("Error parsing shape CompositeShape,null", e.getMessage());
        }
    }

    @Test
    public void testCreatePointShapeType() throws Exception {
        String script = "Point,0.43,-5.6";
        Point point = (Point)ShapeFactory.createShapeType(script);
        assertEquals(0, point.getArea(), 0.001);
        assertEquals(0.43, point.getX(), 0.001);
        assertEquals(-5.6, point.getY(), 0.001);

        script = "Point,0";
        try {
            Point point1 = (Point)ShapeFactory.createShapeType(script);
            fail("Should have thrown exception");
        } catch(Exception e) {
            assertEquals("Error parsing shape Point,0", e.getMessage());
        }
    }

    @Test
    public void testCreateLineShapeType() throws Exception {
        String script = "Line,-9.99,0,0,4.5";
        Line line = (Line)ShapeFactory.createShapeType(script);
        assertEquals(-9.99, line.getVertices()[0].getX(), 0.001);
        assertEquals(0, line.getVertices()[0].getY(), 0.001);
        assertEquals(0, line.getVertices()[1].getX(), 0.001);
        assertEquals(4.5, line.getVertices()[1].getY(), 0.001);
        assertEquals(0, line.getArea(),0.001);

        script = "Line,0";
        try {
            Line line1 = (Line)ShapeFactory.createShapeType(script);
            fail("Should have thrown exception");
        } catch(Exception e) {
            assertEquals("Error parsing shape Line,0", e.getMessage());
        }
    }

    @Test
    public void testCreateEllipseShapeType() throws Exception {
        String script = "Ellipse,8,16,3.2, 4.5";
        Ellipse ellipse = (Ellipse)ShapeFactory.createShapeType(script);
        assertEquals(8, ellipse.getCenter().getX(), 0.001);
        assertEquals(16, ellipse.getCenter().getY(), 0.001);
        assertEquals(3.2, ellipse.getHeight(), 0.001);
        assertEquals(4.5, ellipse.getWidth(), 0.001);

        script = "Ellipse,0";
        try {
            Ellipse ellipse1 = (Ellipse)ShapeFactory.createShapeType(script);
            fail("Should have thrown exception");
        } catch(Exception e) {
            assertEquals("Error parsing shape Ellipse,0", e.getMessage());
        }
    }

    @Test
    public void testCreateRectangleShapeType() throws Exception {
        String script = "Rectangle,0,5,5,10";
        Rectangle rectangle = (Rectangle)ShapeFactory.createShapeType(script);
        assertEquals(50, rectangle.getArea(), 0.001);
        assertEquals(5, rectangle.getHeight(), 0.001);
        assertEquals(10, rectangle.getWidth(), 0.001);
        assertEquals(10, rectangle.getVertices()[1].getX(),0.001);
        assertEquals(5, rectangle.getVertices()[1].getY(), 0.001);

        script = "Rectangle,0";
        try {
            Rectangle rectangle1 = (Rectangle)ShapeFactory.createShapeType(script);
            fail("Should have thrown exception");
        } catch(Exception e) {
            assertEquals("Error parsing shape Rectangle,0", e.getMessage());
        }
    }

    @Test
    public void testCreateTriangleShapeType() throws Exception {
        String script = "Triangle,0,0,6,0,3,3";
        Triangle triangle = (Triangle)ShapeFactory.createShapeType(script);
        assertEquals(0, triangle.getVertices()[0].getX(), 0.001);
        assertEquals(0, triangle.getVertices()[0].getY(), 0.001);
        assertEquals(9, triangle.getArea(),0.001);

        script = "Triangle,0";
        try {
            Triangle triangle1 = (Triangle)ShapeFactory.createShapeType(script);
            fail("Should have thrown exception");
        } catch(Exception e) {
            assertEquals("Error parsing shape Triangle,0", e.getMessage());
        }
    }

    @Test
    public void testCreateCircleShapeType() throws Exception {
        String script = "Circle,6,-4.5,3";
        Circle circle = (Circle)ShapeFactory.createShapeType(script);
        assertEquals(6, circle.getCenter().getX(), 0.001);
        assertEquals(-4.5, circle.getCenter().getY(), 0.001);
        assertEquals(3, circle.getRadius(), 0.001);

        script = "Circle,0";
        try {
            Circle circle1 = (Circle)ShapeFactory.createShapeType(script);
            fail("Should have thrown exception");
        } catch(Exception e) {
            assertEquals("Error parsing shape Circle,0", e.getMessage());
        }
    }

    @Test
    public void testCreateSquareShapeType() throws Exception {
        String script = "Square,0,9,4.232";
        Square square = (Square)ShapeFactory.createShapeType(script);
        assertEquals(17.909824, square.getArea(), 0.001);
        assertEquals(4.232, square.getSideLength(), 0.001);
        assertEquals(4.232, square.getVertices()[1].getX(), 0.001);

        script = "Square,0";
        try {
            Square square1 = (Square)ShapeFactory.createShapeType(script);
            fail("Should have thrown exception");
        } catch(Exception e) {
            assertEquals("Error parsing shape Square,0", e.getMessage());
        }
    }

    @Test
    public void testBadCreateShapeType() throws Exception {
        String script = "Line,0,0,0,0";
        try {
            Line line = (Line)ShapeFactory.createShapeType(script);
            fail("Should have thrown exception");
        } catch (ShapeException e) {
            assertEquals("A line must have a length > 0", e.getMessage());
        }

        script = "Line,0,0,1,null";
        try {
            Line line = (Line)ShapeFactory.createShapeType(script);
            fail("Should have thrown exception");
        } catch (ShapeException e) {
            assertEquals("Error parsing shape Line,0,0,1,null", e.getMessage());
        }

        script = "Point,0.678";
        try {
            Point point = (Point)ShapeFactory.createShapeType(script);
            fail("Should have thrown exception");
        } catch (ShapeException e) {
            assertEquals("Error parsing shape Point,0.678", e.getMessage());
        }

        script = "Rectangle,null";
        try {
            Rectangle rectangle = (Rectangle)ShapeFactory.createShapeType(script);
            fail("Should have thrown exception");
        } catch (ShapeException e) {
            assertEquals("Error parsing shape Rectangle,null", e.getMessage());
        }

        script = "Oval,0,5,20,30";
        try {
            Shape shape = ShapeFactory.createShapeType(script);
            fail("Should have thrown exception");
        } catch (ShapeException e) {
            assertEquals("Error parsing shape Oval,0,5,20,30", e.getMessage());
        }
    }
}
