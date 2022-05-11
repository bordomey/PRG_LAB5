package controller.actions;


public interface ActionManager {
    void pushAction(Action action);
    void act();
    int size();
    void poll();
    void clear();
}