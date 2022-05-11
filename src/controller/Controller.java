package controller;


import controller.actions.ActionManager;
import controller.commands.CommandManager;
import controller.input.InputManager;
import collection.CollectionItem;
import collection.CollectionManager;

public interface Controller {
    ActionManager getActionManager();
    CommandManager getCommandManager();
    InputManager getInputManager();

    String savePath();
    void setSavePath(String savePath);
    void exec();
    void close();
    void consoleMode();
    void fileMode(String absPath);

    void displayError(String errorMsg);
}
