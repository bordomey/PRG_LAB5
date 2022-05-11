package controller.commands.basic;

import collection.CollectionManager;
import collection.element.Ticket;
import controller.Controller;
import controller.FileController;
import controller.commands.AbstractCommand;
import controller.commands.CommandParameters;
import controller.commands.exceptions.CommandException;

import java.util.LinkedList;
import java.util.List;

public class SaveCommand extends AbstractCommand {
    private final CollectionManager<?> collectionManager;
    public SaveCommand(Controller controller, CollectionManager<?> collectionManager) {
        super(controller, "save", "save collection to .json file");
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(CommandParameters params) throws CommandException {
        FileController fileController = new FileController(params.getParamAt(0));
        fileController.writeCollection((List<Ticket>) collectionManager.asList());
    }

}
