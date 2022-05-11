package controller.commands.basic;

import collection.CollectionManager;
import controller.Controller;
import controller.commands.AbstractCommand;
import controller.commands.CommandParameters;
import controller.commands.exceptions.CommandException;
import controller.commands.exceptions.WrongParamException;

public class RemoveByID extends AbstractCommand {
    public final CollectionManager<?> collectionManager;
    public RemoveByID(Controller controller, CollectionManager<?> collectionManager) {
        super(controller, "remove_by_id", "remove element with typed id");
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(CommandParameters params) throws CommandException {
        try {
            Long id = Long.valueOf(params.getParamAt(0));
            collectionManager.removeById(id);
        } catch (NumberFormatException e) {
            throw new WrongParamException("id");
        }
    }
}
