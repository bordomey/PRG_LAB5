package collection.exceptions;


public class InvalidDataFormatException extends DataException {
    public InvalidDataFormatException() {
        super("Invalid data format");
    }
    public InvalidDataFormatException(String cause) {
        super(String.format("Invalid data format (%s)", cause));
    }
}
