package controller.commands.basic;

import collection.CollectionManager;
import controller.Controller;
import controller.commands.AbstractCommand;
import controller.commands.CommandParameters;
import interaction.requests.InterfaceRequest;
import interaction.requests.InterfaceRequestType;
import interaction.ui.InterfaceComponent;
import interaction.ui.console.ConsoleTable;

public class InfoCommand extends AbstractCommand {
    private final InterfaceComponent view;
    private final CollectionManager<?> collectionManager;
    public InfoCommand(Controller controller, InterfaceComponent view, CollectionManager<?> collectionManager) {
        super(controller,"info", "displays info about collection");
        this.view = view;
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(CommandParameters params) {
        view.handleRequest(
                new InterfaceRequest(
                        InterfaceRequestType.MESSAGE,
                        (new ConsoleTable(collectionManager.getCollectionInfoTable()))
                                .setTableName("Info about collection").toString()));
    }
}