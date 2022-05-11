package controller.commands;

import controller.commands.exceptions.CommandException;
public interface Command {
    String getName();
    String getDescription();
    void execute(CommandParameters params) throws CommandException;
}
