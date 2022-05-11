package controller.commands.exceptions;

public class OpenFileException extends CommandException {
    public OpenFileException(String path) {
        super(String.format("Can't open '%s'", path));
    }
    public OpenFileException(String path, String cause) {
        super(String.format("Can't open '%s'\n Cause: %s", path, cause));
    }
}