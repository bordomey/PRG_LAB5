package controller.actions;


import java.util.ArrayDeque;
import java.util.Queue;

public class ConcreteActionManager implements ActionManager {
    private final Queue<Action> actionList;

    {
        actionList = new ArrayDeque<>();
    }

    @Override
    public int size() {
        return actionList.size();
    }

    @Override
    public void pushAction(Action action) {
        actionList.add(action);
    }

    @Override
    public void act() {
        if(size()!=0)
            actionList.peek().act();
    }

    @Override
    public void poll() {
        actionList.poll();
    }

    @Override
    public void clear() {
        actionList.clear();
    }
}
