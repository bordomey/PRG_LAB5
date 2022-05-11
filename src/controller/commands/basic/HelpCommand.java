package controller.commands.basic;


import controller.Controller;
import controller.commands.AbstractCommand;
import controller.commands.CommandParameters;
import interaction.requests.InterfaceRequest;
import interaction.requests.InterfaceRequestType;
import interaction.ui.InterfaceComponent;
import interaction.ui.console.ConsoleTable;

public class HelpCommand extends AbstractCommand {
    private final InterfaceComponent view;
    public HelpCommand(Controller controller, InterfaceComponent view) {
        super(controller, "help", "display all available commands");
        this.view = view;
    }

    @Override
    public void execute(CommandParameters params) {
        view.handleRequest(
                new InterfaceRequest(InterfaceRequestType.MESSAGE,
                        new ConsoleTable(commandManager.getInfoTable()).setTableName("List of available commands").toString())
        );
    }
}