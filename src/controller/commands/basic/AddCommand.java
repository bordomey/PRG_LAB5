package controller.commands.basic;

import collection.*;
import controller.Controller;
import controller.actions.basic.CollectionItemUpdateAction;
import controller.commands.*;
import java.util.Optional;

public class AddCommand<T extends CollectionItem> extends AbstractCommand {
    private final CollectionManager<T> collectionManager;
    public AddCommand(Controller controller, CollectionManager<T> collectionManager) {
        super(controller, "add", "add new element to collection");
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(CommandParameters params) {
        T collectionItem = collectionManager.generateNew();
        CollectionItemUpdateAction.generateListOfActions(inputManager, collectionManager, collectionItem).forEach(actionManager::pushAction);
        actionManager.pushAction(() -> collectionManager.add(collectionItem));
    }
}
