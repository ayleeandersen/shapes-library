package shapes;

import org.junit.Test;

import static org.junit.Assert.*;

public class EmbeddedPictureFlyweightFactoryTest {
    @Test
    public void testCreateEmbeddedPicture() throws Exception {
        EmbeddedPicture embeddedPicture = EmbeddedPictureFlyweightFactory.createEmbeddedPicture("images/cars.jpg", new Point(0,0), 200, 200);
        assertEquals(40000, embeddedPicture.getArea(), 0.001);
        assertEquals(0, embeddedPicture.getLocation().getX(), 0.001);
        assertEquals(0, embeddedPicture.getLocation().getY(), 0.001);

        embeddedPicture = EmbeddedPictureFlyweightFactory.createEmbeddedPicture("images/cars.jpg", new Point(50,55), 100,50);
        assertEquals(5000, embeddedPicture.getArea(), 0.001);
        assertEquals(50, embeddedPicture.getLocation().getX(), 0.001);
        assertEquals(55, embeddedPicture.getLocation().getY(), 0.001);

        try {
            embeddedPicture = EmbeddedPictureFlyweightFactory.createEmbeddedPicture("images/bad.img", new Point(50,50), 100, 50);
            fail("Should have thrown exception");
        } catch (Exception e) {
            assertEquals("Cannot read image from source", e.getMessage());
        }

        try {
            embeddedPicture = EmbeddedPictureFlyweightFactory.createEmbeddedPicture(null, new Point(50,50), 100, 50);
            fail("Should have thrown exception");
        } catch (Exception e) {
            assertEquals("Cannot read image from source", e.getMessage());
        }

        try {
            embeddedPicture = EmbeddedPictureFlyweightFactory.createEmbeddedPicture("images/cars.jpg", null, 100, 50);
            fail("Should have thrown exception");
        } catch (Exception e) {
            assertEquals("Must have a valid location", e.getMessage());
            // this is throwing the exceptions in the EmbeddedPicture class, so we know they are being handled. More thorough tests can be found in EmbeddedPictureTest
        }
    }

    @Test
    public void testGetBufferedImages() throws Exception {
        int size = EmbeddedPictureFlyweightFactory.getBufferedImages().size();
        assertEquals(size, EmbeddedPictureFlyweightFactory.getBufferedImages().size());

        String image = EmbeddedPictureFlyweightFactory.getBufferedImages().containsKey("images/cars.jpg") ? "cars" : "toystory";

        EmbeddedPictureFlyweightFactory.createEmbeddedPicture("images/cars.jpg", new Point(0,0), 200,200);
        if (image.equals("cars")) {
            assertEquals(size, EmbeddedPictureFlyweightFactory.getBufferedImages().size());
        } else {
            size++;
            assertEquals(size, EmbeddedPictureFlyweightFactory.getBufferedImages().size());
        }
        assertTrue(EmbeddedPictureFlyweightFactory.getBufferedImages().containsKey("images/cars.jpg"));

        EmbeddedPictureFlyweightFactory.createEmbeddedPicture("images/cars.jpg", new Point(50,50), 100, 50);
        assertEquals(size, EmbeddedPictureFlyweightFactory.getBufferedImages().size());

        EmbeddedPictureFlyweightFactory.createEmbeddedPicture("images/toystory.jpeg", new Point(100,100), 50, 200);
        if (image.equals("toystory")) {
            assertEquals(size, EmbeddedPictureFlyweightFactory.getBufferedImages().size());
        } else {
            size++;
            assertEquals(size, EmbeddedPictureFlyweightFactory.getBufferedImages().size());
        }
        assertTrue(EmbeddedPictureFlyweightFactory.getBufferedImages().containsKey("images/toystory.jpeg"));
    }
}
