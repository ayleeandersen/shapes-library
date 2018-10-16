package shapes;

public class Validator {
    public static void validateDouble(double value, String errorMessage) throws ShapeException {
        if (Double.isInfinite(value) || Double.isNaN(value))
            throw new ShapeException(errorMessage);
    }

    public static void validatePositiveDouble(double value, String errorMessage) throws ShapeException {
        validateDouble(value, errorMessage);
        if (value<0)
            throw new ShapeException(errorMessage);
    }

    public static void validateNotNull(Object obj, String errorMessage) throws ShapeException {
        if (obj == null)
            throw new ShapeException(errorMessage);
    }

    public static void validateLineLength(double length, String errorMessage) throws ShapeException {
        validateDouble(length, errorMessage);
        if (length < 0.00000001)
            throw new ShapeException(errorMessage);
    }

    public static void validateTriangle(Line line1, Line line2, Line line3, String errorMessage) throws ShapeException {
        if (line1.getSlope() == line2.getSlope() || line1.getSlope() == line3.getSlope() || line2.getSlope() == line3.getSlope()) {
            throw new ShapeException(errorMessage);
        }
    }
}
