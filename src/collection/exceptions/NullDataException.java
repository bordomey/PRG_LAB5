package collection.exceptions;


public class NullDataException extends DataException {
    public NullDataException() {
        super("Field can't be null");
    }
}
