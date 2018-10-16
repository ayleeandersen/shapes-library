package shapes;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;

public class EmbeddedPictureFlyweightFactory {
    // Intrinsic state: the resource
    // Extrinsic state: the location, the width, and the height
    private static HashMap<String, BufferedImage> bufferedImages = new HashMap<>();

    public static EmbeddedPicture createEmbeddedPicture(String resource, Point location, double width, double height) throws Exception {
        BufferedImage bufferedImage;
        if (bufferedImages.containsKey(resource)) {
            bufferedImage = bufferedImages.get(resource);
        } else {
            try {
                bufferedImage = ImageIO.read(new FileInputStream(new File(resource)));
                bufferedImages.put(resource, bufferedImage);
            } catch (Exception e) {
                throw new ShapeException("Cannot read image from source");
            }
        }
        return new EmbeddedPicture(bufferedImage, resource, location, width, height);
    }

    public static HashMap<String, BufferedImage> getBufferedImages() { return bufferedImages; }
}
