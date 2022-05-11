
import collection.CollectionManager;
import collection.LinkedListCollectionManager;
import collection.element.Ticket;
import controller.ApplicationController;
import controller.Controller;
import controller.actions.basic.OpenFileAction;
import controller.commands.basic.RemoveLowerCommand;
import controller.commands.ticket.CountLessThanPersonCommand;
import controller.commands.ticket.MaxByPersonCommand;
import interaction.ui.ConcreteInterfaceComposite;
import interaction.ui.console.ConsoleErrorPrinter;
import interaction.ui.console.ConsolePrinter;

import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        CollectionManager<Ticket> ticketCollectionManager = new LinkedListCollectionManager<Ticket>() {
            @Override
            public Ticket generateNew() {
                return new Ticket(generateId());
            }
        };



        ConcreteInterfaceComposite console = new ConcreteInterfaceComposite();
        console.add(new ConsolePrinter());
        console.add(new ConsoleErrorPrinter());

        Controller controller = new ApplicationController<>(console, ticketCollectionManager);

        controller.getCommandManager().addCommand(new RemoveLowerCommand<>(controller, ticketCollectionManager));
        controller.getCommandManager().addCommand(new MaxByPersonCommand<Ticket>(controller, ticketCollectionManager, console));
        controller.getCommandManager().addCommand(new CountLessThanPersonCommand<Ticket>(controller, ticketCollectionManager, console));

        if(args.length>0)  {
            controller.getActionManager().pushAction(new OpenFileAction(String.join(" ", args), ticketCollectionManager));
            controller.setSavePath(String.join(" ", args));
        } else {
            controller.setSavePath("default.json");
        }
        controller.exec();

    }
}
