package controller.actions.basic;


import collection.CollectionItem;
import collection.CollectionItemAdapter;
import collection.CollectionManager;
import collection.element.Ticket;
import controller.FileController;
import controller.actions.Action;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class OpenFileAction implements Action {
    private final String path;
    private final CollectionManager<? extends CollectionItem> collectionManager;

    public OpenFileAction(String path, CollectionManager<? extends CollectionItem> collectionManager) {
        this.path = path;
        this.collectionManager = collectionManager;
    }

    @Override
    public void act() {
        FileController fileController = new FileController(path);
        ArrayList<Ticket> newCollection = fileController.readCollection();
        collectionManager.parse(newCollection);
    }
}
