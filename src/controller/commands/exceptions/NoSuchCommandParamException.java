package controller.commands.exceptions;


public class NoSuchCommandParamException extends CommandException {
    public NoSuchCommandParamException() {
        super("Can't find the required params for command");
    }
}
