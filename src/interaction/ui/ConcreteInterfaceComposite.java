package interaction.ui;

import interaction.requests.InterfaceRequest;

import java.util.ArrayList;
import java.util.List;


public class ConcreteInterfaceComposite implements InterfaceComposite {
    private final List<InterfaceComponent> childList = new ArrayList<>();

    @Override
    public void handleRequest(InterfaceRequest interfaceRequest) {
        childList.forEach(child -> child.handleRequest(interfaceRequest));
    }

    @Override
    public void add(InterfaceComponent component) {
        childList.add(component);
    }
}
