package collection.exceptions;



public class UniqueParamException extends CollectionException {
    public UniqueParamException(String paramName, String param) {
        super(String.format("Collection already has element with param %s(%s)", paramName, param));
    }
}
