package exeption;

public class NoFreeSpaceException extends RuntimeException {
    public NoFreeSpaceException() {
    }

    public NoFreeSpaceException(String message) {
        super(message);
    }

    public NoFreeSpaceException(String message, Throwable cause) {
        super(message, cause);
    }
}
