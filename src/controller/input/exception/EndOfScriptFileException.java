package controller.input.exception;

public class EndOfScriptFileException extends InputException{
    public EndOfScriptFileException() {
        super("End of the file");
    }
}