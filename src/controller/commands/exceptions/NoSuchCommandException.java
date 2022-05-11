package controller.commands.exceptions;

public class NoSuchCommandException extends CommandException {
    public NoSuchCommandException(String commandName) {
        super(String.format("No such command with name %s", commandName));
    }
}