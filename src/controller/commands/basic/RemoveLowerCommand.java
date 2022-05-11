package controller.commands.basic;

import collection.CollectionManager;
import collection.*;
import collection.element.*;
import controller.Controller;
import controller.actions.Action;
import controller.actions.basic.CollectionItemUpdateAction;
import controller.commands.AbstractCommand;
import controller.commands.CommandParameters;
import controller.commands.exceptions.CommandException;

public class RemoveLowerCommand<T extends Ticket> extends AbstractCommand {
    private final CollectionManager<T> collectionManager;
    private CollectionItem collectionItem;

    public RemoveLowerCommand(Controller controller, CollectionManager<T> collectionManager) {
        super(controller, "remove_lower", "removes items lower than given one");
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(CommandParameters params) throws CommandException {
        T givenItem = collectionManager.generateNew();
        CollectionItemUpdateAction.generateListOfActions(inputManager, collectionManager, givenItem).forEach(actionManager::pushAction);
        Action kk = new Action() {
            @Override
            public void act() {
                for (
                        T t : collectionManager.asList()) {
                    if (givenItem.compareTo(t) > 0) {
                        collectionManager.removeById(t.getId());
                    }
                }
            }
        };
        actionManager.pushAction(kk);
        }
    }

