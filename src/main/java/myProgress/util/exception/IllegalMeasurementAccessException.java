package myProgress.util.exception;

public class IllegalMeasurementAccessException extends RuntimeException {
    public IllegalMeasurementAccessException(String message) {
        super(message);
    }

    public IllegalMeasurementAccessException() {
        super("Access denied");
    }
}