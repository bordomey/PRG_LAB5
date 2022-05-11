package controller.commands.exceptions;

public class WrongParamException extends CommandException {
    public WrongParamException(String param) {
        super(String.format("Typed wrong param \"%s\"", param));
    }
}
