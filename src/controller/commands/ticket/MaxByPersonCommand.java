package controller.commands.ticket;

import collection.AbstractCollectionManager;
import collection.CollectionItem;
import collection.CollectionManager;
import collection.element.Ticket;
import controller.Controller;
import controller.actions.Action;
import controller.actions.basic.CollectionItemUpdateAction;
import controller.commands.AbstractCommand;
import controller.commands.CommandParameters;
import controller.commands.exceptions.CommandException;
import controller.input.InputManager;
import interaction.requests.InterfaceRequest;
import interaction.requests.InterfaceRequestType;
import interaction.ui.InterfaceComponent;
import interaction.ui.console.ConsoleTable;

import java.util.ArrayList;

public class MaxByPersonCommand<T extends Ticket> extends AbstractCommand {
    private final CollectionManager<T> collectionManager;
    private final InterfaceComponent view;
    private CollectionItem collectionItem;

    public MaxByPersonCommand(Controller controller, CollectionManager<T> collectionManager, InterfaceComponent view) {
        super(controller, "max_by_person", "print element with max person");
        this.collectionManager = collectionManager;
        this.view = view;
    }

    @Override
    public void execute(CommandParameters params) throws CommandException {
        if (collectionManager.size() != 0) {
            ArrayList<CollectionItem> list = new ArrayList<>();
            T maxItem = collectionManager.asList().get(0);
                    for (
                            T t : collectionManager.asList()) {
                        if (maxItem.getPerson().compareTo(t.getPerson()) < 0) {
                            maxItem = t;
                        }
                    }
                    list.add(maxItem);
            view.handleRequest(
                    new InterfaceRequest(InterfaceRequestType.MESSAGE,
                            new ConsoleTable(new AbstractCollectionManager.CollectionContentTable<CollectionItem>(list)).toString()));
        }
        else
        {
            new InterfaceRequest(InterfaceRequestType.ERROR, "collection is empty");
        }
    }
}
