package interaction.ui.console;


import interaction.requests.*;
import interaction.ui.InterfaceLeaf;

public class ConsolePrinter implements InterfaceLeaf {
    @Override
    public void handleRequest(InterfaceRequest interfaceRequest) {
        if(interfaceRequest.getType()== InterfaceRequestType.MESSAGE)
            System.out.print(interfaceRequest.getMessage());
    }
}