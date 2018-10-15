package shapes;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;

public class ShapeFactory {
    public static Shape createShapeType(String message) throws Exception {
        ArrayList<String> messageList = new ArrayList<String>(Arrays.asList(message.replace("\n", ",").split(",")));
        try {
            switch (messageList.get(0)) {
                case "CompositeShape":
                    ArrayList<String> lines = new ArrayList<>(Arrays.asList(message.split("\n")));
                    Constructor<CompositeShape> compositeShape;
                    if (Integer.parseInt(lines.get(0).split(",")[1]) > 0) {
                        compositeShape = CompositeShape.class.getConstructor(ArrayList.class);
                        ArrayList<Shape> shapes = new ArrayList<>();
                        for (int line = 1; line < lines.size(); line++) {
                            message = message.replaceFirst(".*\\n", "");
                            shapes.add(createShapeType(message));
                            if (lines.get(line).startsWith("CompositeShape")) {
                                break;
                            }
                        }
                        return compositeShape.newInstance(shapes);
                    } else {
                        compositeShape = CompositeShape.class.getConstructor();
                        return compositeShape.newInstance();
                    }
                case "Point":
                    Constructor<Point> point = Point.class.getConstructor(double.class, double.class);
                    return point.newInstance(Double.parseDouble(messageList.get(1)), Double.parseDouble(messageList.get(2)));
                case "Line":
                    Constructor<Line> line = Line.class.getConstructor(double.class, double.class, double.class, double.class);
                    return line.newInstance(Double.parseDouble(messageList.get(1)), Double.parseDouble(messageList.get(2)),
                            Double.parseDouble(messageList.get(3)), Double.parseDouble(messageList.get(4)));
                case "Ellipse":
                    Constructor<Ellipse> ellipse = Ellipse.class.getConstructor(Point.class, double.class, double.class);
                    return ellipse.newInstance(new Point(Double.parseDouble(messageList.get(1)), Double.parseDouble(messageList.get(2))),
                            Double.parseDouble(messageList.get(3)), Double.parseDouble(messageList.get(4)));
                case "Rectangle":
                    Constructor<Rectangle> rectangle = Rectangle.class.getConstructor(Point.class, double.class, double.class);
                    return rectangle.newInstance(new Point(Double.parseDouble(messageList.get(1)), Double.parseDouble(messageList.get(2))),
                            Double.parseDouble(messageList.get(3)), Double.parseDouble(messageList.get(4)));
                case "Triangle":
                    Constructor<Triangle> triangle = Triangle.class.getConstructor(Point.class, Point.class, Point.class);
                    return triangle.newInstance(new Point(Double.parseDouble(messageList.get(1)), Double.parseDouble(messageList.get(2))),
                            new Point(Double.parseDouble(messageList.get(3)), Double.parseDouble(messageList.get(4))),
                            new Point(Double.parseDouble(messageList.get(5)), Double.parseDouble(messageList.get(6))));
                case "Circle":
                    Constructor<Circle> circle = Circle.class.getConstructor(double.class, double.class, double.class);
                    return circle.newInstance(Double.parseDouble(messageList.get(1)), Double.parseDouble(messageList.get(2)), Double.parseDouble(messageList.get(3)));
                case "Square":
                    Constructor<Square> square = Square.class.getConstructor(Point.class, double.class);
                    return square.newInstance(new Point(Double.parseDouble(messageList.get(1)), Double.parseDouble(messageList.get(2))), Double.parseDouble(messageList.get(3)));
                default:
                    return null;
            }
        } catch(InvocationTargetException e) {
                throw new ShapeException(e.getTargetException().getMessage());
        } catch(Exception e) {
            throw new ShapeException("Error parsing shape " + message);
        }
    }
}
