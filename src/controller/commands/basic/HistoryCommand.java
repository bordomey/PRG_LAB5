package controller.commands.basic;

import controller.Controller;
import controller.commands.AbstractCommand;
import controller.commands.CommandParameters;
import interaction.requests.InterfaceRequest;
import interaction.requests.InterfaceRequestType;
import interaction.ui.InterfaceComponent;
import interaction.ui.console.ConsoleTable;

import java.util.List;
import java.util.Queue;

public class HistoryCommand  extends AbstractCommand {
    private final InterfaceComponent view;
    private Queue<String> history;
    public HistoryCommand(Controller controller, InterfaceComponent view, Queue<String> history) {
        super(controller, "history", "display last 10 commands");
        this.view = view;
        this.history = history;
    }

    @Override
    public void execute(CommandParameters params) {
        view.handleRequest(
                new InterfaceRequest(InterfaceRequestType.MESSAGE,
                        history.toString())
        );
    }
}
