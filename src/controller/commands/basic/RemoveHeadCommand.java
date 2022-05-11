package controller.commands.basic;

import collection.AbstractCollectionManager;
import collection.CollectionItem;
import collection.LinkedListCollectionManager;
import collection.StringTable;
import collection.element.Ticket;
import controller.Controller;
import controller.commands.AbstractCommand;
import controller.commands.CommandParameters;
import controller.commands.exceptions.CommandException;
import interaction.requests.InterfaceRequest;
import interaction.requests.InterfaceRequestType;
import interaction.ui.InterfaceComponent;
import interaction.ui.console.ConsoleTable;

import java.util.ArrayList;

public class RemoveHeadCommand extends AbstractCommand{
    private final LinkedListCollectionManager<?> collectionManager;
    private final InterfaceComponent view;
    public RemoveHeadCommand(Controller controller, InterfaceComponent view, LinkedListCollectionManager<?> collectionManager) {
        super(controller, "remove_head", "removes head of linked list");
        this.collectionManager = collectionManager;
        this.view = view;
    }

    @Override
    public void execute(CommandParameters params) throws CommandException {
        ArrayList<CollectionItem> list = new ArrayList<>();
        if(collectionManager.getHead() != null) {
            list.add((CollectionItem) collectionManager.getHead());
            view.handleRequest(
                    new InterfaceRequest(InterfaceRequestType.MESSAGE,
                            new ConsoleTable(new AbstractCollectionManager.CollectionContentTable<CollectionItem>(list)).toString()));
            collectionManager.removeHead();
        }
        else
        {
            view.handleRequest(new InterfaceRequest(InterfaceRequestType.MESSAGE, "Collection is empty"));
        }
    }
}
