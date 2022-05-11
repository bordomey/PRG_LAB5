package controller.input;


import controller.input.exception.InputFlushedException;
import interaction.requests.InterfaceRequest;
import interaction.requests.InterfaceRequestType;
import interaction.ui.InterfaceComponent;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ConsoleInputStrategy implements InputStrategy {
    private final InterfaceComponent interfaceComponent;
    private  Scanner scanner;

    {
        scanner = new Scanner(System.in);
    }

    public ConsoleInputStrategy(InterfaceComponent interfaceComponent) {
        this.interfaceComponent = interfaceComponent;;
    }

    @Override
    public void handleRequest(InputRequest inputAction) throws InputFlushedException {
        interfaceComponent.handleRequest(new InterfaceRequest(InterfaceRequestType.MESSAGE, inputAction.getMessage()));
        try {
            inputAction.respond(scanner.nextLine().trim());
        } catch (NoSuchElementException e) {
            throw new InputFlushedException();
        }
    }
}
