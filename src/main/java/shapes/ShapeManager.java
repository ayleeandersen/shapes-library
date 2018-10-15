package shapes;

import org.apache.commons.io.IOUtils;

import java.io.InputStream;
import java.io.OutputStream;

public class ShapeManager {
    public Shape loadFromScript(InputStream inputStream) throws Exception {
        String input = IOUtils.toString(inputStream, "UTF-8");
        // TODO: test bad script
        return create(input);
    }

    public Shape create(String message) {
        try {
            return ShapeFactory.createShapeType(message);
        } catch (Exception e) {
            //TODO: test invalid (Bad) script
            System.err.println("Error creating shape: " + message);
        }
        return null;
    }

    public void saveToScript(Shape shape, OutputStream outputStream) throws Exception {
        outputStream.write(shape.toString().getBytes());
        // TODO: test null shape
    }

    public void renderToImage() {
        // TODO: finish and test
    }
}
