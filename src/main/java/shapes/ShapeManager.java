package shapes;

import org.apache.commons.io.IOUtils;

import java.awt.*;
import java.io.InputStream;
import java.io.OutputStream;

public class ShapeManager {
    public Shape loadFromScript(InputStream inputStream) throws Exception {
        String input = IOUtils.toString(inputStream, "UTF-8");
        return create(input);
    }

    public Shape create(String message) throws Exception {
        return ShapeFactory.createShapeType(message);
    }

    public void saveToScript(Shape shape, OutputStream outputStream) throws ShapeException {
        try {
            outputStream.write(shape.toString().getBytes());
        } catch (Exception e) {
            throw new ShapeException("Error writing shape " + shape + " to script");
        }
    }

    public void render(Shape shape, Graphics2D graphics) throws ShapeException {
        try {
            shape.render(graphics);
        } catch(Exception e) {
            throw new ShapeException("Error rendering shape");
        }
    }
}
