package controller.actions.basic;

import controller.commands.*;
import controller.input.InputManager;
import controller.input.InputRequest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommandInputAction extends InputAction {
    private final CommandManager commandManager;

    public CommandInputAction(InputManager inputManager, CommandManager commandManager) {
        super(inputManager, new InputRequest("Enter command: "));
        this.commandManager = commandManager;
    }

    @Override
    protected void action() {

        String[] respond = inputRequest.getRespond().split(" ");
        List<String> params = new ArrayList<>(Arrays.asList(respond));
        params.remove(0);
        commandManager.executeCommand((respond.length==0) ? " " : respond[0], new CommandParameters(params));
    }
}
