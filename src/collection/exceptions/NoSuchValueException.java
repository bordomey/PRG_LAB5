package collection.exceptions;


public class NoSuchValueException extends CollectionException {
    public NoSuchValueException(String valueName) {
        super(String.format("No such value with name %s in element of collection", valueName));
    }
}
