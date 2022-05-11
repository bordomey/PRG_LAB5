package controller.commands;

import controller.commands.exceptions.NoSuchCommandException;
import collection.StringTable;

import java.util.List;

public interface CommandManager {
    void addCommand(Command command);
    boolean removeCommand(String commandName);
    boolean hasCommand(String commandName);
    void executeCommand(String commandName, CommandParameters params) throws NoSuchCommandException;
    StringTable getInfoTable();
}