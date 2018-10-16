package shapes;

import org.junit.Test;

import static org.junit.Assert.*;

public class ValidatorTest {

    @Test
    public void testValidateDouble() throws ShapeException {
        Validator.validateDouble(123, "Test message");
        Validator.validateDouble(0.00123, "Test message");
        Validator.validateDouble(-123, "Test message");
        Validator.validateDouble(-0.00123, "Test messagee");

        try {
            Validator.validateDouble(Double.POSITIVE_INFINITY,"Invalid");
            fail("Expected exception not thrown");
        } catch (ShapeException ex) {
            assertEquals("Invalid", ex.getMessage());
        }

        try {
            Validator.validateDouble(Double.NEGATIVE_INFINITY,"Invalid");
            fail("Expected exception not thrown");
        } catch (ShapeException ex) {
            assertEquals("Invalid", ex.getMessage());
        }

        try {
            Validator.validateDouble(Double.NaN,"Invalid");
            fail("Expected exception not thrown");
        } catch (ShapeException ex) {
            assertEquals("Invalid", ex.getMessage());
        }
    }

    @Test
    public void testValidatePositiveDouble()  throws ShapeException  {
        Validator.validatePositiveDouble(456, "Test message");
        Validator.validatePositiveDouble(0.34523, "Test message");

        try {
            Validator.validatePositiveDouble(-123, "Negative not allowed");
            fail("Expected exception not thrown");
        } catch (ShapeException ex) {
            assertEquals("Negative not allowed", ex.getMessage());
        }

        try {
            Validator.validatePositiveDouble(-0.123, "Negative not allowed");
            fail("Expected exception not thrown");
        } catch (ShapeException ex) {
            assertEquals("Negative not allowed", ex.getMessage());
        }

        try {
            Validator.validatePositiveDouble(Double.POSITIVE_INFINITY,"Invalid");
            fail("Expected exception not thrown");
        } catch (ShapeException ex) {
            assertEquals("Invalid", ex.getMessage());
        }

        try {
            Validator.validatePositiveDouble(Double.NEGATIVE_INFINITY,"Invalid");
            fail("Expected exception not thrown");
        } catch (ShapeException ex) {
            assertEquals("Invalid", ex.getMessage());
        }

        try {
            Validator.validatePositiveDouble(Double.NaN,"Invalid");
            fail("Expected exception not thrown");
        } catch (ShapeException ex) {
            assertEquals("Invalid", ex.getMessage());
        }
    }

    @Test
    public void testValidatePoint() throws ShapeException {
        Validator.validateNotNull(new Point(0,-1), "Test message");

        try {
            Validator.validateNotNull(null,"Invalid");
            fail("Expected exception not thrown");
        } catch (ShapeException ex) {
            assertEquals("Invalid", ex.getMessage());
        }
    }

    @Test
    public void testValidateLineLength() throws ShapeException {
        Validator.validateLineLength(123, "Test message");

        try {
            Validator.validateLineLength(0,"Invalid");
            fail("Expected exception not thrown");
        } catch (ShapeException ex) {
            assertEquals("Invalid", ex.getMessage());
        }

        try {
            Validator.validateLineLength(-123,"Invalid");
            fail("Expected exception not thrown");
        } catch (ShapeException ex) {
            assertEquals("Invalid", ex.getMessage());
        }

        try {
            Validator.validateLineLength(Double.POSITIVE_INFINITY,"Invalid");
            fail("Expected exception not thrown");
        } catch (ShapeException ex) {
            assertEquals("Invalid", ex.getMessage());
        }

        try {
            Validator.validateLineLength(Double.NEGATIVE_INFINITY,"Invalid");
            fail("Expected exception not thrown");
        } catch (ShapeException ex) {
            assertEquals("Invalid", ex.getMessage());
        }

        try {
            Validator.validateLineLength(Double.NaN,"Invalid");
            fail("Expected exception not thrown");
        } catch (ShapeException ex) {
            assertEquals("Invalid", ex.getMessage());
        }
    }

    @Test
    public void validateTriangle() throws ShapeException {
        Line line1 = new Line(new Point(0, 1), new Point(3, 4));
        Line line2 = new Line(new Point(3, 4), new Point(7, 13));
        Line line3 = new Line(new Point(7, 13), new Point(0, 1));
        Validator.validateTriangle(line1, line2, line3, "Test Valid");

        line1 = new Line(new Point(0, 1), new Point(1, 2));
        line2 = new Line(new Point(1, 2), new Point(2, 3));
        line3 = new Line(new Point(2, 3), new Point(0, 1));
        try {
            Validator.validateTriangle(line1, line2, line3,"Invalid");
            fail("Expected exception not thrown");
        } catch (ShapeException ex) {
            assertEquals("Invalid", ex.getMessage());
        }
    }
}