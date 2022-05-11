package controller.actions.basic;

import controller.actions.*;
import controller.input.*;

import java.awt.event.KeyEvent;
import java.io.IOException;

abstract public class InputAction implements Action {
    protected InputManager inputManager;
    protected InputRequest inputRequest;

    public InputAction(InputManager inputManager, InputRequest inputRequest) {
        this.inputManager = inputManager;
        this.inputRequest = inputRequest;
    }

    @Override
    final public void act() {
        inputManager.handleInputRequest(inputRequest);
        action();
    }

    abstract protected void action();
}
