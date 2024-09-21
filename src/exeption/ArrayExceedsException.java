package exeption;

public class ArrayExceedsException extends RuntimeException {
    public ArrayExceedsException() {
    }

    public ArrayExceedsException(String message) {
        super(message);
    }

    public ArrayExceedsException(String message, Throwable cause) {
        super(message, cause);
    }
}
