package exeption;

public class IsFullExceptionException extends RuntimeException {
    public IsFullExceptionException() {
    }

    public IsFullExceptionException(String message) {
        super(message);
    }

    public IsFullExceptionException(String message, Throwable cause) {
        super(message, cause);
    }
}
