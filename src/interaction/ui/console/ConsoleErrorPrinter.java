package interaction.ui.console;

import interaction.requests.InterfaceRequest;
import interaction.requests.InterfaceRequestType;
import interaction.ui.InterfaceLeaf;

public class ConsoleErrorPrinter implements InterfaceLeaf {
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_RESET = "\u001B[0m";

    @Override
    public void handleRequest(InterfaceRequest interfaceRequest) {
        if(interfaceRequest.getType()== InterfaceRequestType.ERROR) {
            System.err.println(ANSI_RED+interfaceRequest.getMessage()+ANSI_RESET);
        }
    }
}
