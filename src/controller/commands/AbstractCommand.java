package controller.commands;

import controller.Controller;
import controller.actions.ActionManager;
import controller.input.InputManager;

abstract public class AbstractCommand implements Command {
    protected String name;
    protected String description;

    protected final Controller controller;
    protected final InputManager inputManager;
    protected final ActionManager actionManager;
    protected final CommandManager commandManager;

    public AbstractCommand(Controller controller, String name, String description) {
        this.name = name;
        this.description = description;
        this.controller = controller;
        inputManager = controller.getInputManager();
        actionManager = controller.getActionManager();
        commandManager = controller.getCommandManager();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }
}
