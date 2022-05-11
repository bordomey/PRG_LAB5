package controller.input;

public class InputManager {
    private InputStrategy strategy;

    public InputManager(InputStrategy strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(InputStrategy strategy) {
        this.strategy = strategy;
    }

    public void handleInputRequest(InputRequest inputAction){
        strategy.handleRequest(inputAction);
    }
}
