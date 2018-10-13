package shapes;

import org.apache.commons.io.IOUtils;

import java.io.InputStream;
import java.io.OutputStream;

public class ShapeManager {
    public Shape loadFromScript(InputStream inputStream) throws Exception {
        String input = IOUtils.toString(inputStream, "UTF-8");
        return create(input);
    }

    public Shape create(String message) {
        try {
            return ShapeFactory.createShapeType(message);
        } catch (Exception e) {
            System.err.println("Error creating shape: " + message);
        }
        return null;
    }

    public void saveToScript(Shape shape, OutputStream outputStream) throws Exception {
        outputStream.write(shape.toString().getBytes());
    }

    public void renderToImage() {
        // TODO: finish and test
    }
}
