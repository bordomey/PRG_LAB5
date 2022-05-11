package controller.commands.basic;


import controller.Controller;
import controller.commands.AbstractCommand;
import controller.commands.CommandParameters;
import controller.commands.exceptions.CommandException;
import controller.commands.exceptions.ScriptOverloadException;
import controller.input.ScriptInputStrategy;
import interaction.ui.InterfaceComponent;

public class ExecuteScriptCommand extends AbstractCommand {
    public ExecuteScriptCommand(Controller controller, InterfaceComponent interfaceComponent) {
        super(controller, "execute_script", "execute script from absolute path");
    }

    @Override
    public void execute(CommandParameters params) throws CommandException {
        if(ScriptInputStrategy.stackSize()>20) throw new ScriptOverloadException();
        controller.fileMode(params.getFullRespond());
    }
}
