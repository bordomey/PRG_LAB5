package controller.actions.basic;

import collection.*;
import collection.exceptions.UniqueParamException;
import controller.actions.*;
import controller.input.*;

import java.util.ArrayList;
import java.util.List;

public class CollectionItemUpdateAction<T extends CollectionItem> extends InputAction {
    private final CollectionManager<T> collectionManager;
    private final T collectionItem;
    private final String paramName;

    public CollectionItemUpdateAction(InputManager inputManager, CollectionManager<T> collectionManager, T collectionItem, String paramName) {
        super(inputManager, new InputRequest("Enter "+paramName+"("+collectionItem.getFormatMap().get(paramName)+")"+": "));
        this.collectionManager = collectionManager;
        this.collectionItem = collectionItem;
        this.paramName = paramName;
    }

    public static <T extends CollectionItem> List<Action> generateListOfActions(InputManager inputManager, CollectionManager<T> collectionManager, T collectionItem) {
        return collectionItem.getSettersTopics().stream().collect(
                ArrayList::new,
                (list, item) -> list.add(new CollectionItemUpdateAction<>(inputManager, collectionManager, collectionItem, item)),
                ArrayList::addAll
        );
    }

    @Override
    protected void action() {
        String respond = inputRequest.getRespond();
        if(respond.equals("-")) return;
        if(collectionItem.getUniqueValueNameList().stream().anyMatch(paramName::equals)
                &&collectionManager.countValue(paramName, respond)> ((collectionItem.getValue(paramName).equals(respond)) ? 1 : 0)) throw new UniqueParamException(paramName, respond);
        collectionItem.setValue(paramName, inputRequest.getRespond());
    }
}
