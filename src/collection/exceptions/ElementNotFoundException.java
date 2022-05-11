package collection.exceptions;

public class ElementNotFoundException extends CollectionException {
    public ElementNotFoundException() {
        super("No such element in collection");
    }
}