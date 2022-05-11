package controller.commands.ticket;

import collection.AbstractCollectionManager;
import collection.CollectionItem;
import collection.CollectionManager;
import collection.element.Color;
import collection.element.Person;
import collection.element.Ticket;
import collection.exceptions.InvalidDataFormatException;
import collection.exceptions.InvalidInputException;
import controller.Controller;
import controller.actions.Action;
import controller.actions.basic.InputAction;
import controller.commands.AbstractCommand;
import controller.commands.CommandParameters;
import controller.commands.exceptions.CommandException;
import controller.input.InputRequest;
import interaction.requests.InterfaceRequest;
import interaction.requests.InterfaceRequestType;
import interaction.ui.InterfaceComponent;
import interaction.ui.console.ConsoleTable;

import java.util.ArrayList;

public class CountLessThanPersonCommand<T extends Ticket> extends AbstractCommand {
    private final CollectionManager<T> collectionManager;
    private final InterfaceComponent view;
    private CollectionItem collectionItem;

    public CountLessThanPersonCommand(Controller controller, CollectionManager<T> collectionManager, InterfaceComponent view) {
        super(controller, "count_less_than_person", "count elements with person less than given one");
        this.collectionManager = collectionManager;
        this.view = view;
    }

    @Override
    public void execute(CommandParameters params) throws CommandException {
        if (collectionManager.size() != 0) {
            Person person = new Person();
            InputRequest inputIDRequest = new  InputRequest("Enter passportID ("+ collectionManager.generateNew().getFormatMap().get("passportID")+"):");
            InputRequest inputHairColorRequest = new InputRequest("Enter Hair Color (" + collectionManager.generateNew().getFormatMap().get("hairColor")+"):");
            actionManager.pushAction(new InputAction(inputManager, inputIDRequest) {
                @Override
                protected void action() {
                    try {
                        person.setPassportID(inputIDRequest.getRespond());
                    }
                    catch (InvalidDataFormatException exception){
                        throw new InvalidInputException("passportID", inputIDRequest.getRespond(), exception);
                    }
                }
            });
            actionManager.pushAction(new InputAction(inputManager, inputHairColorRequest) {
                @Override
                protected void action() {
                    try {
                        person.setHairColor(Color.valueOf(inputHairColorRequest.getRespond()));
                    }
                    catch (IllegalArgumentException e){
                        throw new InvalidInputException(inputHairColorRequest.getRespond(), "Hair Color");
                    }
                }
            });
            T maxItem = collectionManager.generateNew();

            actionManager.pushAction(new Action() {
                @Override
                public void act() {
                    long ans = 0;
                    maxItem.setPerson(person);
                    for (
                            T t : collectionManager.asList()) {
                        if (maxItem.getPerson().compareTo(t.getPerson()) < 0) {
                            ans++;
                        }
                    }
                    view.handleRequest(
                            new InterfaceRequest(InterfaceRequestType.MESSAGE,
                                    (String) ("Amount of Tickets with person lower than recieved is "+ ans + "\n")));
                }
            });
        }
        else
        {
            new InterfaceRequest(InterfaceRequestType.ERROR, "collection is empty");
        }
    }
}
