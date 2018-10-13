package shapes;

import java.util.ArrayList;

public class CompositeShape extends Shape {
    private ArrayList<Shape> shapes = new ArrayList<>();

    public CompositeShape() {}

    public CompositeShape(ArrayList<Shape> shapes) {
        if (shapes != null) {
            this.shapes = shapes;
        }
    }

    public void add(Shape shape) {
        shapes.add(shape);
    }

    public void remove(Shape shape) {
        shapes.remove(shape);
    }

    public void removeAll() {
        shapes.clear();
    }

    public void move(double deltaX, double deltaY) throws ShapeException {
        for(Shape shape : shapes) {
            shape.move(deltaX, deltaY);
        }
    }

    public double getArea() {
        double area = 0;
        for(Shape shape : shapes) {
            area += shape.getArea();
        }
        return area;
    }

    public ArrayList<Shape> getShapes() {
        return shapes;
    }

    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("CompositeShape," + shapes.size() + "\n");
        for(Shape shape : shapes) {
            result.append(shape.toString() + "\n");
        }
        return result.toString();
    }
}
