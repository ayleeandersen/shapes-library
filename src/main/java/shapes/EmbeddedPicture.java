package shapes;

import java.awt.*;
import java.awt.image.BufferedImage;

public class EmbeddedPicture extends Shape {
    private String resource;
    private BufferedImage bufferedImage;
    private Point location;
    private double width;
    private double height;

    public EmbeddedPicture(BufferedImage bufferedImage, String resource, Point location, double width, double height) throws ShapeException {
        Validator.validateNotNull(location, "Must have a valid location");
        Validator.validateLineLength(height, "Must have a valid height");
        Validator.validateLineLength(width, "Must have a valid width");
        Validator.validateNotNull(resource, "Must have a resource");
        Validator.validateNotNull(bufferedImage, "Must have a buffered image");

        this.bufferedImage = bufferedImage;
        this.resource = resource;
        this.location = location;
        this.width = width;
        this.height = height;
    }

    public void move(double deltaX, double deltaY) throws ShapeException {
        location.move(deltaX, deltaY);
    }

    public double getArea() {
        return width*height;
    }

    public void render(Graphics2D graphics) throws ShapeException {
        try {
            BufferedImage subImage = bufferedImage.getSubimage((int)location.getX(), (int)location.getY(), (int)width, (int)height);
            graphics.drawImage(subImage, 0, 0, Color.WHITE, null);
        } catch (Exception e) {
            throw new ShapeException("Error creating sub-image");
        }
    }

    public Point getLocation() {
        return location;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public String toString() {
        // TODO: add to ShapeFactory and test in both regular shape and composite shape
        return "EmbeddedPicture," + resource + "," + location.getX() + "," + location.getY() + "," + width + "," + height;
    }
}
