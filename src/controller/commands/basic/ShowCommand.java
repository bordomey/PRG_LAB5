package controller.commands.basic;

import collection.CollectionManager;
import controller.Controller;
import controller.commands.AbstractCommand;
import controller.commands.CommandParameters;
import interaction.requests.InterfaceRequest;
import interaction.requests.InterfaceRequestType;
import interaction.ui.InterfaceComponent;
import interaction.ui.console.ConsoleTable;

public class ShowCommand extends AbstractCommand {
    private final CollectionManager<?> collectionManager;
    private final InterfaceComponent view;
    public ShowCommand(Controller controller, InterfaceComponent view, CollectionManager<?> collectionManager) {
        super(controller, "show", "display all elements of the collection");
        this.collectionManager = collectionManager;
        this.view = view;
    }

    @Override
    public void execute(CommandParameters params) {
        view.handleRequest(
                new InterfaceRequest(InterfaceRequestType.MESSAGE,
                        new ConsoleTable(collectionManager.getCollectionContentTable())
                                .setTableName("Elements of the collection").toString())
        );
    }
}
