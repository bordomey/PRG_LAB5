package collection.exceptions;


public class InvalidInputException extends RuntimeException {
    public InvalidInputException(String valueName, String value) {
        super(String.format("Can't set value \"%s\" for field \"%s\"", valueName, value));
    }
    public InvalidInputException(String valueName, String value, RuntimeException cause) {
        super(String.format("Can't set value \"%s\" for field \"%s\". Cause: %s", valueName, value, cause.getMessage()));
    }
}