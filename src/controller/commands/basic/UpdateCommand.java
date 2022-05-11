package controller.commands.basic;

import collection.CollectionItem;
import collection.CollectionManager;
import controller.Controller;
import controller.actions.basic.CollectionItemUpdateAction;
import controller.commands.AbstractCommand;
import controller.commands.CommandParameters;
import controller.commands.exceptions.WrongParamException;

public class UpdateCommand<T extends CollectionItem> extends AbstractCommand {
    private final CollectionManager<T> collectionManager;
    public UpdateCommand(Controller controller, CollectionManager<T> collectionManager) {
        super(controller, "update", "update command by typed id");
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(CommandParameters params) {
        try {
            T collectionItem = collectionManager.getById(Long.valueOf(params.getParamAt(0)));
            CollectionItemUpdateAction.generateListOfActions(inputManager, collectionManager, collectionItem).forEach(actionManager::pushAction);

        } catch (NumberFormatException e) {
            throw new WrongParamException("id");
        }
    }
}
