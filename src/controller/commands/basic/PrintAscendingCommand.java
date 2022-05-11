package controller.commands.basic;

import collection.CollectionManager;
import controller.Controller;
import controller.commands.AbstractCommand;
import controller.commands.CommandParameters;
import interaction.requests.InterfaceRequest;
import interaction.requests.InterfaceRequestType;
import interaction.ui.InterfaceComponent;
import interaction.ui.console.ConsoleTable;

public class PrintAscendingCommand extends AbstractCommand {
    private final CollectionManager<?> collectionManager;
    private final InterfaceComponent view;
    public PrintAscendingCommand(Controller controller, InterfaceComponent view, CollectionManager<?> collectionManager) {
        super(controller, "print_ascending", "display all elements of the collection in ascending order");
        this.collectionManager = collectionManager;
        this.view = view;
    }

    @Override
    public void execute(CommandParameters params) {
        collectionManager.sort();
        view.handleRequest(
                new InterfaceRequest(InterfaceRequestType.MESSAGE,
                        new ConsoleTable(collectionManager.getCollectionContentTable())
                                .setTableName("Elements of the collection").toString())
        );
    }
}
