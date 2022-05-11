package controller.commands.basic;

import collection.CollectionManager;
import controller.Controller;
import controller.commands.AbstractCommand;
import controller.commands.CommandParameters;
import interaction.requests.InterfaceRequest;
import interaction.requests.InterfaceRequestType;
import interaction.ui.InterfaceComponent;

public class ClearCommand extends AbstractCommand {
    private final InterfaceComponent view;
    private final CollectionManager<?> collectionManager;
    public ClearCommand(Controller controller, InterfaceComponent view, CollectionManager<?> collectionManager) {
        super(controller, "clear", "clear the collection");
        this.view = view;
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(CommandParameters params) {
        collectionManager.clear();
        view.handleRequest(new InterfaceRequest(InterfaceRequestType.MESSAGE, "Collection was cleared\n"));
    }
}
