package controller.commands.basic;

import controller.Controller;
import controller.commands.AbstractCommand;
import controller.commands.CommandParameters;
import interaction.ui.InterfaceComponent;

public class ExitCommand extends AbstractCommand {
    public ExitCommand(Controller controller, InterfaceComponent interfaceComponent) {
        super(controller, "exit", "exit program without saving");
    }

    @Override
    public void execute(CommandParameters params) {
        controller.close();
    }
}
